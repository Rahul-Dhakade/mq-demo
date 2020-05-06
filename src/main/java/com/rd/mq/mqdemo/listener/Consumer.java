package com.rd.mq.mqdemo.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.BytesMessage;
import javax.jms.Message;
import javax.jms.TextMessage;

@Component
public class Consumer {

    @JmsListener(destination = "demo-one", containerFactory = "containerFactory1")
    @JmsListener(destination = "demo-two", containerFactory = "containerFactory2")
    public void consume(Message message) throws Exception {

        System.out.println("Received message!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        try{
            if(message instanceof TextMessage){
                System.out.println(((TextMessage) message).getText());
            }else if(message instanceof BytesMessage){
                BytesMessage bytesMessage = (BytesMessage) message;
                byte[] bytes = new byte[(int) bytesMessage.getBodyLength()];
                bytesMessage.readBytes(bytes);
                String msg = new String(bytes,"UTF-8");
                System.out.println(msg);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
