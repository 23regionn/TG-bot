package com.mycompany.myapp.service.tg.nikita;

import com.mycompany.myapp.domain.Chanell;
import com.mycompany.myapp.repository.ChanellRepository;
import com.mycompany.myapp.service.ChannelService;
import com.mycompany.myapp.service.dto.channel.ChannelInfoDTO;
import com.mycompany.myapp.web.rest.ChanellResource;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class ChannelTestService {

    private final Logger log = LoggerFactory.getLogger(ChannelTestService.class);


    @Autowired
    private ChanellRepository chanellRepository;

    @Autowired
    private ChannelService channelService;

    public void testChannelQuery() {
        try {
            log.info("=== ПРОВЕРКА НАЛИЧИЯ ДАННЫХ В БАЗЕ ===");

            // 1. Проверим сколько всего записей
            long totalCount = chanellRepository.countAllChannels();
            log.info("Всего записей в таблице chanell: {}", totalCount);

            if (totalCount == 0) {
                log.error("Таблица chanell ПУСТАЯ! Нет данных для выборки.");
                return;
            }

            // 2. Посмотрим первые 5 записей
            List<Chanell> sampleChannels = chanellRepository.findAnyChannels();
            log.info("Примеры записей из базы:");
            for (Chanell channel : sampleChannels) {
                log.info("ID: {}, Name: '{}', Link: '{}', lastPayDate: {}, endPublicDate: {}",
                    channel.getId(), channel.getName(), channel.getLink(),
                    channel.getLastPayDate(), channel.getEndPublicDate());
            }

            // 3. Теперь попробуем разные варианты запросов
            testWithDifferentFilters();

        } catch (Exception e) {
            log.error("Ошибка при проверке данных: {}", e.getMessage(), e);
        }
    }

    private void testWithDifferentFilters() {
        log.info("=== ТЕСТИРУЕМ РАЗНЫЕ ВАРИАНТЫ ФИЛЬТРОВ ===");

        ZonedDateTime now = ZonedDateTime.now();

        // Вариант 1: Без фильтров по датам вообще
        testQuery("БЕЗ ФИЛЬТРОВ ДАТ", "", "", null, null, null, null);

        // Вариант 2: Только с фильтром по имени/ссылке
        testQuery("ТОЛЬКО ПО ИМЕНИ", "test", "", null, null, null, null);

        // Вариант 3: Широкий диапазон дат
        testQuery("ШИРОКИЙ ДИАПАЗОН", "", "",
            now.minusYears(10), now.plusYears(10),
            now.minusYears(10), now.plusYears(10));

        // Вариант 4: Только записи с null датами
        testQuery("ТОЛЬКО NULL ДАТЫ", "", "",
            now, now, // невыполнимое условие для lastPayDate
            now, now, // невыполнимое условие для endPublicDate
            true); // флаг для особой логики
    }

    private void testQuery(String testName, String name, String link,
                           ZonedDateTime startDateS, ZonedDateTime startDateE,
                           ZonedDateTime endDateS, ZonedDateTime endDateE) {
        testQuery(testName, name, link, startDateS, startDateE, endDateS, endDateE, false);
    }

    private void testQuery(String testName, String name, String link,
                           ZonedDateTime startDateS, ZonedDateTime startDateE,
                           ZonedDateTime endDateS, ZonedDateTime endDateE,
                           boolean onlyNullDates) {

        try {
            log.info("--- Тест: {} ---", testName);

            Page<ChannelInfoDTO> result;

            if (onlyNullDates) {
                // Используем упрощенный запрос только для null дат
                result = chanellRepository.findChannelsWithNullDates(
                    name, link, PageRequest.of(0, 10));
            } else {
                result = channelService.findChannelInfoDTOPages(
                    name, link, startDateS, startDateE, endDateS, endDateE,
                    PageRequest.of(0, 10));
            }

            log.info("Найдено записей: {}", result.getTotalElements());

            if (result.hasContent()) {
                for (ChannelInfoDTO channel : result.getContent()) {
                    log.info("  Канал: '{}' - '{}' (lastPay: {}, endPublic: {})",
                        channel.getName(), channel.getLink(),
                        channel.getLastPayDate(), channel.getEndPublicDate());
                }
            }

        } catch (Exception e) {
            log.error("Ошибка в тесте {}: {}", testName, e.getMessage());
        }
    }
}
