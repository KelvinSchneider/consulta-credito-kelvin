package com.kelvin.consulta_credito.service;

import com.kelvin.consulta_credito.kafka.CreditoProducer;
import com.kelvin.consulta_credito.model.Credito;
import com.kelvin.consulta_credito.repository.CreditoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CreditoService {

    private final CreditoRepository creditoRepository;
    private final CreditoProducer creditoProducer;

    public CreditoService(CreditoRepository creditoRepository, CreditoProducer creditoProducer) {
        this.creditoRepository = creditoRepository;
        this.creditoProducer = creditoProducer;
    }

    public List<Credito> findAll() {
        return creditoRepository.findAll();
    }

    public List<Credito> findByNumeroNfse(String numeroNfse) {
        List<Credito> creditos = creditoRepository.findByNumeroNfse(numeroNfse);
        creditos.forEach(creditoProducer::sendCredito);
        return creditos;
    }

    public Credito findByNumeroCredito(String numeroCredito) {
        Credito credito = creditoRepository.findByNumeroCredito(numeroCredito);
        Optional.ofNullable(credito).ifPresent(creditoProducer::sendCredito);
        return credito;
    }

}
