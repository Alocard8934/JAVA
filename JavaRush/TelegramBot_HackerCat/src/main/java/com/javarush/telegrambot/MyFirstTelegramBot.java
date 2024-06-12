package com.javarush.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import java.util.Map;

import static com.javarush.telegrambot.TelegramBotContent.*;

public class MyFirstTelegramBot extends MultiSessionTelegramBot {
    public static final String NAME = "new_haker_catBot"; // TODO: добавьте имя бота в кавычках
    public static final String TOKEN = "6761719661:AAHFsoDCkm3u4Q3RfkisQGdBFV09tA90uS8"; //TODO: добавьте токен бота в кавычках

    public MyFirstTelegramBot() {
        super(NAME, TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update updateEvent) {
        // TODO: основной функционал бота будем писать здесь
        // отобразить сообщение о начале игры - нужно взломать холодильник
        if (getMessageText().equals("/start")) {
            setUserGlory(0);
            sendPhotoMessageAsync("step_1_pic");
            sendTextMessageAsync(STEP_1_TEXT, Map.of("Взлом холодильника", "step_1_btn"));
        }
        // отобразить количество очков славы
        if (getMessageText().equals("/glory")) {
            int glory = getUserGlory();
            sendTextMessageAsync("Ваши очки славы: " + glory);
        }

        if (getCallbackQueryButtonKey().equals("step_1_btn")) {
            addUserGlory(20);
            sendPhotoMessageAsync("step_2_pic");
            sendTextMessageAsync(STEP_2_TEXT, Map.of("Взять сосиску! + 20 славы", "step_2_btn",
                    "Взять рыбку! + 20 славы", "step_2_btn",
                    "Скинуть банку с огурцами! +20 славы", "step_2_btn"));
        }

        // взламываем робот - пылесос
        if (getCallbackQueryButtonKey().equals("step_2_btn")) {
            addUserGlory(20);
            sendPhotoMessageAsync("step_3_pic");
            sendTextMessageAsync(STEP_3_TEXT, Map.of("Взлом робота-пылесоса", "step_3_btn"));
        }
        if (getCallbackQueryButtonKey().equals("step_3_btn")) {
            addUserGlory(30);
            sendPhotoMessageAsync("step_4_pic");
            sendTextMessageAsync(STEP_4_TEXT, Map.of("Отправить робот пылесос за едой! + 30 славы", "step_4_btn",
                                                        "Покататься на роботе пылесосе! + 30 славы", "step_4_btn",
                                                        "Убежать от робота вылесоса! +30 славы", "step_4_btn"));
        }
        // взламываем камеру Go-Pro
        if (getCallbackQueryButtonKey().equals("step_4_btn")) {
            addUserGlory(30);
            sendPhotoMessageAsync("step_5_pic");
            sendTextMessageAsync(STEP_5_TEXT, Map.of("Надеть и включить GoPro!", "step_5_btn"));
        }
        if (getCallbackQueryButtonKey().equals("step_5_btn")) {
            addUserGlory(40);
            sendPhotoMessageAsync("step_6_pic");
            sendTextMessageAsync(STEP_6_TEXT, Map.of("Бегать по крышам, снимать на GoPro! +40 славы", "step_6_btn",
                                                    "С GoPro нападать на других котов из засады! +40 славы", "step_6_btn",
                                                    "С GoPro нападать на собак из засады! +40 славы", "step_6_btn"));
        }
        // взламываем комьютер
        if (getCallbackQueryButtonKey().equals("step_6_btn")) {
            addUserGlory(40);
            sendPhotoMessageAsync("step_7_pic");
            sendTextMessageAsync(STEP_7_TEXT, Map.of("Взлом пароля компьютера", "step_7_btn"));
        }
        if (getCallbackQueryButtonKey().equals("step_7_btn")) {
            addUserGlory(50);
            sendPhotoMessageAsync("step_8_pic");
            sendTextMessageAsync(STEP_8_TEXT, Map.of("Выйти во двор и похвастаться", "step_8_btn"));
        }
        // хвастаемся другим котам
        if (getCallbackQueryButtonKey().equals("step_8_btn")) {
            sendPhotoMessageAsync("final_pic");
            sendTextMessageAsync(FINAL_TEXT);
        }
    }


    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}

//        if(getMessageText().equals("/start")){
//sendTextMessageAsync("Welches ist Ihr Lieblingstier?", Map.of("Die Katze","cat_btn","Der Hund", "dog_btn"));
//        }
//        if(getMessageText().equals("/bye")){
//sendTextMessageAsync("Asta la vista, baby!");
//        }
//                if(getCallbackQueryButtonKey().equals("cat_btn")){
//sendPhotoMessageAsync("step_4_pic");
//        }
//                if(getCallbackQueryButtonKey().equals("dog_btn")){
//sendPhotoMessageAsync("step_6_pic");
//        }