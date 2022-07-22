package ru.ribenjyeo.telegram_bot.repository;

import ru.ribenjyeo.telegram_bot.dto.User;
import ru.ribenjyeo.telegram_bot.exception.DbException;

import java.util.List;

///////////////////////////////////////////////////////////////////////////////////
//  IUserRepository - класс, который описывает методы, для работы с записями БД  //
///////////////////////////////////////////////////////////////////////////////////

public interface IUserRepository {

    /**
     * Возвращает список записей по id
     *
     * @return запрашиваемая запись
     * @throws DbException в случае ошибки БД
     */
    User getById(int id);

    /**
     * Возвращает список записей
     *
     * @return список всех записей
     * @throws DbException в случае ошибки БД
     */
    List<User> getUserList();

    /**
     * Вставка новой записи
     *
     * @param entity новая запись
     * @throws DbException в случае ошибки БД
     */
    void insert(User entity);

    /**
     * Удаление записи
     *
     * @param entity удаляемая запись
     * @throws DbException в случае ошибки БД
     */
    void delete(User entity);
}
