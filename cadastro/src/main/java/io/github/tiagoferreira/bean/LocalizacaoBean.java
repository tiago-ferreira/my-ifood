package io.github.tiagoferreira.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalizacaoBean extends BaseBean {

    private Long id;
    private Double latitude;
    private Double longitude;
}
