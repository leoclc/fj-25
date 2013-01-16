package br.com.caelum.financas.testes;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import br.com.caelum.financas.infra.ValidatorUtil;
import br.com.caelum.financas.modelo.Conta;

public class TesteValidacaoContaSemTitular {
	public static void main(String[] args) {
		Validator validator = new ValidatorUtil().getValidator();

		Conta conta = new Conta();
		Set<ConstraintViolation<Conta>> erros = validator.validate(conta);
		for (ConstraintViolation<Conta> erro : erros) {
			System.out.println(erro.getMessage());
			System.out.println(erro.getPropertyPath());
		}

	}
}
