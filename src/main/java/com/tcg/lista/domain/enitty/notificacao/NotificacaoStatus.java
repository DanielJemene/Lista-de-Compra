package com.tcg.lista.domain.enitty.notificacao;

public enum NotificacaoStatus {
    NAO_LIDA(0), LIDA(1);

    private int value;

    NotificacaoStatus(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public static NotificacaoStatus fromValue(int value){
        for (NotificacaoStatus status : NotificacaoStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Valor numérico inválido: " + value);
    }
}
