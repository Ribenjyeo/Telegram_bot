package ru.ribenjyeo.telegram_bot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/////////////////////////////////////////////////////////////////////////////////////////
//  NotFoundException - класс, который вызывается, когда ответ не найден, наследуется  //
//                                  от BaseException                                   //
/////////////////////////////////////////////////////////////////////////////////////////


@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends BaseException {

    private final static String MESSAGE = "Не найдено";

    public NotFoundException(Throwable t) {
        super (MESSAGE, t);
    }

    public NotFoundException() {
        super (MESSAGE);
    }
}