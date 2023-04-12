package br.unitins.ecommerce.model.produto.cafe;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Intensidade {

    SUAVE(1, "Suave"),
    MEDIA(2, "Media"),
    INTENSA(3, "Intensa");

    private int id;
    private String label;

    Intensidade(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Intensidade valueOf(Integer id) throws IllegalArgumentException {
        if (id == null) {
            return null;
        }

        for (Intensidade intensidade : Intensidade.values()) {
            if (id.equals(intensidade.getId())) {
                return intensidade;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }

}
