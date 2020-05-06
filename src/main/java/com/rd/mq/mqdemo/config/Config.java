package com.rd.mq.mqdemo.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.Queue;

@Configuration
public class Config {

    @Value("${activemq.broker-url1}")
    private String brokerUrl1;

    @Value("${activemq.broker-url2}")
    private String brokerUrl2;

    @Bean(name = "containerFactory1")
    public DefaultJmsListenerContainerFactory containerFactory1(DefaultJmsListenerContainerFactoryConfigurer defaultJmsListenerContainerFactoryConfigurer){
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(brokerUrl1);
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        defaultJmsListenerContainerFactoryConfigurer.configure(factory,activeMQConnectionFactory);
        factory.setMessageConverter(mappingJackson2MessageConverter());
        return factory;
    }

    @Bean(name = "containerFactory2")
    public DefaultJmsListenerContainerFactory containerFactory2(DefaultJmsListenerContainerFactoryConfigurer defaultJmsListenerContainerFactoryConfigurer){
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(brokerUrl2);
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        defaultJmsListenerContainerFactoryConfigurer.configure(factory,activeMQConnectionFactory);
        factory.setMessageConverter(mappingJackson2MessageConverter());
        return factory;
    }

    @Bean
    public MappingJackson2MessageConverter mappingJackson2MessageConverter(){
        MappingJackson2MessageConverter mappingJackson2MessageConverter = new MappingJackson2MessageConverter();
        mappingJackson2MessageConverter.setTargetType(MessageType.TEXT);
        mappingJackson2MessageConverter.setTypeIdPropertyName("_type");
        return mappingJackson2MessageConverter;
    }

    @Bean(name = "jmsTemplate1")
    public JmsTemplate jmsTemplate1() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(brokerUrl1);
        return new JmsTemplate(activeMQConnectionFactory);
    }

    @Bean(name = "jmsTemplate2")
    public JmsTemplate jmsTemplate2() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(brokerUrl2);
        return new JmsTemplate(activeMQConnectionFactory);
    }
}
