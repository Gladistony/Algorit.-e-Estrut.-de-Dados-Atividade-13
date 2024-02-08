package tree;

import java.util.ArrayList;
import java.util.List;

import estrut.Tree;

public class BinarySearchTree implements Tree{
    
    private No raiz;

    public BinarySearchTree() {
        raiz = null;
    }

    @Override
    public boolean buscaElemento(int valor) {
        // Chama o método recursivo passando a raiz como parâmetro
        return buscaElementoRecursivo(raiz, valor);
    }

    // Método privado que busca um elemento na árvore de forma recursiva
    private boolean buscaElementoRecursivo(No no, int valor) {
        // Se o nó é nulo, retorna falso
        if (no == null) {
        return false;
        }

        // Se o valor é igual ao valor do nó, retorna verdadeiro
        if (valor == no.valor) {
        return true;
        }

        // Se o valor é menor que o valor do nó, busca na subárvore esquerda
        if (valor < no.valor) {
        return buscaElementoRecursivo(no.esquerda, valor);
        }

        // Se o valor é maior que o valor do nó, busca na subárvore direita
        return buscaElementoRecursivo(no.direita, valor);
    }

    @Override
    public int minimo() {
        return minimo(this.raiz).valor;
    }
    
    private No minimo(No no) {
        return no.esquerda == null ? no : minimo(no.esquerda);
    }
    

    @Override
    public int maximo() {
        return maximo(this.raiz).valor;
    }
    
    private No maximo(No no) {
        return no.direita == null ? no : maximo(no.direita);
    }
    @Override
    public void insereElemento(int valor) {
        raiz = insereElementoRecursivo(raiz, valor);
    }

    // Método privado que insere um elemento na árvore de forma recursiva
    private No insereElementoRecursivo(No atual, int valor) {
    // Se o nó atual é nulo, cria um novo nó com o valor e retorna
        if (atual == null) {
            return new No(valor);
        }

        // Se o valor é menor que o valor do nó atual, insere na subárvore esquerda
        if (valor < atual.valor) {
            atual.esquerda = insereElementoRecursivo(atual.esquerda, valor);
        }
        // Se o valor é maior que o valor do nó atual, insere na subárvore direita
        else if (valor > atual.valor) {
            atual.direita = insereElementoRecursivo(atual.direita, valor);
        }
        // Se o valor é igual ao valor do nó atual, não insere (não permite valores repetidos)
        else {
            return atual;
        }
        // Retorna o nó atual atualizado
        return atual;
    }

    @Override
    public void remove(int valor) {
        raiz = removeRecursivo(raiz, valor);
    }


    private No removeRecursivo(No atual, int valor) {
        // Se o nó atual é nulo, retorna nulo
        if (atual == null) {
          return null;
        }
    
        // Se o valor é menor que o valor do nó atual, remove da subárvore esquerda
        if (valor < atual.valor) {
          atual.esquerda = removeRecursivo(atual.esquerda, valor);
        }
        // Se o valor é maior que o valor do nó atual, remove da subárvore direita
        else if (valor > atual.valor) {
          atual.direita = removeRecursivo(atual.direita, valor);
        }
        // Se o valor é igual ao valor do nó atual, remove o nó atual
        else {
          // Se o nó atual não tem filhos, retorna nulo
          if (atual.esquerda == null && atual.direita == null) {
            return null;
          }
          // Se o nó atual tem apenas um filho, retorna o filho
          if (atual.esquerda == null) {
            return atual.direita;
          }
          if (atual.direita == null) {
            return atual.esquerda;
          }
          // Se o nó atual tem dois filhos, substitui o valor do nó atual pelo menor valor da subárvore direita
          // e remove o nó que contém esse valor
          atual.valor = menorValor(atual.direita);
          atual.direita = removeRecursivo(atual.direita, atual.valor);
        }
    
        // Retorna o nó atual atualizado
        return atual;
    }    

    // Método auxiliar que retorna o menor valor de uma subárvore
    private int menorValor(No no) {
        // Se o nó tem filho esquerdo, continua procurando na subárvore esquerda
        if (no.esquerda != null) {
        return menorValor(no.esquerda);
        }
        // Se o nó não tem filho esquerdo, retorna o valor do nó
        return no.valor;
    }

    @Override
    public int[] preOrdem() {
        // Cria uma lista auxiliar para armazenar os valores
        List<Integer> lista = new ArrayList<>();
        // Chama o método recursivo passando a lista como parâmetro
        preOrdemRecursivo(raiz, lista);
        // Converte a lista em um array de inteiros e retorna
        return lista.stream().mapToInt(i -> i).toArray();
    }

    // Método privado que percorre a árvore em pré-ordem de forma recursiva
    private void preOrdemRecursivo(No no, List<Integer> lista) {
        // Se o nó não é nulo, adiciona o valor do nó na lista, depois visita a subárvore esquerda, depois a subárvore direita
        if (no != null) {
        lista.add(no.valor);
        preOrdemRecursivo(no.esquerda, lista);
        preOrdemRecursivo(no.direita, lista);
        }
    }

    @Override
    public int[] emOrdem() {
        // Cria uma lista auxiliar para armazenar os valores
        List<Integer> lista = new ArrayList<>();
        // Chama o método recursivo passando a lista como parâmetro
        emOrdemRecursivo(raiz, lista);
        // Converte a lista em um array de inteiros e retorna
        return lista.stream().mapToInt(i -> i).toArray();
    }

    // Método privado que percorre a árvore em ordem de forma recursiva
    private void emOrdemRecursivo(No no, List<Integer> lista) {
        // Se o nó não é nulo, visita a subárvore esquerda, depois adiciona o valor do nó na lista, depois visita a subárvore direita
        if (no != null) {
        emOrdemRecursivo(no.esquerda, lista);
        lista.add(no.valor);
        emOrdemRecursivo(no.direita, lista);
        }
    }

    @Override
    public int[] posOrdem() {
        // Cria uma lista auxiliar para armazenar os valores
        List<Integer> lista = new ArrayList<>();
        // Chama o método recursivo passando a lista como parâmetro
        posOrdemRecursivo(raiz, lista);
        // Converte a lista em um array de inteiros e retorna
        return lista.stream().mapToInt(i -> i).toArray();
    }

    // Método privado que percorre a árvore em pós-ordem de forma recursiva
    private void posOrdemRecursivo(No no, List<Integer> lista) {
        // Se o nó não é nulo, visita a subárvore esquerda, depois a subárvore direita, depois adiciona o valor do nó na lista
        if (no != null) {
        posOrdemRecursivo(no.esquerda, lista);
        posOrdemRecursivo(no.direita, lista);
        lista.add(no.valor);
        }
    }

}