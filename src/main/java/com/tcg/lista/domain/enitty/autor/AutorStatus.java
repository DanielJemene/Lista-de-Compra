package com.tcg.lista.domain.enitty.autor;

public enum AutorStatus {
    INATIVO(0), ATIVO(1);

    private int value;

    AutorStatus(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public static AutorStatus fromValue(int value){
        for (AutorStatus status : AutorStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Valor numérico inválido: " + value);
    }
}
