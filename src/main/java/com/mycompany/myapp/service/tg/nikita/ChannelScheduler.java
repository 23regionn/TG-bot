package com.mycompany.myapp.service.tg.nikita;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ChannelScheduler {

    private final Logger log = LoggerFactory.getLogger(ChannelScheduler.class);

    @Autowired
    private ChannelTestService channelTestService;

    // Запускать каждые 5 минут для теста
//    @Scheduled(fixedRate = 60000)
//    public void scheduledChannelCheck() {
//        log.info("Запуск scheduledChannelCheck...");
//        channelTestService.testChannelQuery();
//    }
//
//    // Запускать при старте приложения
//    @EventListener(ApplicationReadyEvent.class)
//    public void onStartup() {
//        log.info("Запуск теста при старте приложения...");
//        channelTestService.testChannelQuery();
//    }
}
