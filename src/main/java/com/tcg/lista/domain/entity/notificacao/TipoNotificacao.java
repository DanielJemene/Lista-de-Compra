package com.tcg.lista.domain.entity.notificacao;

public enum TipoNotificacao {
    SOLICITACAO_AMIZADE(0), SOLICITACAO_PART_LISTA(1);

    private int value;

    TipoNotificacao(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public static TipoNotificacao fromValue(int value){
        for (TipoNotificacao status : TipoNotificacao.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Valor numérico inválido: " + value);
    }
}
