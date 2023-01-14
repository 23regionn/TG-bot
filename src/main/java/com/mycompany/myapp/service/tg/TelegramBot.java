package com.mycompany.myapp.service.tg;

import com.mycompany.myapp.config.tg.BotConfig;
import com.mycompany.myapp.domain.Category;
import com.mycompany.myapp.domain.Chanell;
import com.mycompany.myapp.domain.TGUser;
import com.mycompany.myapp.repository.CategoryRepository;
import com.mycompany.myapp.repository.ChanellRepository;
import com.mycompany.myapp.repository.TGUserRepository;
import com.mycompany.myapp.service.TgUserRepositoryService;
import com.vdurmont.emoji.EmojiParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.nio.channels.Channel;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

//@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final Logger log = LoggerFactory.getLogger(TelegramBot.class);

    @Autowired
    private TGUserRepository TgUserRepository;

    @Autowired
    private TgUserRepositoryService tgUserRepositoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ChanellRepository chanellRepository;

    final BotConfig config;

    static final String HELP_TEXT = "This bot is created to demonstrate Spring capabilities.\n\n" +
            "You can execute commands from the main menu on the left or by typing a command:\n\n" +
            "Type /start to see a welcome message\n\n" +
            "Type /mydata to see data stored about yourself\n\n" +
            "Type /help to see this message again";

    static final String YES_BUTTON = "YES_BUTTON";
    static final String NO_BUTTON = "NO_BUTTON";
    static final String FIND_CHANNEL = "FIND_CHANNEL";
    static final String ADD_CHANNEL = "ADD_CHANNEL";

    static final String CATEGORY = "CATEGORY";
    static final String PRICEDIAP = "PRICEDIAP";
    static final String IDCAT = "IDCAT";

    static final String CHANNEL = "CHANNEL";

    static final String ERROR_TEXT = "Error occurred: ";
    static final String FIND_CAT_FOR_ADD_CHAN = "FIND_CAT_FOR_ADD_CHAN";
    static final String FIRST_CREATE_CH_WITH_CAT = "FIRST_CREATE_CH_WITH_CAT";
    static final String ADD_СH_NAME = "ADD_СH_NAME";
    static final String ADD_СH_LINK = "ADD_СH_LINK";
    static final String ADD_СH_PRICE = "ADD_СH_PRICE";
    static final String ADD_COUNT_SUBSCRIBERS = "ADD_COUNT_SUBSCRIBERS";


    public TelegramBot(BotConfig config) {
        this.config = config;
        List<BotCommand> listofCommands = new ArrayList<>();
        listofCommands.add(new BotCommand("/start", "get a welcome message"));
        listofCommands.add(new BotCommand("/mydata", "get your data stored"));
        listofCommands.add(new BotCommand("/deletedata", "delete my data"));
        listofCommands.add(new BotCommand("/help", "info how to use this bot"));
        listofCommands.add(new BotCommand("/settings", "set your preferences"));
        try {
            this.execute(new SetMyCommands(listofCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error("Error setting bot's command list: " + e.getMessage());
        }
    }

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
            log.info("Сообщение от пользователя " + nameForLog + ", сообщение: " +  messageText);
            TGUser tgUser;
            Optional<TGUser> tgUserOptional = tgUserRepositoryService.getOneChatIdAndDeleteFalse(update.getMessage().getChatId());
            tgUser = tgUserOptional.isPresent() ? tgUserRepositoryService.getOneChatIdAndDeleteFalse(update.getMessage().getChatId()).get() : null;

            if(messageText.equals("/start")){
                registerUser(update.getMessage());
                startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
            }
            else if(messageText.equals("/help")){
                sendMessage(chatId, HELP_TEXT, nameForLog);
            }
            else if(messageText.equals("/register")){
                register(chatId, nameForLog);
            }
            else if(messageText.equals("/oleg")){
                sendMessage(chatId, "Oleg", nameForLog);
            }
            else if(messageText.equals("/category") || messageText.equals("Категории")){
                findCategory(chatId, nameForLog);
            }
            else if(messageText.equals("/channel")){
                checkFindChannelOrAddChannel(chatId, nameForLog);
            }

            // Рассылка пользователям
            else if(messageText.contains("/send") && config.getOwnerId() == chatId) {
                var textToSend = EmojiParser.parseToUnicode(messageText.substring(messageText.indexOf(" ")));
                /*var users = userRepository.findAll(); ЭТОТ ЗАПРОС НЕ ПОДХОДИТ- НУЖНА РАССЫЛКА ВООБЩЕ ВСЕМ ПОЛЬЗОВАТЕЛЯМ.
                for (User user: users){
                    sendMessage(user.getChatId(), textToSend);
                }*/
//                sendMessage(chatId, textToSend, nameForLog);
                sendMessage(chatId, textToSend, nameForLog);
            }

            else if(tgUserRepositoryService.getOneChatIdAndDeleteFalse(update.getMessage().getChatId()).isPresent() &&
                tgUserRepositoryService.getOneChatIdAndDeleteFalse(update.getMessage().getChatId()).get().getCurrentStep() != null &&
                tgUserRepositoryService.getOneChatIdAndDeleteFalse(update.getMessage().getChatId()).get().getCurrentStep().equals(ADD_СH_NAME)){
                TGUser currentUser = tgUserRepositoryService.getOneChatIdAndDeleteFalse(update.getMessage().getChatId()).get();
                if (currentUser.getChatId() == chatId && currentUser.getCurrentStep().equals(ADD_СH_NAME)){
                    if(currentUser.getIdCurrentChannelAction() != null){
                        Chanell chanell = chanellRepository.findById(currentUser.getIdCurrentChannelAction()).get();
                        chanell.setName(messageText);
                        chanellRepository.save(chanell);
                        currentUser.setCurrentStep(ADD_СH_LINK);
                        tgUserRepositoryService.saveTgUser(currentUser);
                        log.info("В канал с id " + chanell.getId() + ", добавленно название: " +  messageText + ", " + nameForLog);
                        sendMessage(chatId, "Добавить ссылку на канал (скопируйте и вставьте) ⬇⬇⬇", nameForLog);
                    }
                }
            }
            else if(tgUserRepositoryService.getOneChatIdAndDeleteFalse(update.getMessage().getChatId()).isPresent() &&
                tgUserRepositoryService.getOneChatIdAndDeleteFalse(update.getMessage().getChatId()).get().getCurrentStep() != null &&
                tgUserRepositoryService.getOneChatIdAndDeleteFalse(update.getMessage().getChatId()).get().getCurrentStep().equals(ADD_СH_LINK)){
                TGUser currentUser = tgUserRepositoryService.getOneChatIdAndDeleteFalse(update.getMessage().getChatId()).get();
                if (currentUser.getChatId() == chatId && currentUser.getCurrentStep().equals(ADD_СH_LINK)){
                    if(currentUser.getIdCurrentChannelAction() != null){
                        Chanell chanell = chanellRepository.findById(currentUser.getIdCurrentChannelAction()).get();
                        chanell.setLink(messageText);
                        chanellRepository.save(chanell);
                        currentUser.setCurrentStep(ADD_СH_PRICE);
                        tgUserRepositoryService.saveTgUser(currentUser);
                        log.info("В канал с id " + chanell.getId() + ", добавленна ссылка : " +  messageText + ", " + nameForLog);
                        sendMessage(chatId, "Добавить прайс (сумма размещения рекламы) ⬇⬇⬇", nameForLog);
                    }
                }
            }
            else if(tgUserRepositoryService.getOneChatIdAndDeleteFalse(update.getMessage().getChatId()).isPresent() &&
                tgUserRepositoryService.getOneChatIdAndDeleteFalse(update.getMessage().getChatId()).get().getCurrentStep() != null &&
                tgUserRepositoryService.getOneChatIdAndDeleteFalse(update.getMessage().getChatId()).get().getCurrentStep().equals(ADD_СH_PRICE)){
                TGUser currentUser = tgUserRepositoryService.getOneChatIdAndDeleteFalse(update.getMessage().getChatId()).get();
                if (currentUser.getChatId() == chatId && currentUser.getCurrentStep().equals(ADD_СH_PRICE)){
                    if(currentUser.getIdCurrentChannelAction() != null){
                        Chanell chanell = chanellRepository.findById(currentUser.getIdCurrentChannelAction()).get();
                        chanell.setPriceDiapozon(Double.valueOf(messageText));
                        chanellRepository.save(chanell);
                        currentUser.setCurrentStep("");
                        tgUserRepositoryService.saveTgUser(currentUser);
                        log.info("В канал с id " + chanell.getId() + ", добавлен прайс для рекламы : " +  messageText + ", " + nameForLog);
                        sendMessage(chatId, "Канал добавлен и проходит модерацию ", nameForLog);
                    }
                }
            }

            else { // Ради дефолтового ответа
                switch (messageText) {
                    default:
                        sendMessage(chatId, "Извините, команда не распознана", nameForLog);

                }
            }
        }
        else if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            long messageId = update.getCallbackQuery().getMessage().getMessageId(); // айди текущего сообщения
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            String nameForLog = update.getCallbackQuery().getMessage().getChat().getFirstName();
            log.info("Сообщение от пользователя " + nameForLog + ", (нажата кнопка): " +  callbackData);

            if(callbackData.equals(YES_BUTTON)){
                String text = "You pressed YES button";
                executeEditText(chatId, nameForLog, text,messageId);
            }
            else if(callbackData.equals(NO_BUTTON)){
                String text = "You pressed NO button";
                executeEditText(chatId, nameForLog, text,messageId);
            }
            else if(callbackData.equals(FIND_CHANNEL)){
                String text = "Выберите каналы";
                executeDeleteMessage(chatId, nameForLog, messageId);
                findCategory(chatId, nameForLog);
            }
            else if(callbackData.equals(ADD_CHANNEL)){
                String text = "Вы нажали добавить канал";
                executeEditText(chatId, nameForLog, text,messageId);
                selectCategory(chatId, nameForLog);
            }
            else if(callbackData.contains(CATEGORY)){
                String text = "Вы нажали на категоррию " + callbackData.replace(CATEGORY,"");
                Long categoryId = Long.valueOf(callbackData.replace(CATEGORY,""));
//                executeEditText(chatId, nameForLog, text,messageId);

//                executeDeleteMessage(chatId, nameForLog, messageId);
                selectPriceDiapozonForGetChanneles(chatId, nameForLog, categoryId);

            }
            else if(callbackData.contains(PRICEDIAP)){
                String text = "Вы нажали на price " + callbackData.replace(PRICEDIAP,"");
                String[] strings = callbackData.split(IDCAT);
                Double price = Double.valueOf(strings[0].replace(PRICEDIAP,""));
                Long categoryId = Long.valueOf(strings[1]);
//                executeEditText(chatId, nameForLog, text, messageId);

//                executeDeleteMessage(chatId, nameForLog, messageId);
                getChanellByPriceDiapozon(chatId, nameForLog, price, categoryId);
            }

            else if(callbackData.contains(CHANNEL)){ // ВРОДЕ НЕ ИСПОЛЬЗУЕТСЯ
                String text = "Вы нажали на channel " + callbackData.replace(CHANNEL,"");
                Long channelId = Long.valueOf(callbackData.replace(CHANNEL,""));
                Optional<Chanell> chanell = chanellRepository.findById(channelId);
                if (chanell.isPresent()){
                    System.out.println(chanell.get().getLink());
                }
            }
            else if(callbackData.contains(FIND_CAT_FOR_ADD_CHAN)){
                Long idCategory = Long.valueOf(callbackData.split(":")[1]);
                addChannelByCategory(chatId, nameForLog, idCategory);

            }
            else if(callbackData.contains(ADD_СH_NAME)){
                Long idChannel = Long.valueOf(callbackData.split(":")[1]);
                Long tgUserId = Long.valueOf(callbackData.split(":")[2]);
//                addChannelName(chatId, nameForLog, idChannel, tgUserId);
            }
        }
    }



    private void register(long chatId, String name){

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

    private void checkFindChannelOrAddChannel(long chatId, String name){

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
    }

    // Найти категории
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

        List<Category> categoryList = categoryRepository.findAll();


        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        for (int i = 0; i < categoryList.size(); i = i +  3) {
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
            }
            rowsInLine.add(rowInLine);
        }

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        executeMessage(message, name);
        log.info("Пользователь с имененем " + name + " получил список категорий " );
    }


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
                    rowInLine.add(button1);
                    if (i + 1 < chanellList.size()) {
                        button2.setText(chanellList.get(i + 1).getName());
                        button2.setCallbackData(CHANNEL + chanellList.get(i + 1).getId());
                        button2.setUrl(chanellList.get(i + 1).getLink());
                        rowInLine.add(button2);
                        if (i + 2 < chanellList.size()) {
                            button3.setText(chanellList.get(i + 2).getName());
                            button3.setCallbackData(CHANNEL + chanellList.get(i + 2).getId());
                            button3.setUrl(chanellList.get(i + 2).getLink());
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


    private void registerUser(Message msg) {

        var chatId = msg.getChatId();
        var chat = msg.getChat();
        var userId = msg.getChat().getId();
        if(tgUserRepositoryService.findAllByChatId(msg.getChatId()).isEmpty()){

            TGUser user = new TGUser();
            user.setChatId(chatId);
            user.setFirstName(chat.getFirstName());
            user.setIdTgUser(userId);
            user.setRegistrationDate(ZonedDateTime.now());
            user.setIsDelete(false);

//            TgUserRepository.save(user);
            tgUserRepositoryService.saveTgUser(user);
            log.info("Пользователь с имененем " + chat.getFirstName() + " сохранен: " + user);
        }
        else if (tgUserRepositoryService.findAllByChatId(msg.getChatId()).isPresent()){
            Set<TGUser> tgUserSet = tgUserRepositoryService.findAllByChatId(msg.getChatId()).get();

            boolean createNewUser = true;
            for (TGUser user: tgUserSet ) {
                if(user.getIsDelete().equals(false)){
                    createNewUser = false;
                    break;
                }
            }

            if (createNewUser == true){
                TGUser user = new TGUser();
                user.setChatId(chatId);
                user.setFirstName(chat.getFirstName());
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
        sendMessageWithBaseKeyBoard(chatId, answer, name);
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

        KeyboardRow row = new KeyboardRow();
        row.add("Баланс");
        row.add("Мои каналы");

        KeyboardRow row1 = new KeyboardRow();
        row1.add("Категории");
        row1.add("Оставить отзыв");

        KeyboardRow row2 = new KeyboardRow();
        row2.add("Связь со службой поддержки");
        row2.add("Аукцион");

        keyboardRows.add(row);
        keyboardRows.add(row1);
        keyboardRows.add(row2);
        keyboardMarkup.setKeyboard(keyboardRows);
        message.setReplyMarkup(keyboardMarkup);

        executeMessage(message, nameForLog);
    }

    private void executeEditText(long chatId, String nameForLog, String textMessage, long messageId){
        // Вместо отправки ответа ниже на кнопках - меняется сообщение
        // в котором был задан вопрос
        EditMessageText message = new EditMessageText();
        message.setChatId(String.valueOf(chatId));
        message.setText(textMessage);
        message.setMessageId((int) messageId);

        try {
            execute(message);
            log.info("Ответ пользователю " + nameForLog + ", answer: "+ message.getText());
        } catch (TelegramApiException e) {
            log.error(ERROR_TEXT + e.getMessage());
        }
    }

    private void executeDeleteMessage(long chatId, String nameForLog, long messageId){
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

    private void executeMessage(SendMessage message, String nameForLog){
        try {
            execute(message);
            log.info("Ответ пользователю " + nameForLog + ", answer: "+ message.getText());
        } catch (TelegramApiException e) {
            log.error(ERROR_TEXT + e.getMessage());
        }
    }

    private void selectCategory(long chatId, String name){

        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Выберите категорию ⬇⬇⬇");

        // создание клавиатуры с кнопками в ответе на сообщение
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); //клавиаутра
        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры

        List<Category> categoryList = categoryRepository.findAll();


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

        Optional<Set<TGUser>> tgUsers = tgUserRepositoryService.findAllByChatId(chatId);
        TGUser currentUser = null;
        if (tgUsers.isPresent()){
            for (TGUser tgUser: tgUsers.get()) {
                if (tgUser.getDelete().equals(false)){
                    currentUser = tgUser;
                }
            }
            currentUser.setCurrentStep(FIND_CAT_FOR_ADD_CHAN);
            tgUserRepositoryService.saveTgUser(currentUser);
        }
        log.info("Пользователь с имененем " + name + " получил список категорий " );
    }

    // Метод первоначального создания каналов
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
            chanell.setName("Андроид");
            chanell.setLink("https://t.me/+6sD7JYkJElgxYjMy");
            chanell.currentDate(ZonedDateTime.now().plusHours(3));
        }
        Category category = categoryRepository.findOneWithEagerRelationships(idCategory).get(); // добавление категории
        chanell = chanellRepository.save(chanell);
        Set<Chanell> chanellSet = new HashSet<>(category.getChanellIds());
        chanellSet.add(chanell);
        category.setChanellIds(chanellSet);
        categoryRepository.save(category);
//        currentUser.setCurrentStep(FIRST_CREATE_CH_WITH_CAT);
        currentUser.setCurrentStep(ADD_СH_NAME);
        currentUser.setIdCurrentChannelAction(chanell.getId());
        tgUserRepositoryService.saveTgUser(currentUser);
        log.info("Пользователь с имененем " + name + " создал канал с категорией " );

        sendMessage(chatId, "Добавить название канала ⬇⬇⬇", name);

  /*      // БЛОК С ОТПРАВКОЙ КНОПКИ НА ЗАПОЛНЕНИЕ ДАННЫХ О КАНАЛЕ
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Добавить название канала ⬇⬇⬇");

        // создание клавиатуры с кнопками в ответе на сообщение
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); // клавиатура
        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        var button1 = new InlineKeyboardButton();
        button1.setText("Добавить название канала ⬇⬇⬇");
        button1.setCallbackData(ADD_СH_NAME + ":" + chanell.getId() + ":" + currentUser.getId());
        rowInLine.add(button1);
        rowsInLine.add(rowInLine);
        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        executeMessage(message, name);
        */
    }

    /*private void addChannelName(long chatId, String name, Long channelId, Long tgUserId){
        Optional<TGUser> tgUsers = TgUserRepository.findById(tgUserId);
        if (tgUsers.isPresent()){
            tgUsers.get().setCurrentStep(ADD_СH_NAME);
        }

        chanell = chanellRepository.save(chanell);
        tgUserRepositoryService.saveTgUser(tgUsers.get());
        log.info("Пользователь с имененем " + name + " создал канал с категорией " );

        // БЛОК С ОТПРАВКОЙ КНОПКИ НА ЗАПОЛНЕНИЕ ДАННЫХ О КАНАЛЕ
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Добавить название канала ⬇⬇⬇");

        // создание клавиатуры с кнопками в ответе на сообщение
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup(); //клавиаутра
        // создание списка со списками с кнопками в ответе на сообщение
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>(); // лист со строками для клавиаутуры
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        var button1 = new InlineKeyboardButton();
        button1.setText("Добавить название канала ⬇⬇⬇");
        button1.setCallbackData(ADD_СH_NAME + ":" + chanell.getId());
        rowInLine.add(button1);
        rowsInLine.add(rowInLine);
        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        executeMessage(message, name);
    }*/


    @Scheduled(fixedDelay = 60000)
    private void sendAds(){

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
