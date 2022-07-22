package ru.ribenjyeo.telegram_bot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.ribenjyeo.telegram_bot.dto.User;
import ru.ribenjyeo.telegram_bot.repository.IUserRepository;

/////////////////////////////////////////////////////////////////////////////////////////////////
// Producer, как раз тот класс, который шлет сообщения в топик users, а так же здесь мы можем  //
//              изменять формат самого сообщения и данные, которые он отправляет               //
/////////////////////////////////////////////////////////////////////////////////////////////////

@Service
@Slf4j
public class Producer {

    private static final String TOPIC = "users";
    protected final IUserRepository repo;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public Producer(IUserRepository repo) {
        this.repo = repo;
    }

    public void sendMessage(User user) {
        if (user.getName () == null || user.getDescription ().isEmpty ())
            log.info ("#### Empty name/description message");
        log.info ("#### Producing message [user={}]", user);
        kafkaTemplate.send (TOPIC, "Writing in log -> " + user);
    }
}