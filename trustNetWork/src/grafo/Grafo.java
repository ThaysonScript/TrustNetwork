package grafo;

import java.util.*;

public class Grafo {
    // Mapa que armazena cada vértice e suas arestas de saída.
    private final Map<String, List<Aresta>> adjacencias;

    public Grafo() {
        this.adjacencias = new HashMap<>();
    }

    // Adiciona um vértice se ele ainda não existir.
    public void adicionarVertice(String vertice) {
        adjacencias.putIfAbsent(vertice, new ArrayList<>());
    }

    // Adiciona uma aresta direcionada de 'origem' para 'destino' com um dado peso.
    public void adicionarAresta(String origem, String destino, double peso) {
        this.adicionarVertice(origem);
        this.adicionarVertice(destino);
        adjacencias.get(origem).add(new Aresta(destino, peso));
    }

    // Retorna a lista de arestas (vizinhos) de um vértice.
    public List<Aresta> getAdjacentes(String vertice) {
        return adjacencias.getOrDefault(vertice, new ArrayList<>());
    }

    // Retorna todos os vértices do grafo.
    public Set<String> getVertices() {
        return adjacencias.keySet();
    }

    // Imprime o grafo: para cada vértice, lista suas conexões.
    public void imprimirGrafo() {
        for (String vertice : getVertices()) {
            System.out.print(vertice + " -> ");
            List<Aresta> vizinhos = getAdjacentes(vertice);
            for (Aresta aresta : vizinhos) {
                System.out.print(aresta + " ");
            }
            System.out.println();
        }
    }
}
