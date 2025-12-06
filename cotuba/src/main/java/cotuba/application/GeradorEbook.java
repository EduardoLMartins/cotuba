package cotuba.application;

import cotuba.domain.Ebook;
import cotuba.domain.FormatoEbook;
/*
 * Classse criada para Generalizar ainda mais as classes geradorPDF e geradorEPUB 
 * fazendo assim um acoplamento melhor para novas funcionalidades ao gerar Ebooks
 * 
 */

public interface GeradorEbook {

  void gera(Ebook ebook);

  static GeradorEbook cria(FormatoEbook formato) {
    return formato.getGerador();
  }

}