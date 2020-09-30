package io.github.tiagoferreira.bean;


import java.math.BigDecimal;
public class PratoBean extends BaseBean {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private RestauranteBean restaurante;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public RestauranteBean getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(RestauranteBean restaurante) {
        this.restaurante = restaurante;
    }
}
