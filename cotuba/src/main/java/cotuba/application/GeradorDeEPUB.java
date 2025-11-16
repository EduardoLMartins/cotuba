package cotuba.application;

import cotuba.domain.Ebook;
import cotuba.epub.GeradorEPUBComEpublib;

public interface GeradorDeEPUB {

	void gera(Ebook ebook);

	static GeradorDeEPUB cria() {
		return new GeradorEPUBComEpublib();
	}
}