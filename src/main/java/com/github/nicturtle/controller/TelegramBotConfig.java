package com.github.nicturtle.controller;

import com.github.nicturtle.controller.command.CommandContainer;
import com.github.nicturtle.controller.service.SendBotMessageServiceImpl;
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

    @PostConstruct
    private void init() throws TelegramApiException {
        telegramBotsApi.registerBot(this);
    }
    public TelegramBotConfig () throws TelegramApiException {
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this));
    }
    private Map<Long, UserState> userStates = new HashMap<>();

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update);

        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            long chatId = update.getMessage().getChatId();

            //view message in console
            System.out.println(update.getMessage().getText());
            System.out.println(update.getMessage().getChatId());

            //TODO: create a collections and beans (Spring)
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();
                commandContainer.retrieveCommand(commandIdentifier).execute(update);
                userStates.put(chatId, new UserState(commandIdentifier, commandIdentifier));
                deleteTwoLastMessages(update);
            } else if (update.getMessage().getText().matches("[0-9]+")) {
                if (userStates.get(chatId).getCurrentMenu().equals("addWax")) {
                    MaterialStocks.waxQuantity += Integer.parseInt(message);
                } else if (userStates.get(chatId).getCurrentMenu().equals("addGlass")) {
                    MaterialStocks.glassQuantity = Integer.parseInt(message);
                } else if (userStates.get(chatId).getCurrentMenu().equals("addOil")) {
                    MaterialStocks.aromaOilQuantity = Integer.parseInt(message);
                } else if (userStates.get(chatId).getCurrentMenu().equals("addWicks")) {
                    MaterialStocks.wickQuantity = Integer.parseInt(message);
                }
            } else {
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
            }
        } else if (update.hasCallbackQuery()) {
            long chatId = update.getCallbackQuery().getFrom().getId();
            UserState userState = userStates.get(chatId);
            if (update.getCallbackQuery().getData().equals("addWax")) {
                //deleteLastMessage(update);
                userState.setCurrentMenu("addWax");
                sendMessage(chatId, "введите количество нового воска");
            } else if (update.getCallbackQuery().getData().equals("addGlass")) {
                //deleteLastMessage(update);
                userState.setCurrentMenu("addGlassMenu");
                sendMessage(chatId, "введите количество новых стаканов");
            } else if (update.getCallbackQuery().getData().equals("addOil")) {
                //deleteLastMessage(update);
                userState.setCurrentMenu("addOilMenu");
                sendMessage(chatId, "введите количество новоых масел");
            } else if (update.getCallbackQuery().getData().equals("addWicks")) {
                //deleteLastMessage(update);
                userState.setCurrentMenu("addWicksMenu");
                sendMessage(chatId, "введите количество новых фитилей");
            }
        }
    }

    private void deleteTwoLastMessages (Update update) {
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

    //TODO:fix
    private void deleteLastMessage (Update update) {
        DeleteMessage deleteMessage = new DeleteMessage();
        if(update.hasMessage()) {
            deleteMessage.setMessageId(update.getMessage().getMessageId());
            deleteMessage.setChatId(update.getMessage().getChatId());
        } else if (update.hasCallbackQuery()) {
            deleteMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
            deleteMessage.setChatId(update.getMessage().getChatId());
        } else {
            return;
        }
        try {
            execute(deleteMessage);
        }catch(TelegramApiException tae) {
            throw new RuntimeException(tae);
        }
    }

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
