package com.github.nicturtle.controller.command;

import lombok.Getter;


@Getter
public enum CommandName {

    START ("/start"),
    RESTOCK ("/restock"),
    SOLD ("/sold"),
    PRESENTED ("/presented"),
    STOCKS ("/stocks"),
    ADD_NEW_MATERIAL ("/add"),
    NO("nocommand");

    private final String commandName;
    CommandName(String commandName) {
        this.commandName = commandName;
    }
    public String getCommandName() {
        return commandName;
    }
}
