package br.com.cenpros.api.api;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()){
            Message message = update.getMessage();

            if(message.hasText()){
                String text = message.getText();

                if(text.equals("/start")){
                    System.out.println("Gabriel Rezende");

                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setText("Testando");
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    sendMessage.setChatId("-788892618");
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "CenprosBot";
    }

    @Override
    public String getBotToken() {
        return "5099074353:AAFY_t51l8Pw04zC3jiAqAUP_CGjmgy3Xvo";
    }
}
