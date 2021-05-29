package by.realovka.telegrambot.bot;

import by.realovka.telegrambot.entity.City;
import by.realovka.telegrambot.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;

    private final CityService cityService;

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }


    @Override
    public void onUpdateReceived(Update update) {
        if (!update.getMessage().hasText() || !update.hasMessage())
            return;
        String text = update.getMessage().getText();
        String chatId = update.getMessage().getChatId().toString();
        String answer = null;
        City city;
        if(text.startsWith("/")) {
            switch (text) {
                case "/start":
                    answer = "Привет! Я туристический бот. Введите город для получения информации";
                    break;
                case "/help":
                    answer = "Ввведите город с большой буквы";
                    break;
            }
        } else {
            city = cityService.findByName(text);
           answer = city.getDescription();
        }

        try {
            execute(new SendMessage(chatId, answer));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
