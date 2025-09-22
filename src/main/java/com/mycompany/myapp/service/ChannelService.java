package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Chanell;
import com.mycompany.myapp.repository.CategoryRepository;
import com.mycompany.myapp.repository.ChanellRepository;
import com.mycompany.myapp.repository.CityRepository;
import com.mycompany.myapp.service.dto.ChannelNameAndIDDTO;
import com.mycompany.myapp.service.dto.channel.ChannelInfoDTO;
import com.mycompany.myapp.service.tg.nikita.ChannelTestService;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class ChannelService {

    private final Logger log = LoggerFactory.getLogger(ChannelService.class);

    private final ChanellRepository chanellRepository;
    private final CategoryRepository categoryRepository;
    private final CityRepository cityRepository;

    private Specification<Chanell> buildSpec(
        String name,
        String link,
        ZonedDateTime startDateS,
        ZonedDateTime startDateE,
        ZonedDateTime endDateS,
        ZonedDateTime endDateE
    ) {
        return (root, query, cb) -> {
            List<Predicate> preds = new ArrayList<>();

            // name
            if (name != null && !name.trim().isEmpty()) {
                preds.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            // link
            if (link != null && !link.trim().isEmpty()) {
                preds.add(cb.like(cb.lower(root.get("link")), "%" + link.toLowerCase() + "%"));
            }

            /*
             * ВАЖНО: тут мы не знаем точно, какого типа поля в сущности:
             * - если в Chanell lastPayDate/endPublicDate — ZonedDateTime (или OffsetDateTime),
             *   используем их напрямую.
             * - если это LocalDateTime — нужно конвертировать входные ZonedDateTime -> LocalDateTime.
             *
             * Я покажу оба варианта: сначала предположим ZonedDateTime; если у тебя поля LocalDateTime —
             * см. комментированный кусок дальше.
             */

            // 1) Предполагаем, что поля сущности — ZonedDateTime/OffsetDateTime (Comparable)
            if (startDateS != null) {
                preds.add(cb.or(cb.isNull(root.get("lastPayDate")), cb.greaterThanOrEqualTo(root.get("lastPayDate"), startDateS)));
            }
            if (startDateE != null) {
                preds.add(cb.or(cb.isNull(root.get("lastPayDate")), cb.lessThanOrEqualTo(root.get("lastPayDate"), startDateE)));
            }
            if (endDateS != null) {
                preds.add(cb.or(cb.isNull(root.get("endPublicDate")), cb.greaterThanOrEqualTo(root.get("endPublicDate"), endDateS)));
            }
            if (endDateE != null) {
                preds.add(cb.or(cb.isNull(root.get("endPublicDate")), cb.lessThanOrEqualTo(root.get("endPublicDate"), endDateE)));
            }

            return cb.and(preds.toArray(new Predicate[0]));
        };
    }

    public ChannelService(ChanellRepository chanellRepository, CategoryRepository categoryRepository, CityRepository cityRepository) {
        this.chanellRepository = chanellRepository;
        this.categoryRepository = categoryRepository;
        this.cityRepository = cityRepository;
    }

    public List<ChannelNameAndIDDTO> getAllChanellsNamesAndIdDTO() {
        return chanellRepository.getAllChanellsNamesAndIdDTO();
    }

    public List<ChannelInfoDTO> getAllChanellsInfoDTO() {
        return chanellRepository.getAllChanellsInfoDTO();
    }

    public Page<ChannelInfoDTO> findChannelInfoDTOPages(
        String name,
        String link,
        ZonedDateTime startDateS,
        ZonedDateTime startDateE,
        ZonedDateTime endDateS,
        ZonedDateTime endDateE,
        Pageable pageable
    ) {
        log.info("Параметры запроса:");
        log.info("name: {}", name);
        log.info("link: {}", link);
        log.info("startDateS: {}", startDateS);
        log.info("startDateE: {}", startDateE);
        log.info("endDateS: {}", endDateS);
        log.info("endDateE: {}", endDateE);

        final String nameNorm = name != null ? name : "";
        final String linkNorm = link != null ? link : "";

        Specification<Chanell> spec = buildSpec(nameNorm, linkNorm, startDateS, startDateE, endDateS, endDateE);

        Page<Chanell> page = chanellRepository.findAll(spec, pageable);

        return page.map(
            c ->
                new ChannelInfoDTO(
                    c.getId(),
                    c.getName(),
                    c.getLink(),
                    c.getIsModerate(),
                    c.getContacts(),
                    c.getStartDate(),
                    c.getLastPayDate(),
                    c.getEndPublicDate(),
                    c.getComment(),
                    c.getPriceForPay(),
                    c.getIsPay()
                )
        );
    }
}
