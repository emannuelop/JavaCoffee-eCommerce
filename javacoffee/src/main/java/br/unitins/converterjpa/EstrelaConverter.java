package br.unitins.converterjpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.unitins.model.produto.Estrela;

@Converter(autoApply = true)
public class EstrelaConverter implements AttributeConverter<Estrela, Integer>{

    @Override
    public Integer convertToDatabaseColumn(Estrela estrela) {
        return estrela == null ? null : estrela.getId();
    }

    @Override
    public Estrela convertToEntityAttribute(Integer id) {
        return Estrela.valueOf(id);
    }
}