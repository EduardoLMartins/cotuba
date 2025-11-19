package cotuba.domain;

import cotuba.application.GeradorEbook;
import cotuba.epub.GeradorEPUB;
import cotuba.pdf.GeradorPDF;
import cotuba.html.GeradorHTML;


public enum FormatoEbook {

	PDF(new GeradorPDF()), EPUB(new GeradorEPUB()), HTML(new GeradorHTML());
	
	private GeradorEbook gerador;
	
	private FormatoEbook(GeradorEbook gerador) {
		
		this.gerador = gerador;
		// TODO Auto-generated constructor stub
	}
	
	public GeradorEbook getGeradorEbook() {
		return gerador;
	}
}
