package br.com.alura.tdd.service;

import br.com.alura.tdd.modelo.Funcionario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BonusServiceTest {

    private BonusService service;

    @BeforeEach
    void initialize() {
        service = new BonusService();
    }

    @Test
    void bonus_ShouldBeZero_ForHighPayingJobs() {
        Funcionario highPaidEmployee = new Funcionario("Daniel", LocalDate.now(), new BigDecimal(11000));
        assertThrows(IllegalArgumentException.class, () -> service.calcularBonus(highPaidEmployee));
    }

    @Test
    void bonus_ShouldBeTenPercentOfSalary() {
        BigDecimal salary = new BigDecimal(1000);
        BigDecimal tenPercentSalary = salary.multiply(new BigDecimal("0.1"));
        BigDecimal bonus = service.calcularBonus(new Funcionario("Daniel", LocalDate.now(), salary));
        assertEquals(tenPercentSalary, bonus);
    }

}