package br.com.alura.tdd.service;

import static org.junit.jupiter.api.Assertions.*;

import br.com.alura.tdd.modelo.Funcionario;
import br.com.alura.tdd.modelo.Performance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReajusteServiceTest {

    private ReajusteService service;

    @BeforeEach
    void initialize() {
        service = new ReajusteService();
    }

    @Test
    void employeeWithPoorPerformance_shouldReceiveThreePercentRaise() {
        BigDecimal salary = new BigDecimal("1000");
        Funcionario poorPerformanceEmployee = new Funcionario("Anne", LocalDate.now(), salary);
        BigDecimal raise = service.calculateRaise(poorPerformanceEmployee, Performance.POOR);
        assertEquals(new BigDecimal("1.03"), raise);
        assertEquals(new BigDecimal("1030.00"), poorPerformanceEmployee.getSalario());
    }

    @Test
    void employeeWithAveragePerformance_shouldReceiveFifteenPercentRaise() {
        BigDecimal salary = new BigDecimal("1000");
        Funcionario avgPerformanceEmployee = new Funcionario("Ben", LocalDate.now(), salary);
        BigDecimal raise = service.calculateRaise(avgPerformanceEmployee, Performance.AVERAGE);
        assertEquals(new BigDecimal("1.15"), raise);
        assertEquals(new BigDecimal("1150.00"), avgPerformanceEmployee.getSalario());
    }

    @Test
    void employeeWithGoodPerformance_shouldReceiveThirtyPercentRaise() {
        ReajusteService service = new ReajusteService();
        BigDecimal salary = new BigDecimal("1000");
        Funcionario goodPerformanceEmployee = new Funcionario("Ben", LocalDate.now(), salary);
        BigDecimal raise = service.calculateRaise(goodPerformanceEmployee, Performance.GOOD);
        assertEquals(new BigDecimal("1.30"), raise);
        assertEquals(new BigDecimal("1300.00"), goodPerformanceEmployee.getSalario());

    }
}
