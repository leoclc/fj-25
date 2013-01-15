package br.com.caelum.financas.testes;

import javax.persistence.EntityManager;

import br.com.caelum.financas.infra.JPAUtil;

public class TesteAberturaConexoes {
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 30; i++) {
			EntityManager entityManager = new JPAUtil().getEntityManager();
			entityManager.getTransaction().begin();
			System.out.println("criado entity managager numero" + i);
		}
		Thread.sleep(30*1000);
	}
}
