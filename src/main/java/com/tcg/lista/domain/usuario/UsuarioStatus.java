package com.tcg.lista.domain.usuario;

public enum UsuarioStatus {
    INATIVO(0), ATIVO(1);

    private int value;

    UsuarioStatus(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public static UsuarioStatus fromValue(int value){
        for (UsuarioStatus status : UsuarioStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Valor numérico inválido: " + value);
    }
}
