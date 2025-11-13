package cotuba;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.epub.EpubWriter;

/*
 *  * Classe criada apartir da pagina 34 para unificação de toda utilização dos pacote restantes e principalmente do pacote nl.siegmann.epublib
 *  que faz a criação dos epubs
 *  seguindo o SRP - Single Responsability Principle
 */
public class GeradorEPUB {

	public void gera(Ebook ebook) {

		Path arquivoDeSaida = ebook.getArquivoDeSaida();
		var epub = new Book();

		for (Capitulo capitulo : ebook.getCapitulos()) {
			String html = capitulo.getConteudoHTML();
			
			String titulodoCapitulo = capitulo.getTitulo();
			
		}
		
		var epubWriter = new EpubWriter();

		try {
			epubWriter.write(epub, Files.newOutputStream(arquivoDeSaida));
		} catch (IOException ex) {
			throw new IllegalStateException("Erro ao criar arquivo EPUB: " + arquivoDeSaida.toAbsolutePath(), ex);
		}
	}
}
