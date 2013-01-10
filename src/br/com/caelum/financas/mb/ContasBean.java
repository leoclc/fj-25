package br.com.caelum.financas.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.com.caelum.financas.dao.ContaDAO;
import br.com.caelum.financas.dao.MovimentacaoDAO;
import br.com.caelum.financas.infra.JPAUtil;
import br.com.caelum.financas.modelo.Conta;


@ViewScoped
@ManagedBean
public class ContasBean {
	private Conta conta = new Conta();
	private List<Conta> contas;	
	
	
	
	public void grava() {
		
		EntityManager entityManager = new JPAUtil().getEntityManager();
		entityManager.getTransaction().begin();
		ContaDAO dao = new ContaDAO(entityManager);
		if(conta.getId()==null){
			dao.adiciona(conta);
		} else {
			dao.altera(conta);
		}
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		limpaFormularioDoJSF();
		
	}
	


	public void remove() {
		EntityManager entityManager = new JPAUtil().getEntityManager();
		ContaDAO dao = new ContaDAO(entityManager);
		MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO(entityManager);
		entityManager.getTransaction().begin();
		Conta contaRemover = dao.busca(this.conta.getId());
		dao.remove(contaRemover);
		entityManager.getTransaction().commit();
		entityManager.close();
		
		limpaFormularioDoJSF();
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	
	public List<Conta> getContas() {
		EntityManager entityManager = new JPAUtil().getEntityManager();
		ContaDAO dao = new ContaDAO(entityManager);
		this.contas = dao.lista();
		return this.contas;
	}
	
	/**
	 * Esse método apenas limpa o formulário da forma com que o JSF espera.
	 * Invoque-o no momento em que precisar do formulário vazio.
	 */
	private void limpaFormularioDoJSF() {
        this.conta = new Conta();
	}	
}
