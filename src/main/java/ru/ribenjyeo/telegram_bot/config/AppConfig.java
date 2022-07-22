package ru.ribenjyeo.telegram_bot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

///////////////////////////////////////////////////////////////////////////////////////
//  Прописываем конфигурация бинов нашего приложения в пакете config, тут настройки  //
//                  инициализации TelegramBotsApi и ObjectMapper                     //
///////////////////////////////////////////////////////////////////////////////////////

@Configuration
public class AppConfig {

    @Bean
    ObjectMapper customObjectMapper() {
        return new ObjectMapper ();
    }

    @Bean
    TelegramBotsApi telegramBotsApi() throws TelegramApiException {
        return new TelegramBotsApi (DefaultBotSession.class);
    }
}
