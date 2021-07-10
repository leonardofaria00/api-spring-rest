package com.dominio.api.jms;

import java.util.concurrent.CountDownLatch;

import org.springframework.stereotype.Component;

@Component
public class ReceptorDeMensagem {

	private CountDownLatch latch = new CountDownLatch(1);

	public void recebedorDeMensagens(String message) {
		System.out.println("Recebida <" + message + ">");
		latch.countDown();
	}

	public CountDownLatch getLatch() {
		return latch;
	}
}
