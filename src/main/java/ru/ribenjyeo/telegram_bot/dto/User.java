package ru.ribenjyeo.telegram_bot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

///////////////////////////
//  Модель пользователя  //
///////////////////////////

@Data
@RequiredArgsConstructor
public class User {
    /**
     * Id пользователя
     */
    @JsonProperty("id")
    private final int id;
    /**
     * Имя пользователя
     */
    @JsonProperty("name")
    private final String name;
    /**
     * Мысли пользователя
     */
    @JsonProperty("description")
    private final String description;

    private String startWord = "";

    @Override
    public String toString() {
        return startWord + description;
    }
}