package com.dominio.api;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.dominio.api.jms.ReceptorDeMensagem;

@SpringBootApplication
public class ApiApplication {

	public static final String nomeDeTrocaDoTopico = "spring-boot-exchange";

	static final String filaName = "spring-boot";

	@Bean
	Queue queue() {
		return new Queue(filaName, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(nomeDeTrocaDoTopico);
	}

	/*
	 * Nesse caso, usamos uma troca de tópico e a fila é vinculada a uma chave de roteamento de foo.bar.#, o que significa que
	 * todas as mensagens enviadas com uma chave de roteamento que começa com foo.bar.são roteadas para a fila.
	 */
	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");// une a fila, o tópico com a chave de roteamento
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(filaName);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	MessageListenerAdapter AdaptadorDeEscuta(ReceptorDeMensagem recebedor) {
		return new MessageListenerAdapter(recebedor, "recebedorDeMensagens");
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
