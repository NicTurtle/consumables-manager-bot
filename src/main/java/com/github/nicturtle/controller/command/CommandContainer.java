package com.github.nicturtle.controller.command;


import com.github.nicturtle.controller.service.SendBotMessageService;
import com.google.common.collect.ImmutableMap;

import static com.github.nicturtle.controller.command.CommandName.*;

/**
 * Container of the {@link Command}s, which are using for handling telegram commands.
 */
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
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }

}
