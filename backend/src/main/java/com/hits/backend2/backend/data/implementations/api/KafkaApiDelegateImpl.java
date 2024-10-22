package com.hits.backend2.backend.data.implementations.api;

import com.hits.backend2.crutch.api.KafkaApiDelegate;
import com.hits.backend2.crutch.model.MessageRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class KafkaApiDelegateImpl implements KafkaApiDelegate {
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public ResponseEntity<Void> postMessageToTopic(String topicId, MessageRequest messageRequest) {
        kafkaTemplate.send(topicId, messageRequest.getMessage());

        return ResponseEntity.ok().build();
    }
}
