package br.com.caelum.financas.testes;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.financas.dao.MovimentacaoDAO;
import br.com.caelum.financas.infra.JPAUtil;
import br.com.caelum.financas.modelo.Movimentacao;

public class TesteCriteriaSimples {
	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();
		MovimentacaoDAO dao = new MovimentacaoDAO(em);
		List<Movimentacao> list = dao.todasComCriteria();
		for (Movimentacao movimentacao : list) {
			System.out.println(movimentacao);
		}
	}
}
