package com.example;

import java.io.IOException;

import com.example.service.ServicioStatusHistoryServiceImpl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example")
public class Application implements CommandLineRunner {

	@Autowired
	ServicioStatusHistoryServiceImpl mServicioStatusHistoryServiceImpl;
	
	private static Logger LOG = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Runnable runnable = new 
		Runnable() {
					@Override
					public void run() {
						// Esto se ejecuta en segundo plano una única vez
						while (true) {
							try {
								Thread.sleep(300000);
								Document doc;
								try {
								  // Obtening Data from http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx
								  doc = Jsoup.connect("http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx").get();
									  mServicioStatusHistoryServiceImpl.consult(doc);      
							
								} catch (IOException e) {
								  // TODO Auto-generated catch block
								  e.printStackTrace();
								}
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				};
				// Creamos un hilo nuevo
				Thread hilo = new Thread(runnable);
				hilo.start();
		}

}
