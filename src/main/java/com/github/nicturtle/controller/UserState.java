package com.github.nicturtle.controller;

public class UserState {
    private String currentMenu;
    private String lastCommand;
    private int lastBotMessageId;

    public UserState(String currentMenu, String lastCommand) {
        this.currentMenu = currentMenu;
        this.lastCommand = lastCommand;
    }

    public String getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(String currentMenu) {
        this.currentMenu = currentMenu;
    }

    public String getLastCommand() {
        return lastCommand;
    }

    public void setLastCommand(String lastCommand) {
        this.lastCommand = lastCommand;
    }

    public int getLastBotMessageId() {
        return lastBotMessageId;
    }

    public void setLastBotMessageId(int lastBotMessageId) {
        this.lastBotMessageId = lastBotMessageId;
    }
}

