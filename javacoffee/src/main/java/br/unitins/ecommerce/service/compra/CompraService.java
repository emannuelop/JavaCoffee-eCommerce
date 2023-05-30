package br.unitins.ecommerce.service.compra;

import br.unitins.ecommerce.dto.compra.CompraResponseDTO;
import br.unitins.ecommerce.dto.compra.ItemCompraDTO;

public interface CompraService {

    CompraResponseDTO getCompra (Long idUsuario);
    
    void insertIntoCompra (Long idCompra, ItemCompraDTO itemCompraDTO);

    void removeItemCompra (Long idUsuario, Long idItemCompra);
}
