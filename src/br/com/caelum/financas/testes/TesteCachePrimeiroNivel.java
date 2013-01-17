package br.com.caelum.financas.testes;

import javax.persistence.EntityManager;

import br.com.caelum.financas.infra.JPAUtil;
import br.com.caelum.financas.modelo.Conta;

public class TesteCachePrimeiroNivel {
	public static void main(String[] args) {
		EntityManager entityManager = new JPAUtil().getEntityManager();
		Conta primeiraConta = entityManager.find(Conta.class,6);
		Conta segundaConta = entityManager.find(Conta.class, 8);
		
		System.out.println("Titular da primeira conta:"+primeiraConta.getTitular());
		System.out.println("Titular da segunda conta:"+segundaConta.getTitular());
	}
}
