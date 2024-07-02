package com.github.nicturtle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@SpringBootApplication
@ComponentScan
@EntityScan(basePackages = "com.github.nicturtle.model.entity")
@EnableJpaRepositories(basePackages = "com.github.nicturtle.model.repository")
public class ConsumablesManagerBotApplication {
	public static void main(String[] args) throws TelegramApiRequestException {
		SpringApplication.run(ConsumablesManagerBotApplication.class, args);
	}
}