package com.mycompany.myapp.service.tg;

import com.vdurmont.emoji.EmojiParser;

public class TelegramServiceForMessage {

    public static String test(){
        return "String";
    }

    public static String startCommandReceived(String name) {

        String answer = EmojiParser.parseToUnicode("Hi, " + name + ", nice to meet you!" + " :blush:" + " :grinning:");
        return answer;
    }
}
