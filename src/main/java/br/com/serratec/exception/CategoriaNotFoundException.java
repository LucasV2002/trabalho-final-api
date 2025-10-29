package br.com.serratec.exception;

public class CategoriaNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CategoriaNotFoundException(Long id) {
        super("Categoria com ID " + id + " não encontrada.");
    }
}
