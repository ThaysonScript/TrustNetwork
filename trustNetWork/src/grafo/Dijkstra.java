package grafo;

import java.util.*;
import java.lang.Math;

public class Dijkstra {
    public static List<String> encontrarCaminho(Grafo grafo, String origem, String destino) {
        // Mapa para armazenar o custo acumulado (soma dos -log(peso)).
        Map<String, Double> dist = new HashMap<>();
        // Mapa para rastrear os predecessores (para reconstruir o caminho).
        Map<String, String> antecessor = new HashMap<>();
        // Fila de prioridade para selecionar o próximo vértice com menor custo.
        PriorityQueue<Par> fila = new PriorityQueue<>(Comparator.comparingDouble(p -> p.custo));

        // Inicializa todos os vértices com custo infinito.
        for (String vertice : grafo.getVertices()) {
            dist.put(vertice, Double.POSITIVE_INFINITY);
        }
        // O custo da origem é zero.
        dist.put(origem, 0.0);
        fila.add(new Par(origem, 0.0));

        while (!fila.isEmpty()) {
            Par atual = fila.poll();
            String v = atual.vertice;
            double custoAtual = atual.custo;

            // Se o custo atual for maior que o registrado, pule.
            if (custoAtual > dist.get(v)) {
                continue;
            }

            // Se chegou no destino, podemos interromper.
            if (v.equals(destino)) {
                break;
            }

            // Percorre os vizinhos do vértice atual.
            for (Aresta aresta : grafo.getAdjacentes(v)) {
                String vizinho = aresta.getDestino();
                // Converte o peso para custo usando -log(peso)
                double custoAresta = -Math.log(aresta.getPeso());
                double novoCusto = dist.get(v) + custoAresta;
                if (novoCusto < dist.get(vizinho)) {
                    dist.put(vizinho, novoCusto);
                    antecessor.put(vizinho, v);
                    fila.add(new Par(vizinho, novoCusto));
                }
            }
        }

        // Reconstrói o caminho a partir do destino até a origem.
        List<String> caminho = new ArrayList<>();
        String atual = destino;
        if (!antecessor.containsKey(destino) && !origem.equals(destino)) {
            // Se não foi encontrado caminho, retorna lista vazia.
            return caminho;
        }
        while (atual != null) {
            caminho.add(atual);
            atual = antecessor.get(atual);
        }
        Collections.reverse(caminho);
        return caminho;
    }


    public static double calcularConfianca(double custoTotal) {
        return Math.exp(-custoTotal);
    }

    // Classe auxiliar para armazenar um vértice e seu custo acumulado.
    static class Par {
        String vertice;
        double custo;

        public Par(String vertice, double custo) {
            this.vertice = vertice;
            this.custo = custo;
        }
    }
}
