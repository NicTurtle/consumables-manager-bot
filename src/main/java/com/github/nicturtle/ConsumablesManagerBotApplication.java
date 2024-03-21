package com.github.nicturtle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@SpringBootApplication
public class ConsumablesManagerBotApplication {
	public static void main(String[] args) throws TelegramApiRequestException {
		SpringApplication.run(ConsumablesManagerBotApplication.class, args);
	}
}
