package com.dominio.api.jms;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.dominio.api.ApiApplication;

/*
 * Messaging with RabbitMQ
 * https://spring.io/guides/gs/messaging-rabbitmq/
 */
@Component
public class Runner implements CommandLineRunner {

	private final RabbitTemplate rabbitTemplate;
	private final ReceptorDeMensagem receptor;

	public Runner(ReceptorDeMensagem receptor, RabbitTemplate rabbitTemplate) {
		this.receptor = receptor;
		this.rabbitTemplate = rabbitTemplate;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Enviando message...");
		rabbitTemplate.convertAndSend(ApiApplication.nomeDeTrocaDoTopico, "foo.bar.baz", "Olá para RabbitMQ!");
		receptor.getLatch().await(10000, TimeUnit.MILLISECONDS);
	}

}