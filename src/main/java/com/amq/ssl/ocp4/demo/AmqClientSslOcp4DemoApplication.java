package com.amq.ssl.ocp4.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class AmqClientSslOcp4DemoApplication implements CommandLineRunner{

	@Autowired
	private JmsTemplate jmsTemplate;

	public static void main(String[] args) {

		SpringApplication.run(AmqClientSslOcp4DemoApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		sendMessage("Hello World!");
	}

	public void sendMessage(String text) {
		System.out.println(String.format("Sending '%s'", text));
		this.jmsTemplate.convertAndSend("TestQABC", text);
	}

	@JmsListener(destination = "TestQABC")
	public void receiveMessage(String text) {

		System.out.println(String.format("Received '%s'", text));
	}

}
