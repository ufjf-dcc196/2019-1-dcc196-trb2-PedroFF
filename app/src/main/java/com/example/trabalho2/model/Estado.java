package com.example.trabalho2.model;

public enum Estado {
    AFAZER(1, "A fazer"),
    EMEXECUCAO(2, "Em execução"),
    BLOQUEADA(3, "Bloqueada"),
    CONCLUIDA(4, "Concluida");

    private Integer codigo;
    private String descricao;

    private Estado(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Estado toEnum(Integer cod){
        if (cod==null)
            return null;
        for (Estado estado: values()){
            if (cod.equals(estado.getCodigo())) {
                return estado;
            }
        }

        throw new IllegalArgumentException("Id inválido: " +cod);
    }
}
