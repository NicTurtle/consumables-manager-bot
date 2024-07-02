package com.github.nicturtle.controller;

import com.github.nicturtle.controller.command.CommandContainer;
import com.github.nicturtle.service.SendBotMessageServiceImpl;
import com.github.nicturtle.model.MaterialStocks;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.HashMap;
import java.util.Map;

import static com.github.nicturtle.controller.command.CommandName.NO;

@Component
public class TelegramBotConfig extends TelegramLongPollingBot {
    private static final String COMMAND_PREFIX = "/";

    @Value("${bot.token}")
    private String botToken;

    private final CommandContainer commandContainer;
    private final TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
    private final Map<Long, UserState> userStates = new HashMap<>();

    @PostConstruct
    private void init() throws TelegramApiException {
        telegramBotsApi.registerBot(this);
    }
    public TelegramBotConfig () throws TelegramApiException {
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this));
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            long chatId = update.getMessage().getChatId();

            System.out.println(update.getMessage().getText());
            System.out.println(update.getMessage().getChatId());

            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();
                commandContainer.retrieveCommand(commandIdentifier).execute(update);
                userStates.put(chatId, new UserState(commandIdentifier, commandIdentifier));
                deleteTwoLastMessages(update);
            } else if (update.getMessage().getText().matches("[0-9]+")) {
                if (userStates.get(chatId) != null) {
                    if ("addWax".equals(userStates.get(chatId).getCurrentMenu())) {
                        MaterialStocks.waxQuantity += Integer.parseInt(message);
                    } else if ("addGlass".equals(userStates.get(chatId).getCurrentMenu())) {
                        MaterialStocks.glassQuantity = Integer.parseInt(message);
                    } else if ("addOil".equals(userStates.get(chatId).getCurrentMenu())) {
                        MaterialStocks.aromaOilQuantity = Integer.parseInt(message);
                    } else if ("addWicks".equals(userStates.get(chatId).getCurrentMenu())) {
                        MaterialStocks.wickQuantity = Integer.parseInt(message);
                    }
                }
            } else {
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
            }
        } else if (update.hasCallbackQuery()) {
            long chatId = update.getCallbackQuery().getFrom().getId();
            UserState userState = userStates.get(chatId);
            if (userState != null) {
                if ("addWax".equals(update.getCallbackQuery().getData())) {
                    userState.setCurrentMenu("addWax");
                    sendMessage(chatId, "Введите количество нового воска");
                } else if ("addGlass".equals(update.getCallbackQuery().getData())) {
                    userState.setCurrentMenu("addGlassMenu");
                    sendMessage(chatId, "Введите количество новых стаканов");
                } else if ("addOil".equals(update.getCallbackQuery().getData())) {
                    userState.setCurrentMenu("addOilMenu");
                    sendMessage(chatId, "Введите количество новых масел");
                } else if ("addWicks".equals(update.getCallbackQuery().getData())) {
                    userState.setCurrentMenu("addWicksMenu");
                    sendMessage(chatId, "Введите количество новых фитилей");
                }
            }
        }
    }

    private void deleteTwoLastMessages(Update update) {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(update.getMessage().getChatId());
        deleteMessage.setMessageId(update.getMessage().getMessageId());
        try {
            execute(deleteMessage);
            deleteMessage.setMessageId(update.getMessage().getMessageId() - 1);
            execute(deleteMessage);
        } catch (TelegramApiException tae) {
            tae.printStackTrace();
        }
    }

//    private void deleteLastMessage(Update update) {
//        DeleteMessage deleteMessage = new DeleteMessage();
//        if (update.hasMessage()) {
//            deleteMessage.setMessageId(update.getMessage().getMessageId());
//            deleteMessage.setChatId(update.getMessage().getChatId());
//        } else if (update.hasCallbackQuery()) {
//            deleteMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
//            deleteMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
//        } else {
//            return;
//        }
//        try {
//            execute(deleteMessage);
//        } catch (TelegramApiException tae) {
//            tae.printStackTrace();
//        }
//    }

    public void sendMessage(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
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
