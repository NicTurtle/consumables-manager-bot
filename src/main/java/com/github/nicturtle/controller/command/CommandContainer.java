package com.github.nicturtle.controller.command;

import com.github.nicturtle.service.SendBotMessageService;
import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Component;

import static com.github.nicturtle.controller.command.CommandName.*;

@Component
public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService) {

        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService))
                .put(RESTOCK.getCommandName(), new RestockCommand(sendBotMessageService))
                .put(SOLD.getCommandName(), new SoldCommand(sendBotMessageService))
                .put(PRESENTED.getCommandName(), new PresentedCommand(sendBotMessageService))
                .put(STOCKS.getCommandName(), new StocksCommand(sendBotMessageService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .put(ADD_NEW_MATERIAL.getCommandName(), new AddMaterialCommand(sendBotMessageService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }

}
