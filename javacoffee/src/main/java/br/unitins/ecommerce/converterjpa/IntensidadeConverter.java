package br.unitins.ecommerce.converterjpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.unitins.model.produto.cafe.Intensidade;

@Converter(autoApply = true)
public class IntensidadeConverter implements AttributeConverter<Intensidade, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Intensidade intensidade) {
        return intensidade == null ? null : intensidade.getId();
    }

    @Override
    public Intensidade convertToEntityAttribute(Integer id) {
        return Intensidade.valueOf(id);
    }
}
