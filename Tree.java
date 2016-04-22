package arvore;

import exceptions.ArvoreVaziaException;
import exceptions.NaoExisteException;

public class Tree {
	//ATRIBUTO SHOW
	public BinaryTreeNode raiz;

	//METODOS SHOWS
	public void insert(int number) {
		// AUXILIAR SHOW
		BinaryTreeNode galho = new BinaryTreeNode(number);
		BinaryTreeNode aux = raiz;
		// CHECAGEM SHOW
		if (raiz == null) {
			raiz = galho;
			System.out.println(galho.getInfo() +" raiz");
		} else {
			// #PARTIUPERCORRERARVORE
			do {
				if (number > aux.getInfo()) {
					// #INDOPRADIREITA
					if (aux.getRight() == null) {
						aux.setRight(galho);
						System.out.println(aux.getInfo() + " direita ->" + galho.getInfo() + " fim");
						return;
					} else {
						// VOLTANDO PRO LOOP
						System.out.println(aux.getInfo() + " direita ->" + aux.getRight().getInfo());
						aux = aux.getRight();
					}
				} else {
					// #INDOPRAESQUERDA(ECA FORA PT)
					if (aux.getLeft() == null) {
						aux.setLeft(galho);
						System.out.println(aux.getInfo() + " esquerda ->" + galho.getInfo()+ " fim");
						return;
					} else {
						// VOLTANDO PRO LOOP
						System.out.println(aux.getInfo() + " esquerda ->" + aux.getLeft().getInfo());
						aux = aux.getLeft();
					}
				}
			} // LOOP INFINITO SHOW
			while (true);
		}
	}

	public BinaryTreeNode find(int valor) throws ArvoreVaziaException, NaoExisteException{
		// se nao existe o valor na arvore
		if(!exists(valor)) throw new NaoExisteException("O elemento nao existe.");
		// se a arvore estiver vazia
		else if(raiz == null) throw new ArvoreVaziaException("A arvore esta vazia");
		else{
			// criando no auxiliar
			BinaryTreeNode aux = raiz;
			do {
				// verificando se o valor atual eh igual ao valor recebido como parametro
				if(valor == aux.getInfo()) return aux;
				// andando pela arvore em busca do valor, que existe.
				else if (valor > aux.getInfo()) {
					aux = aux.getRight();
				} else if(valor < aux.getInfo()) {
					aux = aux.getLeft();
				}
			}while(true);
		}
	}
	public boolean exists(int valor) throws ArvoreVaziaException{
		// se a arvore estiver vazia, nao tem pra que procurar pela existencia.
		if(raiz == null) throw new ArvoreVaziaException("A arvore esta vazia");
		// criando no auxiliar
		BinaryTreeNode aux = raiz;
		do {
			// Mesmo esquema do metodo "find()", porem dessa vez, se encontrado, retornara "true"
			if(valor == aux.getInfo()) return true;
			else if (valor > aux.getInfo()) {
				aux = aux.getRight();
			} else if(valor < aux.getInfo()) {
				aux = aux.getLeft();
			}
		}while(aux != null);
		// se chegou no ponto em que aux == null, o valor nao existe na arvore.
		return false;
	}
}
