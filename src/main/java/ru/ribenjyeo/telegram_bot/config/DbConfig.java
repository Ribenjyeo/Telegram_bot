package ru.ribenjyeo.telegram_bot.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

////////////////////////////////////////////////////////////
//  Класс в котором описывается настройка SpringDataJdbc  //
////////////////////////////////////////////////////////////

@Configuration
public class DbConfig extends DefaultDbConfig {

    @Bean
    @Qualifier("bot-db")
    @ConfigurationProperties(prefix = "app.db.bot-db")
    SpringDataJdbcProperties gitlabJdbcProperties() {
        return new SpringDataJdbcProperties ();
    }

    @Bean
    @Qualifier("bot-db")
    public DataSource gitlabDataSource(@Qualifier("bot-db") SpringDataJdbcProperties properties) {
        return hikariDataSource ("db", properties);
    }

    @Bean
    @Qualifier("bot-db")
    JdbcTemplate gitlabJdbcTemplate(@Qualifier("bot-db") DataSource dataSource) {
        return new JdbcTemplate (dataSource);
    }

    @Data
    @NoArgsConstructor
    public static class SpringDataJdbcProperties {

        // контаста
        private static final String H2_DATABASE_DRIVER = "org.h2.Driver";

        /**
         * JDBC URL
         */
        String url;
        /**
         * JDBC драйвер
         */
        String driver;
        /**
         * JDBC имя
         */
        String user;
        /**
         * JDBC пароль
         */
        String password;
        /**
         * Hikari / Vertica maxPoolSize property
         */
        String poolSize;
        /**
         * Минимальный размер пула
         */
        int minPoolSize = 4;
        /**
         * Максимальный размер пула
         */
        int maxPoolSize = 10;
        /**
         * Это свойство управляет максимальным количеством времени (в миллисекундах), в течение
         * которого соединение может бездействовать в пуле. Значение 0 означает, что неиспользуемые
         * соединения никогда не удаляются из пула.
         */
        long idleTimeout;
        /**
         * Это свойство управляет максимальным временем жизни соединения в пуле. Когда соединение достигает
         * этого тайм-аута, даже если оно недавно использовалось, оно будет исключено из пула.Используемое
         * соединение никогда не будет удалено, только когда оно простаивает, оно будет удалено.
         */
        long maxLifetime;
        /**
         * Объем размера вставки
         */
        Integer bulkSize;


        /**
         * All-args constructor for {@link SpringDataJdbcProperties#toString()} (logging)
         *
         * @param url      JDBC driver class name property
         * @param driver   JDBC driver class name property
         * @param user     JDBC username property
         * @param password JDBC password property
         * @param poolSize Hikari / Vertica maxPoolSize property
         * @param bulkSize bulk insert size
         */
        public SpringDataJdbcProperties(
                String url, String driver, String user, String password, String poolSize, Integer bulkSize) {
            this.url = url;
            this.driver = driver;
            this.user = user;
            this.password = password;
            this.poolSize = poolSize;
            this.bulkSize = bulkSize;
        }

        /**
         * Возвращает истину, если экземпляр описывает in-memory H2 database
         *
         * @return истина, если экземпляр описывает in-memory H2 database
         */
        public boolean isH2Database() {
            return driver.equals (H2_DATABASE_DRIVER);
        }

        /**
         * Возвращает строковое представление экземпляра объекта в формате JSON
         *
         * @return строковое представление экземпляра объекта в формате JSON
         */
        @Override
        public String toString() {
            var props = new SpringDataJdbcProperties (
                    url, driver, user, ((password == null) || password.isEmpty ()) ? "" : "*****", poolSize, bulkSize);
            return Json.encode (props);
        }

    }

}