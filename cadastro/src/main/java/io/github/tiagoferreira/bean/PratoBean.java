package io.github.tiagoferreira.bean;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PratoBean extends BaseBean {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private RestauranteBean restaurante;
}
