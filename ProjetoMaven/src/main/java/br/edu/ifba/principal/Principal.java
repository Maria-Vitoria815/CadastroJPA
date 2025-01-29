package br.edu.ifba.principal;
import java.util.List;

import br.edu.ifba.basicas.Categoria;
import br.edu.ifba.basicas.Cliente;
import br.edu.ifba.basicas.Endereco;
import br.edu.ifba.basicas.Produto;
import br.edu.ifba.dao.GetEntityManager;
import jakarta.persistence.EntityManager;

public class Principal {

    public static void main(String[] args) {
        EntityManager em = GetEntityManager.getConnectionJpa();

        
        Categoria cat = new Categoria();
		cat.setDescricao("Afiliado");
		em.getTransaction().begin();
		em.persist(cat);
		em.getTransaction().commit();


        Cliente c = new Cliente();
		c.setNome("Kate Austen");
		c.setCpf("162.163.8675-00");
		c.setRg("4815162342");
		c.setCategoria(em.find(Categoria.class, 1));


        Endereco e = new Endereco();
		e.setBairro("Centro");
		e.setCep("48601-270");
		e.setCidade("Paulo Afonso");
		e.setEstado("Bahia");
		e.setNumero("1159");
		e.setRua("São francisco");
		c.setEndereco(e);


		
		em.getTransaction().begin();
		em.merge(c);
		em.getTransaction().commit();

		System.out.println(c);



    
        Produto p1 = new Produto();
        p1.setDescricao("Açúcar Demerara");
        p1.setEstoque(100);
        p1.setValor(5.50);

        Produto p2 = new Produto();
        p2.setDescricao("Sabão em pó");
        p2.setEstoque(50);
        p2.setValor(25.75);

        Produto p3 = new Produto();
        p3.setDescricao("Óleo de Girassol");
        p3.setEstoque(130);
        p3.setValor(9.75);

        Produto p4 = new Produto();
        p4.setDescricao("Leite em pó Camponesa");
        p4.setEstoque(200);
        p4.setValor(5.75);

        em.getTransaction().begin();
        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
        em.persist(p4);

        em.getTransaction().commit();
        
        listarProdutos(em);

    }

    private static void listarProdutos(EntityManager em) {
        List<Produto> produtos = em.createQuery("SELECT p FROM Produto p", Produto.class).getResultList();
        System.out.println("\nLista de Produtos e Estoque:");
        for (Produto p : produtos) {
            System.out.println("ID: " + p.getId() + ", Descrição: " + p.getDescricao() + 
                              ", Estoque: " + p.getEstoque() + ", Valor: R$" + p.getValor());
        }
    }


}

