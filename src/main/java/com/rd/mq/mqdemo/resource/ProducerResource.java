package com.rd.mq.mqdemo.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;

@RestController
@RequestMapping("/rest/publish")
public class ProducerResource {

    @Qualifier(value = "jmsTemplate1")
    @Autowired
    JmsTemplate jmsTemplate1;

    @Qualifier(value = "jmsTemplate2")
    @Autowired
    JmsTemplate jmsTemplate2;

    @GetMapping("/q1/{message}")
    public String publish(@PathVariable("message")
                          final String message) {

        jmsTemplate1.convertAndSend("demo-one", message);

        return "Published Successfully";
    }

    @GetMapping("/q2/{message}")
    public String publish2(@PathVariable("message")
                          final String message) {

        jmsTemplate2.convertAndSend("demo-two", message);

        return "Published Successfully";
    }
}
