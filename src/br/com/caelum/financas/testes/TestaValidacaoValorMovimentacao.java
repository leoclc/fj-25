package br.com.caelum.financas.testes;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import br.com.caelum.financas.infra.ValidatorUtil;
import br.com.caelum.financas.modelo.Movimentacao;

public class TestaValidacaoValorMovimentacao {
	public static void main(String[] args) {
		Movimentacao m = new Movimentacao();
		m.setValor(BigDecimal.ZERO);
		Validator validator = new ValidatorUtil().getValidator();
		Set<ConstraintViolation<Movimentacao>> list = validator.validate(m);
		for (ConstraintViolation<Movimentacao> erro : list) {
			System.out.println(erro.getMessage());
			System.out.println(erro.getPropertyPath());
		}
	}
}
