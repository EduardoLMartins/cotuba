package cotuba.application;

import cotuba.domain.Ebook;
/*
 * Classse criada para Generalizar ainda mais as classes geradorPDF e geradorEPUB 
 * fazendo assim um acoplamento melhor para novas funcionalidades ao gerar Ebooks
 * 
 */
import cotuba.epub.GeradorEPUB;
import cotuba.pdf.GeradorPDF;

public interface GeradorEbook {

	void gera(Ebook ebook);

	static GeradorEbook cria(String formato) {
		GeradorEbook gerador;

		if ("pdf".equals(formato)) {

			gerador = new GeradorPDF();

		} else if ("epub".equals(formato)) {

			gerador = new GeradorEPUB();

		} else {

			throw new RuntimeException("Formato do ebook inválido: " + formato);

		}

		return gerador;
	};
}
