package br.com.caelum.financas.mb;

import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.persistence.EntityManager;

import br.com.caelum.financas.dao.ContaDAO;
import br.com.caelum.financas.dao.MovimentacaoDAO;
import br.com.caelum.financas.dao.TagDAO;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.Tag;
import br.com.caelum.financas.modelo.TipoMovimentacao;

@ManagedBean
public class MovimentacoesBean {
	private List<Movimentacao> movimentacoes;
	private Movimentacao movimentacao = new Movimentacao();
	private Integer contaId;
	private String tags;

	@ManagedProperty(name = "em", value = "#{requestScope.em}")
	private EntityManager entityManager;

	public void setEm(EntityManager em) {
		this.entityManager = em;
	}

	private void gravaEAssociaAsTags(EntityManager em) {
		String[] nomesDasTags = this.tags.split(" ");
		TagDAO tagDAO = new TagDAO(em);
		for (String nome : nomesDasTags) {
			Tag tag = tagDAO.adicionaOuBuscaTagComNome(nome);
			movimentacao.getTags().add(tag);
		}
	}

	public void grava() {
		MovimentacaoDAO dao = new MovimentacaoDAO(entityManager);
		ContaDAO contaDAO = new ContaDAO(entityManager);
		Conta contaRelacionada = contaDAO.busca(contaId);
		movimentacao.setConta(contaRelacionada);
		gravaEAssociaAsTags(entityManager);
		dao.adiciona(movimentacao);
		limpaFormularioDoJSF();

	}

	public void remove() {
		MovimentacaoDAO dao = new MovimentacaoDAO(entityManager);
		Movimentacao busca = dao.busca(movimentacao.getId());
		dao.remove(busca);
		limpaFormularioDoJSF();
	}

	public List<Movimentacao> getMovimentacoes() {
		MovimentacaoDAO dao = new MovimentacaoDAO(entityManager);
		movimentacoes = dao.lista();
		return movimentacoes;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Movimentacao getMovimentacao() {
		if (movimentacao.getData() == null) {
			movimentacao.setData(Calendar.getInstance());
		}
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

	public Integer getContaId() {
		return contaId;
	}

	public void setContaId(Integer contaId) {
		this.contaId = contaId;
	}

	/**
	 * Esse método apenas limpa o formulário da forma com que o JSF espera.
	 * Invoque-o no momento em que precisar do formulário vazio.
	 */
	private void limpaFormularioDoJSF() {
		this.movimentacao = new Movimentacao();
		this.tags = null;
	}

	public TipoMovimentacao[] getTiposDeMovimentacao() {
		return TipoMovimentacao.values();
	}
}
