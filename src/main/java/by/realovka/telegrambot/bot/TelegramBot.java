package by.realovka.telegrambot.bot;

import by.realovka.telegrambot.entity.City;
import by.realovka.telegrambot.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@AllArgsConstructor
@PropertySource("classpath:application.properties")
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;

    private CityService cityService;

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onRegister() {
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.getMessage().hasText() || !update.hasMessage())
            return;
        String text = update.getMessage().getText();
        String chatId = update.getMessage().getChatId().toString();
        String answer;
//        try {
//            City city = city.showCityByName(text);
//            answer = city.getDescription();
//        } catch (CityNotFoundException ex) {
//            answer = ex.getMessage();
//        }

        switch (text){
            case "/start": answer = "Привет. Я туристический бот!";
                break;
            case "/help": answer = "Для получения информации вы должны написать название города";
                break;
        }


//        try {
//            execute(new SendMessage(chatId, answer));
//            execute(new SendMessage(chatId, "О каком городе мне рассказать?"));
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
    }
}