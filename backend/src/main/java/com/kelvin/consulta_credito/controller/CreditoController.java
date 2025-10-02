package com.kelvin.consulta_credito.controller;

import com.kelvin.consulta_credito.model.Credito;
import com.kelvin.consulta_credito.service.CreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creditos")
@CrossOrigin(origins = "http://localhost:4200")
public class CreditoController {

    @Autowired
    private final CreditoService creditoService;

    public CreditoController(CreditoService creditoService) {
        this.creditoService = creditoService;
    }

    @GetMapping
    public List<Credito> getCreditos() {
        return creditoService.findAll();
    }

    @GetMapping("/{numeroNfse}")
    public List<Credito> getCreditosByNfse(@PathVariable String numeroNfse) {
        return creditoService.findByNumeroNfse(numeroNfse);
    }

    @GetMapping("/credito/{numeroCredito}")
    public Credito getCreditoByNumero(@PathVariable String numeroCredito) {
        return creditoService.findByNumeroCredito(numeroCredito);
    }

}
