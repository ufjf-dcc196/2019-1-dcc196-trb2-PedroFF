package model;

import enuns.Estado;

import java.util.List;

public class Tarefa {
    private Long id;
    private String titulo;
    private String descricao;
    private Integer grauDificuldade;
    private Estado estado;
    private List<Etiqueta> etiquetas;

    public List<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getGrauDificuldade() {
        return grauDificuldade;
    }

    public void setGrauDificuldade(Integer grauDificuldade) {
        this.grauDificuldade = grauDificuldade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
