package ru.ribenjyeo.telegram_bot.exception;

import lombok.extern.slf4j.Slf4j;

//////////////////////////////////////////////////////////////////////////////////////
//  BaseException - класс, который наследуется от RuntimeException, в конструкторе  //
//                 принимает 2 параметра - сообщение и тело ошибки                  //
//////////////////////////////////////////////////////////////////////////////////////

@Slf4j
public class BaseException extends RuntimeException {

    public BaseException(String msg, Throwable t) {
        super (msg, t);
        log.error (msg, t);
    }

    public BaseException(String msg) {
        super (msg);
        log.error (msg);
    }

}