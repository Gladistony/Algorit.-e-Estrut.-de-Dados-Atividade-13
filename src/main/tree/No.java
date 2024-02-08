package tree;

class No {
    int valor; // Valor armazenado no nó
    No esquerda; // Referência ao filho esquerdo
    No direita; // Referência ao filho direito
  
    // Construtor que recebe um valor e inicializa os filhos como nulos
    No(int valor) {
      this.valor = valor;
      esquerda = null;
      direita = null;
    }
  }