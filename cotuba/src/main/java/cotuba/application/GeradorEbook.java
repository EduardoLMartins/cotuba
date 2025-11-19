package cotuba.application;

import java.util.HashMap;
import java.util.Map;

import cotuba.domain.Ebook;
import cotuba.domain.FormatoEbook;
/*
 * Classse criada para Generalizar ainda mais as classes geradorPDF e geradorEPUB 
 * fazendo assim um acoplamento melhor para novas funcionalidades ao gerar Ebooks
 * 
 */
import cotuba.epub.GeradorEPUB;
import cotuba.pdf.GeradorPDF;

public interface GeradorEbook {

	Map<String, GeradorEbook> GERADORES = new HashMap<String, GeradorEbook>() {{
		put("pdf", new GeradorPDF());
		put("epub", new GeradorEPUB());
	}};
	
	void gera(Ebook ebook);

	static GeradorEbook cria(FormatoEbook formato) {
		return formato.getGeradorEbook();
	};
}
