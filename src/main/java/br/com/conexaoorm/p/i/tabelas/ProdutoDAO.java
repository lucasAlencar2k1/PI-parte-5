package br.com.conexaoorm.p.i.tabelas;

import jakarta.persistence.TypedQuery;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProdutoDAO {

    Fornecedor fornecedor = new Fornecedor();
    JPAutil manager = new JPAutil();

    public List<Produto> listarProdutos() {

        String comando = "SELECT DISTINCT p FROM Produto p LEFT JOIN FETCH p.fornecedores"; // <== MUDANÇA  AQUI 

        TypedQuery<Produto> produtos = manager.getEntityManager().createQuery(comando, Produto.class);

        return produtos.getResultList();

    }

    public void cadastrarProduto(String nome, int quantidade, BigDecimal preco, String descricao, LocalDate dataDeCadastro, Fornecedor fornecedorSelecionado) {

        try {

            Produto novoProduto = new Produto();

            novoProduto.setNome(nome);
            novoProduto.setQuantidade(quantidade);
            novoProduto.setPreco(preco);
            novoProduto.setDescricao(descricao);
            novoProduto.setDataDeCadastro(dataDeCadastro);

            manager.getEntityManager().getTransaction().begin();

            if (fornecedorSelecionado != null) {
                Fornecedor fornecedorGerenciado = manager.getEntityManager().merge(fornecedorSelecionado);
                novoProduto.getFornecedores().add(fornecedorGerenciado);
                fornecedorGerenciado.getProdutos().add(novoProduto);
            }

            manager.getEntityManager().persist(novoProduto);
            manager.getEntityManager().getTransaction().commit();

        } catch (Exception ex) {

            if (manager.getEntityManager().getTransaction().isActive()) {
                manager.getEntityManager().getTransaction().rollback();
            }
            System.out.println("Falha ao cadastrar produto!!! " + ex.getMessage());
            ex.printStackTrace();

        } finally {
            manager.getCloseEntityManager();
        }

    }

}
