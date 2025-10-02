package com.kelvin.consulta_credito.kafka;

import com.kelvin.consulta_credito.model.Credito;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CreditoProducer {

    private final KafkaTemplate<String, Credito> kafkaTemplate;
    private static final String TOPIC = "credito-topic";

    public CreditoProducer(KafkaTemplate<String, Credito> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCredito(Credito credito) {
        kafkaTemplate.send(TOPIC, credito);
    }
}
