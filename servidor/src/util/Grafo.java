package util;

/**
 * Created by wanderson on 28/07/17.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Grafo {
    public class Vertice {
        String nome;
        List<Aresta> adj;

        Vertice(String nome) {
            this.nome = nome;
            this.adj = new ArrayList<Aresta>();
        }

        void addAdj(Aresta e) {
            adj.add(e);
        }

        public String getNome() {
            return nome;
        }
    }

    public class Aresta {
        Vertice origem;
        Vertice destino;

        Aresta(Vertice origem, Vertice destino) {
            this.origem = origem;
            this.destino = destino;
        }
    }

    List<Vertice> vertices;
    List<Aresta> arestas;

    public Grafo() {
        vertices = new ArrayList<Vertice>();
        arestas = new ArrayList<Aresta>();
    }

    Vertice addVertice(String nome) {
        Vertice v = new Vertice(nome);
        vertices.add(v);
        return v;
    }

    Aresta addAresta(Vertice origem, Vertice destino) {
        Aresta e = new Aresta(origem, destino);
        origem.addAdj(e);
        arestas.add(e);
        return e;
    }

    public String toString() {
        String r = "";
        for (Vertice u : vertices) {
            r += u.nome + " -> ";
            for (Aresta e : u.adj) {
                Vertice v = e.destino;
                r += v.nome + ", ";
            }
            r += "\n";
        }
        return r;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Vertice> array = new ArrayList();
        Grafo g = new Grafo();
        Vertice v1 = null;
        Vertice v2 = null;

        Scanner scanner = new Scanner(new FileReader("rotas.data")).useDelimiter("\\||\\n");

        while (scanner.hasNext()) {
            String linha = scanner.nextLine();
            if (linha.startsWith(";")) {
                String[] aux = linha.trim().split(";");
                for (int i = 0; i < aux.length - 1; i++) {
                    System.out.println("vertice.." + aux[i + 1] + ".");
                    array.add(g.addVertice(aux[i + 1]));
                }
            } else{
                String[] aux = linha.trim().split(":");
                for (int i = 0; i < array.size(); i++) {
                    if (array.get(i).getNome().equals(aux[0])) {
                        v1 = array.get(i);
                        for (int j = 0; j < array.size(); j++) {
                            if (array.get(j).getNome().equals(aux[1])) {
                                v2 = array.get(j);
                                g.addAresta(v1, v2);
                                v1 = null;
                                v2 = null;
                            }
                        }
                    }
                }
            }
        }

        System.out.println("tam: " + array.size());
        System.out.println(g);
    }
}


 /*Grafo f = new Grafo();
        Vertice s = f.addVertice("s");
        Vertice t = f.addVertice("t");
        Vertice y = f.addVertice("y");
        Aresta st = f.addAresta(s, t);
        Aresta sy = f.addAresta(s, y);
        Aresta ty = f.addAresta(t, y);
        Aresta yt = f.addAresta(y, t);
        System.out.println(f);*/