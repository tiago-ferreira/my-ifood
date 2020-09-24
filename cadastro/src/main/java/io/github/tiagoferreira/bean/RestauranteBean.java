package io.github.tiagoferreira.bean;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class RestauranteBean extends BaseBean {

    private Long id;
    private String nome;
    private String cnpj;
    private String proprietario;
    private LocalDate dataCriacao;
    private LocalDateTime dataAtualizacao;
    private LocalizacaoBean localizacao;
}
