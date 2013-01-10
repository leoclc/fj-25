package br.com.caelum.financas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;

public class MovimentacaoDAO {

	private final DAO<Movimentacao> dao;
	private final EntityManager em;

	public MovimentacaoDAO(EntityManager em) {
		this.em = em;
		dao = new DAO<Movimentacao>(em,Movimentacao.class);			
	}

	public List<Movimentacao> listaTodasMovimentacoes(Conta conta){
		String jpql = "select m from Movimentacao m "+
					  "where m.conta= :conta order by m.valor desc";
		Query query = this.em.createQuery(jpql);
		query.setParameter("conta", conta);
		return query.getResultList();
	}
	
	public void adiciona(Movimentacao t) {
		dao.adiciona(t);
	}

	public Movimentacao busca(Integer id) {
		return dao.busca(id);
	}

	public List<Movimentacao> lista() {
		return dao.lista();
	}

	public void remove(Movimentacao t) {
		dao.remove(t);
	}
	
	
	
	
	
	
}
