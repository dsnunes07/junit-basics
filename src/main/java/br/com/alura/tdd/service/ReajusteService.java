package br.com.alura.tdd.service;

import br.com.alura.tdd.modelo.Funcionario;
import br.com.alura.tdd.modelo.Performance;

import java.math.BigDecimal;

public class ReajusteService {
    public BigDecimal calculateRaise (Funcionario f, Performance p) {
        f.raiseSalary(p.raisePercentual());
        return p.raisePercentual();
    }
}
