package com.email.writer.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmailGeneratorService {


    private final WebClient webClient;

    @Value("${gemini.api.url}")
    private String geminiApiUrl;
    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public EmailGeneratorService(WebClient webClient){
    this.webClient = webClient;
    }


    public  String generateEmailReply (EmailRequest emailRequest){
        //Building the prompt
        String prompt = buildPrompt(emailRequest);

        //Crafting a request
    Map<String , Object> requestBody = Map.of(
            "Contents", new Object[] {
                    Map.of("Parts", new Object[]{
                            Map.of("text", prompt)
                    })
            }
    );

    // Request and Response
String response = webClient.post()
        .uri(geminiApiUrl + geminiApiKey)
        .header("Content-Type","application/json")
        .retrieve()
        .bodyToMono(String.class)
        .block();
//




    private String buildPrompt(EmailRequest emailRequest) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Generate a professional email reply for the following content.Please don't generate a subject line.");
        if (emailRequest.getTone() != null && !emailRequest.getTone().isEmpty()){
            prompt.append("Use a ").append(emailRequest.getTone()).append(" tone. ");
        }

        prompt.append("\nOriginal email: \n").append(emailRequest.getEmailContent());
        return prompt.toString();

    }
}
