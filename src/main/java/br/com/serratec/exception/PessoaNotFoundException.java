package br.com.serratec.exception;

public class PessoaNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PessoaNotFoundException(Long id) {
        super("Pessoa com ID " + id + " não encontrada.");
    }
}