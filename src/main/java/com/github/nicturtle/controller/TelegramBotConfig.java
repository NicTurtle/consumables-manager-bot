package com.github.nicturtle.controller;

import com.github.nicturtle.controller.command.CommandContainer;
import com.github.nicturtle.service.*;
import com.github.nicturtle.model.entity.AromaOil;
import com.github.nicturtle.model.entity.Glass;
import com.github.nicturtle.model.entity.Wax;
import com.github.nicturtle.model.entity.Wick;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AromaOilService aromaOilService;

    @Autowired
    private GlassService glassService;

    @Autowired
    private WaxService waxService;

    @Autowired
    private WickService wickService;

    @PostConstruct
    private void init() throws TelegramApiException {
        telegramBotsApi.registerBot(this);
        System.out.println("Bot successfully initialized!");
    }

    public TelegramBotConfig() throws TelegramApiException {
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
                    int quantity = Integer.parseInt(message);
                    if ("addWax".equals(userStates.get(chatId).getCurrentMenu())) {
                        Wax wax = new Wax();
                        wax.setQuantity(quantity);
                        waxService.saveWax(wax);
                    } else if ("addGlass".equals(userStates.get(chatId).getCurrentMenu())) {
                        Glass glass = new Glass();
                        glass.setQuantity(quantity);
                        glass.setType("default"); // Установите значение для поля type
                        glassService.saveGlass(glass);
                    } else if ("addOil".equals(userStates.get(chatId).getCurrentMenu())) {
                        AromaOil aromaOil = new AromaOil();
                        aromaOil.setQuantity(quantity);
                        aromaOilService.saveAromaOil(aromaOil);
                    } else if ("addWicks".equals(userStates.get(chatId).getCurrentMenu())) {
                        Wick wick = new Wick();
                        wick.setQuantity(quantity);
                        wickService.saveWick(wick);
                    }
                    sendMessage(chatId, "Материал успешно добавлен.");
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
                    userState.setCurrentMenu("addGlass");
                    sendMessage(chatId, "Введите количество новых стаканов");
                } else if ("addOil".equals(update.getCallbackQuery().getData())) {
                    userState.setCurrentMenu("addOil");
                    sendMessage(chatId, "Введите количество новых масел");
                } else if ("addWicks".equals(update.getCallbackQuery().getData())) {
                    userState.setCurrentMenu("addWicks");
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
