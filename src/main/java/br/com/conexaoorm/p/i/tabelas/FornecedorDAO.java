package br.com.conexaoorm.p.i.tabelas;

import jakarta.persistence.TypedQuery;
import java.util.List;

public class FornecedorDAO {

    JPAutil manager = new JPAutil();

    public List<Fornecedor> listaFornecedores() {

        String comando = "SELECT f FROM Fornecedor f";

        TypedQuery<Fornecedor> fornecedores = manager.getEntityManager().createQuery(comando, Fornecedor.class);

        return fornecedores.getResultList();

    }

    public void cadastrarFornecedor(String nomeEmpresa, String telefone, String email, String endereco, String cnpj, Produto produtoSelecionado) {

        Fornecedor novoFornecedor = new Fornecedor();

        try {
            novoFornecedor.setNomeEmpresa(nomeEmpresa);
            novoFornecedor.setTelefone(telefone);
            novoFornecedor.setEmail(email);
            novoFornecedor.setEndereco(endereco);
            novoFornecedor.setCnpj(cnpj);

            if (produtoSelecionado != null) {
                Produto produtoGerenciado = manager.getEntityManager().merge(produtoSelecionado);
                novoFornecedor.getProdutos().add(produtoGerenciado);
                produtoGerenciado.getFornecedores().add(novoFornecedor);
            }
            
            manager.getEntityManager().getTransaction().begin();
            manager.getEntityManager().persist(novoFornecedor);
            manager.getEntityManager().getTransaction().commit();
            

        } catch (Exception ex) {
            
            if (manager.getEntityManager().getTransaction().isActive()) {
                manager.getEntityManager().getTransaction().rollback();
            }
            System.out.println("Falha ao cadastrar novo fornecedor!!! " + ex.getMessage());
            ex.printStackTrace();

            throw ex;
            
        }

    }

    public Fornecedor buscarFornecedorPorId(int id) {

        return manager.getEntityManager().find(Fornecedor.class, id);

    }

}
