package br.unitins.ecommerce.converterjpa;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import br.unitins.ecommerce.model.produto.cafe.Intensidade;

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
