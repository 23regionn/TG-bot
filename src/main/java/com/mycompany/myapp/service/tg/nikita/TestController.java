package com.mycompany.myapp.service.tg.nikita;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private ChannelTestService channelTestService;

//    @GetMapping("/test")
//    public ResponseEntity<String> testChannelsQuery() {
//        try {
//            channelTestService.testChannelQuery();
//            return ResponseEntity.ok("Тестовый запрос выполнен успешно");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body("Ошибка: " + e.getMessage());
//        }
//    }
}
