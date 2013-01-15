package br.com.caelum.financas.testes;

import javax.persistence.EntityManager;

import br.com.caelum.financas.infra.JPAUtil;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.Tag;

public class TestaRecuperaTagsAssociadasAUmaMovimentacao {
	public static void main(String[] args) {
		EntityManager entityManager = new JPAUtil().getEntityManager();
		Movimentacao movimentacao = entityManager.find(Movimentacao.class,14);
		for(Tag tag : movimentacao.getTags()){
			System.out.println(tag.getNome());
		}
		entityManager.close();
	}
}
