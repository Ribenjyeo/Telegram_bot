package ru.ribenjyeo.telegram_bot.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ribenjyeo.telegram_bot.dto.User;
import ru.ribenjyeo.telegram_bot.dto.UserMapper;
import ru.ribenjyeo.telegram_bot.exception.DbException;

import java.util.List;

///////////////////////////////////////////////////////////////////////////////////
// User-Repository - класс, который реализует методы интерфейса IUserRepository  //
///////////////////////////////////////////////////////////////////////////////////

@Slf4j
@Repository
public class UserRepository implements IUserRepository {

    // Константы
    /**
     * Поиск конкректного пользователя
     */
    private static final String SQL_SELECT_BY_NAME = "" +
            "SELECT id, user_name, description FROM user_table WHERE id=?";
    /**
     * Получение списка всех пользователей
     */
    private static final String SQL_SELECT_LIST = "" +
            "SELECT id, user_name, description FROM user_table";
    /**
     * Добавление записи в БД
     */
    private static final String SQL_INSERT = "" +
            "INSERT INTO user_table (user_name, description) VALUES (?, ?)";
    /**
     * Удаление записи из БД
     */
    private static final String SQL_DELETE = "" +
            "DELETE FROM user_table WHERE id = ?";

    protected final static UserMapper USER_MAPPER = new UserMapper ();

    // beans
    protected final JdbcTemplate template;


    /**
     * Req-args constructor for Spring DI
     */
    public UserRepository(@Qualifier("bot-db") JdbcTemplate template) {
        this.template = template;
    }

    /**
     * Возвращает список записей по id
     *
     * @return запрашиваемая запись
     * @throws DbException в случае ошибки БД
     */
    @Override
    public User getById(int id) throws DbException {
        try {
            return DataAccessUtils.singleResult (
                    template.query (SQL_SELECT_BY_NAME, USER_MAPPER, id));
        } catch (DataAccessException exception) {
            throw new DbException (exception);
        }
    }

    /**
     * Возвращает список записей
     *
     * @return запрашиваемая запись
     * @throws DbException в случае ошибки БД
     */
    @Override
    public List<User> getUserList() throws DbException {
        try {
            return template.query (SQL_SELECT_LIST, USER_MAPPER);
        } catch (DataAccessException exception) {
            throw new DbException (exception);
        }
    }

    /**
     * Вставка новой записи
     *
     * @param entity новая запись
     * @throws DbException в случае ошибки БД
     */
    @Override
    public void insert(User entity) throws DbException {
        try {
            // В параметры запроса все поля сущности кроме идентификатора, т.к. он serial и генерируется автоматически
            var result = template.update (SQL_INSERT,
                    entity.getName (),
                    entity.getDescription ());
            if (result != 1) log.trace ("UserRepository.update() with {} rows inserted", entity);
            log.info ("insert({}) result={}", entity, result);
        } catch (DataAccessException exception) {
            throw new DbException (exception);
        }
    }

    /**
     * Удаление записи
     *
     * @param entity удаляемая запись
     * @throws DbException в случае ошибки БД
     */
    @Override
    public void delete(User entity) throws DbException {
        try {
            var result = template.update (SQL_DELETE, entity.getId ());
            if (result != 1) log.trace ("UserRepository.delete() with {} rows inserted", entity);
            log.info ("delete({}) result={}", entity, result);
        } catch (DataAccessException exception) {
            throw new DbException (exception);
        }
    }
}