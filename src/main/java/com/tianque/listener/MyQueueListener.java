package com.tianque.listener;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * Created by 123 on 2017/12/27.
 */
@Component
public class MyQueueListener implements MessageListener {
    @Override
    public void onMessage(Message message) {

        if (message instanceof TextMessage){
            TextMessage textMessage = (TextMessage) message;
            try {
                if (textMessage.getJMSType().equals("user")){
                    System.out.println(textMessage.getJMSType());
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }

            try {
                User  user=new Gson().fromJson(textMessage.getText(),User.class);
                System.out.println(user.getName());
            } catch (JMSException e) {
                e.printStackTrace();
            }

        }

    }
}
