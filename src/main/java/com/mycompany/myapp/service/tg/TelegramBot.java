package com.mycompany.myapp.service.tg;

import com.mycompany.myapp.config.tg.BotConfig;
import com.mycompany.myapp.domain.TGUser;
import com.mycompany.myapp.service.TgUserRepositoryService;
import com.vdurmont.emoji.EmojiParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final Logger log = LoggerFactory.getLogger(TelegramBot.class);

    /*@Autowired
    private TGUserRepository TgUserRepository;*/

    @Autowired
    private TgUserRepositoryService tgUserRepositoryService;

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


    static final String ERROR_TEXT = "Error occurred: ";

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

            // Рассылка пользователям
            if(messageText.contains("/send") && config.getOwnerId() == chatId) {
                var textToSend = EmojiParser.parseToUnicode(messageText.substring(messageText.indexOf(" ")));
                /*var users = userRepository.findAll();
                for (User user: users){
                    sendMessage(user.getChatId(), textToSend);
                }*/
//                sendMessage(chatId, textToSend, nameForLog);
                sendMessage(chatId, textToSend, nameForLog);
            }
            else {
                switch (messageText) {
                    case "/start":
                        registerUser(update.getMessage());
                        startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                        break;

                    case "/help":
//                        sendMessage(chatId, HELP_TEXT, nameForLog);
                        sendMessage(chatId, HELP_TEXT, nameForLog);
                        break;

                    case "/register":
                        register(chatId, nameForLog);
                        break;

                    case "/oleg":
                        sendMessage(chatId, "Oleg", nameForLog);
                        break;

                    case "/channel":
                        checkFindChannelOrAddChannel(chatId, nameForLog);
                    break;
                    default:
//                        sendMessage(chatId, "Sorry, command was not recognized", nameForLog);
                        sendMessage(chatId, "Извините, команда не распознана", nameForLog);

                }
            }
        } else if (update.hasCallbackQuery()) {
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
                String text = "Вы нажали найти каналы";
                executeEditText(chatId, nameForLog, text,messageId);
            }
            else if(callbackData.equals(ADD_CHANNEL)){
                String text = "Вы нажали добавить канал";
                executeEditText(chatId, nameForLog, text,messageId);

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



    private void registerUser(Message msg) {

        if(tgUserRepositoryService.findAllByChatId(msg.getChatId()).isEmpty()){
            var chatId = msg.getChatId();
            var chat = msg.getChat();
            var userId = msg.getChat().getId();
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
                var chatId = msg.getChatId();
                var chat = msg.getChat();
                var userId = msg.getChat().getId();
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

    private void executeMessage(SendMessage message, String nameForLog){
        try {
            execute(message);
            log.info("Ответ пользователю " + nameForLog + ", answer: "+ message.getText());
        } catch (TelegramApiException e) {
            log.error(ERROR_TEXT + e.getMessage());
        }
    }

    private void selectCategory(long chatId, String nameForLog){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Выберите категорию каналов");

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

        executeMessage(message, nameForLog);
    }

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
