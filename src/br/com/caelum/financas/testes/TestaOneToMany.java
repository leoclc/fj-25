package br.com.caelum.financas.testes;

import javax.persistence.EntityManager;

import br.com.caelum.financas.infra.JPAUtil;
import br.com.caelum.financas.modelo.Conta;

public class TestaOneToMany {
	public static void main(String[] args) {
		EntityManager entityManager = new JPAUtil().getEntityManager();
			entityManager.find(Conta.class, 2);
	}
}
