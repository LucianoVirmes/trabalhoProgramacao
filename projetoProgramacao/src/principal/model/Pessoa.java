package principal.model;

import java.time.LocalDate;

public abstract class Pessoa {

	private String nome;
	private String sobrenome;
	private LocalDate dataNascimento;
	private String telefone;
	private String cpf;
	private String email;
	
	/**
	 * 
	 * @return true se cpf for valido
	 */
	public Boolean validaCpf() {
		boolean cpfValido = false;
		int decimaPosicao = 10;
		int total = 0;
		char[] chars = this.getCpf().toCharArray();

		for (int i = 0; i < 9; i++) {
			int valorCaracterEmPosicao = Integer.valueOf(String.valueOf(chars[i]));
			total = total + (valorCaracterEmPosicao * decimaPosicao);
			decimaPosicao--;
		}

		int validacao1 = total % 11;
		validacao1 = 11 - validacao1;
		if (validacao1 > 9) {
			validacao1 = 0;
		}

		int valorCaracterEmPosicao9 = Integer.valueOf(String.valueOf(chars[9]));

		if (validacao1 == valorCaracterEmPosicao9) {
			cpfValido = true;
		}

		int decimaPrimeiraPosicao = 11;
		total = 0;
		for (int i = 0; i < 10; i++) {
			int valorCaracterEmPosicao = Integer.valueOf(String.valueOf(chars[i]));
			total = total + (valorCaracterEmPosicao * decimaPrimeiraPosicao);
			decimaPrimeiraPosicao--;
		}

		int validacao2 = total % 11;
		validacao2 = 11 - validacao2;

		if (validacao2 > 9) {
			validacao2 = 0;
		}

		int valorCaracterEmPosicao10 = Integer.valueOf(String.valueOf(chars[10]));

		if (validacao2 == valorCaracterEmPosicao10) {
			cpfValido = true;
		}

		if (!cpfValido) {
			return false;
		} else {
			return true;
		}

	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	

}
