package com.kelvin.consulta_credito.service;

import com.kelvin.consulta_credito.model.Credito;
import com.kelvin.consulta_credito.repository.CreditoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CreditoService {

    private final CreditoRepository creditoRepository;

    public CreditoService(CreditoRepository creditoRepository) {
        this.creditoRepository = creditoRepository;
    }

    public List<Credito> findAll() {
        return creditoRepository.findAll();
    }

    public List<Credito> findByNumeroNfse(String numeroNfse) {
        return creditoRepository.findByNumeroNfse(numeroNfse);
    }

    public Credito findByNumeroCredito(String numeroCredito) {
        return creditoRepository.findByNumeroCredito(numeroCredito);
    }

}
