package cotuba;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.Heading;
import org.commonmark.node.Node;
import org.commonmark.node.Text;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

/*
 * Classe criada apartir da pagina 40 seguindo SRP - Simple Responsability Principle 
 *  deixando assim para essa classe a a Renderização de MD para HTML
 */
public class RenderizadorMDParaHTML {

	public List<Capitulo> renderiza(Path diretorioDosMD) {

		List<Capitulo> capitulos = new ArrayList<Capitulo>();

		PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**/*.md");

		try (Stream<Path> arquivosMD = Files.walk(diretorioDosMD)) {
			
			// TODO: voltar a pagina 56 para ajustar essa classe como exercicio extra
			
			arquivosMD.filter(Files::isRegularFile).filter(p -> {
				boolean match = matcher.matches(p.toAbsolutePath().normalize());
				return match;
			})

					.sorted().forEach(arquivoMD -> {

						Capitulo capitulo = new Capitulo();
						Parser parser = Parser.builder().build();
						Node document = null;
						try {
							document = parser.parseReader(Files.newBufferedReader(arquivoMD));
							document.accept(new AbstractVisitor() {
								@Override
								public void visit(Heading heading) {
									if (heading.getLevel() == 1) {
										// capítulo
										String tituloDoCapitulo = ((Text) heading.getFirstChild()).getLiteral();
										capitulo.setTitulo(tituloDoCapitulo);
									} else if (heading.getLevel() == 2) {
										// seção
									} else if (heading.getLevel() == 3) {
										// título
									}
								}

							});
						} catch (Exception ex) {
							throw new IllegalStateException("Erro ao fazer parse do arquivo " + arquivoMD, ex);
						}

						try {
							HtmlRenderer renderer = HtmlRenderer.builder().build();
							String html = renderer.render(document);

							capitulo.setConteudoHTML(html);
							capitulos.add(capitulo);

						} catch (Exception ex) {
							throw new IllegalStateException("Erro ao renderizar para HTML o arquivo " + arquivoMD, ex);
						}

					});
		} catch (IOException ex) {
			throw new IllegalStateException(
					"Erro tentando encontrar arquivos .md em " + diretorioDosMD.toAbsolutePath(), ex);
		}

		return capitulos;

	}
}
