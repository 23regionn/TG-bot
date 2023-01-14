package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.TGUser;
import com.mycompany.myapp.repository.TGUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class TgUserRepositoryService {

    private final TGUserRepository tgUserRepository;

    public TgUserRepositoryService(TGUserRepository tgUserRepository) {
        this.tgUserRepository = tgUserRepository;
    }


    public Optional<TGUser> findByChatId(Long chatId) {

        Optional<TGUser> tgUser = Optional.ofNullable(tgUserRepository.findByChatId(chatId));

        return tgUser;
    }

    public Optional<Set<TGUser>> findAllByChatId(Long chatId) {

        Optional<Set<TGUser>> tgUsers = Optional.ofNullable(tgUserRepository.findAllByChatId(chatId));

        return tgUsers;
    }

    public Optional<Set<TGUser>> getByChatIdAndDeleteTrue(Long chatId) {

        Optional<Set<TGUser>> tgUsers = Optional.ofNullable(tgUserRepository.getByChatIdAndDeleteTrue(chatId));

        return tgUsers;
    }

    public Optional<TGUser> getOneChatIdAndDeleteFalse(Long chatId) {
        Optional<TGUser> tgUser = Optional.ofNullable(tgUserRepository.getOneChatIdAndDeleteFalse(chatId));
        return tgUser;
    }

    public TGUser saveTgUser(TGUser tgUser){
        return tgUserRepository.save(tgUser);
    }
}
