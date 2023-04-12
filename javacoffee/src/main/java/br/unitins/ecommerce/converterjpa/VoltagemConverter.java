package br.unitins.ecommerce.converterjpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.unitins.model.produto.cafeteira.Voltagem;

@Converter(autoApply = true)
public class VoltagemConverter implements AttributeConverter<Voltagem, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Voltagem voltagem) {
        return voltagem == null ? null : voltagem.getId();
    }

    @Override
    public Voltagem convertToEntityAttribute(Integer id) {
        return Voltagem.valueOf(id);
    }
}
