package br.com.conexaoorm.p.i.tabelas;

import jakarta.persistence.TypedQuery;
import java.util.List;

public class UsuarioDAO {
    
    JPAutil manager = new JPAutil();

    public Usuario validaUsuario(String nome, String senha) {

        String comando = "SELECT u FROM Usuario u WHERE u.nome = :nome AND u.senha = :senha";

        return manager.getEntityManager().createQuery(comando, Usuario.class)
                .setParameter("nome", nome)
                .setParameter("senha", senha)
                .getSingleResult();

    }
    
    public List<Usuario> listaUsuarios() {
        
        String comando = "SELECT u FROM Usuario u";
                        
        TypedQuery<Usuario> listaDeUsuarios = manager.getEntityManager().createQuery(comando, Usuario.class);
        
        return listaDeUsuarios.getResultList();
        
    }

    public void cadastrarUsuario(String nome, String senha, String email, String role) {
        
        Usuario novoUsuario = new Usuario();

        try {

            novoUsuario.setNome(nome);
            novoUsuario.setSenha(senha);
            novoUsuario.setEmail(email);
            novoUsuario.setRole(role);

            manager.getEntityManager().getTransaction().begin();
            manager.getEntityManager().persist(novoUsuario);
            manager.getEntityManager().getTransaction().commit();

        } catch (Exception ex) {

            manager.getEntityManager().getTransaction().rollback();
            System.out.println("Falha ao cadastrar usuário!!! " + ex.getMessage());

        } finally {
            manager.getCloseEntityManager();
        }

    }

}
