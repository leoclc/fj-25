package br.com.caelum.financas.testes;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import br.com.caelum.financas.infra.ValidatorUtil;
import br.com.caelum.financas.modelo.Conta;

public class TestaValidacaoNumeroEAgencia {
	public static void main(String[] args) {
		Validator validator = new ValidatorUtil().getValidator();
		Conta conta = new Conta();
		conta.setAgencia("itau");
		Set<ConstraintViolation<Conta>> list = validator.validate(conta);
		for (ConstraintViolation<Conta> erro : list) {
			System.out.println(erro.getMessage());
			System.out.println(erro.getPropertyPath());
		}
	}
}
