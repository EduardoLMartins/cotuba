package cotuba.application;

import cotuba.domain.Ebook;
import cotuba.pdf.GeradorPDFIText;

public interface GeradorDePDF {

	void gera(Ebook ebook);

	static GeradorDePDF cria() {
		return new GeradorPDFIText();
	}
}