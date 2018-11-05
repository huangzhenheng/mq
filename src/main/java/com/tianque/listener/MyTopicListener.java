package com.tianque.listener;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by 123 on 2017/12/27.
 */
@Component
public class MyTopicListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println("From Topic Message -> " + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
