package cotuba;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.property.AreaBreakType;

/*
 * Classe criada apartir da pagina 30 para unificação de toda utilização do pacote com.itextpdf
 *  seguindo o SRP - Single Responsability Principle
 *
 */
public class GeradorPDF {

	public void gera(Ebook ebook) {

		Path arquivoDeSaida = ebook.getArquivoDeSaida();
		
		try (var writer = new PdfWriter(Files.newOutputStream(arquivoDeSaida));
				var pdf = new PdfDocument(writer);
				var pdfDocument = new Document(pdf)) {

			for(Capitulo capitulo : ebook.getCapitulos()) {
				String html = capitulo.getConteudoHTML();
				List<IElement> convertElements = HtmlConverter.convertToElements(html);
				for(IElement element : convertElements) {
					pdfDocument.add((IBlockElement) element);
				}
				// TODO: NAO ADICIONAR PAGINA DEPOIS DO ULTIMO CAPITULO
				pdfDocument.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
			}
			
		} catch (Exception ex) {
			throw new IllegalStateException("Erro ao criar arquivo PDF: " + arquivoDeSaida.toAbsolutePath(), ex);
		}
	}
}
