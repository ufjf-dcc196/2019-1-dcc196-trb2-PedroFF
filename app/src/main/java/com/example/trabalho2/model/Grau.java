package com.example.trabalho2.model;

public enum Grau {
    MUITOFACIL(1, "Muito Fácil"),
    FACIL(2, "Fácil"),
    MEDIO(3, "Médio"),
    DIFICIL(4, "Difícil"),
    MUITODIFICIL(5, "Muito Difícil");

    private Integer codigo;
    private String descricao;

    private Grau(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static Grau toEnum(Integer cod) {
        if (cod == null)
            return null;
        for (Grau grau : values()) {
            if (cod.equals(grau.getCodigo())) {
                return grau;
            }
        }
        throw new IllegalArgumentException("Id inválido: " +cod);
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

}
