package br.com.alura.tdd.modelo;

import java.math.BigDecimal;

public enum Performance {
    POOR {
        @Override
        public BigDecimal raisePercentual() {
            return new BigDecimal("1.03");
        }
    },
    AVERAGE {
        @Override
        public BigDecimal raisePercentual() {
            return new BigDecimal("1.15");
        }
    },
    GOOD {
        @Override
        public BigDecimal raisePercentual() {
            return new BigDecimal("1.30");
        }
    };

    public abstract BigDecimal raisePercentual();
}
