
// Eu preciso gerar um arquivo-texto onde cada linha possui um parenteses e possui um valor de válido ou inválido.
// Para isso, eu criei uma classe que junta o parênteses de uma linha do arquivo-texto com o valor de válido ou inválido. 
public class ParentesesBoolean {
    private String parenteses;
    private boolean valido;

    public String getParenteses() {
        return this.parenteses;
    }

    public void setParenteses(String valor) {
        this.parenteses = valor;
    }

    public boolean getValido() {
        return this.valido;
    }

    public void setValido(boolean valor) {
        this.valido = valor;
    }

}
