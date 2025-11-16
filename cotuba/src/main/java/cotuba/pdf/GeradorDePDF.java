package cotuba.pdf;

import cotuba.domain.Ebook;

public interface GeradorDePDF {

	void gera(Ebook ebook);

	static GeradorDePDF cria() {
		return new GeradorPDFImpl();
	}
}