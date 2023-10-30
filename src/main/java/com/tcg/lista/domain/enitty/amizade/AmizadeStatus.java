package com.tcg.lista.domain.enitty.amizade;

public enum AmizadeStatus {
    INATIVO(0), ATIVO(1);

    private int value;

    AmizadeStatus(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public static AmizadeStatus fromValue(int value){
        for (AmizadeStatus status : AmizadeStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Valor numérico inválido: " + value);
    }
}
