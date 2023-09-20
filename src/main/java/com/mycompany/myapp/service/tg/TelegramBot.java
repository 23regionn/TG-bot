package com.mycompany.myapp.service.tg;

import com.mycompany.myapp.config.tg.BotConfig;
import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.repository.*;
import com.mycompany.myapp.service.TgUserRepositoryService;
import com.mycompany.myapp.service.dto.CategoryNameAndIdDTO;
import com.mycompany.myapp.service.dto.CategoryWithCountChanellsDTO;
import com.vdurmont.emoji.EmojiParser;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

//@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final Logger log = LoggerFactory.getLogger(TelegramBot.class);

    @Autowired
    private TGUserRepository tgUserRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private TgUserRepositoryService tgUserRepositoryService;

    @Autowired
    private MessegePannelRepository messegePannelRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EditChannelsRepository editChannelsRepository;

    @Autowired
    private ChanellRepository chanellRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ChanellLogRepository chanellLogRepository;

    @Autowired
    private CategoryLogRepository categoryLogRepository;

    final BotConfig config;

    /*static final String HELP_TEXT = "This bot is created to demonstrate Spring capabilities.\n\n" +
            "You can execute commands from the main menu on the left or by typing a command:\n\n" +
            "Type /start to see a welcome message\n\n" +
            "Type /mydata to see data stored about yourself\n\n" +
            "Type /help to see this message again";*/
    static final String HELP_TEXT =
        "Этот бот предоставляет ссылки \n" +
        "на телеграмм каналы по выбранным категориям. \n\n" +
        "После команды /start кликните на одну из кнопок \n\n" +
        " \"Найти каналы по категориям\", \"Найти каналы по городам\".\n\n" +
        " \"Текстовый поиск категорий\".\n\n" +
        "И далее переходите по ссылкам на каналы.";

    static final String YES_BUTTON = "YES_BUTTON";
    static final String NO_BUTTON = "NO_BUTTON";
    static final String FIND_CHANNEL = "FIND_CHANNEL";
    static final String FIND_TEMATICS_FIRST = "FIND_TEMATICS_FIRST";
    static final String FIND_CITIES = "FIND_CITIES";
    static final String ADMIN_LINK = "ADMIN_LINK";
    static final String ADD_CHANNEL = "ADD_CHANNEL";
    static final String CATEGORY = "CATEGORY";
    static final String CLICK_TEMA = "CLICK_TEMA";
    static final String CITY = "CITY";
    static final String UPDATE_GOROD_MESSAGE = "UPDATE_GO_MES";
    static final String TEMA_GOROD = "TEMA_GOROD";
    static final String PRICEDIAP = "PRICEDIAP";
    static final String IDCAT = "IDCAT";

    static final String CHANNEL = "CHANNEL";
    static final String MY_CHNS = "MY_CHNS";

    static final String ERROR_TEXT = "Error occurred: ";
    static final String FIND_CAT_FOR_ADD_CHAN = "FIND_CAT_FOR_ADD_CHAN";
    static final String ADD_СH_NAME = "ADD_СH_NAME";
    static final String EDIT_СH_NAME = "EDIT_СH_NAME";
    static final String ADD_СH_LINK = "ADD_СH_LINK";
    static final String EDIT_СH_LINK = "EDIT_СH_LINK";
    static final String ADD_СH_PRICE = "ADD_СH_PRICE";
    static final String EDIT_СH_PRICE = "EDIT_СH_PRICE";
    static final String ADD_EDIT_DESCRIPTION = "ADD_EDIT_DESCRIPTION";

    static final String NEXT_PAGE_CAT = "NEXT_PAGE_CAT";
    static final String ANY_PAGE_IN_CAT = "ANY_PAGE_IN_CAT";
    static final String NEXT_PAGE_GOROD_CAT = "NEXT_PAGE_G_C";
    static final String ANY_PAGE_GOROD_IN_CAT = "ANY_PAGE_G_IN_C";
    static final String GORODA_FIRST_LETTER = "GORODA_F_L";
    static final String ALL_LIST_CH = "ALL_LIST_CH";
    static final String ALL_LIST_GOROD_CHANNEL = "ALL_L_G_CH";

    static final String NEXT_PAGE_WITH_TEMATICS = "NEXT_PAGE_W_T";
    static final String NEXT_PAGE_WITH_TEMATICS_FOR_CITY = "NEXT_P_W_T_F_C";
    static final String All_PAGES_TEMATICS = "All_PAGES_TEMATICS";
    static final String All_PAGES_TEMATICS_FOR_CITY = "All_P_T_F_C";
    static final String WORK_WITH_MY_CHANNEL = "WORK_WITH_MY_CHANNEL";
    static final String CREATE_APPROVE_СH = "CREATE_APPROVE_СH";
    static final String CREATE_DISABLE_СH = "CREATE_DISABLE_СH";
    static final String CREATE_BAN_CH = "CREATE_BAN_CH";

    static final String EDIT_APPROVE_СH = "EDIT_APPROVE_СH";
    static final String EDIT_DISABLE_СH = "EDIT_DISABLE_СH";
    static final String EDIT_BAN_CH = "EDIT_BAN_CH";
    private static final String MODERATION = "Модерация канала";
    private static final String EDITING_CHANNEL = "Редактирование канала";
    private static final String SVYAZ_S_ADMINAMI = "SVYAZ_S_ADMINAMI";
    private static final String SVYAZ_S_ADMINAMI_FROM_TEX_PODD_MENU = "SVYAZ_S_ADMINAMI_FROM_TEX_PODD_MENU";
    private static final String TAKE_TO_WORK_AFTER_FALSE_CONTACTING_THE_CHAN = "TAKE_TO_WORK_AFTER_FALSE_CONTACTING_THE_CHAN";
    private static final String REJECT_FINISH_CONTACTING_FOR_ME_AFTER_CHAN_CLICK = "REJECT_FINISH_CONTACTING_FOR_ME_AFTER_CHAN_CLICK";

    private static final String TAKE_TO_WORK_AFTER_FALSE_CONTACTING_FROM_MENU = "TAKE_TO_WORK_AFTER_FALSE_CONTACTING_FROM_MENU";
    private static final String REJECT_FINISH_CONTACTING_FOR_ME_AFTER_FROM_MENU = "REJECT_FINISH_CONTACTING_FOR_ME_AFTER_FROM_MENU";
    private static final String SEND_ADMIN_CHAT_TO_USER = "SEND_ADMIN_CHAT_TO_USER";
    private static final String SEND_ADMIN_LINK_TO_USER_FROM_MENU = "SEND_ADMIN_LINK_TO_USER_FROM_MENU";

    private static final String В_НАЧАЛО = "В начало";
    private static final String V_NACHALO = "V_NACHALO";
    private static final String TEX_PODDERSHKA = "Связаться с тех поддержкой";
    private static final String МОИ_КАНАЛЫ = "Мои каналы";

    public TelegramBot(BotConfig config) {
        this.config = config;
        List<BotCommand> listofCommands = new ArrayList<>();
        listofCommands.add(new BotCommand("/start", "Начало работы с ботом"));
        //        listofCommands.add(new BotCommand("/mydata", "get your data stored"));
        //        listofCommands.add(new BotCommand("/deletedata", "delete my data"));
        listofCommands.add(new BotCommand("/help", "Информация о боте"));
        //        listofCommands.add(new BotCommand("/settings", "set your preferences"));
        listofCommands.add(new BotCommand("/search", "Текстовый поиск категории"));
        listofCommands.add(new BotCommand("/category", "Выберите каналы по категориям"));
        listofCommands.add(new BotCommand("/cities", "Выберите каналы по городам"));
        try {
            this.execute(new SetMyCommands(listofCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error("Error setting bot's command list: " + e.getMessage());
        }
    }

    /*Long currentChatId = null; // DELETE
    Integer currentMessageId = null;*/

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            String nameForLog = update.getMessage().getChat().getFirstName();
            log.info("Сообщение от пользователя " + nameForLog + ", сообщение: " + messageText);
            TGUser tgUser;
            Optional<TGUser> tgUserOptional = tgUserRepositoryService.getOneChatIdAndDeleteFalse(update.getMessage().getChatId());
            tgUser =
                tgUserOptional.isPresent()
                    ? tgUserRepositoryService.getOneChatIdAndDeleteFalse(update.getMessage().getChatId()).get()
                    : null;
            if (messageText.equals("/start")) {
                registerUser(update.getMessage());
                /*if (tgUserOptional.isPresent()){
                    resetStepForUser(tgUser);
                }*/
                startCommandReceived(
                    chatId,
                    update.getMessage().getChat().getFirstName() != null ? update.getMessage().getChat().getFirstName() : ""
                );
            } else if (messageText.equals("/help")) {
                registerUser(update.getMessage());
                /*if (tgUserOptional.isPresent()){
                    resetStepForUser(tgUser);
                }*/
                sendMessage(chatId, HELP_TEXT, nameForLog);
            } /*else if(messageText.equals("/register")){
                if (tgUserOptional.isPresent()){
                    resetStepForUser(tgUser);
                }
                register(chatId, nameForLog);
            }
            else if(messageText.equals("/oleg")){
                if (tgUserOptional.isPresent()){
                    resetStepForUser(tgUser);
                }
                sendMessage(chatId, "Oleg", nameForLog);
            }*/else if (messageText.equals("/category") || messageText.equals("Категории") || messageText.equals("Каналы по категориям")) {
                /*if (tgUserOptional.isPresent()){
                    resetStepForUser(tgUser);
                }*/
                registerUser(update.getMessage());
                findCategoryFirstPage(chatId, nameForLog, false, 0l);
            } else if (messageText.equals("Города") || messageText.equals("Каналы по городам") || messageText.equals("/cities")) {
                /*if (tgUserOptional.isPresent()){
                    resetStepForUser(tgUser);
                }*/
                registerUser(update.getMessage());
                findCityNamesByFirstLetter(chatId, nameForLog);
            } else if (messageText.equals("/search") || messageText.equals("Текстовый поиск категорий")) {
                registerUser(update.getMessage());
                sendSearchCategoriesButton(chatId, nameForLog);
            } /*else if(messageText.equals(МОИ_КАНАЛЫ)){
                if (tgUserOptional.isPresent()){
                    resetStepForUser(tgUser);
                }
                getMyChannels(chatId, nameForLog);
            }*/else if (messageText.equals("Связь с админом")) {
                sendAdminLink(chatId, nameForLog);
            } // Вроде не работает
            /*else if(messageText.equals("Текстовый поиск категорий 🌍🌍🌍")){

                    // ЛОГИКа в случае нажатия на текст поиск из общего меню с клавиатурой
                sendAdminLink(chatId, nameForLog);
            }*/
            else if (messageText.equals("/channel")) {
                if (tgUserOptional.isPresent()) {
                    resetStepForUser(tgUser);
                }
                checkFindChannelOrAddChannel(chatId, nameForLog);
            } else if (messageText.equals(В_НАЧАЛО) || messageText.equals("Вернуться назад")) {
                if (tgUserOptional.isPresent()) {
                    resetStepForUser(tgUser);
                }
                vNachaloCommandReceived(chatId, update.getMessage().getChat().getFirstName());
            } else if (messageText.contains("Кликните на категорию ниже")) {
                InlineKeyboardMarkup mark = update.getMessage().getReplyMarkup();
                mark
                    .getKeyboard()
                    .get(0)
                    .get(0)
                    .setCallbackData(update.getMessage().getReplyMarkup().getKeyboard().get(0).get(0).getCallbackData() + chatId);
                Integer mesId = update.getMessage().getMessageId();
                //                executeEditTextWithKeyBoardAndDisableWebPreview(chatId, nameForLog, update.getMessage().getText(),
                //                    mesId, mark);
                executeDeleteMessage(chatId, nameForLog, mesId);
                executeMessageWithKeybord(nameForLog, chatId, update.getMessage().getText(), mark);
                System.out.println("При отправкке заходит сюда " + update.getMessage());
            }
            // Рассылка пользователям
            else if (messageText.contains("/send") && config.getOwnerId() == chatId) {
                var textToSend = EmojiParser.parseToUnicode(messageText.substring(messageText.indexOf(" ")));
                /*var users = userRepository.findAll(); ЭТОТ ЗАПРОС НЕ ПОДХОДИТ- НУЖНА РАССЫЛКА ВООБЩЕ ВСЕМ ПОЛЬЗОВАТЕЛЯМ.
                for (User user: users){
                    sendMessage(user.getChatId(), textToSend);
                }*/
                //                sendMessage(chatId, textToSend, nameForLog);
                sendMessage(chatId, textToSend, nameForLog);
            } /*  else if (tgUserOptional.isPresent() ){  // Функционал раньше использовался для добавления каналов внутри бота
                if(tgUser.getCurrentStep() != null){
                    if(tgUser.getIdCurrentChannelAction() != null){
                        if(tgUser.getCurrentStep().equals(ADD_СH_NAME)){
                            Chanell chanell = chanellRepository.findById(tgUser.getIdCurrentChannelAction()).get();
                            chanell.setName(messageText);
                            chanellRepository.save(chanell);
                            tgUser.setCurrentStep(ADD_СH_LINK);
                            tgUserRepositoryService.saveTgUser(tgUser);
                            log.info("В канал с id " + chanell.getId() + ", добавленно название: " +  messageText + ", " + nameForLog);
                            sendMessage(chatId, "Добавить ссылку на канал (скопируйте и вставьте) ⬇⬇⬇", nameForLog);
                        }
                        else if(tgUser.getCurrentStep().equals(ADD_СH_LINK)) {
                            Chanell chanell = chanellRepository.findById(tgUser.getIdCurrentChannelAction()).get();
                            chanell.setLink(messageText);
                            chanellRepository.save(chanell);
                            tgUser.setCurrentStep(ADD_СH_PRICE);
                            tgUserRepositoryService.saveTgUser(tgUser);
                            log.info("В канал с id " + chanell.getId() + ", добавленна ссылка : " +  messageText + ", " + nameForLog);
                            sendMessage(chatId, "Добавить прайс (сумма размещения рекламы) ⬇⬇⬇", nameForLog);
                        }
                        else if(tgUser.getCurrentStep().equals(ADD_СH_PRICE)) {
                            Chanell chanell = chanellRepository.findById(tgUser.getIdCurrentChannelAction()).get();
                            chanell.setPriceDiapozon(Double.valueOf(messageText));
                            chanellRepository.save(chanell);
                            tgUser.setCurrentStep("");
                            tgUser.setIdCurrentChannelAction(null);
                            tgUserRepositoryService.saveTgUser(tgUser);
                            log.info("В канал с id " + chanell.getId() + ", добавлен прайс для рекламы : " +  messageText + ", " + nameForLog);
                            sendMessage(chatId, "Канал добавлен и проходит модерацию ", nameForLog);


                           // Заменить айди админа
                            sendChannelToModerate(523559144l, chanell.getId());
                            sendChannelToModerate(1376429566l, chanell.getId());
                        }
                        else if(tgUser.getCurrentStep().equals(WORK_WITH_MY_CHANNEL)){ // Менюшка
                            if(messageText.equals("Изменить название")){
                                tgUser.setCurrentStep(EDIT_СH_NAME);
                                tgUserRepositoryService.saveTgUser(tgUser);
                                sendMessage(chatId, "Введите новое значение для канала ⬇⬇⬇", nameForLog);
                            }
                            else if(messageText.equals("Изменить ценовой диапазон")){
                                tgUser.setCurrentStep(EDIT_СH_PRICE);
                                tgUserRepositoryService.saveTgUser(tgUser);
                                sendMessage(chatId, "Введите новое значение для цены ⬇⬇⬇", nameForLog);
                            }
                            else if(messageText.equals("Изменить ссылку")){
                                tgUser.setCurrentStep(EDIT_СH_LINK);
                                tgUserRepositoryService.saveTgUser(tgUser);
                                sendMessage(chatId, "Введите новую ссылку на канал ⬇⬇⬇", nameForLog);
                            }
                            else if(messageText.equals("Добавить описание")){
                                tgUser.setCurrentStep(ADD_EDIT_DESCRIPTION);
                                tgUserRepositoryService.saveTgUser(tgUser);
                                sendMessage(chatId, "Введите новое описание канала ⬇⬇⬇", nameForLog);
                            }
                        }
                        else if(tgUser.getCurrentStep().equals(EDIT_СH_NAME)
                                || tgUser.getCurrentStep().equals(EDIT_СH_LINK)
                                || tgUser.getCurrentStep().equals(EDIT_СH_PRICE)
                                || tgUser.getCurrentStep().equals(ADD_EDIT_DESCRIPTION)){
                            // Редактирование имени канала
                            Chanell chanell = chanellRepository.findById(tgUser.getIdCurrentChannelAction()).get();
                            EditChannels editChannels = new EditChannels();
                            editChannels.setIdChannel(chanell.getId());
                            editChannels.setLastNameChannel(chanell.getName());
                            editChannels.setNewNameChannel(tgUser.getCurrentStep().equals(EDIT_СH_NAME) ? messageText : chanell.getName());
                            editChannels.setLastLinkToChannel(chanell.getLink());
                            editChannels.setNewlastLinkToChannel(tgUser.getCurrentStep().equals(EDIT_СH_LINK) ? messageText : chanell.getLink());
                            editChannels.setUserName(chanell.getTGUser().getUserName());
                            editChannels.setNewPriceChannel(tgUser.getCurrentStep().equals(EDIT_СH_PRICE) ? Double.valueOf(messageText) : chanell.getPriceDiapozon());
                            editChannels.setAddDescriptionAboutChannel(tgUser.getCurrentStep().equals(ADD_EDIT_DESCRIPTION) ? messageText : chanell.getString1());
                            editChannels.setDateCreateMessage(ZonedDateTime.now());
                            editChannels.setIsApprovedChanhes(false);
                            editChannels = editChannelsRepository.save(editChannels);

                            tgUser.setCurrentStep(WORK_WITH_MY_CHANNEL);
                            tgUser.setIdCurrentChannelAction(chanell.getId());
                            tgUserRepositoryService.saveTgUser(tgUser);

                            // Заменить айди админа
                            sendEditChannelToModerate(523559144l, editChannels.getId());
                            sendEditChannelToModerate(1376429566l, editChannels.getId());
                        }
                        else{
                            sendMessage(chatId, "Извините, команда не распознана", nameForLog);
                        }
                    }
                    else{
                        sendMessage(chatId, "Извините, команда не распознана", nameForLog);
                    }
                }
                else{
                    sendMessage(chatId, "Извините, команда не распознана", nameForLog);
                }
            }*/else { // Ради дефолтового ответа
                switch (messageText) {
                    default:
                        sendMessage(chatId, "Извините, команда не распознана", nameForLog);
                }
            }
        } else if (update.hasInlineQuery()) {
            InlineQuery inlineQuery = update.getInlineQuery();

            // Обработка запроса и формирование ответа
            String query = inlineQuery.getQuery();
            List<InlineQueryResult> results = new ArrayList<>();

            var categories = categoryRepository
                .findCategoriesHaveChanellsAndIsShowTrue(ZonedDateTime.now().minusDays(1))
                .stream()
                .filter(cat -> !cat.getName().isEmpty())
                .filter(cat -> cat.getName().toLowerCase().startsWith(query.toLowerCase()))
                .collect(Collectors.toList());

            InputTextMessageContent messageContent = new InputTextMessageContent();
            messageContent.setMessageText("Кликните на категорию ниже ⬇⬇⬇"); // Текст в двух местах

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
                execute(answer); // Отправка ответа на inline-запрос
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            Long chatId = null;
            Integer messageId = null;
            //            Long chatId = Optional.ofNullable(update.getCallbackQuery().getMessage().getChatId()).orElse(null);
            //            Integer messageId = Optional.ofNullable(update.getCallbackQuery().getMessage().getMessageId()).orElse(null);
            //            Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
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
                executeEditText(chatId, nameForLog, text, messageId);
            } else if (callbackData.equals(NO_BUTTON)) {
                String text = "You pressed NO button";
                executeEditText(chatId, nameForLog, text, messageId);
            } /*else if(callbackData.equals(FIND_CHANNEL)){
//                executeDeleteMessage(chatId, nameForLog, messageId);
                findCategory(chatId, nameForLog);
            }
            else if(callbackData.equals(FIND_CITIES)){
//                executeDeleteMessage(chatId, nameForLog, messageId);
                findCityNamesByFirstLetter(chatId, nameForLog);
            }*/else if (callbackData.contains(FIND_CHANNEL)) {
                findCategoryFirstPage(chatId, nameForLog, false, messageId); // ОДНО и ТОЖЕ С ТЕМ ЧТО ВЫШЕ
            } else if (callbackData.contains(FIND_CITIES)) {
                findCityNamesByFirstLetter(chatId, nameForLog); // ОДНО и ТОЖЕ С ТЕМ ЧТО ВЫШЕ
            } else if (callbackData.contains(FIND_TEMATICS_FIRST)) {
                findCategoryFirstPage(chatId, nameForLog, true, messageId); // ОДНО и ТОЖЕ С ТЕМ ЧТО ВЫШЕ
            } else if (callbackData.contains(All_PAGES_TEMATICS)) {
                findCategoryAllPages(chatId, nameForLog, messageId);
            } else if (callbackData.contains(All_PAGES_TEMATICS_FOR_CITY)) {
                //                String nameCity = String.valueOf(callbackData.split(":")[1]);
                Long cityId = Long.valueOf(callbackData.split(":")[1]);
                findCategoryAllPagesByCity(chatId, nameForLog, messageId, cityId);
            } else if (callbackData.equals(ADMIN_LINK)) {
                //                executeDeleteMessage(chatId, nameForLog, messageId);
                sendAdminLink(chatId, nameForLog);
            } else if (callbackData.contains(NEXT_PAGE_WITH_TEMATICS)) {
                Integer pageNumber = Integer.valueOf(callbackData.split(":")[1]);
                Integer numberInMap = Integer.valueOf(callbackData.split(":")[2]);
                findCategoryNextPage(chatId, nameForLog, pageNumber, messageId, numberInMap);
            } else if (callbackData.contains(NEXT_PAGE_WITH_TEMATICS_FOR_CITY)) {
                Integer pageNumber = Integer.valueOf(callbackData.split(":")[1]);
                //                String nameCity = String.valueOf(callbackData.split(":")[2]);
                Long cityId = Long.valueOf(callbackData.split(":")[2]);
                Integer numberInMap = Integer.valueOf(callbackData.split(":")[3]);
                getCategoriesByCityNameNextPage(chatId, nameForLog, cityId, pageNumber, messageId, numberInMap);
            }/*else if(callbackData.equals(SEARCH_TEMATICS)){ // При нажатии на текстовы поиск категории
                currentChatId = chatId;
            }*/ /*else if(callbackData.equals(ADD_CHANNEL)){
                String text = "Вы нажали добавить канал";
                executeEditText(chatId, nameForLog, text,messageId);
                selectCategory(chatId, nameForLog);
            }*/
            else if (callbackData.contains(CATEGORY)) {
                String text = "Вы нажали на категоррию " + callbackData.replace(CATEGORY, "");
                Long categoryId = Long.valueOf(callbackData.replace(CATEGORY, ""));
                //                executeEditText(chatId, nameForLog, text,messageId);
                //                executeDeleteMessage(chatId, nameForLog, messageId);
                //                selectPriceDiapozonForGetChanneles(chatId, nameForLog, categoryId);
                getChanellByCategoryId(chatId, nameForLog, categoryId);
            } else if (callbackData.contains(CLICK_TEMA)) {
                String text = "Вы нажали на категоррию " + callbackData.replace(CLICK_TEMA, "");
                Long categoryId = Long.valueOf(callbackData.split(":")[1]);
                Long chatIdIn = Long.valueOf(callbackData.split(":")[2]);
                getChanellByCategoryId(chatIdIn, nameForLog, categoryId);
            } else if (callbackData.contains(ALL_LIST_CH)) {
                String text = "Вы нажали на категоррию " + callbackData.replace(ALL_LIST_CH, "");
                Long categoryId = Long.valueOf(callbackData.split(":")[1]);
                getChanellByCategoryIdBigButtons(chatId, nameForLog, categoryId);
            } else if (callbackData.contains(ALL_LIST_GOROD_CHANNEL)) {
                String text = "Вы нажали на категоррию " + callbackData.replace(ALL_LIST_GOROD_CHANNEL, "");
                Long categoryId = Long.valueOf(callbackData.split(":")[1]);
                //                String nameCity = String.valueOf(callbackData.split(":")[2]);
                Long cityId = Long.valueOf(callbackData.split(":")[2]);
                getChanellByCategoryIdByCityBigButtons(chatId, nameForLog, categoryId, cityId);
            } else if (callbackData.contains(NEXT_PAGE_CAT)) {
                String text = "Вы нажали на следующую страницу " + callbackData.replace(NEXT_PAGE_CAT, "");
                Long pageNumber = Long.valueOf(callbackData.split(":")[1]);
                Long categoryId = Long.valueOf(callbackData.split(":")[2]);
                getChanellByCategoryIdAndPageNumber(chatId, nameForLog, categoryId, pageNumber, messageId);
            } else if (callbackData.contains(ANY_PAGE_IN_CAT)) {
                String text = "Вы нажали на следующую страницу " + callbackData.replace(NEXT_PAGE_CAT, "");
                Long pagesCount = Long.valueOf(callbackData.split(":")[1]);
                Long categoryId = Long.valueOf(callbackData.split(":")[2]);
                getAllPagesButtonsByCategoryIdAndPageNumber(chatId, nameForLog, categoryId, pagesCount);
            }
            // Для городов
            else if (callbackData.contains(NEXT_PAGE_GOROD_CAT)) {
                Long pageNumber = Long.valueOf(callbackData.split(":")[1]);
                Long categoryId = Long.valueOf(callbackData.split(":")[2]);
                //                String nameCity = String.valueOf(callbackData.split(":")[3]);
                Long cityId = Long.valueOf(callbackData.split(":")[3]);
                getChanellByCityNameByCategoryIdAndPageNumber(chatId, nameForLog, cityId, categoryId, pageNumber, messageId);
            }
            // Для городов _______
            else if (callbackData.contains(ANY_PAGE_GOROD_IN_CAT)) {
                Long pagesCount = Long.valueOf(callbackData.split(":")[1]);
                Long categoryId = Long.valueOf(callbackData.split(":")[2]);
                //                String nameCity = String.valueOf(callbackData.split(":")[3]);
                Long cityId = Long.valueOf(callbackData.split(":")[3]);
                getAllPagesButtonsByCategoryIdAndPageNumberByCity(chatId, nameForLog, categoryId, pagesCount, cityId);
            } else if (callbackData.contains(GORODA_FIRST_LETTER)) {
                String firstLetterOfCity = String.valueOf(callbackData.split(":")[1]);
                String text = "Вы нажали на первую букву города - " + firstLetterOfCity;
                chooseСity(chatId, nameForLog, firstLetterOfCity);
            } else if (callbackData.contains(CITY)) {
                //                String cityName = String.valueOf(callbackData.split(":")[1]);
                Long cityId = Long.valueOf(callbackData.split(":")[1]);
                //                getCategoriesByCityNameFirstPage(chatId, nameForLog, cityName, false, messageId);
                getCategoriesByCityNameFirstPage(chatId, nameForLog, cityId, false, messageId);
            } else if (callbackData.contains(UPDATE_GOROD_MESSAGE)) {
                //                String cityName = String.valueOf(callbackData.split(":")[1]);
                Long cityId = Long.valueOf(callbackData.split(":")[1]);
                getCategoriesByCityNameFirstPage(chatId, nameForLog, cityId, true, messageId);
            } else if (callbackData.contains(TEMA_GOROD)) {
                //                String cityName = String.valueOf(callbackData.split(":")[1]);
                Long cityId = Long.valueOf(callbackData.split(":")[1]);
                Long categoryId = Long.valueOf(callbackData.split(":")[2]);
                getChanellByCityNameByCategoryId(chatId, nameForLog, cityId, categoryId);
            } /*else if(callbackData.contains(PRICEDIAP)){
                String text = "Вы нажали на price " + callbackData.replace(PRICEDIAP,"");
                String[] strings = callbackData.split(IDCAT);
                Double price = Double.valueOf(strings[0].replace(PRICEDIAP,""));
                Long categoryId = Long.valueOf(strings[1]);
//                executeEditText(chatId, nameForLog, text, messageId);

//                executeDeleteMessage(chatId, nameForLog, messageId);
                getChanellByPriceDiapozon(chatId, nameForLog, price, categoryId);
            }*/else if (callbackData.contains(CHANNEL)) { // ВРОДЕ НЕ ИСПОЛЬЗУЕТСЯ
                String text = "Вы нажали на channel " + callbackData.replace(CHANNEL, "");
                Long channelId = Long.valueOf(callbackData.replace(CHANNEL, ""));
                Optional<Chanell> chanell = chanellRepository.findById(channelId);
                if (chanell.isPresent()) {
                    System.out.println(chanell.get().getLink());
                }
            } /*else if(callbackData.contains(FIND_CAT_FOR_ADD_CHAN)){ // ИСПОЛЬЗУЕТСЯ
                Long idCategory = Long.valueOf(callbackData.split(":")[1]);
                addChannelByCategory(chatId, nameForLog, idCategory);

            }*/else if (callbackData.contains("Кликните на категорию ниже")) {
                //                currentChatId = chatId;
                // ничего не должно происходить
            } /*else if(callbackData.contains(ADD_СH_NAME)){ // ВРОДЕ НЕ ИСПОЛЬЗУЕТСЯ
                Long idChannel = Long.valueOf(callbackData.split(":")[1]);
                Long tgUserId = Long.valueOf(callbackData.split(":")[2]);
//                addChannelName(chatId, nameForLog, idChannel, tgUserId);
            }*/else if (callbackData.contains(CREATE_APPROVE_СH)) {
                Long idChannel = Long.valueOf(callbackData.split(":")[1]);
                approveCreateChannel(idChannel);
                Set<MessegePannel> messegePannels = getAllWhatWeWantDeleteByChannelID(idChannel, MODERATION);
                for (MessegePannel ms : messegePannels) {
                    executeDeleteMessage(ms.getIdAdmin(), nameForLog, ms.getIdMessage());
                    messegePannelRepository.delete(ms);
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
                disableCreateChannel(idChannel, whyFailureINT);
                Set<MessegePannel> messegePannels = getAllWhatWeWantDeleteByChannelID(idChannel, MODERATION);
                for (MessegePannel ms : messegePannels) {
                    executeDeleteMessage(ms.getIdAdmin(), nameForLog, ms.getIdMessage());
                    messegePannelRepository.delete(ms);
                }
            } else if (callbackData.contains(CREATE_BAN_CH)) {
                Long idChannel = Long.valueOf(callbackData.split(":")[1]);
                banCreateChannel(idChannel);
                Set<MessegePannel> messegePannels = getAllWhatWeWantDeleteByChannelID(idChannel, MODERATION);
                for (MessegePannel ms : messegePannels) {
                    executeDeleteMessage(ms.getIdAdmin(), nameForLog, ms.getIdMessage());
                    messegePannelRepository.delete(ms);
                }
            } else if (callbackData.contains(EDIT_APPROVE_СH)) {
                Long idEditChannel = Long.valueOf(callbackData.split(":")[1]);
                approveEditingChannel(idEditChannel);
                Set<MessegePannel> messegePannels = getAllWhatWeWantDeleteByChannelID(idEditChannel, EDITING_CHANNEL);
                for (MessegePannel ms : messegePannels) {
                    executeDeleteMessage(ms.getIdAdmin(), nameForLog, ms.getIdMessage());
                    messegePannelRepository.delete(ms);
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
                disableEditChannel(idEditChannel, whyFailureINT);
                Set<MessegePannel> messegePannels = getAllWhatWeWantDeleteByChannelID(idEditChannel, EDITING_CHANNEL);
                for (MessegePannel ms : messegePannels) {
                    executeDeleteMessage(ms.getIdAdmin(), nameForLog, ms.getIdMessage());
                    messegePannelRepository.delete(ms);
                }
            } else if (callbackData.contains(EDIT_BAN_CH)) {
                Long idChannel = Long.valueOf(callbackData.split(":")[1]);
                banEditChannel(idChannel);
                Set<MessegePannel> messegePannels = getAllWhatWeWantDeleteByChannelID(idChannel, MODERATION);
                for (MessegePannel ms : messegePannels) {
                    executeDeleteMessage(ms.getIdAdmin(), nameForLog, ms.getIdMessage());
                }
            } else if (callbackData.equals(V_NACHALO)) {
                vNachaloCommandReceived(chatId, nameForLog);
            } else if (callbackData.contains(TEX_PODDERSHKA)) {
                Long idChannel = Long.valueOf(callbackData.split(":")[1]);
                // Заменить айди админа
                // Рассылка админам сообщения о необходимости связаться с участником процесса
                sendMessageToTehPoddershkaAfterOtkonitKanal(523559144l, idChannel);
                sendMessageToTehPoddershkaAfterOtkonitKanal(1376429566l, idChannel);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                vNachaloCommandReceived(chatId, nameForLog);
            } else if (callbackData.contains(TAKE_TO_WORK_AFTER_FALSE_CONTACTING_THE_CHAN)) { //
                Long idChannel = Long.valueOf(callbackData.split(":")[1]);
                Set<MessegePannel> messegePannels = getAllWhatWeWantDeleteByChannelID(idChannel, SVYAZ_S_ADMINAMI);
                for (MessegePannel ms : messegePannels) {
                    if (ms.getIdAdmin().equals(chatId)) { // либо поставить отрицание и воткнуть строку удаления сообщения
                        continue;
                    }
                    executeDeleteMessage(ms.getIdAdmin(), nameForLog, ms.getIdMessage());
                }
                sendMessage(chatId, "При завершение общения с пользователем, нажмите - Отклонить/Завершить", nameForLog);
                Chanell chanell = chanellRepository.findById(idChannel).get();
                chanell.setApprovedAdmin(chatId);
                chanellRepository.save(chanell);
            } else if (callbackData.contains(REJECT_FINISH_CONTACTING_FOR_ME_AFTER_CHAN_CLICK)) { // При нажатии на Отклонить
                Long idChannel = Long.valueOf(callbackData.split(":")[1]);
                Set<MessegePannel> messegePannels = getAllWhatWeWantDeleteByChannelID(idChannel, SVYAZ_S_ADMINAMI);
                for (MessegePannel ms : messegePannels) {
                    if (ms.getIdAdmin().equals(chatId)) { // либо поставить отрицание и воткнуть строку удаления сообщения
                        executeDeleteMessage(ms.getIdAdmin(), nameForLog, ms.getIdMessage());
                    }
                }
            } else if (callbackData.contains(SEND_ADMIN_CHAT_TO_USER)) { //
                Long idChannel = Long.valueOf(callbackData.split(":")[1]);
                String linkToAdmin = String.valueOf(callbackData.split(":")[2]);
                Chanell chanell = chanellRepository.findById(idChannel).get();
                sendMessage(
                    chanell.gettGUser().getChatId(),
                    "Не смогли найти ссылку на чат с Вами \n" + " свяжитесь с админом самостоятельно - " + "@" + linkToAdmin,
                    nameForLog
                );
                Set<MessegePannel> messegePannels = getAllWhatWeWantDeleteByChannelID(idChannel, SVYAZ_S_ADMINAMI);
                for (MessegePannel ms : messegePannels) {
                    executeDeleteMessage(ms.getIdAdmin(), nameForLog, ms.getIdMessage());
                }
            }
            //////////////// Связь с админом - закомментированная
            else if (callbackData.contains(TAKE_TO_WORK_AFTER_FALSE_CONTACTING_FROM_MENU)) { //
                Long userChatId = Long.valueOf(callbackData.split(":")[1]);
                Set<MessegePannel> messegePannels = getAllWhatWeWantDeleteByChatId(userChatId.toString(), SVYAZ_S_ADMINAMI);
                for (MessegePannel ms : messegePannels) {
                    if (ms.getIdAdmin().equals(chatId)) { // либо поставить отрицание и воткнуть строку удаления сообщения
                        continue;
                    }
                    executeDeleteMessage(ms.getIdAdmin(), nameForLog, ms.getIdMessage());
                }
                sendMessage(chatId, "При завершение общения с пользователем, нажмите - Отклонить/Завершить", nameForLog);
            } else if (callbackData.contains(REJECT_FINISH_CONTACTING_FOR_ME_AFTER_FROM_MENU)) { // При нажатии на Отклонить
                Long userChatID = Long.valueOf(callbackData.split(":")[1]);
                Set<MessegePannel> messegePannels = getAllWhatWeWantDeleteByChatId(userChatID.toString(), SVYAZ_S_ADMINAMI);
                for (MessegePannel ms : messegePannels) {
                    /*if(ms.getIdAdmin().equals(chatId)){ // либо поставить отрицание и воткнуть строку удаления сообщения
                        executeDeleteMessage(ms.getIdAdmin(), nameForLog, ms.getIdMessage());
                    }*/
                    executeDeleteMessage(ms.getIdAdmin(), nameForLog, ms.getIdMessage());
                }
            } else if (callbackData.contains(SEND_ADMIN_LINK_TO_USER_FROM_MENU)) { //
                Long userChatID = Long.valueOf(callbackData.split(":")[1]);
                String linkToAdmin = String.valueOf(callbackData.split(":")[2]);
                sendMessage(
                    userChatID,
                    "Не смогли найти ссылку на чат с Вами \n" + " свяжитесь с админом самостоятельно - " + "@" + linkToAdmin,
                    nameForLog
                );
                Set<MessegePannel> messegePannels = getAllWhatWeWantDeleteByChatId(userChatID.toString(), SVYAZ_S_ADMINAMI);
                for (MessegePannel ms : messegePannels) {
                    executeDeleteMessage(ms.getIdAdmin(), nameForLog, ms.getIdMessage());
                }
            } else if (callbackData.contains(MY_CHNS)) {
                Long channelID = Long.valueOf(callbackData.split(":")[1]);
                String channelName = chanellRepository.findById(channelID).get().getName();
                String answer = "Выберите действие в меню : " + channelName;
                TGUser tgUser = tgUserRepositoryService
                    .getOneChatIdAndDeleteFalse(update.getCallbackQuery().getMessage().getChatId())
                    .get();
                tgUser.setCurrentStep(WORK_WITH_MY_CHANNEL);
                tgUser.setIdCurrentChannelAction(channelID);
                tgUserRepositoryService.saveTgUser(tgUser);
                sendMessageWithKeyBoardWithAllMyChannels(chatId, answer, nameForLog, channelID);
            }
        }
    }

    private void register(long chatId, String name) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Do you really want to register?");

        // создание клавиатуры с кнопками в ответе на сообщение
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        // создание списка с кнопками в ответе на сообщение
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        var yesButton = new InlineKeyboardButton();

        yesButton.setText("Yes"); // Содержимое ответа в кнопке
        yesButton.setCallbackData(YES_BUTTON); // Привязка кнопки к реагирование на YES_BUTTON в сообщении, типо когда ответ не текст а кол-бек

        var noButton = new InlineKeyboardButton();

        noButton.setText("No");
        noButton.setCallbackData(NO_BUTTON);

        rowInLine.add(yesButton);
        rowInLine.add(noButton);

        rowsInLine.add(rowInLine);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        executeMessage(message, name);
    }

    private void checkFindChannelOrAddChannel(long chatId, String name) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        //         message.setText("Выберите  \"действие\" "); // Обязательное для телеграмма-апи поле
        message.setText("Выберите действие ⬇⬇⬇ "); // Обязательное для телеграмма-апи поле

        // создание клавиатуры с кнопками в ответе на сообщение
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        // создание списка с кнопками в ответе на сообщение
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine2 = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine3 = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine4 = new ArrayList<>();

        var findChannelButton = new InlineKeyboardButton();

        var searchCategory = new InlineKeyboardButton();
        searchCategory.setText("Текстовый поиск категорий 🌍🌍🌍"); // Содержимое ответа в кнопке
        searchCategory.setSwitchInlineQueryCurrentChat(" ");

        findChannelButton.setText("Найти каналы по категориям 🔑🔑🔑"); // Содержимое ответа в кнопке
        findChannelButton.setCallbackData(FIND_CHANNEL); // Привязка кнопки к реагирование на FIND_CHANEL в сообщении, типо когда ответ не текст а кол-бек

        var findCitiesButton = new InlineKeyboardButton();
        findCitiesButton.setText("Найти каналы по городам 🏛🏙🏘"); // Содержимое ответа в кнопке
        findCitiesButton.setCallbackData(FIND_CITIES); // Привязка кнопки к реагирование на FIND_CITIES в сообщении, типо когда ответ не текст а кол-бек

        var linkToAdmin = new InlineKeyboardButton();
        linkToAdmin.setText("Связь с админом ☎☎☎"); // Содержимое ответа в кнопке
        linkToAdmin.setCallbackData(ADMIN_LINK); // Привязка кнопки к реагирование на ADMIN_LINK в сообщении, типо когда ответ не текст а кол-бек

        rowInLine.add(searchCategory);
        rowInLine2.add(findChannelButton);
        rowInLine3.add(findCitiesButton);
        rowInLine4.add(linkToAdmin);

        rowsInLine.add(rowInLine);
        rowsInLine.add(rowInLine2);
        rowsInLine.add(rowInLine3);
        rowsInLine.add(rowInLine4);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        executeMessage(message, name);
    }

    /*private void checkFindChannelOrAddChannel(long chatId, String name){

        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Выберите действие");

        // создание клавиатуры с кнопками в ответе на сообщение
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        // создание списка с кнопками в ответе на сообщение
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        var findChannelButton = new InlineKeyboardButton();

        findChannelButton.setText("Найти каналы"); // Содержимое ответа в кнопке
        findChannelButton.setCallbackData(FIND_CHANNEL); // Привязка кнопки к реагирование на FIND_CHANEL в сообщении, типо когда ответ не текст а кол-бек

        var addChannelButton = new InlineKeyboardButton();

        addChannelButton.setText("Разместить канал");
        addChannelButton.setCallbackData(ADD_CHANNEL);

        rowInLine.add(findChannelButton);
        rowInLine.add(addChannelButton);

        rowsInLine.add(rowInLine);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        executeMessage(message, name);
    }*/

    // Найти категории
    private void findCategoryFirstPage(long chatId, String name, boolean isEditMessage, long messageId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Выберите категорию ⬇⬇⬇");

        // создание клавиатуры с кнопками в ответе на сообщение
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); //клавиаутра
        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры
        // создание списка с кнопками в ответе на сообщение

        List<CategoryNameAndIdDTO> categoryListFromDB = categoryRepository.findCategoriesHaveChanellsAndIsShowTrue(
            ZonedDateTime.now().minusDays(1)
        ); //categoryListFirstPage
        List<CategoryNameAndIdDTO> categoryListFirstPage = categoryListFromDB
            .stream()
            .filter(CategoryNameAndIdDTO::getIsFirst)
            .sorted(Comparator.comparing(CategoryNameAndIdDTO::getScore))
            .collect(Collectors.toList());

        List<CategoryNameAndIdDTO> categoryListNotFirst = categoryRepository
            .findCategoriesHaveChanellsAndIsShowTrue(ZonedDateTime.now().minusDays(1))
            .stream()
            .filter(cat -> cat.getIsFirst().equals(false))
            .sorted(Comparator.comparing(CategoryNameAndIdDTO::getScore))
            .collect(Collectors.toList());

        Map<Integer, List<CategoryNameAndIdDTO>> groupedCategories = IntStream
            .range(0, categoryListNotFirst.size())
            .boxed()
            .collect(Collectors.groupingBy(i -> i / 10, Collectors.mapping(categoryListNotFirst::get, Collectors.toList())));

        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        List<CategoryNameAndIdDTO> categories = categoryListFirstPage.size() > 0
            ? categoryListFirstPage
            : groupedCategories.size() > 0 ? groupedCategories.get(0) : new ArrayList<>();

        if (categories.isEmpty()) {
            sendMessage(chatId, "Что-то пошло ни так😆 Попробуйте заново🤣", name);
        }

        for (int i = 0; i < categories.size(); i = i + 2) {
            var button1 = new InlineKeyboardButton();
            var button2 = new InlineKeyboardButton();
            rowInLine = new ArrayList<>();

            if (i < categories.size()) {
                button1.setText(categories.get(i).getName());
                button1.setCallbackData(CATEGORY + categories.get(i).getId());
                rowInLine.add(button1);
                if (i + 1 < categories.size()) {
                    button2.setText(categories.get(i + 1).getName());
                    button2.setCallbackData(CATEGORY + categories.get(i + 1).getId());
                    rowInLine.add(button2);
                }
            }
            rowsInLine.add(rowInLine);
        }

        if (categoryListFirstPage.size() > 0 && groupedCategories.size() > 0) { //Базовый сценарий
            var allCategoriesPages = new InlineKeyboardButton();
            var nextPage = new InlineKeyboardButton();
            allCategoriesPages.setText("стр.№ 1/" + (1 + groupedCategories.size()));
            nextPage.setText("▶");
            allCategoriesPages.setCallbackData(All_PAGES_TEMATICS); // написать потом
            nextPage.setCallbackData(NEXT_PAGE_WITH_TEMATICS + ":" + 2 + ":" + 0);
            rowInLine = new ArrayList<>();
            rowInLine.add(allCategoriesPages);
            rowInLine.add(nextPage);
            rowsInLine.add(rowInLine);
        } else if (categoryListFirstPage.size() > 0 && groupedCategories.size() == 0) { // когда есть только первичные категории
            var button3 = new InlineKeyboardButton();
            var searchCategory = new InlineKeyboardButton();
            rowInLine = new ArrayList<>();
            searchCategory.setText("Текстовый поиск категорий 🌍🌍🌍"); // Содержимое ответа в кнопке
            searchCategory.setSwitchInlineQueryCurrentChat(" ");
            rowInLine.add(button3);
            rowsInLine.add(rowInLine);
            rowInLine = new ArrayList<>();
            button3.setText("Каналы по городам 🏘🏙");
            button3.setCallbackData(FIND_CITIES + ":");
            rowInLine.add(searchCategory);
            rowsInLine.add(rowInLine);
        } else if (categoryListFirstPage.size() == 0 && groupedCategories.size() > 1) { //Базовый сценарий
            var allCategoriesPages = new InlineKeyboardButton();
            var nextPage = new InlineKeyboardButton();
            allCategoriesPages.setText("стр.№ 1/" + (groupedCategories.size()));
            nextPage.setText("▶");
            allCategoriesPages.setCallbackData(All_PAGES_TEMATICS); // написать потом
            nextPage.setCallbackData(NEXT_PAGE_WITH_TEMATICS + ":" + 2 + ":" + 1);
            rowInLine = new ArrayList<>();
            rowInLine.add(allCategoriesPages);
            rowInLine.add(nextPage);
            rowsInLine.add(rowInLine);
        } else if (categoryListFirstPage.size() > 0 && groupedCategories.size() == 0) { // когда есть только первичные категории
            var button3 = new InlineKeyboardButton();
            var searchCategory = new InlineKeyboardButton();
            rowInLine = new ArrayList<>();
            searchCategory.setText("Текстовый поиск категорий 🌍🌍🌍"); // Содержимое ответа в кнопке
            searchCategory.setSwitchInlineQueryCurrentChat(" ");
            rowInLine.add(button3);
            rowsInLine.add(rowInLine);
            rowInLine = new ArrayList<>();
            button3.setText("Каналы по городам 🏘🏙");
            button3.setCallbackData(FIND_CITIES + ":");
            rowInLine.add(searchCategory);
            rowsInLine.add(rowInLine);
        }

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        if (isEditMessage == true) {
            executeEditTextWithKeyBoardAndDisableWebPreview(chatId, name, message.getText(), messageId, markupInLine);
        } else {
            executeMessage(message, name);
        }
        log.info("Пользователь с имененем " + name + " получил список категорий ");
    }

    // Найти категории - следующая страница
    private void findCategoryNextPage(long chatId, String name, Integer pageNumber, long messageId, Integer numberInMap) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Выберите категорию ⬇⬇⬇");

        // создание клавиатуры с кнопками в ответе на сообщение
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); //клавиаутра
        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры
        // создание списка с кнопками в ответе на сообщение

        List<CategoryNameAndIdDTO> categoryListFromDB = categoryRepository.findCategoriesHaveChanellsAndIsShowTrue(
            ZonedDateTime.now().minusDays(1)
        );

        List<CategoryNameAndIdDTO> categoryListFirstPage = categoryListFromDB
            .stream()
            .filter(CategoryNameAndIdDTO::getIsFirst)
            .sorted(Comparator.comparing(CategoryNameAndIdDTO::getScore))
            .collect(Collectors.toList());

        List<CategoryNameAndIdDTO> categoryListNotFirst = categoryListFromDB
            .stream()
            .filter(cat -> cat.getIsFirst().equals(false))
            .sorted(Comparator.comparing(CategoryNameAndIdDTO::getScore))
            .collect(Collectors.toList());

        Map<Integer, List<CategoryNameAndIdDTO>> groupedCategories = IntStream
            .range(0, categoryListNotFirst.size())
            .boxed()
            .collect(Collectors.groupingBy(i -> i / 10, Collectors.mapping(categoryListNotFirst::get, Collectors.toList())));

        boolean firstExist = categoryListFirstPage.size() > 0;
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        //        for (int i = 0; i < (firstExist ? groupedCategories.get(pageNumber - 2).size() : groupedCategories.get(pageNumber - 1).size()); i = i + 2) {
        for (int i = 0; i < groupedCategories.get(numberInMap).size(); i = i + 2) {
            var button1 = new InlineKeyboardButton();
            var button2 = new InlineKeyboardButton();
            rowInLine = new ArrayList<>();

            if (i < groupedCategories.get(numberInMap).size()) {
                button1.setText(groupedCategories.get(numberInMap).get(i).getName());
                button1.setCallbackData(CATEGORY + groupedCategories.get(numberInMap).get(i).getId());
                rowInLine.add(button1);
                if (i + 1 < groupedCategories.get(numberInMap).size()) {
                    button2.setText(groupedCategories.get(numberInMap).get(i + 1).getName());
                    button2.setCallbackData(CATEGORY + groupedCategories.get(numberInMap).get(i + 1).getId());
                    rowInLine.add(button2);
                }
            }
            rowsInLine.add(rowInLine);
        }

        if (firstExist && pageNumber == (groupedCategories.size() + 1) && pageNumber != 2) { // последняя страница, когда первые проставлены в бд
            var allCategoriesPages = new InlineKeyboardButton();
            var oldPage = new InlineKeyboardButton();
            oldPage.setText("◀");
            allCategoriesPages.setText("стр.№ " + pageNumber + "/" + (1 + groupedCategories.size()));
            oldPage.setCallbackData(NEXT_PAGE_WITH_TEMATICS + ":" + (pageNumber - 1) + ":" + (numberInMap - 1));
            allCategoriesPages.setCallbackData(All_PAGES_TEMATICS); // написать потом
            rowInLine = new ArrayList<>();
            rowInLine.add(oldPage);
            rowInLine.add(allCategoriesPages);
            rowsInLine.add(rowInLine);
        } else if (firstExist && pageNumber == (groupedCategories.size() + 1) && pageNumber == 2) { // последняя страница, когда первые проставлены в бд
            var allCategoriesPages = new InlineKeyboardButton();
            var oldPage = new InlineKeyboardButton();
            oldPage.setText("◀");
            allCategoriesPages.setText("стр.№ " + pageNumber + "/" + (1 + groupedCategories.size()));
            oldPage.setCallbackData(FIND_TEMATICS_FIRST);
            allCategoriesPages.setCallbackData(All_PAGES_TEMATICS); // написать потом
            rowInLine = new ArrayList<>();
            rowInLine.add(oldPage);
            rowInLine.add(allCategoriesPages);
            rowsInLine.add(rowInLine);
        } else if (firstExist && pageNumber != (groupedCategories.size() + 1) && pageNumber != 2) { // вторая страница, когда первые проставлены в бд, существуют ещё страницы
            var allCategoriesPages = new InlineKeyboardButton();
            var nextPage = new InlineKeyboardButton();
            var oldPage = new InlineKeyboardButton();
            oldPage.setText("◀");
            allCategoriesPages.setText("стр.№ " + pageNumber + "/" + (1 + groupedCategories.size()));
            nextPage.setText("▶");
            oldPage.setCallbackData(NEXT_PAGE_WITH_TEMATICS + ":" + (pageNumber - 1) + ":" + (numberInMap - 1));
            allCategoriesPages.setCallbackData(All_PAGES_TEMATICS); // написать потом
            nextPage.setCallbackData(NEXT_PAGE_WITH_TEMATICS + ":" + (1 + pageNumber) + ":" + (numberInMap + 1));
            rowInLine = new ArrayList<>();
            rowInLine.add(oldPage);
            rowInLine.add(allCategoriesPages);
            rowInLine.add(nextPage);
            rowsInLine.add(rowInLine);
        } else if (firstExist && pageNumber != (groupedCategories.size() + 1) && pageNumber == 2) { // вторая страница, когда первые проставлены в бд, существуют ещё страницы
            var allCategoriesPages = new InlineKeyboardButton();
            var nextPage = new InlineKeyboardButton();
            var oldPage = new InlineKeyboardButton();
            oldPage.setText("◀");
            allCategoriesPages.setText("стр.№ " + pageNumber + "/" + (1 + groupedCategories.size()));
            nextPage.setText("▶");
            oldPage.setCallbackData(FIND_TEMATICS_FIRST);
            allCategoriesPages.setCallbackData(All_PAGES_TEMATICS); // написать потом
            nextPage.setCallbackData(NEXT_PAGE_WITH_TEMATICS + ":" + (1 + pageNumber) + ":" + (numberInMap + 1));
            rowInLine = new ArrayList<>();
            rowInLine.add(oldPage);
            rowInLine.add(allCategoriesPages);
            rowInLine.add(nextPage);
            rowsInLine.add(rowInLine);
        } else if (!firstExist && pageNumber == (groupedCategories.size()) && pageNumber != 2) { // последняя страница, когда первые не проставлены в бд
            var allCategoriesPages = new InlineKeyboardButton();
            var oldPage = new InlineKeyboardButton();
            oldPage.setText("◀");
            allCategoriesPages.setText("стр.№ " + pageNumber + "/" + (groupedCategories.size()));
            oldPage.setCallbackData(NEXT_PAGE_WITH_TEMATICS + ":" + (pageNumber - 1) + ":" + (numberInMap - 1));
            allCategoriesPages.setCallbackData(All_PAGES_TEMATICS); // написать потом
            rowInLine = new ArrayList<>();
            rowInLine.add(oldPage);
            rowInLine.add(allCategoriesPages);
            rowsInLine.add(rowInLine);
        } else if (!firstExist && pageNumber == (groupedCategories.size()) && pageNumber == 2) { // последняя страница, когда первые не проставлены в бд
            var allCategoriesPages = new InlineKeyboardButton();
            var oldPage = new InlineKeyboardButton();
            oldPage.setText("◀");
            allCategoriesPages.setText("стр.№ " + pageNumber + "/" + (groupedCategories.size()));
            oldPage.setCallbackData(FIND_TEMATICS_FIRST);
            allCategoriesPages.setCallbackData(All_PAGES_TEMATICS); // написать потом
            rowInLine = new ArrayList<>();
            rowInLine.add(oldPage);
            rowInLine.add(allCategoriesPages);
            rowsInLine.add(rowInLine);
        } else if (!firstExist && pageNumber != (groupedCategories.size()) && pageNumber != 2) { //  не вторая страница, когда первые не проставлены в бд, существуют ещё страницы
            var allCategoriesPages = new InlineKeyboardButton();
            var nextPage = new InlineKeyboardButton();
            var oldPage = new InlineKeyboardButton();
            oldPage.setText("◀");
            allCategoriesPages.setText("стр.№ " + pageNumber + "/" + (groupedCategories.size()));
            nextPage.setText("▶");
            oldPage.setCallbackData(NEXT_PAGE_WITH_TEMATICS + ":" + (pageNumber - 1) + ":" + (numberInMap - 1));
            allCategoriesPages.setCallbackData(All_PAGES_TEMATICS); // написать потом
            nextPage.setCallbackData(NEXT_PAGE_WITH_TEMATICS + ":" + (1 + pageNumber) + ":" + (numberInMap + 1));
            rowInLine = new ArrayList<>();
            rowInLine.add(oldPage);
            rowInLine.add(allCategoriesPages);
            rowInLine.add(nextPage);
            rowsInLine.add(rowInLine);
        } else if (!firstExist && pageNumber != (groupedCategories.size()) && pageNumber == 2) { // вторая страница, когда первые не проставлены в бд, существуют ещё страницы
            var allCategoriesPages = new InlineKeyboardButton();
            var nextPage = new InlineKeyboardButton();
            var oldPage = new InlineKeyboardButton();
            oldPage.setText("◀");
            allCategoriesPages.setText("стр.№ " + pageNumber + "/" + (groupedCategories.size()));
            nextPage.setText("▶");
            oldPage.setCallbackData(FIND_TEMATICS_FIRST);
            allCategoriesPages.setCallbackData(All_PAGES_TEMATICS); // написать потом
            nextPage.setCallbackData(NEXT_PAGE_WITH_TEMATICS + ":" + (1 + pageNumber) + ":" + (numberInMap + 1));
            rowInLine = new ArrayList<>();
            rowInLine.add(oldPage);
            rowInLine.add(allCategoriesPages);
            rowInLine.add(nextPage);
            rowsInLine.add(rowInLine);
        }

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        //        executeMessage(message, name);
        executeEditTextWithKeyBoardAndDisableWebPreview(chatId, name, message.getText(), messageId, markupInLine);
        log.info("Пользователь с имененем " + name + " получил список категорий ");
    }

    // Найти категории - все страницы с категориями
    private void findCategoryAllPages(long chatId, String name, long messageId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Выберите номер страницы с категорией ⬇⬇⬇");

        // создание клавиатуры с кнопками в ответе на сообщение
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); //клавиаутра
        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры
        // создание списка с кнопками в ответе на сообщение

        List<CategoryNameAndIdDTO> categoryListFromDB = categoryRepository.findCategoriesHaveChanellsAndIsShowTrue(
            ZonedDateTime.now().minusDays(1)
        );

        List<CategoryNameAndIdDTO> categoryListFirstPage = categoryListFromDB
            .stream()
            .filter(CategoryNameAndIdDTO::getIsFirst)
            .sorted(Comparator.comparing(CategoryNameAndIdDTO::getScore))
            .collect(Collectors.toList());

        List<CategoryNameAndIdDTO> categoryListNotFirst = categoryListFromDB
            .stream()
            .filter(cat -> cat.getIsFirst().equals(false))
            .sorted(Comparator.comparing(CategoryNameAndIdDTO::getScore))
            .collect(Collectors.toList());

        Map<Integer, List<CategoryNameAndIdDTO>> groupedCategories = IntStream
            .range(0, categoryListNotFirst.size())
            .boxed()
            .collect(Collectors.groupingBy(i -> i / 10, Collectors.mapping(categoryListNotFirst::get, Collectors.toList())));

        boolean firstExist = categoryListFirstPage.size() > 0;

        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        if (firstExist) {
            for (int i = -1; i < groupedCategories.size(); i++) {
                if (i == -1) {
                    var button = new InlineKeyboardButton();
                    button.setText("№1");
                    button.setCallbackData(FIND_TEMATICS_FIRST); // ДЕЙСТВИЕ ПО ЗАМЕНЕ ТЕКУЩЕГО СООБЩЕНИЯ
                    rowInLine.add(button);
                    continue;
                }

                var button = new InlineKeyboardButton();
                button.setText("№" + (i + 2));
                button.setCallbackData(NEXT_PAGE_WITH_TEMATICS + ":" + (i + 2) + ":" + (i)); // ДЕЙСТВИЕ ПО ЗАМЕНЕ ТЕКУЩЕГО СООБЩЕНИЯ
                rowInLine.add(button);

                if (rowInLine.size() == 4) {
                    rowsInLine.add(rowInLine);
                    rowInLine = new ArrayList<>();
                }
            }
        } else {
            for (int i = 0; i < groupedCategories.size(); i++) {
                if (i == 0) {
                    var button = new InlineKeyboardButton();
                    button.setText("№1");
                    button.setCallbackData(FIND_TEMATICS_FIRST); // ДЕЙСТВИЕ ПО ЗАМЕНЕ ТЕКУЩЕГО СООБЩЕНИЯ
                    rowInLine.add(button);
                    continue;
                }

                var button = new InlineKeyboardButton();
                button.setText("№" + (i + 1));
                button.setCallbackData(NEXT_PAGE_WITH_TEMATICS + ":" + (i + 1) + ":" + (i)); // ДЕЙСТВИЕ ПО ЗАМЕНЕ ТЕКУЩЕГО СООБЩЕНИЯ
                rowInLine.add(button);

                if (rowInLine.size() == 4) {
                    rowsInLine.add(rowInLine);
                    rowInLine = new ArrayList<>();
                }
            }
        }

        if (rowsInLine.size() == 0 && rowInLine.size() < 5) {
            rowsInLine.add(rowInLine);
        }

        var button = new InlineKeyboardButton();
        var button2 = new InlineKeyboardButton();
        var searchCategory = new InlineKeyboardButton();
        rowInLine = new ArrayList<>();
        searchCategory.setText("Текстовый поиск категорий 🌍🌍🌍"); // Содержимое ответа в кнопке
        searchCategory.setSwitchInlineQueryCurrentChat(" ");
        rowInLine.add(searchCategory);
        rowsInLine.add(rowInLine);
        rowInLine = new ArrayList<>();
        button2.setText("Каналы по городам 🏘🏙");
        button2.setCallbackData(FIND_CITIES + ":");
        rowInLine.add(button2);
        rowsInLine.add(rowInLine);
        rowInLine = new ArrayList<>();
        button.setText("Каналы по категориям 👁‍👁‍");
        button.setCallbackData(FIND_CHANNEL + ":");
        rowInLine.add(button);
        rowsInLine.add(rowInLine);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        //        executeMessage(message, name);
        executeEditTextWithKeyBoardAndDisableWebPreview(chatId, name, message.getText(), messageId, markupInLine);
        log.info("Пользователь с имененем " + name + " получил список страниц категорий ");
    }

    private void findCategoryAllPagesByCity(long chatId, String name, long messageId, Long cityId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Выберите номер страницы с категорией ⬇⬇⬇");

        // создание клавиатуры с кнопками в ответе на сообщение
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); //клавиаутра
        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры
        // создание списка с кнопками в ответе на сообщение

        //        String nameCity = cityRepository.findById(cityId).get().getCityName();

        List<CategoryNameAndIdDTO> categoryListFromDB = categoryRepository.findCategoriesByCityId(cityId, ZonedDateTime.now().minusDays(1));

        List<CategoryNameAndIdDTO> categoriesFirst = categoryListFromDB
            .stream()
            .filter(c -> c.getIsFirst().equals(true))
            .sorted(Comparator.comparing(CategoryNameAndIdDTO::getScore))
            .collect(Collectors.toList());

        // ВСТАВИЛ НА СЛУЧАЙ, КОГДА НЕТ ФЁРСТ КАТЕГОРИЙ
        List<CategoryNameAndIdDTO> categoryNotFirst = categoryListFromDB
            .stream()
            .filter(cat -> cat.getIsFirst().equals(false))
            .sorted(Comparator.comparing(CategoryNameAndIdDTO::getScore))
            .collect(Collectors.toList());

        Map<Integer, List<CategoryNameAndIdDTO>> groupedCategories = IntStream
            .range(0, categoryNotFirst.size())
            .boxed()
            .collect(Collectors.groupingBy(i -> i / 10, Collectors.mapping(categoryNotFirst::get, Collectors.toList())));

        List<CategoryNameAndIdDTO> categoriesToShow = categoriesFirst.size() > 0
            ? categoriesFirst
            : groupedCategories.size() > 0 ? groupedCategories.get(0) : new ArrayList<>();

        boolean firstExist = categoriesFirst.size() > 0;
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        if (firstExist) {
            for (int i = -1; i < groupedCategories.size(); i++) {
                if (i == -1) {
                    var button = new InlineKeyboardButton();
                    button.setText("№1");
                    button.setCallbackData(CITY + ":" + cityId); // ДЕЙСТВИЕ ПО ЗАМЕНЕ ТЕКУЩЕГО СООБЩЕНИЯ
                    rowInLine.add(button);
                    continue;
                }

                var button = new InlineKeyboardButton();
                button.setText("№" + (i + 2));
                button.setCallbackData(NEXT_PAGE_WITH_TEMATICS_FOR_CITY + ":" + (i + 2) + ":" + cityId + ":" + i); // ДЕЙСТВИЕ ПО ЗАМЕНЕ ТЕКУЩЕГО СООБЩЕНИЯ
                rowInLine.add(button);

                if (rowInLine.size() == 4) {
                    rowsInLine.add(rowInLine);
                    rowInLine = new ArrayList<>();
                }
            }
        } else {
            for (int i = 0; i < groupedCategories.size(); i++) {
                if (i == 0) {
                    var button = new InlineKeyboardButton();
                    button.setText("№1");
                    button.setCallbackData(CITY + ":" + cityId); // ДЕЙСТВИЕ ПО ЗАМЕНЕ ТЕКУЩЕГО СООБЩЕНИЯ
                    rowInLine.add(button);
                    continue;
                }

                var button = new InlineKeyboardButton();
                button.setText("№" + (i + 1));
                button.setCallbackData(NEXT_PAGE_WITH_TEMATICS_FOR_CITY + ":" + (i + 1) + ":" + cityId + ":" + i); // ДЕЙСТВИЕ ПО ЗАМЕНЕ ТЕКУЩЕГО СООБЩЕНИЯ
                rowInLine.add(button);

                if (rowInLine.size() == 4) {
                    rowsInLine.add(rowInLine);
                    rowInLine = new ArrayList<>();
                }
            }
        }

        if (rowsInLine.size() == 0 && rowInLine.size() < 5) {
            rowsInLine.add(rowInLine);
        }

        var button = new InlineKeyboardButton();
        var button2 = new InlineKeyboardButton();
        var searchCategory = new InlineKeyboardButton();
        rowInLine = new ArrayList<>();
        searchCategory.setText("Текстовый поиск категорий 🌍🌍🌍"); // Содержимое ответа в кнопке
        searchCategory.setSwitchInlineQueryCurrentChat(" ");
        rowInLine.add(searchCategory);
        rowsInLine.add(rowInLine);
        rowInLine = new ArrayList<>();
        button2.setText("Каналы по городам 🏘🏙");
        button2.setCallbackData(FIND_CITIES + ":");
        rowInLine.add(button2);
        rowsInLine.add(rowInLine);
        rowInLine = new ArrayList<>();
        button.setText("Каналы по категориям 👁‍👁‍");
        button.setCallbackData(FIND_CHANNEL + ":");
        rowInLine.add(button);
        rowsInLine.add(rowInLine);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        //        executeMessage(message, name);
        executeEditTextWithKeyBoardAndDisableWebPreview(chatId, name, message.getText(), messageId, markupInLine);
        log.info("Пользователь с имененем " + name + " получил список страниц категорий ");
    }

    /* // Найти категории старый вариант - без листалки
    private void findCategory(long chatId, String name){

        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Выберите категорию ⬇⬇⬇");

        // создание клавиатуры с кнопками в ответе на сообщение
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); //клавиаутра
        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры
        // создание списка с кнопками в ответе на сообщение
        // List<InlineKeyboardButton> rowInLine = new ArrayList<>(); // одна строка // сама строка клавиатуры

//        List<CategoryWithCountChanellsDTO> categoryList = categoryRepository.findCategoriesHaveChanellsAndBool1True("");
        List<CategoryNameAndIdDTO> categoryList = categoryRepository.findCategoriesHaveChanellsAndBool1True();

        categoryList.sort(Comparator.comparing(CategoryNameAndIdDTO::getLong1));

        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        *//*for (int i = 0; i < categoryList.size(); i = i +  3) {
            var button1 = new InlineKeyboardButton();
            var button2 = new InlineKeyboardButton();
            var button3 = new InlineKeyboardButton();
            rowInLine = new ArrayList<>();

            if(i < categoryList.size()) {
                button1.setText(categoryList.get(i).getName());
                button1.setCallbackData(CATEGORY + categoryList.get(i).getId());
                rowInLine.add(button1);
                if (i + 1 < categoryList.size()) {
                    button2.setText(categoryList.get(i + 1).getName());
                    button2.setCallbackData(CATEGORY + categoryList.get(i + 1).getId());
                    rowInLine.add(button2);
                    if (i + 2 < categoryList.size()) {
                        button3.setText(categoryList.get(i + 2).getName());
                        button3.setCallbackData(CATEGORY + categoryList.get(i + 2).getId());
                        rowInLine.add(button3);
                    }
                }
            }*//*

        for (int i = 0; i < categoryList.size(); i = i +  2) {
            var button1 = new InlineKeyboardButton();
            var button2 = new InlineKeyboardButton();
            rowInLine = new ArrayList<>();

            if(i < categoryList.size()) {
                button1.setText(categoryList.get(i).getName());
                button1.setCallbackData(CATEGORY + categoryList.get(i).getId());
                rowInLine.add(button1);
                if (i + 1 < categoryList.size()) {
                    button2.setText(categoryList.get(i + 1).getName());
                    button2.setCallbackData(CATEGORY + categoryList.get(i + 1).getId());
                    rowInLine.add(button2);
                }
            }
            rowsInLine.add(rowInLine);
        }

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        executeMessage(message, name);
        log.info("Пользователь с имененем " + name + " получил список категорий " );
    }*/

    /*// Найти города  - Старый вариант в котором сразу отображаются Названия городов
    private void findCities(long chatId, String name){

        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Выберите город ⬇⬇⬇");

        // создание клавиатуры с кнопками в ответе на сообщение
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); //клавиаутра
        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры
        // создание списка с кнопками в ответе на сообщение
        // List<InlineKeyboardButton> rowInLine = new ArrayList<>(); // одна строка // сама строка клавиатуры


        *//*List<Chanell> chanellListByCities = chanellRepository.getChannelsWithCities();
        List<String> channelsCitiesName = new ArrayList<>();
        List<String> finalList = new ArrayList<>();
        chanellListByCities.stream().forEach(ch -> channelsCitiesName.add(ch.getCity()));
        finalList = channelsCitiesName.stream().distinct().sorted().collect(Collectors.toList());*//*

        List<String> finalList = new ArrayList<>();
        chanellRepository.getCitiesNames().stream().sorted().forEach(nameCity-> finalList.add(nameCity));


        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        for (int i = 0; i < finalList.size(); i = i +  3) {
            var button1 = new InlineKeyboardButton();
            var button2 = new InlineKeyboardButton();
            var button3 = new InlineKeyboardButton();
            rowInLine = new ArrayList<>();

            if(i < finalList.size()) {
                if (finalList.get(i)!= null && !finalList.get(i).isEmpty()){
                    button1.setText(finalList.get(i));
                    button1.setCallbackData(CITY + finalList.get(i));
                    rowInLine.add(button1);
                }
                if (i + 1 < finalList.size()) {
                    if (finalList.get(i + 1) != null && !finalList.get(i + 1).isEmpty()){
                        button2.setText(finalList.get(i + 1));
                        button2.setCallbackData(CITY + finalList.get(i + 1));
                        rowInLine.add(button2);
                    }
                    if (i + 2 < finalList.size()) {
                        if (finalList.get(i + 2) != null && !finalList.get(i + 2).isEmpty()){
                            button3.setText(finalList.get(i + 2));
                            button3.setCallbackData(CITY + finalList.get(i + 2));
                            rowInLine.add(button3);
                        }
                    }
                }
            }
            rowsInLine.add(rowInLine);
        }

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        executeMessage(message, name);
        log.info("Пользователь с имененем " + name + " получил список городов " );
    }*/

    // Найти города  - НОВЫЙ ВАРИАНТ, В КОТОРОМ ОТОБРАЗЯТСЯ ПЕРВЫЕ БУКВЫ ГОРОДОВ
    private void findCityNamesByFirstLetter(long chatId, String name) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Выберите первую букву города 🔠🔠⬇");

        // создание клавиатуры с кнопками в ответе на сообщение
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); //клавиаутра
        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры
        // создание списка с кнопками в ответе на сообщение
        List<InlineKeyboardButton> rowInLine = new ArrayList<>(); // одна строка // сама строка клавиатуры

        // старый вариант с поиском городов из колонки город в каналах
        /*List<String> finalList = new ArrayList<>();
        finalList = chanellRepository.getCitiesNames().stream().sorted().filter(str -> !str.isEmpty())
            .map(nameCity-> String.valueOf(nameCity.charAt(0)))
            .distinct().collect(Collectors.toList());*/

        // Новый вариант с городами из таблицы города
        List<String> finalList = new ArrayList<>();
        finalList =
            cityRepository
                .getCitiesNames()
                .stream()
                .sorted()
                .filter(str -> !str.isEmpty())
                .map(nameCity -> String.valueOf(nameCity.charAt(0)))
                .distinct()
                .collect(Collectors.toList());

        // МОЖНО ВПИСАТЬ - ПОЛУЧИТЬ СПИСОК ВСЕХ ГОРОДОВ

        rowInLine = new ArrayList<>();

        for (int i = 0; i < finalList.size(); i++) {
            if (rowInLine.size() == 5) {
                rowsInLine.add(rowInLine);
                rowInLine = new ArrayList<>();
            }

            var button = new InlineKeyboardButton();
            button.setText(finalList.get(i));
            button.setCallbackData(GORODA_FIRST_LETTER + ":" + finalList.get(i)); // ДЕЙСТВИЕ ПО Поиску всех городов на эту букву
            rowInLine.add(button);

            if (i == finalList.size() - 1) {
                rowsInLine.add(rowInLine);
                break;
            }
        }

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        executeMessage(message, name);
        log.info("Пользователь с имененем " + name + " получил список букв городов ");
    }

    private void chooseСity(long chatId, String name, String firstLetter) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Выберите город ⬇⬇⬇");

        // создание клавиатуры с кнопками в ответе на сообщение
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); //клавиаутра
        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры
        // создание списка с кнопками в ответе на сообщение
        // List<InlineKeyboardButton> rowInLine = new ArrayList<>(); // одна строка // сама строка клавиатуры

        // Старый вариант
        /*List<String> finalList = new ArrayList<>();
        finalList = chanellRepository.getCitiesByFirstLetter(firstLetter).stream().sorted()
            .distinct().collect(Collectors.toList());*/

        // Новый вариант
        List<City> finalList = new ArrayList<>();
        finalList =
            cityRepository
                .getCitiesByFirstLetter(firstLetter)
                .stream()
                .sorted(
                    (o1, o2) -> {
                        if (o1.getCityName().equals(o2.getCityName())) {
                            return 0;
                        }
                        return o1.getCityName().compareTo(o2.getCityName());
                    }
                )
                .distinct()
                .collect(Collectors.toList());

        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        for (int i = 0; i < finalList.size(); i = i + 2) {
            var button1 = new InlineKeyboardButton();
            var button2 = new InlineKeyboardButton();
            var button3 = new InlineKeyboardButton();
            rowInLine = new ArrayList<>();

            if (i < finalList.size()) {
                if (finalList.get(i) != null && finalList.get(i).getId() != null) {
                    button1.setText(finalList.get(i).getCityName());
                    button1.setCallbackData(CITY + ":" + finalList.get(i).getId());
                    rowInLine.add(button1);
                }
                if (i + 1 < finalList.size()) {
                    if (finalList.get(i + 1) != null && finalList.get(i + 1).getId() != null) {
                        button2.setText(finalList.get(i + 1).getCityName());
                        button2.setCallbackData(CITY + ":" + finalList.get(i + 1).getId());
                        rowInLine.add(button2);
                    }
                    /*if (i + 2 < finalList.size()) {
                        if (finalList.get(i + 2) != null && !finalList.get(i + 2).isEmpty()){
                            button3.setText(finalList.get(i + 2));
                            button3.setCallbackData(CITY + finalList.get(i + 2));
                            rowInLine.add(button3);
                        }
                    }*/
                }
            }
            rowsInLine.add(rowInLine);
        }

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        executeMessage(message, name);
        log.info("Пользователь с имененем " + name + " получил список городов ");
    }

    /*
    private void selectPriceDiapozonForGetChanneles(long chatId, String name, Long categoryId){

        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
//        message.setText("Выберите ценовой диапозон каналов");

        // создание клавиатуры с кнопками в ответе на сообщение
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); //клавиаутра
        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры

        Optional<Category> category = categoryRepository.findOneWithEagerRelationships(categoryId);
        Set<Double> doubleSet = new HashSet<>();
        if (category.isPresent()){
            for (Chanell chanell: category.get().getChanellIds()) {
                if (chanell.getPriceDiapozon() != null){
                    doubleSet.add(chanell.getPriceDiapozon()); // обработать сценарий с каналами, где отстутствет прайс
                }
            }
            message.setText("Выберите ценовой диапозон каналов в категории: \n" + category.get().getName() + " ⬇⬇⬇");
        }
        List<Double> doubleList = new ArrayList<>();
        for (Double  d: doubleSet) {
            doubleList.add(d);
        }

        Collections.sort(doubleList);
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        for (int i = 0; i < doubleList.size(); i = i +  3) {
            var button1 = new InlineKeyboardButton();
            var button2 = new InlineKeyboardButton();
            var button3 = new InlineKeyboardButton();
            rowInLine = new ArrayList<>();

            if(i < doubleList.size()) {
                button1.setText(doubleList.get(i).toString());
                button1.setCallbackData(PRICEDIAP + doubleList.get(i) + IDCAT + categoryId);
                rowInLine.add(button1);
                if (i + 1 < doubleList.size()) {
                    button2.setText(doubleList.get(i + 1).toString());
                    button2.setCallbackData(PRICEDIAP + doubleList.get(i + 1) + IDCAT + categoryId);
                    rowInLine.add(button2);
                    if (i + 2 < doubleList.size()) {
                        button3.setText(doubleList.get(i + 2).toString());
                        button3.setCallbackData(PRICEDIAP + doubleList.get(i + 2) + IDCAT + categoryId);
                        rowInLine.add(button3);
                    }
                }
            }
            rowsInLine.add(rowInLine);
        }

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        executeMessage(message, name);
        log.info("Пользователь с имененем " + name + " получил список категорий " );
    }
*/

    // Возвращает список каналов по категории кликабельными кнопками - СТАРЫЙ ВАРИАНТ С 3 кнопками
    /*private void getChanellByCategoryId(long chatId, String name, Long categoryId){

        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Выберите каналы");

        // создание клавиатуры с кнопками в ответе на сообщение
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); //клавиаутра
        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры

        Optional<Category> category = categoryRepository.findOneWithEagerRelationships(categoryId);
        List<Chanell> chanellList = new ArrayList<>();
        if (category.isPresent()){
            for (Chanell chanell: category.get().getChanellIds()) {
                chanellList.add(chanell);
            }

            chanellList.sort((Chanell o1, Chanell o2) -> {
                if(o1.getScore() != null && o2.getScore() != null){
                    return o1.getScore().compareTo(o2.getScore());
                }
                return o1.getName().compareTo(o2.getName());
            });
        }
        if (chanellList.isEmpty()){
            sendMessage(chatId, "Что-то пошло ни так😆 Попробуйте заново🤣", name);

        } else{
            List<InlineKeyboardButton> rowInLine = new ArrayList<>();

            for (int i = 0; i < chanellList.size(); i = i +  3) {
                var button1 = new InlineKeyboardButton();
                var button2 = new InlineKeyboardButton();
                var button3 = new InlineKeyboardButton();
                rowInLine = new ArrayList<>();

                if(i < chanellList.size()) {
                    button1.setText(chanellList.get(i).getName());
                    button1.setCallbackData(CHANNEL + chanellList.get(i).getId());
                    button1.setUrl(chanellList.get(i).getLink());
                    try {
                        button1.setUrl(chanellList.get(i).getLink());
                    } catch (Exception e){
                        log.info("При записи ссылки возникла ошибка == " + e.getMessage());
                        button1.setUrl("https://t.me/" + chanellList.get(i).getLink());
                    }
                    rowInLine.add(button1);
                    if (i + 1 < chanellList.size()) {
                        button2.setText(chanellList.get(i + 1).getName());
                        button2.setCallbackData(CHANNEL + chanellList.get(i + 1).getId());
                        try {
                            button2.setUrl(chanellList.get(i + 1).getLink());
                        } catch (Exception e){
                            log.info("При записи ссылки возникла ошибка == " + e.getMessage());
                            button2.setUrl("https://t.me/" + chanellList.get(i + 1).getLink());
                        }
                        rowInLine.add(button2);
                        if (i + 2 < chanellList.size()) {
                            button3.setText(chanellList.get(i + 2).getName());
                            button3.setCallbackData(CHANNEL + chanellList.get(i + 2).getId());
                            try {
                                button3.setUrl(chanellList.get(i + 2).getLink());
                            }catch (Exception e){
                                log.info("При записи ссылки возникла ошибка == " + e.getMessage());
                                button3.setUrl("https://t.me/" + chanellList.get(i + 2).getLink());
                            }
                            rowInLine.add(button3);
                        }
                    }
                }
                rowsInLine.add(rowInLine);
            }

            markupInLine.setKeyboard(rowsInLine);
            message.setReplyMarkup(markupInLine);

            executeMessage(message, name);
            log.info("Пользователь с имененем " + name + " получил список городов " );
        }
    }*/

    // Возвращает список каналов текстовым сообщением, подлежащим изменению
    private void getChanellByCategoryId(long chatId, String name, Long categoryId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Выберите каналы ⬇⬇⬇");

        Optional<Category> category = categoryRepository.findOneWithEagerRelationships(categoryId, ZonedDateTime.now().minusDays(1));
        List<Chanell> chanellList = new ArrayList<>();

        if (category.isPresent()) {
            for (Chanell chanell : category.get().getChanellIds()) {
                if (chanell.getIsModerate() != null && chanell.getIsModerate() == true) {
                    if ((chanell.getCity() == null || chanell.getCity().isEmpty())) {
                        chanellList.add(chanell);
                    }
                }
            }

            chanellList.sort(
                (Chanell o1, Chanell o2) -> {
                    if (o1.getScore() != null && o2.getScore() != null) {
                        return o1.getScore().compareTo(o2.getScore());
                    }
                    return o1.getName().compareTo(o2.getName());
                }
            );
        }

        if (chanellList.isEmpty()) {
            sendMessage(chatId, "Что-то пошло ни так😆 Попробуйте заново🤣", name);
        } else {
            List<String> allChanellsInformationList = new ArrayList<>();

            for (Chanell chanell : chanellList) {
                var chanString = "\n" + "Имя канала: " + chanell.getName() + " \n" + "Ссылка на канал: \n" + chanell.getLink() + "\n";
                allChanellsInformationList.add(chanString);
            }

            StringBuilder stringBuilder = new StringBuilder();

            //            Map<Integer, List<String>> mapInfoChannels = new LinkedHashMap<>();

            Map<Integer, List<String>> map = IntStream
                .range(0, allChanellsInformationList.size())
                .boxed()
                .collect(Collectors.groupingBy(i -> i / 3, Collectors.mapping(allChanellsInformationList::get, Collectors.toList())));

            //            allChanellsInformationList.stream().limit(3).forEach(str-> stringBuilder.append(str));
            map.get(0).forEach(str -> stringBuilder.append(str));
            message.setText(
                "Категория: " +
                category.get().getName() +
                "\n" +
                "Каналов в категории: " +
                chanellList.size() +
                "\n" +
                "Всего страниц с каналами: " +
                map.size() +
                "\n" +
                "Текущая страница: 1" +
                "\n" +
                "Выберите каналы: \n " +
                stringBuilder +
                " \n\nЕщё каналы ниже⬇⬇⬇"
            );

            message.disableWebPagePreview(); // Отключает отображение баннеров для перехода в канал

            // создание клавиатуры с кнопками в ответе на сообщение
            InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); //клавиаутра

            // создание списка со списками с кнопками в ответе на сообщение
            List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры

            List<InlineKeyboardButton> rowInLine = new ArrayList<>();

            var button1 = new InlineKeyboardButton();
            var button2 = new InlineKeyboardButton();
            rowInLine = new ArrayList<>();

            if (map.size() > 1) {
                button1.setText("стр.№ 1/" + map.size());
                button2.setText("▶");

                button1.setCallbackData(ANY_PAGE_IN_CAT + ":" + map.size() + ":" + categoryId);
                button2.setCallbackData(NEXT_PAGE_CAT + ":" + 1 + ":" + categoryId);

                rowInLine.add(button1);
                rowInLine.add(button2);
            } else if (map.size() == 1) {
                button1.setText("Выбрать другую категорию ✏");
                button1.setCallbackData(FIND_CHANNEL);
                rowInLine.add(button1);
                rowsInLine.add(rowInLine);

                var button3 = new InlineKeyboardButton();
                var searchCategory = new InlineKeyboardButton();
                rowInLine = new ArrayList<>();
                searchCategory.setText("Текстовый поиск категорий 🌍🌍🌍"); // Содержимое ответа в кнопке
                searchCategory.setSwitchInlineQueryCurrentChat(" ");
                rowInLine.add(searchCategory);
                rowsInLine.add(rowInLine);
                rowInLine = new ArrayList<>();
                button3.setText("Каналы по городам 🏘🏙");
                button3.setCallbackData(FIND_CITIES + ":");
                rowInLine.add(button3);
            }

            rowsInLine.add(rowInLine);

            markupInLine.setKeyboard(rowsInLine);
            message.setReplyMarkup(markupInLine);

            int msId = executeMessageReturnMessageID(message, name); // Отправка сообщения с текстами каналов

            log.info("Пользователь с имененем " + name + " получил список городов  + вернулся номер сообщения  = " + msId);
            // АУДИТ ПО НАЖАТИЮ НА КАТЕГОРИИ
            CategoryLog categoryLog = new CategoryLog();
            categoryLog.setChatId(chatId);
            categoryLog.setCatId(categoryId);
            categoryLog.setName(Optional.ofNullable(category.get().getName()).orElse(null));
            categoryLog.setScore(Optional.ofNullable(category.get().getScore()).orElse(null));
            categoryLog.setDateLog(ZonedDateTime.now().plusHours(3l));
            categoryLog.setTgBotApi(true);
            categoryLogRepository.save(categoryLog);
        }
    }

    // Возвращает список каналов текстовым сообщением, подлежащим изменению,
    // Приняв с кнопки NEXT_PAGE_CAT инфу о номере страницы и категории
    private void getChanellByCategoryIdAndPageNumber(long chatId, String name, Long categoryId, Long pageNumberInMap, long messageId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));

        Optional<Category> category = categoryRepository.findOneWithEagerRelationships(categoryId, ZonedDateTime.now().minusDays(1));
        List<Chanell> chanellList = new ArrayList<>();

        if (category.isPresent()) {
            for (Chanell chanell : category.get().getChanellIds()) {
                if (chanell.getIsModerate() == true) {
                    if ((chanell.getCity() == null || chanell.getCity().isEmpty())) {
                        chanellList.add(chanell);
                    }
                }
            }

            chanellList.sort(
                (Chanell o1, Chanell o2) -> {
                    if (o1.getScore() != null && o2.getScore() != null) {
                        return o1.getScore().compareTo(o2.getScore());
                    }
                    return o1.getName().compareTo(o2.getName());
                }
            );
        }

        if (chanellList.isEmpty()) {
            sendMessage(chatId, "Что-то пошло ни так😆 Попробуйте заново🤣", name);
        } else {
            List<String> allChanellsInformationList = new ArrayList<>();

            for (Chanell chanell : chanellList) {
                var chanString = "\n" + "Имя канала: " + chanell.getName() + " \n" + "Ссылка на канал: \n" + chanell.getLink() + "\n";
                allChanellsInformationList.add(chanString);
            }

            StringBuilder stringBuilder = new StringBuilder();

            Map<Integer, List<String>> map = IntStream
                .range(0, allChanellsInformationList.size())
                .boxed()
                .collect(Collectors.groupingBy(i -> i / 3, Collectors.mapping(allChanellsInformationList::get, Collectors.toList())));

            map.get(pageNumberInMap.intValue()).forEach(str -> stringBuilder.append(str));
            message.setText(
                "Категория: " +
                category.get().getName() +
                "\n" +
                "Каналов в категории: " +
                chanellList.size() +
                "\n" +
                "Всего страниц с каналами: " +
                map.size() +
                "\n" +
                "Текущая страница: " +
                (pageNumberInMap + 1) +
                "\n" +
                "Выберите каналы: \n " +
                stringBuilder +
                " \n\nЕщё каналы ниже⬇⬇⬇"
            );

            message.disableWebPagePreview(); // Отключает отображение баннеров для перехода в канал

            // создание клавиатуры с кнопками в ответе на сообщение
            InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); //клавиаутра

            // создание списка со списками с кнопками в ответе на сообщение
            List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры

            List<InlineKeyboardButton> rowInLine = new ArrayList<>();

            var button1 = new InlineKeyboardButton();
            var button2 = new InlineKeyboardButton();
            var button3 = new InlineKeyboardButton();
            rowInLine = new ArrayList<>();

            if ((map.size() - 1) > pageNumberInMap && pageNumberInMap != 0) {
                button1.setText("◀");
                button1.setCallbackData(NEXT_PAGE_CAT + ":" + (pageNumberInMap - 1) + ":" + categoryId);
                button2.setText("стр.№ " + (pageNumberInMap + 1) + "/" + map.size());
                button2.setCallbackData(ANY_PAGE_IN_CAT + ":" + map.size() + ":" + categoryId);
                button3.setText("▶");
                button3.setCallbackData(NEXT_PAGE_CAT + ":" + (pageNumberInMap + 1) + ":" + categoryId);

                rowInLine.add(button1);
                rowInLine.add(button2);
                rowInLine.add(button3);
            } else if (map.size() > pageNumberInMap && pageNumberInMap == 0) {
                button2.setText("стр.№ " + (pageNumberInMap + 1) + "/" + map.size());
                button2.setCallbackData(ANY_PAGE_IN_CAT + ":" + map.size() + ":" + categoryId);
                button3.setText("▶");
                button3.setCallbackData(NEXT_PAGE_CAT + ":" + (pageNumberInMap + 1) + ":" + categoryId);

                rowInLine.add(button2);
                rowInLine.add(button3);
            } else if ((map.size() - 1) == pageNumberInMap) {
                button1.setText("◀");
                button1.setCallbackData(NEXT_PAGE_CAT + ":" + (pageNumberInMap - 1) + ":" + categoryId);
                button2.setText("стр.№ " + (pageNumberInMap + 1) + "/" + map.size());
                button2.setCallbackData(ANY_PAGE_IN_CAT + ":" + map.size() + ":" + categoryId);

                rowInLine.add(button1);
                rowInLine.add(button2);
            }

            rowsInLine.add(rowInLine);

            markupInLine.setKeyboard(rowsInLine);
            message.setReplyMarkup(markupInLine);

            executeEditTextWithKeyBoardAndDisableWebPreview(chatId, name, message.getText(), messageId, markupInLine);
            log.info("Пользователь с имененем " + name + " получил список городов  + вернулся номер сообщения  = ");
        }
    }

    // Метод отдает все кнопки со страницами на эту категорию, плюс кнопку со всеми каналами в категории
    private void getAllPagesButtonsByCategoryIdAndPageNumber(long chatId, String name, Long categoryId, Long pageCount) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));

        //        Optional<Category> category = categoryRepository.findOneWithEagerRelationships(categoryId);
        CategoryWithCountChanellsDTO category = categoryRepository.findCategoryByIdWithoutCity(
            categoryId,
            ZonedDateTime.now().minusDays(1)
        );
        message.setText(
            "Категория: " +
            category.getName().toUpperCase() +
            "\nКаналов в категории: " +
            category.getCountChannelsInCategory() +
            "\n" +
            "Всего страниц: " +
            pageCount +
            "\n\n" +
            "Выберите одну из страниц ⬇⬇⬇ \nИли получите все  🔑🔑🔑 " +
            " \n\n"
        );

        message.disableWebPagePreview(); // Отключает отображение баннеров для перехода в канал

        // создание клавиатуры с кнопками в ответе на сообщение
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); //клавиаутра

        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры

        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        var button1 = new InlineKeyboardButton();
        rowInLine = new ArrayList<>();
        button1.setText("Все каналы " + category.getName().toUpperCase() + " 👁👁‍");
        button1.setCallbackData(ALL_LIST_CH + ":" + categoryId); // ЗАГЛУШКА НА ПОЛУЧЕНИЕ ВСЕХ КАНАЛОВ
        rowInLine.add(button1);

        rowsInLine.add(rowInLine);
        rowInLine = new ArrayList<>();

        for (int i = 1; i <= pageCount.intValue(); i++) {
            if (rowInLine.size() == 5) {
                rowsInLine.add(rowInLine);
                rowInLine = new ArrayList<>();
            }

            var button = new InlineKeyboardButton();
            button.setText("№" + i);
            button.setCallbackData(NEXT_PAGE_CAT + ":" + (i - 1) + ":" + categoryId); // ДЕЙСТВИЕ ПО ЗАМЕНЕ ТЕКУЩЕГО СООБЩЕНИЯ
            rowInLine.add(button);

            if (i == pageCount.intValue()) {
                rowsInLine.add(rowInLine);
                break;
            }
        }

        var searchCategory = new InlineKeyboardButton();
        var button = new InlineKeyboardButton();
        var button2 = new InlineKeyboardButton();

        rowInLine = new ArrayList<>();
        searchCategory.setText("Текстовый поиск категорий 🌍🌍🌍"); // Содержимое ответа в кнопке
        searchCategory.setSwitchInlineQueryCurrentChat(" ");
        rowInLine.add(searchCategory);
        rowsInLine.add(rowInLine);

        rowInLine = new ArrayList<>();
        button.setText("Каналы по категориям 👁‍👁‍");
        button.setCallbackData(FIND_CHANNEL + ":");
        rowInLine.add(button);
        rowsInLine.add(rowInLine);
        rowInLine = new ArrayList<>();
        button2.setText("Каналы по городам 🏘🏙");
        button2.setCallbackData(FIND_CITIES + ":");
        rowInLine.add(button2);
        rowsInLine.add(rowInLine);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        //        executeEditTextWithKeyBoardAndDisableWebPreview(chatId, name, message.getText(), messageId, markupInLine);
        executeMessageWithKeybord(name, chatId, message.getText(), markupInLine);
        log.info("Пользователь с имененем " + name + " получил список городов  + вернулся номер сообщения  = ");
    }

    private void getAllPagesButtonsByCategoryIdAndPageNumberByCity(long chatId, String name, Long categoryId, Long pageCount, Long cityId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        //        Optional<Category> category = categoryRepository.findOneWithEagerRelationships(categoryId);
        String cityName = cityRepository.findById(cityId).get().getCityName();

        //        CategoryWithCountChanellsDTO category = categoryRepository.findCategoryByIdWithCity(categoryId, cityName);
        CategoryWithCountChanellsDTO category = categoryRepository.findCategoryByIdWithCityId(
            categoryId,
            cityId,
            ZonedDateTime.now().minusDays(1)
        );

        message.setText(
            "Город: " +
            cityName +
            "\nКатегория: " +
            category.getName().toUpperCase() +
            "\nКаналов в категории: " +
            category.getCountChannelsInCategory() +
            "\n\n" +
            "Всего страниц: " +
            pageCount +
            "\n\n" +
            "Выберите одну из страниц ⬇⬇⬇ \nИли получите все каналы 🔑🔑🔑 " +
            " \n\n"
        );

        message.disableWebPagePreview(); // Отключает отображение баннеров для перехода в канал

        // создание клавиатуры с кнопками в ответе на сообщение
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); //клавиаутра

        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры

        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        var button1 = new InlineKeyboardButton();
        rowInLine = new ArrayList<>();
        button1.setText(cityName + ": " + category.getName() + " 👁👁‍");
        button1.setCallbackData(ALL_LIST_GOROD_CHANNEL + ":" + categoryId + ":" + cityId); // ЗАГЛУШКА НА ПОЛУЧЕНИЕ ВСЕХ КАНАЛОВ
        rowInLine.add(button1);

        rowsInLine.add(rowInLine);
        rowInLine = new ArrayList<>();

        for (int i = 1; i <= pageCount.intValue(); i++) {
            if (rowInLine.size() == 4) {
                rowsInLine.add(rowInLine);
                rowInLine = new ArrayList<>();
            }

            var button = new InlineKeyboardButton();
            button.setText("№" + i);
            button.setCallbackData(NEXT_PAGE_GOROD_CAT + ":" + (i - 1) + ":" + categoryId + ":" + cityId); // ДЕЙСТВИЕ ПО ЗАМЕНЕ ТЕКУЩЕГО СООБЩЕНИЯ
            rowInLine.add(button);

            if (i == pageCount.intValue()) {
                rowsInLine.add(rowInLine);
                break;
            }
        }

        var button = new InlineKeyboardButton();
        var button2 = new InlineKeyboardButton();
        InlineKeyboardButton searchCategory = new InlineKeyboardButton();
        rowInLine = new ArrayList<>();
        searchCategory.setText("Текстовый поиск категорий 🌍🌍🌍"); // Содержимое ответа в кнопке
        searchCategory.setSwitchInlineQueryCurrentChat(" ");
        rowInLine.add(searchCategory);
        rowsInLine.add(rowInLine);
        rowInLine = new ArrayList<>();
        button.setText("Каналы по категориям 👁‍👁‍");
        button.setCallbackData(FIND_CHANNEL + ":");
        rowInLine.add(button);
        rowsInLine.add(rowInLine);
        rowInLine = new ArrayList<>();

        button2.setText("Каналы по городам 🏘🏙");
        button2.setCallbackData(FIND_CITIES + ":");
        rowInLine.add(button2);
        rowsInLine.add(rowInLine);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        //        executeEditTextWithKeyBoardAndDisableWebPreview(chatId, name, message.getText(), messageId, markupInLine);
        executeMessageWithKeybord(name, chatId, message.getText(), markupInLine);
        log.info("Пользователь с имененем " + name + " получил список городов  + вернулся номер сообщения  = ");
    }

    private void getChanellByCategoryIdBigButtons(long chatId, String name, Long categoryId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Выберите каналы ⬇⬇⬇");

        // создание клавиатуры с кнопками в ответе на сообщение
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); //клавиаутра
        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры

        Optional<Category> category = categoryRepository.findOneWithEagerRelationships(categoryId, ZonedDateTime.now().minusDays(1));
        List<Chanell> chanellList = new ArrayList<>();
        if (category.isPresent()) {
            message.setText("Выберите каналы категории: " + category.get().getName().toUpperCase());
            for (Chanell chanell : category.get().getChanellIds()) {
                chanellList.add(chanell);
            }

            chanellList.sort(
                (Chanell o1, Chanell o2) -> {
                    if (o1.getScore() != null && o2.getScore() != null) {
                        return o1.getScore().compareTo(o2.getScore());
                    }
                    return o1.getName().compareTo(o2.getName());
                }
            );
        }
        if (chanellList.isEmpty()) {
            sendMessage(chatId, "Что-то пошло ни так😆 Попробуйте заново🤣", name);
        } else {
            List<InlineKeyboardButton> rowInLine = new ArrayList<>();

            for (int i = 0; i < chanellList.size(); i = i + 2) {
                var button1 = new InlineKeyboardButton();
                var button2 = new InlineKeyboardButton();
                rowInLine = new ArrayList<>();

                if (i < chanellList.size()) {
                    button1.setText(chanellList.get(i).getName());
                    button1.setCallbackData(CHANNEL + chanellList.get(i).getId());
                    button1.setUrl(chanellList.get(i).getLink());
                    try {
                        button1.setUrl(chanellList.get(i).getLink());
                    } catch (Exception e) {
                        log.info("При записи ссылки возникла ошибка == " + e.getMessage());
                        button1.setUrl("https://t.me/" + chanellList.get(i).getLink());
                    }
                    rowInLine.add(button1);
                    if (i + 1 < chanellList.size()) {
                        button2.setText(chanellList.get(i + 1).getName());
                        button2.setCallbackData(CHANNEL + chanellList.get(i + 1).getId());
                        try {
                            button2.setUrl(chanellList.get(i + 1).getLink());
                        } catch (Exception e) {
                            log.info("При записи ссылки возникла ошибка == " + e.getMessage());
                            button2.setUrl("https://t.me/" + chanellList.get(i + 1).getLink());
                        }
                        rowInLine.add(button2);
                    }
                }
                rowsInLine.add(rowInLine);
            }

            var button1 = new InlineKeyboardButton();
            var button2 = new InlineKeyboardButton();

            var searchCategory = new InlineKeyboardButton();
            rowInLine = new ArrayList<>();
            searchCategory.setText("Текстовый поиск категорий 🌍🌍🌍"); // Содержимое ответа в кнопке
            searchCategory.setSwitchInlineQueryCurrentChat(" ");
            rowInLine.add(searchCategory);
            rowsInLine.add(rowInLine);

            rowInLine = new ArrayList<>();
            button1.setText("Каналы по категориям 👁‍👁‍");
            button1.setCallbackData(FIND_CHANNEL + ":");
            rowInLine.add(button1);
            rowsInLine.add(rowInLine);
            rowInLine = new ArrayList<>();
            button2.setText("Каналы по городам 🏘🏙");
            button2.setCallbackData(FIND_CITIES + ":");
            rowInLine.add(button2);
            rowsInLine.add(rowInLine);

            markupInLine.setKeyboard(rowsInLine);
            message.setReplyMarkup(markupInLine);

            executeMessage(message, name);
            log.info("Пользователь с имененем " + name + " получил список городов ");
        }
    }

    private void getChanellByCategoryIdByCityBigButtons(long chatId, String name, Long categoryId, Long cityId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        //        message.setText(cityName + ": каналы "  + + "⬇⬇⬇");

        // создание клавиатуры с кнопками в ответе на сообщение
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); //клавиаутра
        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры

        String cityName = cityRepository.findById(cityId).get().getCityName();

        //        Optional<Category> category = categoryRepository.findOneWithEagerRelationships(categoryId, cityName);
        Optional<Category> category = categoryRepository.findOneWithEagerRelationshipsWithCityId(
            categoryId,
            cityId,
            ZonedDateTime.now().minusDays(1)
        );
        List<Chanell> chanellList = new ArrayList<>();
        if (category.isPresent()) {
            message.setText(cityName + ": " + category.get().getName().toUpperCase() + "⬇⬇⬇");
            for (Chanell chanell : category.get().getChanellIds()) {
                chanellList.add(chanell);
            }

            chanellList.sort(
                (Chanell o1, Chanell o2) -> {
                    if (o1.getScore() != null && o2.getScore() != null) {
                        return o1.getScore().compareTo(o2.getScore());
                    }
                    return o1.getName().compareTo(o2.getName());
                }
            );
        }
        if (chanellList.isEmpty()) {
            sendMessage(chatId, "Что-то пошло ни так😆 Попробуйте заново🤣", name);
        } else {
            List<InlineKeyboardButton> rowInLine = new ArrayList<>();

            for (int i = 0; i < chanellList.size(); i = i + 2) {
                var button1 = new InlineKeyboardButton();
                var button2 = new InlineKeyboardButton();
                rowInLine = new ArrayList<>();

                if (i < chanellList.size()) {
                    button1.setText(chanellList.get(i).getName());
                    button1.setCallbackData(CHANNEL + chanellList.get(i).getId());
                    button1.setUrl(chanellList.get(i).getLink());
                    try {
                        button1.setUrl(chanellList.get(i).getLink());
                    } catch (Exception e) {
                        log.info("При записи ссылки возникла ошибка == " + e.getMessage());
                        button1.setUrl("https://t.me/" + chanellList.get(i).getLink());
                    }
                    rowInLine.add(button1);
                    if (i + 1 < chanellList.size()) {
                        button2.setText(chanellList.get(i + 1).getName());
                        button2.setCallbackData(CHANNEL + chanellList.get(i + 1).getId());
                        try {
                            button2.setUrl(chanellList.get(i + 1).getLink());
                        } catch (Exception e) {
                            log.info("При записи ссылки возникла ошибка == " + e.getMessage());
                            button2.setUrl("https://t.me/" + chanellList.get(i + 1).getLink());
                        }
                        rowInLine.add(button2);
                    }
                }
                rowsInLine.add(rowInLine);
            }

            var button1 = new InlineKeyboardButton();
            var button2 = new InlineKeyboardButton();
            rowInLine = new ArrayList<>();
            button1.setText("Каналы по категориям 👁‍👁‍");
            button1.setCallbackData(FIND_CHANNEL + ":");
            rowInLine.add(button1);
            rowsInLine.add(rowInLine);
            rowInLine = new ArrayList<>();
            button2.setText("Каналы по городам 🏘🏙");
            button2.setCallbackData(FIND_CITIES + ":");
            rowInLine.add(button2);
            rowsInLine.add(rowInLine);

            markupInLine.setKeyboard(rowsInLine);
            message.setReplyMarkup(markupInLine);

            executeMessage(message, name);
            log.info("Пользователь с имененем " + name + " получил список городов ");
        }
    }

    private void getChanellByCityNameByCategoryId(long chatId, String name, Long cityId, Long categoryId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));

        // создание клавиатуры с кнопками в ответе на сообщение
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); //клавиаутра
        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры

        String nameCity = cityRepository.findById(cityId).get().getCityName();

        CategoryNameAndIdDTO category = categoryRepository.findCategoryById(categoryId);

        /*List<Chanell> chanellList = chanellRepository.findChannelsByCityAndCategory(nameCity, categoryId)
            .stream().filter(c -> c.getScore() != null)
            .sorted(Comparator.comparing(Chanell::getScore))
            .collect(Collectors.toList());*/
        List<Chanell> chanellList = chanellRepository
            .findChannelsByCityIDAndCategory(cityId, categoryId, ZonedDateTime.now().minusDays(1))
            .stream()
            .filter(c -> c.getScore() != null)
            .sorted(Comparator.comparing(Chanell::getScore))
            .collect(Collectors.toList());

        if (chanellList.isEmpty()) {
            sendMessage(chatId, "Что-то пошло ни так😆 Попробуйте заново🤣", name);
        } else {
            List<String> allChanellsInformationList = new ArrayList<>();
            for (Chanell chanell : chanellList) {
                var chanString = "\n" + "Имя канала: " + chanell.getName() + " \n" + "Ссылка на канал: \n" + chanell.getLink() + "\n";
                allChanellsInformationList.add(chanString);
            }

            StringBuilder stringBuilder = new StringBuilder();
            Map<Integer, List<String>> map = IntStream
                .range(0, allChanellsInformationList.size())
                .boxed()
                .collect(Collectors.groupingBy(i -> i / 3, Collectors.mapping(allChanellsInformationList::get, Collectors.toList())));

            map.get(0).forEach(str -> stringBuilder.append(str));
            message.setText(
                "Город: " +
                nameCity +
                "\n" +
                "Категория: " +
                category.getName() +
                "\n" +
                "Каналов в категории: " +
                chanellList.size() +
                "\n" +
                "Всего страниц с каналами: " +
                map.size() +
                "\n" +
                "Текущая страница: 1" +
                "\n" +
                "Выберите каналы: \n " +
                stringBuilder +
                " \n\nЕщё каналы ниже⬇⬇⬇"
            );

            message.disableWebPagePreview(); // Отключает отображение баннеров для перехода в канал

            List<InlineKeyboardButton> rowInLine = new ArrayList<>();

            var button1 = new InlineKeyboardButton();
            var button2 = new InlineKeyboardButton();
            InlineKeyboardButton button3 = new InlineKeyboardButton();
            InlineKeyboardButton searchCategory = new InlineKeyboardButton();
            rowInLine = new ArrayList<>();

            if (map.size() > 1) {
                button1.setText("стр.№ 1/" + map.size());
                button2.setText("▶");

                button1.setCallbackData(ANY_PAGE_GOROD_IN_CAT + ":" + map.size() + ":" + categoryId + ":" + cityId);
                button2.setCallbackData(NEXT_PAGE_GOROD_CAT + ":" + 1 + ":" + categoryId + ":" + cityId);

                rowInLine.add(button1);
                rowInLine.add(button2);
            } else if (map.size() == 1) {
                button1.setText(nameCity + ": другие категории " + " ✏");
                button1.setCallbackData(CITY + ":" + cityId);
                rowInLine.add(button1);
                rowsInLine.add(rowInLine);

                rowInLine = new ArrayList<>();
                searchCategory.setText("Текстовый поиск категорий 🌍🌍🌍"); // Содержимое ответа в кнопке
                searchCategory.setSwitchInlineQueryCurrentChat(" ");
                rowInLine.add(searchCategory);

                rowInLine = new ArrayList<>();
                button2.setText("Каналы по категориям 👁‍👁‍");
                button2.setCallbackData(FIND_CHANNEL + ":");
                rowInLine.add(button2);
                rowsInLine.add(rowInLine);
                rowInLine = new ArrayList<>();

                button3.setText("Каналы по городам 🏘🏙");
                button3.setCallbackData(FIND_CITIES + ":");
                rowInLine.add(button3);
                //                rowsInLine.add(rowInLine);

            }

            rowsInLine.add(rowInLine);

            markupInLine.setKeyboard(rowsInLine);
            message.setReplyMarkup(markupInLine);

            int msId = executeMessageReturnMessageID(message, name); // Отправка сообщения с текстами каналов

            log.info("Пользователь с имененем " + name + " получил список городов  + вернулся номер сообщения  = " + msId);
            // АУДИТ ПО НАЖАТИЮ НА КАТЕГОРИИ
            CategoryLog categoryLog = new CategoryLog();
            categoryLog.setChatId(chatId);
            categoryLog.setCatId(categoryId);
            categoryLog.setName(category.getName());
            categoryLog.setScore(category.getScore());
            categoryLog.setDateLog(ZonedDateTime.now().plusHours(3l));
            categoryLog.setCityName(nameCity);
            categoryLog.setCityId(cityId);
            categoryLog.setTgBotApi(true);
            categoryLogRepository.save(categoryLog);
        }
    }

    // Возвращает список каналов текстовым сообщением, подлежащим изменению,
    // Приняв с кнопки NEXT_GOROD_PAGE_CAT инфу о номере страницы и категории
    private void getChanellByCityNameByCategoryIdAndPageNumber(
        long chatId,
        String name,
        Long cityId,
        Long categoryId,
        Long pageNumberInMap,
        long messageId
    ) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));

        // создание клавиатуры с кнопками в ответе на сообщение
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); //клавиаутра
        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры

        CategoryNameAndIdDTO category = categoryRepository.findCategoryById(categoryId);

        String nameCity = cityRepository.findById(cityId).get().getCityName();

        /*List<Chanell> chanellList = chanellRepository.findChannelsByCityAndCategory(nameCity, categoryId)
            .stream().filter(c -> c.getScore() != null)
            .sorted(Comparator.comparing(Chanell::getScore))
            .collect(Collectors.toList());*/

        List<Chanell> chanellList = chanellRepository
            .findChannelsByCityIDAndCategory(cityId, categoryId, ZonedDateTime.now().minusDays(1))
            .stream()
            .filter(c -> c.getScore() != null)
            .sorted(Comparator.comparing(Chanell::getScore))
            .collect(Collectors.toList());

        if (chanellList.isEmpty()) {
            sendMessage(chatId, "Что-то пошло ни так😆 Попробуйте заново🤣", name);
        } else {
            List<String> allChanellsInformationList = new ArrayList<>();

            for (Chanell chanell : chanellList) {
                var chanString = "\n" + "Имя канала: " + chanell.getName() + " \n" + "Ссылка на канал: \n" + chanell.getLink() + "\n";
                allChanellsInformationList.add(chanString);
            }

            StringBuilder stringBuilder = new StringBuilder();

            Map<Integer, List<String>> map = IntStream
                .range(0, allChanellsInformationList.size())
                .boxed()
                .collect(Collectors.groupingBy(i -> i / 3, Collectors.mapping(allChanellsInformationList::get, Collectors.toList())));

            map.get(pageNumberInMap.intValue()).forEach(str -> stringBuilder.append(str));
            message.setText(
                "Город: " +
                nameCity +
                "\n" +
                "Категория: " +
                category.getName() +
                "\n" +
                "Каналов в категории: " +
                chanellList.size() +
                "\n" +
                "Всего страниц с каналами: " +
                map.size() +
                "\n" +
                "Текущая страница: " +
                (pageNumberInMap + 1) +
                "\n" +
                "Выберите каналы: \n " +
                stringBuilder +
                " \n\nЕщё каналы ниже⬇⬇⬇"
            );

            message.disableWebPagePreview(); // Отключает отображение баннеров для перехода в канал

            List<InlineKeyboardButton> rowInLine = new ArrayList<>();

            var button1 = new InlineKeyboardButton();
            var button2 = new InlineKeyboardButton();
            var button3 = new InlineKeyboardButton();
            rowInLine = new ArrayList<>();

            if ((map.size() - 1) > pageNumberInMap && pageNumberInMap != 0) {
                button1.setText("◀");
                button1.setCallbackData(NEXT_PAGE_GOROD_CAT + ":" + (pageNumberInMap - 1) + ":" + categoryId + ":" + cityId);

                button2.setText("стр.№ " + (pageNumberInMap + 1) + "/" + map.size());
                button2.setCallbackData(ANY_PAGE_GOROD_IN_CAT + ":" + map.size() + ":" + categoryId + ":" + cityId);
                button3.setText("▶");
                button3.setCallbackData(NEXT_PAGE_GOROD_CAT + ":" + (pageNumberInMap + 1) + ":" + categoryId + ":" + cityId);

                rowInLine.add(button1);
                rowInLine.add(button2);
                rowInLine.add(button3);
            } else if (map.size() > pageNumberInMap && pageNumberInMap == 0) {
                button2.setText("стр.№ " + (pageNumberInMap + 1) + "/" + map.size());
                button2.setCallbackData(ANY_PAGE_GOROD_IN_CAT + ":" + map.size() + ":" + categoryId + ":" + cityId);
                button3.setText("▶");
                button3.setCallbackData(NEXT_PAGE_GOROD_CAT + ":" + (pageNumberInMap + 1) + ":" + categoryId + ":" + cityId);

                rowInLine.add(button2);
                rowInLine.add(button3);
            } else if ((map.size() - 1) == pageNumberInMap) {
                button1.setText("◀");
                button1.setCallbackData(NEXT_PAGE_GOROD_CAT + ":" + (pageNumberInMap - 1) + ":" + categoryId + ":" + cityId);

                button2.setText("стр.№ " + (pageNumberInMap + 1) + "/" + map.size());
                button2.setCallbackData(ANY_PAGE_GOROD_IN_CAT + ":" + map.size() + ":" + categoryId + ":" + cityId);

                rowInLine.add(button1);
                rowInLine.add(button2);
            }

            rowsInLine.add(rowInLine);

            markupInLine.setKeyboard(rowsInLine);
            message.setReplyMarkup(markupInLine);

            executeEditTextWithKeyBoardAndDisableWebPreview(chatId, name, message.getText(), messageId, markupInLine);
            log.info("Пользователь с имененем " + name + " получил список городов  + вернулся номер сообщения  = ");
        }
    }

    // Список категорий по имени города - версия с пролистыванием страниц, первая страница
    private void getCategoriesByCityNameFirstPage(long chatId, String name, Long cityId, boolean isEditMessage, long messageId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));

        String nameCity = cityRepository.findById(cityId).get().getCityName();

        message.setText("Выберите категории по городу - " + nameCity + " ⬇⬇⬇");

        // создание клавиатуры с кнопками в ответе на сообщение
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); //клавиаутра
        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры

        /*List<CategoryNameAndIdDTO> categoryListFromDB = categoryRepository
            .findCategoriesByCity(nameCity);*/

        List<CategoryNameAndIdDTO> categoryListFromDB = categoryRepository.findCategoriesByCityId(cityId, ZonedDateTime.now().minusDays(1));

        List<CategoryNameAndIdDTO> categoriesFirst = categoryListFromDB
            .stream()
            .filter(c -> c.getIsFirst().equals(true))
            .sorted(Comparator.comparing(CategoryNameAndIdDTO::getScore))
            .collect(Collectors.toList());

        // ВСТАВИЛ НА СЛУЧАЙ, КОГДА НЕТ ФЁРСТ КАТЕГОРИЙ
        List<CategoryNameAndIdDTO> categoryNotFist = categoryListFromDB
            .stream()
            .filter(cat -> cat.getIsFirst().equals(false))
            .sorted(Comparator.comparing(CategoryNameAndIdDTO::getScore))
            .collect(Collectors.toList());

        Map<Integer, List<CategoryNameAndIdDTO>> groupedCategories = IntStream
            .range(0, categoryNotFist.size())
            .boxed()
            .collect(Collectors.groupingBy(i -> i / 10, Collectors.mapping(categoryNotFist::get, Collectors.toList())));

        List<CategoryNameAndIdDTO> categoriesToShow = categoriesFirst.size() > 0
            ? categoriesFirst
            : groupedCategories.size() > 0 ? groupedCategories.get(0) : new ArrayList<>();

        if (categoriesToShow.isEmpty()) {
            sendMessage(chatId, "Что-то пошло ни так😆 Попробуйте заново🤣", name);
        } else {
            List<InlineKeyboardButton> rowInLine = new ArrayList<>();

            for (int i = 0; i < categoriesToShow.size(); i = i + 2) {
                var button1 = new InlineKeyboardButton();
                var button2 = new InlineKeyboardButton();
                rowInLine = new ArrayList<>();

                if (i < categoriesToShow.size()) {
                    button1.setText(categoriesToShow.get(i).getName());
                    button1.setCallbackData(TEMA_GOROD + ":" + cityId + ":" + categoriesToShow.get(i).getId());
                    rowInLine.add(button1);
                    if (i + 1 < categoriesToShow.size()) {
                        button2.setText(categoriesToShow.get(i + 1).getName());
                        button2.setCallbackData(TEMA_GOROD + ":" + cityId + ":" + categoriesToShow.get(i + 1).getId());
                        rowInLine.add(button2);
                    }
                }
                rowsInLine.add(rowInLine);
            }

            if (categoriesFirst.size() > 0 && categoryNotFist.size() > 0) { //Базовый сценарий
                var allCategoriesPages = new InlineKeyboardButton();
                var nextPage = new InlineKeyboardButton();
                //                allCategoriesPages.setText("стр.№ 1/" + ( 1 + ((int) Math.ceil((categoryListFromDB.size() - categoriesFirst.size())/10.0))));
                allCategoriesPages.setText("стр.№ 1/" + ((categoriesFirst.size() > 0 ? 1 : 0) + groupedCategories.size()));
                nextPage.setText("▶");
                allCategoriesPages.setCallbackData(All_PAGES_TEMATICS_FOR_CITY + ":" + cityId); // написать потом
                nextPage.setCallbackData(NEXT_PAGE_WITH_TEMATICS_FOR_CITY + ":" + 2 + ":" + cityId + ":" + 0);
                rowInLine = new ArrayList<>();
                rowInLine.add(allCategoriesPages);
                rowInLine.add(nextPage);
                rowsInLine.add(rowInLine);
            } else if (categoriesFirst.size() > 0 && categoryNotFist.size() == 0) { // когда есть только первичные категории
                var button1 = new InlineKeyboardButton();
                var button2 = new InlineKeyboardButton();
                var button3 = new InlineKeyboardButton();
                var searchCategory = new InlineKeyboardButton();

                /*rowInLine = new ArrayList<>();
                button1.setText(nameCity + ": другие категории " + " ✏");
                button1.setCallbackData(CITY + ":" + nameCity);
                rowInLine.add(button1);
                rowsInLine.add(rowInLine);*/
                rowInLine = new ArrayList<>();
                searchCategory.setText("Текстовый поиск категорий 🌍🌍🌍"); // Содержимое ответа в кнопке
                searchCategory.setSwitchInlineQueryCurrentChat(" ");
                rowInLine.add(searchCategory);
                rowsInLine.add(rowInLine);

                rowInLine = new ArrayList<>();
                button2.setText("Каналы по категориям 👁‍👁‍");
                button2.setCallbackData(FIND_CHANNEL + ":");
                rowInLine.add(button2);
                rowsInLine.add(rowInLine);
                rowInLine = new ArrayList<>();
                button3.setText("Каналы по городам 🏘🏙");
                button3.setCallbackData(FIND_CITIES + ":");
                rowInLine.add(button3);
                rowsInLine.add(rowInLine);
            } else if (categoriesFirst.size() == 0 && groupedCategories.size() > 1) {
                var allCategoriesPages = new InlineKeyboardButton();
                var nextPage = new InlineKeyboardButton();
                //                allCategoriesPages.setText("стр.№ 1/" + ( 1 + ((int) Math.ceil((categoryListFromDB.size() - categoriesFirst.size())/10.0))));
                allCategoriesPages.setText("стр.№ 1/" + ((categoriesFirst.size() > 0 ? 1 : 0) + groupedCategories.size()));
                nextPage.setText("▶");
                allCategoriesPages.setCallbackData(All_PAGES_TEMATICS_FOR_CITY + ":" + cityId); // написать потом
                nextPage.setCallbackData(NEXT_PAGE_WITH_TEMATICS_FOR_CITY + ":" + 2 + ":" + cityId + ":" + 1);
                rowInLine = new ArrayList<>();
                rowInLine.add(allCategoriesPages);
                rowInLine.add(nextPage);
                rowsInLine.add(rowInLine);
            } else if (categoriesFirst.size() == 0 && groupedCategories.size() == 1) {
                //                var button1 = new InlineKeyboardButton();
                var button2 = new InlineKeyboardButton();
                var button3 = new InlineKeyboardButton();
                var searchCategory = new InlineKeyboardButton();

                /*rowInLine = new ArrayList<>();
                button1.setText(nameCity + ": другие категории " + " ✏");
                button1.setCallbackData(CITY + ":" + nameCity);
                rowInLine.add(button1);
                rowsInLine.add(rowInLine);*/
                rowInLine = new ArrayList<>();
                searchCategory.setText("Текстовый поиск категорий 🌍🌍🌍"); // Содержимое ответа в кнопке
                searchCategory.setSwitchInlineQueryCurrentChat(" ");
                rowInLine.add(searchCategory);
                rowsInLine.add(rowInLine);

                rowInLine = new ArrayList<>();
                button2.setText("Каналы по категориям 👁‍👁‍");
                button2.setCallbackData(FIND_CHANNEL + ":");
                rowInLine.add(button2);
                rowsInLine.add(rowInLine);
                rowInLine = new ArrayList<>();
                button3.setText("Каналы по городам 🏘🏙");
                button3.setCallbackData(FIND_CITIES + ":");
                rowInLine.add(button3);
                rowsInLine.add(rowInLine);
            }

            markupInLine.setKeyboard(rowsInLine);
            message.setReplyMarkup(markupInLine);

            if (isEditMessage == true) {
                executeEditTextWithKeyBoardAndDisableWebPreview(chatId, name, message.getText(), messageId, markupInLine);
            } else {
                executeMessage(message, name);
            }
            log.info("Пользователь с имененем " + name + " получил список категорий ");
        }
    }

    // Список категорий по имени города - версия с пролистыванием страниц, следущие страницы
    private void getCategoriesByCityNameNextPage(
        long chatId,
        String name,
        Long cityId,
        Integer pageNumber,
        long messageId,
        Integer numberInMap
    ) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));

        String nameCity = cityRepository.findById(cityId).get().getCityName();
        message.setText("Выберите категории по городу - " + nameCity + " ⬇⬇⬇");

        // создание клавиатуры с кнопками в ответе на сообщение
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); //клавиаутра
        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры

        List<CategoryNameAndIdDTO> categoryListFromDB = categoryRepository.findCategoriesByCityId(cityId, ZonedDateTime.now().minusDays(1));

        List<CategoryNameAndIdDTO> categoryListFirst = categoryListFromDB
            .stream()
            .filter(cat -> cat.getIsFirst().equals(true))
            .sorted(Comparator.comparing(CategoryNameAndIdDTO::getScore))
            .collect(Collectors.toList());

        List<CategoryNameAndIdDTO> categoryListNotFirst = categoryListFromDB
            .stream()
            .filter(cat -> cat.getIsFirst().equals(false))
            .sorted(Comparator.comparing(CategoryNameAndIdDTO::getScore))
            .collect(Collectors.toList());

        Map<Integer, List<CategoryNameAndIdDTO>> groupedCategories = IntStream
            .range(0, categoryListNotFirst.size())
            .boxed()
            .collect(Collectors.groupingBy(i -> i / 10, Collectors.mapping(categoryListNotFirst::get, Collectors.toList())));

        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        int totalCountPages = ((categoryListFirst.size() > 0 ? 1 : 0) + groupedCategories.size());

        for (int i = 0; i < groupedCategories.get(pageNumber - 2).size(); i = i + 2) {
            var button1 = new InlineKeyboardButton();
            var button2 = new InlineKeyboardButton();
            rowInLine = new ArrayList<>();

            if (i < groupedCategories.get(numberInMap).size()) {
                button1.setText(groupedCategories.get(numberInMap).get(i).getName());
                button1.setCallbackData(TEMA_GOROD + ":" + cityId + ":" + groupedCategories.get(numberInMap).get(i).getId());
                rowInLine.add(button1);
                if (i + 1 < groupedCategories.get(numberInMap).size()) {
                    button2.setText(groupedCategories.get(numberInMap).get(i + 1).getName());
                    button2.setCallbackData(TEMA_GOROD + ":" + cityId + ":" + groupedCategories.get(numberInMap).get(i + 1).getId());
                    rowInLine.add(button2);
                }
            }
            rowsInLine.add(rowInLine);
        }

        if (!pageNumber.equals(totalCountPages) && pageNumber != 2) {
            var allCategoriesPages = new InlineKeyboardButton();
            var nextPage = new InlineKeyboardButton();
            var oldPage = new InlineKeyboardButton();
            oldPage.setText("◀");
            allCategoriesPages.setText("стр.№ " + pageNumber + "/" + totalCountPages);
            nextPage.setText("▶");
            oldPage.setCallbackData(NEXT_PAGE_WITH_TEMATICS_FOR_CITY + ":" + (pageNumber - 1) + ":" + cityId + ":" + (numberInMap - 1));
            allCategoriesPages.setCallbackData(All_PAGES_TEMATICS_FOR_CITY + ":" + nameCity); // написать потом
            nextPage.setCallbackData(NEXT_PAGE_WITH_TEMATICS_FOR_CITY + ":" + (pageNumber + 1) + ":" + cityId + ":" + (numberInMap + 1));
            rowInLine = new ArrayList<>();
            rowInLine.add(oldPage);
            rowInLine.add(allCategoriesPages);
            rowInLine.add(nextPage);
            rowsInLine.add(rowInLine);
        } else if (pageNumber.equals(totalCountPages) && pageNumber == 2) {
            var allCategoriesPages = new InlineKeyboardButton();
            var oldPage = new InlineKeyboardButton();
            oldPage.setText("◀");
            allCategoriesPages.setText("стр.№ " + pageNumber + "/" + totalCountPages);
            oldPage.setCallbackData(UPDATE_GOROD_MESSAGE + ":" + cityId);
            allCategoriesPages.setCallbackData(All_PAGES_TEMATICS_FOR_CITY + ":" + cityId); // написать потом
            rowInLine = new ArrayList<>();
            rowInLine.add(oldPage);
            rowInLine.add(allCategoriesPages);
            rowsInLine.add(rowInLine);
        } else if (pageNumber.equals(totalCountPages) && pageNumber != 2) {
            var allCategoriesPages = new InlineKeyboardButton();
            var oldPage = new InlineKeyboardButton();
            oldPage.setText("◀");
            allCategoriesPages.setText("стр.№ " + pageNumber + "/" + totalCountPages);
            oldPage.setCallbackData(NEXT_PAGE_WITH_TEMATICS_FOR_CITY + ":" + (pageNumber - 1) + ":" + cityId + ":" + (numberInMap - 1));
            allCategoriesPages.setCallbackData(All_PAGES_TEMATICS_FOR_CITY + ":" + cityId); // написать потом
            rowInLine = new ArrayList<>();
            rowInLine.add(oldPage);
            rowInLine.add(allCategoriesPages);
            rowsInLine.add(rowInLine);
        } else if (!pageNumber.equals(totalCountPages) && pageNumber == 2) {
            var allCategoriesPages = new InlineKeyboardButton();
            var nextPage = new InlineKeyboardButton();
            var oldPage = new InlineKeyboardButton();
            oldPage.setText("◀");
            allCategoriesPages.setText("стр.№ " + pageNumber + "/" + totalCountPages);
            nextPage.setText("▶");
            oldPage.setCallbackData(UPDATE_GOROD_MESSAGE + ":" + cityId);
            allCategoriesPages.setCallbackData(All_PAGES_TEMATICS_FOR_CITY + ":" + cityId); // написать потом
            nextPage.setCallbackData(NEXT_PAGE_WITH_TEMATICS_FOR_CITY + ":" + (pageNumber + 1) + ":" + cityId + ":" + (numberInMap + 1));
            rowInLine = new ArrayList<>();
            rowInLine.add(oldPage);
            rowInLine.add(allCategoriesPages);
            rowInLine.add(nextPage);
            rowsInLine.add(rowInLine);
        }

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        executeEditTextWithKeyBoardAndDisableWebPreview(chatId, name, message.getText(), messageId, markupInLine);
        log.info("Пользователь с имененем " + name + " получил список страниц категорий ");
    }

    /*// Список категорий по имени города - старая версия
    private void getCategoriesByCityName(long chatId, String name, String nameCity){

        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Выберите категории по городу - " + nameCity + " ⬇⬇⬇");

        // создание клавиатуры с кнопками в ответе на сообщение
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); //клавиаутра
        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры

        List<CategoryNameAndIdDTO> categories = categoryRepository.findCategoriesByCity(nameCity);

        if (categories.isEmpty()){
            sendMessage(chatId, "Что-то пошло ни так😆 Попробуйте заново🤣", name);

        } else{
            List<InlineKeyboardButton> rowInLine = new ArrayList<>();

            for (int i = 0; i < categories.size(); i = i +  3) {
                var button1 = new InlineKeyboardButton();
                var button2 = new InlineKeyboardButton();
                var button3 = new InlineKeyboardButton();
                rowInLine = new ArrayList<>();

                if(i < categories.size()) {
                    button1.setText(categories.get(i).getName());
                    button1.setCallbackData(TEMA_GOROD + ":" + nameCity + ":" + categories.get(i).getId());
                    rowInLine.add(button1);
                    if (i + 1 < categories.size()) {
                        button2.setText(categories.get(i + 1).getName());
                        button2.setCallbackData(TEMA_GOROD + ":" + nameCity + ":" + categories.get(i + 1).getId());
                        rowInLine.add(button2);
                        if (i + 2 < categories.size()) {
                            button3.setText(categories.get(i + 2).getName());
                            button3.setCallbackData(TEMA_GOROD + ":" + nameCity + ":" + categories.get(i + 2).getId());
                            rowInLine.add(button3);
                        }
                    }
                }
                rowsInLine.add(rowInLine);
            }

            markupInLine.setKeyboard(rowsInLine);
            message.setReplyMarkup(markupInLine);

            executeMessage(message, name);
            log.info("Пользователь с имененем " + name + " получил список категорий " );
        }
    }*/
    /*
    private void getChanellByPriceDiapozon(long chatId, String name, Double price, Long categoryId){

        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Выберите каналы");

        // создание клавиатуры с кнопками в ответе на сообщение
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); //клавиаутра
        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры

        Optional<Category> category = categoryRepository.findOneWithEagerRelationships(categoryId);
        List<Chanell> chanellList = new ArrayList<>();
        if (category.isPresent()){
            for (Chanell chanell: category.get().getChanellIds()) {
                if(chanell.getPriceDiapozon() != null){
                    if (chanell.getPriceDiapozon().equals(price)){
                        chanellList.add(chanell);
                    }
                }
            }
        }
        if (chanellList.isEmpty()){
            sendMessage(chatId, "Что-то пошло ни так😆 Попробуйте заново🤣", name);

        } else{
            List<InlineKeyboardButton> rowInLine = new ArrayList<>();

            for (int i = 0; i < chanellList.size(); i = i +  3) {
                var button1 = new InlineKeyboardButton();
                var button2 = new InlineKeyboardButton();
                var button3 = new InlineKeyboardButton();
                rowInLine = new ArrayList<>();

                if(i < chanellList.size()) {
                    button1.setText(chanellList.get(i).getName());
                    button1.setCallbackData(CHANNEL + chanellList.get(i).getId());
                    button1.setUrl(chanellList.get(i).getLink());
                    try {
                        button1.setUrl(chanellList.get(i).getLink());
                    } catch (Exception e){
                        log.info("При записи ссылки возникла ошибка == " + e.getMessage());
                        button1.setUrl("https://t.me/" + chanellList.get(i).getLink());
                    }
                    rowInLine.add(button1);
                    if (i + 1 < chanellList.size()) {
                        button2.setText(chanellList.get(i + 1).getName());
                        button2.setCallbackData(CHANNEL + chanellList.get(i + 1).getId());
                        try {
                            button2.setUrl(chanellList.get(i + 1).getLink());
                        } catch (Exception e){
                            log.info("При записи ссылки возникла ошибка == " + e.getMessage());
                            button2.setUrl("https://t.me/" + chanellList.get(i + 1).getLink());
                        }
                        rowInLine.add(button2);
                        if (i + 2 < chanellList.size()) {
                            button3.setText(chanellList.get(i + 2).getName());
                            button3.setCallbackData(CHANNEL + chanellList.get(i + 2).getId());
                            try {
                                button3.setUrl(chanellList.get(i + 2).getLink());
                            }catch (Exception e){
                                log.info("При записи ссылки возникла ошибка == " + e.getMessage());
                                button3.setUrl("https://t.me/" + chanellList.get(i + 2).getLink());
                            }
                            rowInLine.add(button3);
                        }
                    }
                }
                rowsInLine.add(rowInLine);
            }

            markupInLine.setKeyboard(rowsInLine);
            message.setReplyMarkup(markupInLine);

            executeMessage(message, name);
            log.info("Пользователь с имененем " + name + " получил список категорий " );
        }
    }
*/

    private void registerUser(Message msg) {
        var chatId = msg.getChatId();
        var chat = msg.getChat();
        var userId = msg.getChat().getId();
        if (tgUserRepositoryService.findAllByChatId(msg.getChatId()).isEmpty()) { // ЧТО_ТО ни так с работой Метода
            TGUser user = new TGUser();
            user.setChatId(chatId);
            user.setFirstName(chat.getFirstName() != null ? chat.getFirstName() : null);
            user.setUserName(chat.getUserName().toString() != null ? chat.getUserName().toString() : null);
            user.setIdTgUser(userId);
            user.setRegistrationDate(ZonedDateTime.now());
            user.setIsDelete(false);

            //            TgUserRepository.save(user);
            tgUserRepositoryService.saveTgUser(user);
            log.info("Пользователь с имененем " + chat.getFirstName() + " сохранен: " + user);
        } else if (tgUserRepositoryService.findAllByChatId(msg.getChatId()).isPresent()) {
            Set<TGUser> tgUserSet = tgUserRepositoryService.findAllByChatId(msg.getChatId()).get();

            boolean createNewUser = true;
            for (TGUser user : tgUserSet) {
                if (user.getIsDelete().equals(false)) {
                    createNewUser = false;
                    break;
                }
            }

            if (createNewUser == true) {
                TGUser user = new TGUser();
                user.setChatId(chatId);
                user.setFirstName(chat.getFirstName() != null ? chat.getFirstName() : null);
                user.setUserName(chat.getUserName().toString() != null ? chat.getUserName().toString() : null);
                user.setIdTgUser(userId);
                user.setRegistrationDate(ZonedDateTime.now());
                user.setIsDelete(false);
                tgUserRepositoryService.saveTgUser(user);
                log.info("Пользователь с имененем " + chat.getFirstName() + " сохранен: " + user);
            }
        }
    }

    private void startCommandReceived(long chatId, String name) {
        String answer = EmojiParser.parseToUnicode("Добро пожаловать, " + name + " :blush:" + "👌");
        sendMessageWithBaseKeyBoard(chatId, answer, name); // Отправить базовое сообщение с клавиатурой
        checkFindChannelOrAddChannel(chatId, name); // отправить ответное сообщение с базовой  клавиатурой
    }

    private void vNachaloCommandReceived(long chatId, String name) {
        checkFindChannelOrAddChannel(chatId, name);
        sendMessageWithBaseKeyBoard(chatId, "☝☝☝☝☝", name); //// отправить ответное сообщение с базовой  клавиатурой
    }

    private void vNachaloCommandReceivedWithOutBaseKeyBoard(long chatId, String name) {
        checkFindChannelOrAddChannel(chatId, name);
    }

    // отправить ответное сообщение без клавиатуры
    private void sendMessage(long chatId, String textToSend, String nameForLog) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        executeMessage(message, nameForLog);
    }

    // отправить ответное сообщение с базовой  клавиатурой

    private void sendMessageWithBaseKeyBoard(long chatId, String textToSend, String nameForLog) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true); // размер кнопок в клавиатуре

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add("Каналы по категориям");
        row1.add("Каналы по городам");
        row1.add("Связь с админом");

        KeyboardRow row2 = new KeyboardRow();
        row2.add("Текстовый поиск категорий");

        keyboardRows.add(row1);
        keyboardRows.add(row2);
        keyboardMarkup.setKeyboard(keyboardRows);
        message.setReplyMarkup(keyboardMarkup);

        executeMessage(message, nameForLog);
    }

    /* // отправить ответное сообщение с базовой  клавиатурой
    private void sendMessageWithBaseKeyBoard(long chatId, String textToSend, String nameForLog) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true); // размер кнопок в клавиатуре

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        row.add("Баланс");
        row.add("Мои каналы");

        KeyboardRow row1 = new KeyboardRow();
        row1.add("Категории");
        row1.add("Оставить отзыв");

        KeyboardRow row2 = new KeyboardRow();
        row2.add("Связь с админом");
        row2.add("Аукцион");

        keyboardRows.add(row);
        keyboardRows.add(row1);
        keyboardRows.add(row2);
        keyboardMarkup.setKeyboard(keyboardRows);
        message.setReplyMarkup(keyboardMarkup);

        executeMessage(message, nameForLog);
    }*/

    private void executeEditText(long chatId, String nameForLog, String textMessage, long messageId) {
        // Вместо отправки ответа ниже на кнопках - меняется сообщение
        // в котором был задан вопрос
        EditMessageText message = new EditMessageText();
        message.setChatId(String.valueOf(chatId));
        message.setText(textMessage);
        message.setMessageId((int) messageId);

        try {
            execute(message);
            log.info("Ответ пользователю " + nameForLog + ", answer: " + message.getText());
        } catch (TelegramApiException e) {
            log.error(ERROR_TEXT + e.getMessage());
        }
    }

    private void executeEditTextWithKeyBoardAndDisableWebPreview(
        long chatId,
        String nameForLog,
        String textMessage,
        long messageId,
        InlineKeyboardMarkup markupInLine
    ) {
        // Вместо отправки ответа ниже на кнопках - меняется сообщение
        // в котором был задан вопрос
        EditMessageText message = new EditMessageText();
        message.setChatId(String.valueOf(chatId));
        message.setText(textMessage);
        message.setMessageId((int) messageId);

        message.disableWebPagePreview(); // Отключает отображение баннеров для перехода в канал
        message.setReplyMarkup(markupInLine);

        try {
            execute(message);
            log.info("Ответ пользователю " + nameForLog + ", answer: " + message.getText());
        } catch (TelegramApiException e) {
            log.error(ERROR_TEXT + e.getMessage());
        }
    }

    private void executeDeleteMessage(long chatId, String nameForLog, long messageId) {
        // Вместо отправки ответа ниже на кнопках - удаляется сообщение
        // в котором был задан вопрос

        DeleteMessage message = new DeleteMessage();
        message.setChatId(chatId);
        message.setMessageId((int) messageId);

        try {
            execute(message);
            log.info("У пользователя удалилось сообщение в чате" + nameForLog + ", answer: ");
        } catch (TelegramApiException e) {
            log.error(ERROR_TEXT + e.getMessage());
        }
    }

    private void executeMessage(SendMessage message, String nameForLog) {
        try {
            execute(message);
            log.info("Ответ пользователю " + nameForLog + ", answer: " + message.getText());
        } catch (TelegramApiException e) {
            log.error(ERROR_TEXT + e.getMessage());
        }
    }

    private void executeMessageWithKeybord(String nameForLog, long chatId, String textMessage, InlineKeyboardMarkup markupInLine) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textMessage);

        message.disableWebPagePreview(); // Отключает отображение баннеров для перехода в канал
        message.setReplyMarkup(markupInLine);
        try {
            execute(message);
            log.info("Ответ пользователю " + nameForLog + ", answer: " + message.getText());
        } catch (TelegramApiException e) {
            log.error(ERROR_TEXT + e.getMessage());
        }
    }

    private int executeMessageReturnMessageID(SendMessage message, String nameForLog) {
        try {
            var method = execute(message);
            log.info("Ответ пользователю " + nameForLog + ", answer: " + message.getText());
            return method.getMessageId();
        } catch (TelegramApiException e) {
            log.error(ERROR_TEXT + e.getMessage());
        }
        return 0;
    }

    /*
    private void selectCategory(long chatId, String name){

        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Выберите категорию ⬇⬇⬇");

        // создание клавиатуры с кнопками в ответе на сообщение
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); //клавиаутра
        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры

        List<Category> categoryList = categoryRepository.findAll();
        categoryList.stream().peek(Category::getBoolean1).count();


        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        for (int i = 0; i < categoryList.size(); i = i +  3) {
            var button1 = new InlineKeyboardButton();
            var button2 = new InlineKeyboardButton();
            var button3 = new InlineKeyboardButton();
            rowInLine = new ArrayList<>();

            if(i < categoryList.size()) {
                button1.setText(categoryList.get(i).getName());
                button1.setCallbackData(FIND_CAT_FOR_ADD_CHAN + ":" + categoryList.get(i).getId());
                rowInLine.add(button1);
                if (i + 1 < categoryList.size()) {
                    button2.setText(categoryList.get(i + 1).getName());
                    button2.setCallbackData(FIND_CAT_FOR_ADD_CHAN + ":" + categoryList.get(i + 1).getId());
                    rowInLine.add(button2);
                    if (i + 2 < categoryList.size()) {
                        button3.setText(categoryList.get(i + 2).getName());
                        button3.setCallbackData(FIND_CAT_FOR_ADD_CHAN + ":" + categoryList.get(i + 2).getId());
                        rowInLine.add(button3);
                    }
                }
            }
            rowsInLine.add(rowInLine);
        }

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        executeMessage(message, name);

        */
    /*Optional<Set<TGUser>> tgUsers = tgUserRepositoryService.findAllByChatId(chatId);
        TGUser currentUser = null;
        if (tgUsers.isPresent()){
            for (TGUser tgUser: tgUsers.get()) {
                if (tgUser.getDelete().equals(false)){
                    currentUser = tgUser;
                }
            }
            currentUser.setCurrentStep(FIND_CAT_FOR_ADD_CHAN);
            tgUserRepositoryService.saveTgUser(currentUser);
        }*//*

        log.info("Пользователь с имененем " + name + " получил список категорий " );
    }
*/

    // Метод первоначального создания каналов
    /*
    private void addChannelByCategory(long chatId, String name, Long idCategory){
        Chanell chanell = new Chanell();
        Optional<Set<TGUser>> tgUsers = tgUserRepositoryService.findAllByChatId(chatId);
        TGUser currentUser = null;
        if (tgUsers.isPresent()){
            for (TGUser tgUser: tgUsers.get()) {
                if (tgUser.getDelete().equals(false)){
                    currentUser = tgUser;
                }
            }
            chanell.setTGUser(currentUser); //Назначение каналу текущего пользователя
            chanell.isActive(false);
            chanell.isDelete(false);
            chanell.isModerate(false);
            chanell.setName("Отстутствует");
            chanell.setLink("https://t.me/+6sD7JYkJElgxYjMy");
            chanell.currentDate(ZonedDateTime.now().plusHours(3));
        }
        Category category = categoryRepository.findOneWithEagerRelationships(idCategory).get(); // добавление категории
        chanell = chanellRepository.save(chanell);
        Set<Chanell> chanellSet = new HashSet<>(category.getChanellIds());
        chanellSet.add(chanell);
        category.setChanellIds(chanellSet);
        categoryRepository.save(category);
        currentUser.setCurrentStep(ADD_СH_NAME);
        currentUser.setIdCurrentChannelAction(chanell.getId());
        tgUserRepositoryService.saveTgUser(currentUser);
        log.info("Пользователь с имененем " + name + " создал канал с категорией " );

        sendMessage(chatId, "Добавить название канала ⬇⬇⬇", name);
    }
*/

    private void sendChannelToModerate(long adminId, long channelId) {
        Chanell chanell = chanellRepository.findById(channelId).get();
        String msText = new String(
            "МОДЕРАЦИЯ КАНАЛА: \n" +
            "Пользователь: @" +
            chanell.getTGUser().getFirstName() +
            "\n" +
            "ID: " +
            chanell.getTGUser().getChatId().toString() +
            "\n" +
            "Имя канала: " +
            chanell.getName() +
            "\n" +
            "Ссылка: " +
            chanell.getLink() +
            "\n" +
            "Рекламный прайс: " +
            chanell.getPriceDiapozon().toString()
        );

        // БЛОК С ОТПРАВКОЙ КНОПКИ НА ОДОБРЕНИЕ АДМИНОМ КАНАЛА
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(adminId));
        message.setText(msText);

        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); //клавиаутра
        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        var button1 = new InlineKeyboardButton();
        button1.setText("Подтвердить 👍");
        button1.setCallbackData(CREATE_APPROVE_СH + ":" + chanell.getId());
        rowInLine.add(button1);
        rowsInLine.add(rowInLine);

        var button2 = new InlineKeyboardButton();
        button2.setText("Отклонить - неверный админ 👎");
        button2.setCallbackData(CREATE_DISABLE_СH + ":" + chanell.getId() + ":2");
        rowInLine = new ArrayList<>();
        rowInLine.add(button2);
        rowsInLine.add(rowInLine);

        var button3 = new InlineKeyboardButton();
        button3.setText("Отклонить - не корректное название 👎");
        button3.setCallbackData(CREATE_DISABLE_СH + ":" + chanell.getId() + ":3");
        rowInLine = new ArrayList<>();
        rowInLine.add(button3);
        rowsInLine.add(rowInLine);

        var button4 = new InlineKeyboardButton();
        button4.setText("Отклонить - не правильная ссылка 👎");
        button4.setCallbackData(CREATE_DISABLE_СH + ":" + chanell.getId() + ":4");
        rowInLine = new ArrayList<>();
        rowInLine.add(button4);
        rowsInLine.add(rowInLine);

        var button5 = new InlineKeyboardButton();
        button5.setText("Отклонить - неверная цена 👎");
        button5.setCallbackData(CREATE_DISABLE_СH + ":" + chanell.getId() + ":5");
        rowInLine = new ArrayList<>();
        rowInLine.add(button5);
        rowsInLine.add(rowInLine);

        var button6 = new InlineKeyboardButton();
        button6.setText("Отклонить - канал относится к запретным 👎");
        button6.setCallbackData(CREATE_DISABLE_СH + ":" + chanell.getId() + ":6");
        rowInLine = new ArrayList<>();
        rowInLine.add(button6);
        rowsInLine.add(rowInLine);

        var button7 = new InlineKeyboardButton();
        button7.setText("Бан ❌");
        button7.setCallbackData(CREATE_BAN_CH + ":" + chanell.getId());
        rowInLine = new ArrayList<>();
        rowInLine.add(button7);
        rowsInLine.add(rowInLine);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        int msId = executeMessageReturnMessageID(message, tgUserRepositoryService.getOneChatIdAndDeleteFalse(adminId).get().getFirstName());

        MessegePannel msPan = new MessegePannel();
        msPan.setIdAdmin(adminId);
        if (msId != 0) {
            msPan.setIdMessage(Long.valueOf(msId)); // УБЕДИТЬСЯ ЧТО ЭТО ТОТ САМЫЙ АЙДИШНИК
        }
        msPan.setTextMessage(message.getText());
        msPan.setDateCreateMessage(ZonedDateTime.now());
        msPan.setIdChannel(channelId);
        msPan.setStatus(MODERATION);
        messegePannelRepository.save(msPan);
    }

    private void sendEditChannelToModerate(long adminId, long editChannelId) {
        EditChannels editChannels = editChannelsRepository.findById(editChannelId).get();
        Chanell chanell = chanellRepository.findById(editChannels.getIdChannel()).get();
        String msText = new String(
            "МОДЕРАЦИЯ КАНАЛА: \n" +
            "Пользователь: @" +
            chanell.getTGUser().getFirstName().toString() +
            "\n" +
            "ID: " +
            chanell.getTGUser().getChatId().toString() +
            "\n" +
            "Текущее Имя канала: " +
            chanell.getName().toString() +
            "\n" +
            "Пользователь хочет изменить имя на " +
            editChannels.getNewNameChannel() +
            "\n" +
            "Ссылка: " +
            chanell.getLink() +
            "\n" +
            "Пользователь хочет изменить ссылку на " +
            editChannels.getNewlastLinkToChannel() +
            "\n" +
            "Описание: " +
            chanell.getString1() +
            "\n" +
            "Пользователь хочет изменить описание на: " +
            editChannels.getAddDescriptionAboutChannel() +
            "\n" +
            "Рекламный прайс: " +
            chanell.getPriceDiapozon().toString()
        ) +
        "\n" +
        "Пользователь хочет изменить прайс на " +
        editChannels.getNewPriceChannel();

        // БЛОК С ОТПРАВКОЙ КНОПКИ НА ОДОБРЕНИЕ АДМИНОМ КАНАЛА
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(adminId));
        message.setText(msText);

        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); //клавиаутра
        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        var button1 = new InlineKeyboardButton();
        button1.setText("Подтвердить 👍");
        button1.setCallbackData(EDIT_APPROVE_СH + ":" + editChannels.getId());
        rowInLine.add(button1);
        rowsInLine.add(rowInLine);

        var button2 = new InlineKeyboardButton();
        button2.setText("Отклонить - неверный админ 👎");
        button2.setCallbackData(EDIT_DISABLE_СH + ":" + editChannels.getId() + ":2");
        rowInLine = new ArrayList<>();
        rowInLine.add(button2);
        rowsInLine.add(rowInLine);

        var button3 = new InlineKeyboardButton();
        button3.setText("Отклонить - не корректное название 👎");
        button3.setCallbackData(EDIT_DISABLE_СH + ":" + editChannels.getId() + ":3");
        rowInLine = new ArrayList<>();
        rowInLine.add(button3);
        rowsInLine.add(rowInLine);

        var button4 = new InlineKeyboardButton();
        button4.setText("Отклонить - не правильная ссылка 👎");
        button4.setCallbackData(EDIT_DISABLE_СH + ":" + editChannels.getId() + ":4");
        rowInLine = new ArrayList<>();
        rowInLine.add(button4);
        rowsInLine.add(rowInLine);

        var button5 = new InlineKeyboardButton();
        button5.setText("Отклонить - неверная цена 👎");
        button5.setCallbackData(EDIT_DISABLE_СH + ":" + editChannels.getId() + ":5");
        rowInLine = new ArrayList<>();
        rowInLine.add(button5);
        rowsInLine.add(rowInLine);

        var button6 = new InlineKeyboardButton();
        button6.setText("Отклонить - канал относится к запретным 👎");
        button6.setCallbackData(EDIT_DISABLE_СH + ":" + editChannels.getId() + ":6");
        rowInLine = new ArrayList<>();
        rowInLine.add(button6);
        rowsInLine.add(rowInLine);

        var button8 = new InlineKeyboardButton();
        button8.setText("Отклонить - некорректное описание 👎");
        button8.setCallbackData(EDIT_DISABLE_СH + ":" + editChannels.getId() + ":8");
        rowInLine = new ArrayList<>();
        rowInLine.add(button8);
        rowsInLine.add(rowInLine);

        var button7 = new InlineKeyboardButton();
        button7.setText("Бан ❌");
        button7.setCallbackData(EDIT_BAN_CH + ":" + editChannels.getId());
        rowInLine = new ArrayList<>();
        rowInLine.add(button7);
        rowsInLine.add(rowInLine);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        int msId = executeMessageReturnMessageID(message, tgUserRepositoryService.getOneChatIdAndDeleteFalse(adminId).get().getFirstName());

        MessegePannel msPan = new MessegePannel();
        msPan.setIdAdmin(adminId);
        if (msId != 0) {
            msPan.setIdMessage(Long.valueOf(msId)); // УБЕДИТЬСЯ ЧТО ЭТО ТОТ САМЫЙ АЙДИШНИК
        }

        msPan.setTextMessage(message.getText());
        msPan.setDateCreateMessage(ZonedDateTime.now());
        msPan.setIdChannel(editChannelId);
        msPan.setStatus(EDITING_CHANNEL);
        messegePannelRepository.save(msPan);
    }

    private void sendMessageToTehPoddershkaAfterOtkonitKanal(long adminId, long channelId) {
        TGUser admin = tgUserRepositoryService.getOneChatIdAndDeleteFalse(adminId).get();
        Chanell chanell = chanellRepository.findById(channelId).get();
        String msText = new String(
            "Запрос на переписку от пользователя: \n" +
            "Пользователь: @" +
            (
                chanell.getTGUser().getUserName() != null
                    ? chanell.getTGUser().getUserName() != "" ? chanell.getTGUser().getUserName() : null
                    : null
            ) +
            "\n" +
            "(После того как отклонили канал)" +
            "\n" +
            "ID: " +
            chanell.getTGUser().getChatId().toString() +
            "\n" +
            "Имя канала: " +
            chanell.getName().toString() +
            "\n" +
            "Ссылка: " +
            chanell.getLink().toString() +
            "\n" +
            "Рекламный прайс: " +
            chanell.getPriceDiapozon().toString()
        );

        // БЛОК С КНОПАКАМИ - ОТВЕТ на СВЯЗЬ с поддержкой
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(adminId));
        message.setText(msText);

        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); //клавиаутра
        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        var button1 = new InlineKeyboardButton();
        button1.setText("Взять в работу👍");
        button1.setCallbackData(TAKE_TO_WORK_AFTER_FALSE_CONTACTING_THE_CHAN + ":" + chanell.getId());
        /*if(chanell.getTGUser().getUserName() != null || chanell.getTGUser().getUserName() != ""){
            button1.setUrl("https://t.me/" + chanell.getTGUser().getUserName());
        }*/
        rowInLine.add(button1);

        var button2 = new InlineKeyboardButton();
        button2.setText("Отклонить/Завершить (удалить у меня) 👎");
        button2.setCallbackData(REJECT_FINISH_CONTACTING_FOR_ME_AFTER_CHAN_CLICK + ":" + chanell.getId());
        rowInLine.add(button2);
        rowsInLine.add(rowInLine);

        var button3 = new InlineKeyboardButton();
        button3.setText("Отправить пользователю ссылку на админа");
        button3.setCallbackData(SEND_ADMIN_CHAT_TO_USER + ":" + chanell.getId() + ":" + admin.getUserName());
        rowInLine = new ArrayList<>();
        rowInLine.add(button3);
        rowsInLine.add(rowInLine);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        int msId = executeMessageReturnMessageID(message, tgUserRepositoryService.getOneChatIdAndDeleteFalse(adminId).get().getFirstName());

        MessegePannel msPan = new MessegePannel();
        msPan.setIdAdmin(adminId);
        if (msId != 0) {
            msPan.setIdMessage(Long.valueOf(msId));
        }
        msPan.setTextMessage(message.getText());
        msPan.setDateCreateMessage(ZonedDateTime.now());
        msPan.setIdChannel(channelId);
        msPan.setStatus(SVYAZ_S_ADMINAMI);
        messegePannelRepository.save(msPan);
    }

    private void sendMessageToTehPoddershkaFromMenu(long adminId, long userId) {
        TGUser admin = tgUserRepositoryService.getOneChatIdAndDeleteFalse(adminId).get();
        TGUser tgUser = tgUserRepositoryService.getOneChatIdAndDeleteFalse(userId).get();
        String msText = new String(
            "Запрос на переписку от пользователя: \n" +
            "Пользователь: @" +
            (tgUser.getUserName() != null ? tgUser.getUserName() != "" ? tgUser.getUserName() : null : null) +
            "\n" +
            "(Вызов кнопки из меню)" +
            "\n" +
            "ID: " +
            tgUser.getChatId().toString()
        );

        // БЛОК С КНОПАКАМИ - ОТВЕТ на СВЯЗЬ с поддержкой
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(adminId));
        message.setText(msText);

        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); //клавиаутра
        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        var button1 = new InlineKeyboardButton();
        button1.setText("Взять в работу👍");
        button1.setCallbackData(TAKE_TO_WORK_AFTER_FALSE_CONTACTING_FROM_MENU + ":" + tgUser.getChatId());
        /*if(tgUser.getUserName() != null || tgUser.getUserName() != ""){
            button1.setUrl("https://t.me/" + tgUser.getUserName());
        }*/
        rowInLine.add(button1);

        var button2 = new InlineKeyboardButton();
        button2.setText("Отклонить/Завершить (удалить у меня) 👎");
        button2.setCallbackData(REJECT_FINISH_CONTACTING_FOR_ME_AFTER_FROM_MENU + ":" + tgUser.getChatId());
        rowInLine.add(button2);
        rowsInLine.add(rowInLine);

        var button3 = new InlineKeyboardButton();
        button3.setText("Отправить пользователю ссылку на админа");
        button3.setCallbackData(SEND_ADMIN_LINK_TO_USER_FROM_MENU + ":" + tgUser.getChatId() + ":" + admin.getUserName());
        rowInLine = new ArrayList<>();
        rowInLine.add(button3);
        rowsInLine.add(rowInLine);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        int msId = executeMessageReturnMessageID(message, tgUserRepositoryService.getOneChatIdAndDeleteFalse(adminId).get().getFirstName());

        MessegePannel msPan = new MessegePannel();
        msPan.setIdAdmin(adminId);
        if (msId != 0) {
            msPan.setIdMessage(Long.valueOf(msId));
        }
        msPan.setTextMessage(message.getText());
        msPan.setDateCreateMessage(ZonedDateTime.now());
        msPan.setServiceField1(tgUser.getChatId().toString());
        msPan.setStatus(SVYAZ_S_ADMINAMI);
        messegePannelRepository.save(msPan);
    }

    private void resetStepForUser(TGUser tgUser) {
        tgUser.setCurrentStep("");
        tgUser.setIdCurrentChannelAction(null);
        tgUserRepositoryService.saveTgUser(tgUser);
    }

    private void approveCreateChannel(long channelId) {
        Chanell chanell = chanellRepository.findById(channelId).get();
        chanell.setIsModerate(true);
        chanell.setIsActive(true);
        chanellRepository.save(chanell);
        sendMessage(
            chanell.getTGUser().getChatId(),
            "Канал \"" +
            chanell.getName() +
            "\"" +
            "- успешно прошёл модерацию ✔✔✔ \n " +
            " (Далее можете работать с ним в разделе \"Мои каналы\")",
            chanell.getTGUser().getFirstName()
        );
        vNachaloCommandReceivedWithOutBaseKeyBoard(chanell.getTGUser().getChatId(), chanell.getTGUser().getFirstName());
    }

    private void approveEditingChannel(long editingChannelId) {
        EditChannels editChannel = editChannelsRepository.findById(editingChannelId).get();
        editChannel.setIsApprovedChanhes(true);
        editChannelsRepository.save(editChannel);
        Chanell chanell = chanellRepository.findById(editChannel.getIdChannel()).get();
        chanell.setIsModerate(true);
        chanell.setIsActive(true);
        chanell.setString1(editChannel.getAddDescriptionAboutChannel());
        chanell.setName(editChannel.getNewNameChannel());
        chanell.setLink(editChannel.getNewlastLinkToChannel());
        chanell.setPriceDiapozon(editChannel.getNewPriceChannel());
        chanellRepository.save(chanell);
        sendMessage(
            chanell.getTGUser().getChatId(),
            "Канал \"" +
            chanell.getName() +
            "\"" +
            "- успешно прошёл модерацию ✔✔✔ \n " +
            " (Далее можете работать с ним в разделе \"Мои каналы\")",
            chanell.getTGUser().getFirstName()
        );
        vNachaloCommandReceivedWithOutBaseKeyBoard(chanell.getTGUser().getChatId(), chanell.getTGUser().getFirstName());
    }

    private void disableCreateChannel(long channelId, String whyFailure) {
        Chanell chanell = chanellRepository.findById(channelId).get();
        chanell.setIsModerate(false);
        chanell.setIsActive(false);
        chanellRepository.save(chanell);

        SendMessage message = new SendMessage();
        message.setChatId(chanell.getTGUser().getChatId());
        message.setText("Канал \"" + chanell.getName() + "\"" + " -  НЕ прошёл модерацию \n" + "по причине: " + whyFailure + "❌❌❌");

        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); //клавиаутра
        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        var button1 = new InlineKeyboardButton();
        button1.setText("В начало ⬆⬆⬆");
        button1.setCallbackData(V_NACHALO);
        rowInLine.add(button1);

        var button2 = new InlineKeyboardButton();
        button2.setText("Связаться с тех поддержкой 🔔");
        button2.setCallbackData(TEX_PODDERSHKA + ":" + chanell.getId());
        rowInLine.add(button2);
        rowsInLine.add(rowInLine);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        executeMessage(message, chanell.getTGUser().getFirstName());
    }

    private void disableEditChannel(long editingChannelId, String whyFailure) {
        EditChannels editChannel = editChannelsRepository.findById(editingChannelId).get();
        editChannel.setIsApprovedChanhes(true);
        editChannelsRepository.save(editChannel);
        Chanell chanell = chanellRepository.findById(editChannel.getIdChannel()).get();

        SendMessage message = new SendMessage();
        message.setChatId(chanell.getTGUser().getChatId());
        message.setText("Канал \"" + chanell.getName() + "\"" + " -  НЕ прошёл модерацию \n" + "по причине: " + whyFailure + "❌❌❌");

        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); //клавиаутра
        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        var button1 = new InlineKeyboardButton();
        button1.setText("В начало ⬆⬆⬆");
        button1.setCallbackData(V_NACHALO);
        rowInLine.add(button1);

        var button2 = new InlineKeyboardButton();
        button2.setText("Связаться с тех поддержкой 🔔");
        button2.setCallbackData(TEX_PODDERSHKA + ":" + chanell.getId());
        rowInLine.add(button2);
        rowsInLine.add(rowInLine);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        executeMessage(message, chanell.getTGUser().getFirstName());
    }

    private void banCreateChannel(long channelId) {
        Chanell chanell = chanellRepository.findById(channelId).get();
        TGUser tgUser = tgUserRepositoryService.getOneChatIdAndDeleteFalse(chanell.gettGUser().getChatId()).get();
        tgUser.setBlocked(true);
        tgUserRepositoryService.saveTgUser(tgUser);
        chanellRepository.delete(chanell);
        log.info("Пользователь- " + tgUser.getFirstName() + ", был заблокирован");
    }

    private void banEditChannel(long editingChannelId) {
        EditChannels editChannel = editChannelsRepository.findById(editingChannelId).get();
        editChannel.setIsApprovedChanhes(true);
        editChannelsRepository.save(editChannel);
        Chanell chanell = chanellRepository.findById(editChannel.getIdChannel()).get();
        TGUser tgUser = tgUserRepositoryService.getOneChatIdAndDeleteFalse(chanell.gettGUser().getChatId()).get();
        tgUser.setBlocked(true);
        tgUserRepositoryService.saveTgUser(tgUser);
        chanellRepository.delete(chanell);
        log.info("Пользователь- " + tgUser.getFirstName() + ", был заблокирован");
    }

    private Set<MessegePannel> getAllWhatWeWantDeleteByChannelID(long channelId, String action) {
        Set<MessegePannel> messagePannels = messegePannelRepository.getByChannelIdAndStatus(channelId, action);
        return messagePannels;
    }

    private Set<MessegePannel> getAllWhatWeWantDeleteByChatId(String chatId, String action) {
        Set<MessegePannel> messegePannels = messegePannelRepository.getByChatIdAndStatus(chatId, action);
        return messegePannels;
    }

    // Метод даёт список каналов для конкретного пользователя
    private void getMyChannels(long chatId, String name) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        //        message.setText("Список Ваших каналов ⬇⬇⬇");

        // создание клавиатуры с кнопками в ответе на сообщение
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); //клавиаутра
        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры
        // создание списка с кнопками в ответе на сообщение
        // List<InlineKeyboardButton> rowInLine = new ArrayList<>(); // одна строка // сама строка клавиатуры

        TGUser tgUser = tgUserRepositoryService.getOneChatIdAndDeleteFalse(chatId).get();
        List<Chanell> channelList = chanellRepository.getAllByTGUser(tgUser);
        List<Chanell> listWithNotModerateChannels = new ArrayList<>();

        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        for (Chanell chanell : channelList) {
            if (chanell.getIsModerate().equals(true)) {
                rowInLine = new ArrayList<>();
                var button1 = new InlineKeyboardButton();
                button1.setText(chanell.getName());
                button1.setCallbackData(MY_CHNS + ":" + chanell.getId());
                rowInLine.add(button1);
                rowsInLine.add(rowInLine);
            } else {
                listWithNotModerateChannels.add(chanell);
            }
        }
        /*for (int i = 0; i < channelList.size(); i++) {
            rowInLine = new ArrayList<>();
            var button1 = new InlineKeyboardButton();
            button1.setText(channelList.get(i).getName());
            button1.setCallbackData(MY_CHNS + channelList.get(i).getId());
            rowInLine.add(button1);
            rowsInLine.add(rowInLine);
        }*/
        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);
        message.setText(
            rowsInLine.size() > 0
                ? "Список Ваших каналов ⬇⬇⬇"
                : listWithNotModerateChannels.size() > 0
                    ? " Добавленные вами каналы пока что проходят модерацию, ожидайте" + ", Вам поступит оповещение"
                    : "  У вас пока нет добавленных каналов"
        );

        executeMessage(message, name);
        log.info("Пользователь с имененем " + name + " получил список своих каналов ");
    }

    private void sendMessageWithKeyBoardWithAllMyChannels(long chatId, String textToSend, String nameForLog, Long channelID) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true); // размер кнопок в клавиатуре

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add("Изменить ценовой диапазон");
        row1.add("Изменить название");

        KeyboardRow row2 = new KeyboardRow();
        row2.add("Изменить ссылку");
        row2.add("Добавить описание");

        KeyboardRow row3 = new KeyboardRow();
        row3.add("Вернуться назад");
        row3.add("Скрыть канал");

        keyboardRows.add(row1);
        keyboardRows.add(row2);
        keyboardRows.add(row3);
        keyboardMarkup.setKeyboard(keyboardRows);
        message.setReplyMarkup(keyboardMarkup);

        executeMessage(message, nameForLog);
    }

    public void sendAdminLink(long chatId, String nameForLog) {
        //        Set<TGUser> admins = tgUserRepository.getAllAdmins();
        List<Admin> admins = adminRepository.findAll();
        Set<String> adminsName = admins.stream().filter(a -> a.getIsActive()).map(admin -> admin.getLink()).collect(Collectors.toSet());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Свяжитесь с администратором по ссылке: \n");
        adminsName.forEach(adminName -> stringBuilder.append(adminName + " \n\n"));
        /*admins.forEach(admin ->
            sendMessage(chatId, "Свяжитесь с администратором по ссылке: \n"
                + " - " + "@" + admin.getUserName(), nameForLog) );*/
        sendMessage(chatId, stringBuilder.toString(), nameForLog);
    }

    private void sendSearchCategoriesButton(long chatId, String name) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Кликните на кнопку для поиска категорий ⬇⬇⬇");

        // создание клавиатуры с кнопками в ответе на сообщение
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); //клавиаутра
        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры
        // создание списка с кнопками в ответе на сообщение

        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        var searchCategory = new InlineKeyboardButton();
        rowInLine = new ArrayList<>();
        searchCategory.setText("Текстовый поиск категорий 🌍🌍🌍"); // Содержимое ответа в кнопке
        searchCategory.setSwitchInlineQueryCurrentChat(" ");
        rowInLine.add(searchCategory);
        rowsInLine.add(rowInLine);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        executeMessage(message, name);
        log.info("Пользователь с имененем " + name + " получил кнопку для текстового поиска категорий ");
    }

    @Scheduled(fixedDelay = 60000)
    private void sendAds() {
        /*var ads = adsRepository.findAll();
        var users = userRepository.findAll();
        for(Ads ad: ads) {
            for (User user: users){
                sendMessage(user.getChatId(), ad.getAd());
            }
        }*/
        //        sendMessage(config.getOwnerId(), "textToSend", "Olezhan");
    }
}
