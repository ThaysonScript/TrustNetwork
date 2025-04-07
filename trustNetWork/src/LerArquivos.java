import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LerArquivos {
    public List<String> nomes = new ArrayList<>();
    public Map<String, Integer> nomesParaIndice = new HashMap<>();

    public List<String> lerArquivo(String nomeArquivo) {
        try (BufferedReader arquivo = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;

            int i = 1;
            while ((linha = arquivo.readLine()) != null) {
                linha = linha.trim();

                if (!linha.isEmpty()) {
                    nomes.add(linha);
                    nomesParaIndice.put(linha, i++);
                }
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return nomes;
    }

    public void mostrarNomesCarregados() {
        System.out.println("Nomes carregados:");
        for (String nome : nomes) {
            System.out.println(nome);
        }
    }

    public void mostrarIndiceNomesCarregados(String nome) {
        System.out.println("Índice: " + nomesParaIndice.get(nome));
    }
}
