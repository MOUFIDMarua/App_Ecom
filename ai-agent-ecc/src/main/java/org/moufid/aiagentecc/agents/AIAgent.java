package org.moufid.aiagentecc.agents;

import org.moufid.aiagentecc.tools.AgentTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import reactor.core.publisher.Flux;

@Service
public class AIAgent {
    private ChatClient chatClient;

    public AIAgent(ChatClient.Builder chatClient, ChatMemory chatMemory, AgentTools agentTools, VectorStore vectorStore) {
        this.chatClient = chatClient
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .defaultTools(agentTools)
                .defaultAdvisors(new QuestionAnswerAdvisor(vectorStore))
                .build();
    }

    public Flux<String> onQuery(String query) {
        return chatClient.prompt()
                .user(query)
                .stream()
                .content();
    }
}