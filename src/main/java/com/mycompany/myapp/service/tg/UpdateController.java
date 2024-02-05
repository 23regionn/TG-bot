package com.mycompany.myapp.service.tg;

import static com.mycompany.myapp.service.tg.Constants.*;

import com.mycompany.myapp.domain.Chanell;
import com.mycompany.myapp.domain.MessegePannel;
import com.mycompany.myapp.domain.SearchTypeLog;
import com.mycompany.myapp.domain.TGUser;
import com.mycompany.myapp.service.dto.CategoryNameAndIdDTO;
import com.vdurmont.emoji.EmojiParser;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class UpdateController {

    private final Logger log = LoggerFactory.getLogger(TelegramBot.class);

    private TelegramBot telegramBot;

    public UpdateController() {}

    public void registerBot(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void processUpdate(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            String nameForLog = update.getMessage().getChat().getFirstName();
            log.info("Сообщение от пользователя " + nameForLog + ", сообщение: " + messageText);
            TGUser tgUser;
            Optional<TGUser> tgUserOptional = telegramBot.tgUserRepositoryService.getOneChatIdAndDeleteFalse(
                update.getMessage().getChatId()
            );
            tgUser =
                tgUserOptional.isPresent()
                    ? telegramBot.tgUserRepositoryService.getOneChatIdAndDeleteFalse(update.getMessage().getChatId()).get()
                    : null;
            if (messageText.equals("/start")) {
                telegramBot.registerUser(update.getMessage());
                telegramBot.startCommandReceived(
                    chatId,
                    update.getMessage().getChat().getFirstName() != null
                        ? update.getMessage().getChat().getFirstName()
                        : update.getMessage().getChat().getUserName() != null ? update.getMessage().getChat().getUserName() : ""
                );
            } else if (messageText.equals("/help")) {
                telegramBot.registerUser(update.getMessage());
                telegramBot.sendMessage(chatId, HELP_TEXT, nameForLog);
            } else if (messageText.equals("/start menu") || messageText.equals(MENU) || messageText.equals("/menu")) {
                telegramBot.registerUser(update.getMessage());
                telegramBot.checkFindChannelOrAddChannel(chatId, nameForLog);
            } else if (messageText.equals("/category") || messageText.equals("Категории") || messageText.equals("Каналы по категориям")) {
                telegramBot.registerUser(update.getMessage());
                telegramBot.findCategoryFirstPage(chatId, nameForLog, false, 0l);
            } else if (messageText.equals("Города") || messageText.equals("Каналы по городам") || messageText.equals("/cities")) {
                telegramBot.registerUser(update.getMessage());
                telegramBot.findCityNamesByFirstLetter(chatId, nameForLog);
            } else if (messageText.equals("/search") || messageText.equals("Ввести название категории")) {
                telegramBot.registerUser(update.getMessage());
                telegramBot.sendSearchCategoriesButton(chatId, nameForLog);
            } else if (messageText.equals("Связь с админом")) {
                telegramBot.sendAdminLink(chatId, nameForLog);
            } else if (messageText.equals("/channel")) {
                if (tgUserOptional.isPresent()) {
                    telegramBot.resetStepForUser(tgUser);
                }
                telegramBot.checkFindChannelOrAddChannel(chatId, nameForLog);
            } else if (messageText.equals(В_НАЧАЛО) || messageText.equals("Вернуться назад")) {
                if (tgUserOptional.isPresent()) {
                    telegramBot.resetStepForUser(tgUser);
                }
                telegramBot.vNachaloCommandReceived(chatId, update.getMessage().getChat().getFirstName());
            } else if (messageText.contains("Кликните на категорию")) {
                InlineKeyboardMarkup mark = update.getMessage().getReplyMarkup();
                mark
                    .getKeyboard()
                    .get(0)
                    .get(0)
                    .setCallbackData(update.getMessage().getReplyMarkup().getKeyboard().get(0).get(0).getCallbackData() + chatId);
                Integer mesId = update.getMessage().getMessageId();
                telegramBot.executeDeleteMessage(chatId, nameForLog, mesId);
                //                telegramBot.executeMessageWithKeybord(nameForLog, chatId, update.getMessage().getText(), mark);
                String input = update.getMessage().getText().replaceAll("[\\p{So}]", "").strip();
                String answer = "<u>" + input + ": </u>👇👇👇";
                telegramBot.executeMessageWithKeybord(nameForLog, chatId, answer, mark);
                System.out.println("При отправкке заходит сюда " + update.getMessage());
                telegramBot.searchTypeLogRepository.save(new SearchTypeLog(chatId, true));
            }
            // Рассылка пользователям
            else if (messageText.contains("/send") && telegramBot.getOwnerId() == chatId) {
                var textToSend = EmojiParser.parseToUnicode(messageText.substring(messageText.indexOf(" ")));
                telegramBot.sendMessage(chatId, textToSend, nameForLog);
            } else { // Ради дефолтового ответа
                switch (messageText) {
                    default:
                        //                        telegramBot.sendMessage(chatId, "Извините, команда не распознана", nameForLog);
                        telegramBot.checkFindChannelOrAddChannel(chatId, nameForLog);
                }
            }
        } else if (update.hasInlineQuery()) {
            InlineQuery inlineQuery = update.getInlineQuery();

            // Обработка запроса и формирование ответа
            String query = inlineQuery.getQuery();
            List<InlineQueryResult> results = new ArrayList<>();

            // Получение параметра offset из InlineQuery
            String offset = inlineQuery.getOffset();

            int page = 0;
            if (offset != null && !offset.isEmpty()) {
                try {
                    page = Integer.parseInt(offset);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            List<CategoryNameAndIdDTO> categories = telegramBot.categoryRepository
                .findCategoriesForSearchMethodsBot(ZonedDateTime.now().minusDays(1))
                //                                .findAllNames()
                .stream()
                .filter(cat -> !cat.getName().isEmpty())
                .filter(cat -> cat.getName().toLowerCase().contains(query.toLowerCase()))
                .sorted(Comparator.comparing(CategoryNameAndIdDTO::getIsFirst).reversed().thenComparing(CategoryNameAndIdDTO::getScore))
                .distinct()
                .skip(page * PAGE_SIZE) // Пропускаем элементы на предыдущих страницах
                .limit(PAGE_SIZE) // Ограничиваем количество элементов на текущей странице
                .collect(Collectors.toList());

            InputTextMessageContent messageContent = new InputTextMessageContent();
            messageContent.setMessageText("Кликните на категорию \uD83D\uDC47" + "\uD83D\uDC47" + "\uD83D\uDC47"); // Текст в двух местах

            for (int i = 0; i < categories.size(); i++) {
                String name = categories.get(i).getName();

                InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
                // создание списка со списками с кнопками в ответе на сообщение
                List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
                // создание списка с кнопками в ответе на сообщение
                List<InlineKeyboardButton> rowInLine = new ArrayList<>();
                var buttonResponse = new InlineKeyboardButton();

                buttonResponse.setText(name); // Содержимое ответа в кнопке
                buttonResponse.setCallbackData(CLICK_TEMA + ":" + categories.get(i).getId() + ":"); // Привязка кнопки к реагирование на Button в сообщении, типо когда ответ не текст а кол-бек
                rowInLine.add(buttonResponse);

                rowsInLine.add(rowInLine);
                markupInLine.setKeyboard(rowsInLine);

                results.add(new InlineQueryResultArticle(("" + i), name, messageContent, markupInLine, null, null, null, null, null, null));
            }

            // Настройка параметров ответа
            AnswerInlineQuery answer = new AnswerInlineQuery();
            answer.setInlineQueryId(inlineQuery.getId());
            answer.setResults(results);

            try {
                telegramBot.execute(answer); // Отправка ответа на inline-запрос
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            Long chatId = null;
            Integer messageId = null;
            String nameForLog = "";
            if (update != null && update.hasCallbackQuery()) {
                CallbackQuery callbackQuery = update.getCallbackQuery();
                if (callbackQuery != null && callbackQuery.getMessage() != null) {
                    Message message = callbackQuery.getMessage();
                    if (message.getMessageId() != null) {
                        messageId = message.getMessageId();
                    }
                    if (message.getChatId() != null) {
                        chatId = message.getChatId();
                    }
                    if (message.getChat() != null) {
                        if (message.getChat().getFirstName() != null) {
                            nameForLog = message.getChat().getFirstName();
                        }
                    }
                }
            }

            log.info("Сообщение от пользователя " + nameForLog + ", (нажата кнопка): " + callbackData);

            if (callbackData.equals(YES_BUTTON)) {
                String text = "You pressed YES button";
                telegramBot.executeEditText(chatId, nameForLog, text, messageId);
            } else if (callbackData.equals(NO_BUTTON)) {
                String text = "You pressed NO button";
                telegramBot.executeEditText(chatId, nameForLog, text, messageId);
            } else if (callbackData.contains(FIND_CHANNEL)) {
                telegramBot.findCategoryFirstPage(chatId, nameForLog, false, messageId); // ОДНО и ТОЖЕ С ТЕМ ЧТО ВЫШЕ
            } else if (callbackData.contains(FIND_CITIES)) {
                telegramBot.findCityNamesByFirstLetter(chatId, nameForLog); // ОДНО и ТОЖЕ С ТЕМ ЧТО ВЫШЕ
            } else if (callbackData.contains(FIND_TEMATICS_FIRST)) {
                telegramBot.findCategoryFirstPage(chatId, nameForLog, true, messageId); // ОДНО и ТОЖЕ С ТЕМ ЧТО ВЫШЕ
            } else if (callbackData.contains(All_PAGES_TEMATICS)) {
                telegramBot.findCategoryAllPages(chatId, nameForLog, messageId);
            } else if (callbackData.contains(All_PAGES_TEMATICS_FOR_CITY)) {
                Long cityId = Long.valueOf(callbackData.split(":")[1]);
                telegramBot.findCategoryAllPagesByCity(chatId, nameForLog, messageId, cityId);
            } else if (callbackData.equals(ADMIN_LINK)) {
                telegramBot.sendAdminLink(chatId, nameForLog);
            } else if (callbackData.contains(NEXT_PAGE_WITH_TEMATICS)) {
                Integer pageNumber = Integer.valueOf(callbackData.split(":")[1]);
                Integer numberInMap = Integer.valueOf(callbackData.split(":")[2]);
                telegramBot.findCategoryNextPage(chatId, nameForLog, pageNumber, messageId, numberInMap);
            } else if (callbackData.contains(NEXT_PAGE_WITH_TEMATICS_FOR_CITY)) {
                Integer pageNumber = Integer.valueOf(callbackData.split(":")[1]);
                Long cityId = Long.valueOf(callbackData.split(":")[2]);
                Integer numberInMap = Integer.valueOf(callbackData.split(":")[3]);
                telegramBot.getCategoriesByCityNameNextPage(chatId, nameForLog, cityId, pageNumber, messageId, numberInMap);
            } else if (callbackData.contains(CATEGORY)) {
                if (update.getCallbackQuery() != null) {
                    if (update.getCallbackQuery().getMessage() != null) {
                        telegramBot.registerUser(update.getCallbackQuery().getMessage());
                    }
                }
                Long categoryId = Long.valueOf(callbackData.replace(CATEGORY, ""));
                telegramBot.getChanellByCategoryId(chatId, nameForLog, categoryId);
            } else if (callbackData.contains(CLICK_TEMA)) {
                Long categoryId = Long.valueOf(callbackData.split(":")[1]);
                Long chatIdIn = Long.valueOf(callbackData.split(":")[2]);
                telegramBot.getChanellByCategoryId(chatIdIn, nameForLog, categoryId);
            } else if (callbackData.contains(ALL_LIST_CH)) {
                Long categoryId = Long.valueOf(callbackData.split(":")[1]);
                telegramBot.getChanellByCategoryIdBigButtons(chatId, nameForLog, categoryId);
            } else if (callbackData.contains(ALL_LIST_GOROD_CHANNEL)) {
                Long relCategoryCityId = Long.valueOf(callbackData.split(":")[1]);
                telegramBot.getChanellByCategoryIdByCityBigButtons(chatId, nameForLog, relCategoryCityId);
            } else if (callbackData.contains(NEXT_PAGE_CAT)) {
                Long pageNumber = Long.valueOf(callbackData.split(":")[1]);
                Long categoryId = Long.valueOf(callbackData.split(":")[2]);
                telegramBot.getChanellByCategoryIdAndPageNumber(chatId, nameForLog, categoryId, pageNumber, messageId);
            } else if (callbackData.contains(ANY_PAGE_IN_CAT)) {
                Long pagesCount = Long.valueOf(callbackData.split(":")[1]);
                Long categoryId = Long.valueOf(callbackData.split(":")[2]);
                telegramBot.getAllPagesButtonsByCategoryIdAndPageNumber(chatId, nameForLog, categoryId, pagesCount);
            }
            // Для городов
            else if (callbackData.contains(NEXT_PAGE_GOROD_CAT)) {
                Long pageNumber = Long.valueOf(callbackData.split(":")[1]);
                Long relCategoryCityId = Long.valueOf(callbackData.split(":")[2]);
                telegramBot.getChanellByCityNameByCategoryIdAndPageNumber(chatId, nameForLog, relCategoryCityId, pageNumber, messageId);
            }
            // Для городов _______
            else if (callbackData.contains(ANY_PAGE_GOROD_IN_CAT)) {
                Long pagesCount = Long.valueOf(callbackData.split(":")[1]);
                Long relCategoryCityId = Long.valueOf(callbackData.split(":")[2]);
                telegramBot.getAllPagesButtonsByCategoryIdAndPageNumberByCity(chatId, nameForLog, relCategoryCityId, pagesCount);
            } else if (callbackData.contains(GORODA_FIRST_LETTER)) {
                String firstLetterOfCity = String.valueOf(callbackData.split(":")[1]);
                telegramBot.chooseСity(chatId, nameForLog, firstLetterOfCity);
            } else if (callbackData.contains(CITY)) {
                Long cityId = Long.valueOf(callbackData.split(":")[1]);
                telegramBot.getCategoriesByCityNameFirstPage(chatId, nameForLog, cityId, false, messageId);
            } else if (callbackData.contains(UPDATE_GOROD_MESSAGE)) {
                Long cityId = Long.valueOf(callbackData.split(":")[1]);
                telegramBot.getCategoriesByCityNameFirstPage(chatId, nameForLog, cityId, true, messageId);
            } else if (callbackData.contains(TEMA_GOROD)) {
                if (update.getCallbackQuery() != null) {
                    if (update.getCallbackQuery().getMessage() != null) {
                        telegramBot.registerUser(update.getCallbackQuery().getMessage());
                    }
                }
                Long relCategoryCityId = Long.valueOf(callbackData.split(":")[1]);
                telegramBot.getChanellByCityNameByCategoryId(chatId, nameForLog, relCategoryCityId);
            } else if (callbackData.contains(CHANNEL)) { // ВРОДЕ НЕ ИСПОЛЬЗУЕТСЯ
                String text = "Вы нажали на channel " + callbackData.replace(CHANNEL, "");
                Long channelId = Long.valueOf(callbackData.replace(CHANNEL, ""));
                Optional<Chanell> chanell = telegramBot.chanellRepository.findById(channelId);
                if (chanell.isPresent()) {
                    System.out.println(chanell.get().getLink());
                }
            } else if (callbackData.contains("Кликните на категорию")) {
                //                currentChatId = chatId;
                // ничего не должно происходить
            } else if (callbackData.contains(CREATE_APPROVE_СH)) {
                Long idChannel = Long.valueOf(callbackData.split(":")[1]);
                telegramBot.approveCreateChannel(idChannel);
                Set<MessegePannel> messegePannels = telegramBot.getAllWhatWeWantDeleteByChannelID(idChannel, MODERATION);
                for (MessegePannel ms : messegePannels) {
                    telegramBot.executeDeleteMessage(ms.getIdAdmin(), nameForLog, ms.getIdMessage());
                    telegramBot.messegePannelRepository.delete(ms);
                }
            } else if (callbackData.contains(CREATE_DISABLE_СH)) {
                Long idChannel = Long.valueOf(callbackData.split(":")[1]);
                String whyFailureINT = null;
                switch (String.valueOf(callbackData.split(":")[2])) {
                    case "2":
                        whyFailureINT = (" не совпадение админа канала и вас ");
                        break;
                    case "3":
                        whyFailureINT = (" не корректное название ");
                        break;
                    case "4":
                        whyFailureINT = (" не правильная ссылка ");
                        break;
                    case "5":
                        whyFailureINT = (" не верно указана цена ");
                        break;
                    case "6":
                        whyFailureINT = (" канал относится к запретным ");
                        break;
                    default:
                        whyFailureINT = ("");
                        break;
                }
                telegramBot.disableCreateChannel(idChannel, whyFailureINT);
                Set<MessegePannel> messegePannels = telegramBot.getAllWhatWeWantDeleteByChannelID(idChannel, MODERATION);
                for (MessegePannel ms : messegePannels) {
                    telegramBot.executeDeleteMessage(ms.getIdAdmin(), nameForLog, ms.getIdMessage());
                    telegramBot.messegePannelRepository.delete(ms);
                }
            } else if (callbackData.contains(CREATE_BAN_CH)) {
                Long idChannel = Long.valueOf(callbackData.split(":")[1]);
                telegramBot.banCreateChannel(idChannel);
                Set<MessegePannel> messegePannels = telegramBot.getAllWhatWeWantDeleteByChannelID(idChannel, MODERATION);
                for (MessegePannel ms : messegePannels) {
                    telegramBot.executeDeleteMessage(ms.getIdAdmin(), nameForLog, ms.getIdMessage());
                    telegramBot.messegePannelRepository.delete(ms);
                }
            } else if (callbackData.contains(EDIT_APPROVE_СH)) {
                Long idEditChannel = Long.valueOf(callbackData.split(":")[1]);
                telegramBot.approveEditingChannel(idEditChannel);
                Set<MessegePannel> messegePannels = telegramBot.getAllWhatWeWantDeleteByChannelID(idEditChannel, EDITING_CHANNEL);
                for (MessegePannel ms : messegePannels) {
                    telegramBot.executeDeleteMessage(ms.getIdAdmin(), nameForLog, ms.getIdMessage());
                    telegramBot.messegePannelRepository.delete(ms);
                }
            } else if (callbackData.contains(EDIT_DISABLE_СH)) {
                Long idEditChannel = Long.valueOf(callbackData.split(":")[1]);
                String whyFailureINT = null;
                switch (String.valueOf(callbackData.split(":")[2])) {
                    case "2":
                        whyFailureINT = (" не совпадение админа канала и вас ");
                        break;
                    case "3":
                        whyFailureINT = (" не корректное название ");
                        break;
                    case "4":
                        whyFailureINT = (" не правильная ссылка ");
                        break;
                    case "5":
                        whyFailureINT = (" не верно указана цена ");
                        break;
                    case "6":
                        whyFailureINT = (" канал относится к запретным ");
                        break;
                    case "8":
                        whyFailureINT = (" не корректное описание ");
                        break;
                    default:
                        whyFailureINT = ("");
                        break;
                }
                telegramBot.disableEditChannel(idEditChannel, whyFailureINT);
                Set<MessegePannel> messegePannels = telegramBot.getAllWhatWeWantDeleteByChannelID(idEditChannel, EDITING_CHANNEL);
                for (MessegePannel ms : messegePannels) {
                    telegramBot.executeDeleteMessage(ms.getIdAdmin(), nameForLog, ms.getIdMessage());
                    telegramBot.messegePannelRepository.delete(ms);
                }
            } else if (callbackData.contains(EDIT_BAN_CH)) {
                Long idChannel = Long.valueOf(callbackData.split(":")[1]);
                telegramBot.banEditChannel(idChannel);
                Set<MessegePannel> messegePannels = telegramBot.getAllWhatWeWantDeleteByChannelID(idChannel, MODERATION);
                for (MessegePannel ms : messegePannels) {
                    telegramBot.executeDeleteMessage(ms.getIdAdmin(), nameForLog, ms.getIdMessage());
                }
            } else if (callbackData.equals(V_NACHALO)) {
                telegramBot.vNachaloCommandReceived(chatId, nameForLog);
            } else if (callbackData.contains(TEX_PODDERSHKA)) {
                Long idChannel = Long.valueOf(callbackData.split(":")[1]);
                // Заменить айди админа
                // Рассылка админам сообщения о необходимости связаться с участником процесса
                telegramBot.sendMessageToTehPoddershkaAfterOtkonitKanal(523559144l, idChannel);
                telegramBot.sendMessageToTehPoddershkaAfterOtkonitKanal(1376429566l, idChannel);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                telegramBot.vNachaloCommandReceived(chatId, nameForLog);
            } else if (callbackData.contains(TAKE_TO_WORK_AFTER_FALSE_CONTACTING_THE_CHAN)) { //
                Long idChannel = Long.valueOf(callbackData.split(":")[1]);
                Set<MessegePannel> messegePannels = telegramBot.getAllWhatWeWantDeleteByChannelID(idChannel, SVYAZ_S_ADMINAMI);
                for (MessegePannel ms : messegePannels) {
                    if (ms.getIdAdmin().equals(chatId)) { // либо поставить отрицание и воткнуть строку удаления сообщения
                        continue;
                    }
                    telegramBot.executeDeleteMessage(ms.getIdAdmin(), nameForLog, ms.getIdMessage());
                }
                telegramBot.sendMessage(chatId, "При завершение общения с пользователем, нажмите - Отклонить/Завершить", nameForLog);
                Chanell chanell = telegramBot.chanellRepository.findById(idChannel).get();
                chanell.setApprovedAdmin(chatId);
                telegramBot.chanellRepository.save(chanell);
            } else if (callbackData.contains(REJECT_FINISH_CONTACTING_FOR_ME_AFTER_CHAN_CLICK)) { // При нажатии на Отклонить
                Long idChannel = Long.valueOf(callbackData.split(":")[1]);
                Set<MessegePannel> messegePannels = telegramBot.getAllWhatWeWantDeleteByChannelID(idChannel, SVYAZ_S_ADMINAMI);
                for (MessegePannel ms : messegePannels) {
                    if (ms.getIdAdmin().equals(chatId)) { // либо поставить отрицание и воткнуть строку удаления сообщения
                        telegramBot.executeDeleteMessage(ms.getIdAdmin(), nameForLog, ms.getIdMessage());
                    }
                }
            } else if (callbackData.contains(SEND_ADMIN_CHAT_TO_USER)) { //
                Long idChannel = Long.valueOf(callbackData.split(":")[1]);
                String linkToAdmin = String.valueOf(callbackData.split(":")[2]);
                Chanell chanell = telegramBot.chanellRepository.findById(idChannel).get();
                telegramBot.sendMessage(
                    chanell.gettGUser().getChatId(),
                    "Не смогли найти ссылку на чат с Вами \n" + " свяжитесь с админом самостоятельно - " + "@" + linkToAdmin,
                    nameForLog
                );
                Set<MessegePannel> messegePannels = telegramBot.getAllWhatWeWantDeleteByChannelID(idChannel, SVYAZ_S_ADMINAMI);
                for (MessegePannel ms : messegePannels) {
                    telegramBot.executeDeleteMessage(ms.getIdAdmin(), nameForLog, ms.getIdMessage());
                }
            }
            //////////////// Связь с админом - закомментированная
            else if (callbackData.contains(TAKE_TO_WORK_AFTER_FALSE_CONTACTING_FROM_MENU)) { //
                Long userChatId = Long.valueOf(callbackData.split(":")[1]);
                Set<MessegePannel> messegePannels = telegramBot.getAllWhatWeWantDeleteByChatId(userChatId.toString(), SVYAZ_S_ADMINAMI);
                for (MessegePannel ms : messegePannels) {
                    if (ms.getIdAdmin().equals(chatId)) { // либо поставить отрицание и воткнуть строку удаления сообщения
                        continue;
                    }
                    telegramBot.executeDeleteMessage(ms.getIdAdmin(), nameForLog, ms.getIdMessage());
                }
                telegramBot.sendMessage(chatId, "При завершение общения с пользователем, нажмите - Отклонить/Завершить", nameForLog);
            } else if (callbackData.contains(REJECT_FINISH_CONTACTING_FOR_ME_AFTER_FROM_MENU)) { // При нажатии на Отклонить
                Long userChatID = Long.valueOf(callbackData.split(":")[1]);
                Set<MessegePannel> messegePannels = telegramBot.getAllWhatWeWantDeleteByChatId(userChatID.toString(), SVYAZ_S_ADMINAMI);
                for (MessegePannel ms : messegePannels) {
                    /*if(ms.getIdAdmin().equals(chatId)){ // либо поставить отрицание и воткнуть строку удаления сообщения
                        executeDeleteMessage(ms.getIdAdmin(), nameForLog, ms.getIdMessage());
                    }*/
                    telegramBot.executeDeleteMessage(ms.getIdAdmin(), nameForLog, ms.getIdMessage());
                }
            } else if (callbackData.contains(SEND_ADMIN_LINK_TO_USER_FROM_MENU)) { //
                Long userChatID = Long.valueOf(callbackData.split(":")[1]);
                String linkToAdmin = String.valueOf(callbackData.split(":")[2]);
                telegramBot.sendMessage(
                    userChatID,
                    "Не смогли найти ссылку на чат с Вами \n" + " свяжитесь с админом самостоятельно - " + "@" + linkToAdmin,
                    nameForLog
                );
                Set<MessegePannel> messegePannels = telegramBot.getAllWhatWeWantDeleteByChatId(userChatID.toString(), SVYAZ_S_ADMINAMI);
                for (MessegePannel ms : messegePannels) {
                    telegramBot.executeDeleteMessage(ms.getIdAdmin(), nameForLog, ms.getIdMessage());
                }
            } else if (callbackData.contains(MY_CHNS)) {
                Long channelID = Long.valueOf(callbackData.split(":")[1]);
                String channelName = telegramBot.chanellRepository.findById(channelID).get().getName();
                String answer = "Выберите действие в меню : " + channelName;
                TGUser tgUser = telegramBot.tgUserRepositoryService
                    .getOneChatIdAndDeleteFalse(update.getCallbackQuery().getMessage().getChatId())
                    .get();
                tgUser.setCurrentStep(WORK_WITH_MY_CHANNEL);
                tgUser.setIdCurrentChannelAction(channelID);
                telegramBot.tgUserRepositoryService.saveTgUser(tgUser);
                telegramBot.sendMessageWithKeyBoardWithAllMyChannels(chatId, answer, nameForLog, channelID);
            }
        }
    }
}
