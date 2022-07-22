package ru.ribenjyeo.telegram_bot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

////////////////////////////////////////////////////////////////////////
//  DbException - класс, который обрабатывает ошибки связанные с БД,  //
//                  наследуется от RuntimeException                   //
////////////////////////////////////////////////////////////////////////

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DbException extends RuntimeException {

    private static final String MESSAGE = "Ошибка БД";

    public DbException(String message) {
        super (message);
    }

    public DbException(Throwable cause) {
        super (MESSAGE, cause);
    }
}
