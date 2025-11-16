package cotuba.epub;

import cotuba.domain.Ebook;

public interface GeradorDeEPUB {

	void gera(Ebook ebook);

	static GeradorDeEPUB cria() {
		return new GeradorEPUBImpl();
	}
}