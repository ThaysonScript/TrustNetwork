import grafo.Dijkstra;
import grafo.Grafo;
import grafo.GrafoRandomico;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        LerArquivos novaLeituraArquivo = new LerArquivos();

        List<String> nomes = novaLeituraArquivo.lerArquivo("trustNetWork/src/dados/seeds.txt");

//        Grafo grafo = GrafoRandomico.construirGrafoAleatorio(nomes, 0.01);
//
//        List<String> caminho = Dijkstra.encontrarCaminho(grafo, "Rafael Costa", "Ester Almeida");
//
//        if (caminho.isEmpty()) {
//            System.out.println("Nenhum caminho de confiança encontrado entre Alice e Bob.");
//        } else {
//            System.out.println("Caminho encontrado: " + caminho);
//         }


        System.out.println("Total de nomes: " + nomes.size());

        // 2. Construir o grafo com conexões aleatórias (por exemplo, probabilidade de 5%)
        Grafo grafo = GrafoRandomico.construirGrafoAleatorio(nomes, 0.05);
        System.out.println("\nGrafo criado (cada vértice e suas arestas):");
        grafo.imprimirGrafo();

        // 3. Escolher dois nomes para testar a busca de caminho com Dijkstra
        // Exemplo: "Alice" e "Bob". Certifique-se de que esses nomes existam em nomes.txt.
        String origem = "Alice";
        String destino = "Bob";

        System.out.println("\nProcurando caminho de confiança entre " + origem + " e " + destino + "...");
        List<String> caminho = Dijkstra.encontrarCaminho(grafo, origem, destino);
        if (caminho.isEmpty()) {
            System.out.println("Nenhum caminho de confiança encontrado entre " + origem + " e " + destino + ".");
        } else {
            System.out.println("Caminho encontrado:");
            System.out.println(String.join(" -> ", caminho));
            // Para calcular a confiança acumulada, precisamos somar os custos dos trechos do caminho.
            double custoTotal = 0.0;
            for (int i = 0; i < caminho.size() - 1; i++) {
                String atual = caminho.get(i);
                String proximo = caminho.get(i + 1);
                // Percorre as arestas de 'atual' para encontrar a que leva a 'proximo'
                double custoAresta = Double.POSITIVE_INFINITY;
                for (var aresta : grafo.getAdjacentes(atual)) {
                    if (aresta.getDestino().equals(proximo)) {
                        custoAresta = -Math.log(aresta.getPeso());
                        break;
                    }
                }
                custoTotal += custoAresta;
            }
            double confianca = Dijkstra.calcularConfianca(custoTotal);
            System.out.println("Confiança acumulada (produto dos pesos): " + String.format("%.4f", confianca));
        }
    }
}
