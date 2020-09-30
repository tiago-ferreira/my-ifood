package io.github.tiagoferreira.bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RestauranteBean extends BaseBean {

    private Long id;
    private String nome;
    private String cnpj;
    private String proprietario;
    private LocalDate dataCriacao;
    private LocalDateTime dataAtualizacao;
    private LocalizacaoBean localizacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public LocalizacaoBean getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(LocalizacaoBean localizacao) {
        this.localizacao = localizacao;
    }
}
