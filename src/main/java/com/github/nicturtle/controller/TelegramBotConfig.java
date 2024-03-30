package com.github.nicturtle.controller;

import com.github.nicturtle.model.MaterialStocks;
import com.github.nicturtle.controller.command.CommandContainer;
import com.github.nicturtle.controller.service.SendBotMessageServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import static com.github.nicturtle.controller.command.CommandName.NO;

@Component
public class TelegramBotConfig extends TelegramLongPollingBot {
    public static String COMMAND_PREFIX = "/";
    @Value("${bot.token}")
    private String botToken;
    private final CommandContainer commandContainer;
    private final TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);

    @PostConstruct
    private void init() throws TelegramApiException {
        telegramBotsApi.registerBot(this);
    }
    public TelegramBotConfig () throws TelegramApiException {
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this));
    }
    @Override
    public void onUpdateReceived(Update update) {

        System.out.println(update.getMessage().getText());

        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0];
                commandContainer.retrieveCommand(commandIdentifier).execute(update);
                deleteTwoMessages(update);
            } else {
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
            }
        }
    }
    public void deleteTwoMessages (Update update) {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(update.getMessage().getChatId());
        deleteMessage.setMessageId(update.getMessage().getMessageId());
        try {
            execute(deleteMessage);
            deleteMessage.setMessageId(update.getMessage().getMessageId()-1);
            execute(deleteMessage);
        }catch(TelegramApiException tae) {
            throw new RuntimeException(tae);
        }
    }
    @Override
    public String getBotToken() {
        return botToken;
    }
    @Override
    public String getBotUsername() {
        return "consumables-manager-bot";
    }
}
