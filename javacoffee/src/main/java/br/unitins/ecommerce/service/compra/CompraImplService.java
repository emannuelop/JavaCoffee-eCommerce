package br.unitins.ecommerce.service.compra;

import java.util.List;

import br.unitins.ecommerce.dto.compra.CompraResponseDTO;
import br.unitins.ecommerce.dto.compra.ItemCompraDTO;
import br.unitins.ecommerce.model.compra.Compra;
import br.unitins.ecommerce.model.compra.ItemCompra;
import br.unitins.ecommerce.model.produto.Produto;
import br.unitins.ecommerce.repository.CompraRepository;
import br.unitins.ecommerce.repository.ItemCompraRepository;
import br.unitins.ecommerce.repository.UsuarioRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CompraImplService implements CompraService {

    @Inject
    CompraRepository compraRepository;

    @Inject
    ItemCompraRepository itemCompraRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    PanacheRepository<? extends Produto> produtoRepository;

    @Override
    public CompraResponseDTO getCompra(Long idUsuario) {
        
        Compra compra = compraRepository.findByUsuario(usuarioRepository.findById(idUsuario));

        if (compra == null)
            throw new NullPointerException("compra não encontrada");

        return new CompraResponseDTO(compra);
    }

    @Override
    @Transactional
    public void insertIntoCompra(Long idUsuario, ItemCompraDTO itemCompraDTO) throws NullPointerException {
        
        Produto produto = validar(itemCompraDTO);

        Compra compra = validar(idUsuario);

        Integer indice = validar(produto, compra.getItemCompra());

        ItemCompra itemCompra;

        if (indice != null) {

            itemCompra = compra.getItemCompra().get(indice);

            itemCompra.updateQuantidade(itemCompraDTO.quantidade());

            compra.plusTotalCompra(itemCompra.getPrecoUnitario() * itemCompraDTO.quantidade());
        }

        else {

            itemCompra = new ItemCompra(produto, itemCompraDTO.quantidade());

            itemCompraRepository.persist(itemCompra);

            compra.setItemCompra(itemCompra);

            compra.plusTotalCompra(itemCompra.getPrecoUnitario() * itemCompra.getQuantidade());
        }
    }

    @Override
    @Transactional
    public void removeItemCompra(Long idUsuario, Long idProduto) {
        
        Compra compra = compraRepository.findByUsuarioWhereNotFinished(usuarioRepository.findById(idUsuario));

        if (compra == null)
            throw new NullPointerException("Não há nenhuma compra em andamento");

        ItemCompra itemCompra = itemCompraRepository.findByProduto(produtoRepository.findById(idProduto));

        compra.minusTotalCompra(itemCompra.getPrecoUnitario() * itemCompra.getQuantidade());

        compra.getItemCompra().remove(itemCompra);
    }

    private Integer validar(Produto produto, List<ItemCompra> listaItens) {

        for (int i = 0; i < listaItens.size(); i++) {
            
            if (listaItens.get(i).contains(produto))
                return i;
        }

        return null;
    }

    private Compra validar(Long idUsuario) {

        Compra compra = compraRepository.findByUsuarioWhereNotFinished(usuarioRepository.findById(idUsuario));

        if (compra == null) {

            Compra newCompra = new Compra(usuarioRepository.findById(idUsuario));

            compraRepository.persist(newCompra);

            return newCompra;
        }

        else
            return compra;
    }

    private Produto validar(ItemCompraDTO itemCompraDTO) throws NullPointerException {

        Produto produto = produtoRepository.findById(itemCompraDTO.idProduto());

        if (produto.getEstoque() > itemCompraDTO.quantidade())
            return produto;

        else
            throw new NullPointerException("quantidade em estoque insuficiente para a quantidade requisitada");
    }
}
