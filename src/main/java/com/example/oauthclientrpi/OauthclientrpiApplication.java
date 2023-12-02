package com.example.oauthclientrpi;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class OauthclientrpiApplication implements ApplicationRunner {

  public static void main(String[] args) {
    SpringApplication.run(OauthclientrpiApplication.class, args);
  }

  @Autowired
  private WebClient webClient;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    Object block = webClient.get()
        .uri("http://localhost:8080/kafka/publish/test")
        .attributes(clientRegistrationId("admin"))
        .retrieve()
        .bodyToMono(Object.class).block();

    System.out.println(block);
  }
}
