package br.com.serratec.exception;

public class ClienteNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClienteNotFoundException(Long id) {
        super("Cliente com ID " + id + " não encontrado.");
    }
}
