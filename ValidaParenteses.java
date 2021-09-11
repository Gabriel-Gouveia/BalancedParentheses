import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.io.*;

public class ValidaParenteses {

    // Essa lista vai guardar linhas de um arquivo-texto que será lido.
    static List<String> linhas;

    // Essa lista vai guardar objetos que juntam o parênteses com o valor de válido
    // ou inválido.
    // Essa lista vai ser útil para gerar o arquivo prog-check.txt
    static List<ParentesesBoolean> listaParaSaida = new ArrayList<>();

    // Nesse método eu vou pegar uma lista de linhas que a classe Arquivo retorna e
    // vou atribuir essa lista de linhas de um arquivo-texto à minha lista de
    // linhas.
    // No final, eu vou precisar gerar um arquivo-texto onde cada linha do arquivo
    // possua o parênteses e o valor de válido ou inválido desse parênteses.
    // Para gerar esse arquivo-texto de saída, eu vou criar um objeto que junta o
    // parênteses com o valor de válido ou inválido.
    // No método abaixo, esse objeto vai guardar apenas a linha e depois a lista
    // para a saída vai guardar esse objeto.
    public static void leArquivo() {
        linhas = Arquivo.retornaLinhas("prog.txt");

        for (String linha : linhas) {
            ParentesesBoolean p = new ParentesesBoolean();
            p.setParenteses(linha);
            listaParaSaida.add(p);
        }
    }

    // Esse método vai definir se tal parênteses é valido ou inválido. Vai atribuir um valor boolean.
    public static void analisaParenteses() {
        for (ParentesesBoolean objeto : listaParaSaida) {
            objeto.setValido(validandoParenteses(objeto.getParenteses()));
        }
    }

    // Gravando prog-check.txt, que é o arquivo de saída dizendo se cada parênteses é válido ou inválido.
    public static void escreveArquivo() {
        String conteudo = "";
        for (ParentesesBoolean objeto : listaParaSaida) {
            if (objeto.getValido() == false) {
                conteudo += objeto.getParenteses() + " - " + "INVALIDO" + "\n";
            }
            if (objeto.getValido() == true) {
                conteudo += objeto.getParenteses() + " - " + "OK" + "\n";
            }
        }
        Arquivo.Write("prog-check.txt", conteudo);
    }

    public static boolean validandoParenteses(String linha) {
        // Pilha que vai guardar abertura de parênteses, chave ou colchetes.
        // Se encontrar um char que fecha parênteses, chave ou colchetes, essa pilha
        // remove a abertura mais recente daquele tipo.
        // O parênteses, chave ou colchetes é considerado válido quando a pilha está
        // vazia.
        // Quando a pilha está vazia, significa que todos os parênteses foram fechados
        // sem ter nada aberto faltando ser fechado.
        Stack<Character> pilha = new Stack<>();

        // Em cada linha, eu irei andar de char em char, vendo se o char abre um
        // parênteses.
        for (int i = 0; i < linha.length(); i++) {

            // Se o char abre um parênteses
            if (linha.charAt(i) == '(' || linha.charAt(i) == '[' || linha.charAt(i) == '{' || linha.charAt(i) == '<') {
                // Coloco esse char na pilha.
                pilha.push(linha.charAt(i));
            } else {
                if (pilha.isEmpty()) {
                    return false;
                }
                if (linha.charAt(i) == ')' && pilha.peek() != '(' || linha.charAt(i) == ']' && pilha.peek() != '['
                        || linha.charAt(i) == '}' && pilha.peek() != '{'
                        || linha.charAt(i) == '>' && pilha.peek() != '<') {
                    return false;
                }
                pilha.pop();
            }
        }
        return pilha.isEmpty();
    }

    public static void main(String[] args) {
        File f = new File("prog.txt");

        if (f.exists()) {
            leArquivo();
            analisaParenteses();
            escreveArquivo();
        }

        else {
            System.out.println("Nenhum arquivo com o nome de prog.txt foi encontrado.");
        }
    }
}