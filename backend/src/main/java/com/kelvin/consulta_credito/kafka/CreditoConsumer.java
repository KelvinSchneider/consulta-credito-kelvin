package com.kelvin.consulta_credito.kafka;

import com.kelvin.consulta_credito.model.Credito;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CreditoConsumer {

    @KafkaListener(topics = "credito-topic", groupId = "credito-group")
    public void consumeCredito(Credito credito) {
        System.out.println("Mensagem recebida do Kafka: " + credito.toString());
    }
}