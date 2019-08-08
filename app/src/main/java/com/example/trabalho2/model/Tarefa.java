package com.example.trabalho2.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Tarefa implements Serializable {
    private Long id;
    private String titulo;
    private String descricao;
    private Grau grauDificuldade;
    private Estado estado;
    private List<Tag> tags;
    private Date dt_limite;
    private Date dt_atualizacao;
    private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public Tarefa(Long id, String titulo, String descricao, int grauDificuldade, int estado, String dt_limite, String dt_atualizacao) throws ParseException {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.grauDificuldade = Grau.toEnum(grauDificuldade);
        this.estado = Estado.toEnum(estado);
        this.dt_limite = format.parse(dt_limite);
        this.dt_atualizacao = format.parse(dt_atualizacao);
    }

    public String getDt_limite() {
        return format.format(dt_limite);
    }

    public void setDt_limite(Date dt_limite) {
        this.dt_limite = dt_limite;
    }

    public String getDt_atualizacao() {
        return format.format(dt_atualizacao);
    }

    public void setDt_atualizacao(Date dt_atualizacao) {
        this.dt_atualizacao = dt_atualizacao;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
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

    public Grau getGrauDificuldade() {
        return grauDificuldade;
    }

    public void setGrauDificuldade(Grau grauDificuldade) {
        this.grauDificuldade = grauDificuldade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(),
                "Id: %d \n" +
                "Título: %s \n" +
                "Data Limite: %s \n" +
                "Data Atualização: %s \n" +
                "Grau de Dificuldade: %s \n" +
                "Estado: %s",
                this.getId(),
                this.getTitulo(),
                this.getDt_limite(),
                this.getDt_atualizacao(),
                this.getGrauDificuldade().getDescricao(),
                this.getEstado().getDescricao());
    }
}
