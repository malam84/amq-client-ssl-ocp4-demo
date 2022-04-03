package com.amq.ssl.ocp4.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.apache.activemq.ActiveMQSslConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@EnableJms
@Configuration
public class ActiveMQConfiguration {

    @Value("${spring.activemq.broker-url}")
    String brokerUrl;

    @Value("${spring.activemq.user}")
    String username;

    @Value("${spring.activemq.password}")
    String password;

    @Value("${spring.activemq.ssl.trustStorePath}")
    String trustStorePath;
    @Value("${spring.activemq.ssl.trustStorePass}")
    String trustStorePass;


    @Bean
    public ActiveMQSslConnectionFactory activeMQSslConnectionFactory() throws Exception {
        ActiveMQSslConnectionFactory factory = new ActiveMQSslConnectionFactory(brokerUrl);
        factory.setTrustStore(trustStorePath);
        factory.setTrustStorePassword(trustStorePass);
        factory.setUserName(username);
        factory.setPassword(password);
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate() throws Exception {
        JmsTemplate template = new JmsTemplate(activeMQSslConnectionFactory());
        template.setDefaultDestinationName("TestQABC");
        return template;
    }
}