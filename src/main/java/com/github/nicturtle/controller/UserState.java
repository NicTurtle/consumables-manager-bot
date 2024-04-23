package com.github.nicturtle.controller;

public class UserState {
    private String currentMenu;
    private String lastCommand;

    public UserState(String currentMenu, String lastCommand) {
        this.currentMenu = currentMenu;
        this.lastCommand = lastCommand;
    }

    // Геттеры и сеттеры
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
}

