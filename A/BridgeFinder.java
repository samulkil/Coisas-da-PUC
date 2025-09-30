import java.io.*;
import java.util.*;

public class BridgeFinder {

    static List<List<Integer>> graph;
    static int time = 0;
    static int[] disc, low, parent;
    static boolean[] visited;

    public static void main(String[] args) {
        
        String filePath = "E:\\Documentos2\\Grafos\\Trabalho\\teste3.txt"; 
        try {
            findBridges(filePath); // chamada 
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    public static void findBridges(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
// -----------------------------------------------"Montando" grafo,Colocando Arestas----------------------------------------------
        int n = Integer.parseInt(reader.readLine().trim());
        graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                String[] parts = line.trim().split(" ");
                int u = Integer.parseInt(parts[0]);
                int v = Integer.parseInt(parts[1]);

                // não-direcionado
                graph.get(u).add(v);
                graph.get(v).add(u);
            }
        }
        reader.close();
// ---------------------------------------------Metodo em Si------------------------------------------
        disc = new int[n]; //TD da DFS
        low = new int[n]; //menor tempo alcançável a partir do vértice (retornos ou descendentes) coisa do Tarjan
        parent = new int[n]; // pai na DFS
        visited = new boolean[n]; // se foi visitado
        Arrays.fill(parent, -1); // zerando tudo no começo

        System.out.println("Pontes encontradas:");
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }
    }

    static void dfs(int u) { //Metodo Recursivo da DFS em java
        visited[u] = true;
        disc[u] = low[u] = ++time;

        for (int v : graph.get(u)) {
            if (!visited[v]) {
                parent[v] = u;
                dfs(v);
                                                    //Importante notar que aqui saiu da Recursão, logo a DFS do vertice atual terminou 
                low[u] = Math.min(low[u], low[v]);  // atualizando o LOW de acordo com o filho e  pai

                if (low[v] > disc[u]) {             // Se o LOW for maior que a descoberta significa que não há outro caminho para vertice,logo PONTE
                    System.out.println(u + " - " + v);
                }
            } else if (v != parent[u]) {            // Se não, significa que há outro caminho que pode ter um low menor, então primeiro garantir que v não é pai direto de u...
                low[u] = Math.min(low[u], disc[v]); // ...Depois atualizar o low para refletir o menor entre os caminhos
            }
        }
    }
}
