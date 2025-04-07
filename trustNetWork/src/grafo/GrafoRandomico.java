package grafo;

import java.util.List;
import java.util.Random;

public class GrafoRandomico {
    public static Grafo construirGrafoAleatorio(List<String> nomes, double probabilidadeAresta) {
        Grafo grafo = new Grafo();
        Random random = new Random();

        // Adiciona todos os vértices ao grafo.
        for (String nome : nomes) {
            grafo.adicionarVertice(nome);
        }

        int n = nomes.size();
        // Para cada par de vértices, cria uma aresta com base na probabilidade.
        for (int i = 0; i < n; i++) {
            String origem = nomes.get(i);
            for (int j = 0; j < n; j++) {
                if (i == j) continue; // ignora laços (conexão consigo mesmo)
                if (random.nextDouble() < probabilidadeAresta) {
                    String destino = nomes.get(j);
                    // Gera um peso aleatório entre 0.1 e 1.0
                    double peso = 0.1 + (1.0 - 0.1) * random.nextDouble();
                    grafo.adicionarAresta(origem, destino, peso);
                }
            }
        }

        return grafo;
    }
}
