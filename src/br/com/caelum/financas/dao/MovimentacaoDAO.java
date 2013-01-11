package br.com.caelum.financas.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.infra.JPAUtil;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.modelo.ValorPorMesEAno;

public class MovimentacaoDAO {

	private final DAO<Movimentacao> dao;
	private final EntityManager em;

	public MovimentacaoDAO(EntityManager em) {
		this.em = em;
		dao = new DAO<Movimentacao>(em,Movimentacao.class);			
	}

	public List<ValorPorMesEAno> listaMesesComMovimentacoes(Conta c, TipoMovimentacao tipo){
	EntityManager entityManager = new JPAUtil().getEntityManager();
	String jpql = "select new br.com.caelum.financas.modelo.ValorPorMesEAno(month(m.data),year(m.data),sum(m.valor)) from Movimentacao m where m.conta = :conta and m.tipoMovimentacao = :tipo group by year(m.data),month(m.data) order by sum(m.valor) desc";
	Query query = entityManager.createQuery(jpql);
	query.setParameter("conta", c);
	query.setParameter("tipo", tipo);
	return query.getResultList();
	
	}
	
	public List<Movimentacao> buscaTodasMovimentacoesDaConta(String titular){
		String jpql="select m from Movimentacao m where m.conta.titular like :titular";
		TypedQuery<Movimentacao> query = this.em.createQuery(jpql, Movimentacao.class);
		query.setParameter("titular", "%"+titular+"%");
		return query.getResultList();
	}

	public BigDecimal calculaTotalMovimentado(Conta conta, TipoMovimentacao tipo){
	String jpql = "select sum(m.valor) from Movimentacao m where m.conta=:conta and m.tipoMovimentacao=:tipo";
			TypedQuery<BigDecimal> query = this.em.createQuery(jpql,BigDecimal.class);
			query.setParameter("conta", conta);
			query.setParameter("tipo", tipo);
			return query.getSingleResult();
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
