package com.kelvin.consulta_credito.kafka;

import com.kelvin.consulta_credito.model.Credito;

public record CreditoMessage(Credito credito, String tipoAcao) {}
