package com.github.fabriciolfj.ia;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatClient chatClient;

    public String queryAi(final String prompt) {
        return chatClient.call(prompt);
    }

    public String getCityGuide(final String city, final String interest) {
        var template = """
                Sou um turista, o que fazer na cidade {city}.
                Estou interessado em {interest}
                """;

        final PromptTemplate promptTemplate = new PromptTemplate(template);
        final Map<String, Object> params = Map.of("city", city, "interest", interest);

        final var prompt = promptTemplate.create(params);
        return chatClient.call(prompt).getResult().getOutput().getContent();
    }
}
