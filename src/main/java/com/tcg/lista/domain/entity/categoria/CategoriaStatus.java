package com.tcg.lista.domain.entity.categoria;

public enum CategoriaStatus {
    INATIVO(0), ATIVO(1);

    private int value;

    CategoriaStatus(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public static CategoriaStatus fromValue(int value){
        for (CategoriaStatus status : CategoriaStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Valor numérico inválido: " + value);
    }
}
