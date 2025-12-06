package cotuba.domain;


/*
 * Classe criada no na pagina 47 para a implementação de domain model para geração de ebooks 
 */

public class Capitulo {

  private String titulo;

  private String conteudoHTML;

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getConteudoHTML() {
    return conteudoHTML;
  }

  public void setConteudoHTML(String conteudoHTML) {
    this.conteudoHTML = conteudoHTML;
  }

}