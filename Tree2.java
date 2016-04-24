package arvore;

import exceptions.*;

public class Tree {
	//ATRIBUTO SHOW
	public BinaryTreeNode raiz;

	//METODOS SHOWS
	public void insert(int valor) throws ArvoreVaziaException, ElementoExistenteException {
		BinaryTreeNode aux = raiz;
		BinaryTreeNode valorNode = new BinaryTreeNode(valor);
		if(raiz == null) raiz = valorNode;
		else if(exists(valor)) throw new ElementoExistenteException("O elemento ja existe.");
		else{
			if(valor > aux.getInfo()){
				if(aux.getRight() != null) aux = aux.getRight();
				else aux.setRight(valorNode);
			}else if (valor < aux.getInfo()){
				if(aux.getLeft() != null ) aux = aux.getLeft();
				else aux.setLeft(valorNode);
			}
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
			if (valor > aux.getInfo()) {
				aux = aux.getRight();
			} else if(valor < aux.getInfo()) {
				aux = aux.getLeft();
			}
		}while(aux != null);
		// se chegou no ponto em que aux == null, o valor nao existe na arvore.
		return false;
	}

	public void remove(int valor) throws ArvoreVaziaException, NaoExisteException{
		BinaryTreeNode aux = find(valor);

		if( (aux.getLeft() == null) && (aux.getRight() == null) ){
			aux = null;
			return;
		}else if( ((aux.getRight() == null) && (aux.getLeft() != null)) ){
			BinaryTreeNode auxValor = raiz;
			BinaryTreeNode auxFather;
			BinaryTreeNode auxFilho;
			do{
				if(valor == auxValor.getInfo()){
					auxFather = getFather(auxValor.getInfo());
					if(auxFather.getInfo() > auxValor.getInfo()) auxFather.setLeft(auxValor.getLeft());
					else if(auxFather.getInfo() < auxValor.getInfo()) auxFather.setRight(auxValor.getLeft());
					auxValor = null;
				}else if (valor > auxValor.getInfo()) {
					auxValor = auxValor.getRight();
				} else if(valor < auxValor.getInfo()) {
					auxValor = auxValor.getLeft();
				}
			}while(auxValor != null);
			return;
		}else if( (aux.getRight() != null) && (aux.getLeft() == null) ){
			BinaryTreeNode auxValor = raiz;
			BinaryTreeNode auxFather;
			BinaryTreeNode auxFilho;
			do{
				if(valor == auxValor.getInfo()){
					auxFather = getFather(auxValor.getInfo());
					if(auxFather.getInfo() > auxValor.getInfo()) auxFather.setLeft(auxValor.getRight());
					else if(auxFather.getInfo() < auxValor.getInfo()) auxFather.setRight(auxValor.getRight());
					auxValor = null;
				}else if (valor > auxValor.getInfo()) {
					auxValor = auxValor.getRight();
				} else if(valor < auxValor.getInfo()) {
					auxValor = auxValor.getLeft();
				}
			}while(auxValor != null);
			return;
		}else{
			BinaryTreeNode auxMinimo = aux;
			BinaryTreeNode aux2 = aux;
			do{
				auxMinimo = auxMinimo.getRight();
			}while(auxMinimo.getRight() != null);
				auxMinimo = getFather(auxMinimo.getInfo());
			do{
				auxMinimo = auxMinimo.getLeft();
			}while(auxMinimo.getLeft() != null);
			aux2 = auxMinimo;
			auxMinimo = null;
			return;
		}
	}

	// PROBLEMA NULLPOINTEREXCEPTION GETFATHER OLHAR DEPOIS

	public BinaryTreeNode getFather(int valor){

		BinaryTreeNode aux = raiz;
		do {
			if((aux.getRight().getInfo() == valor) || (aux.getLeft().getInfo() == valor)) return aux;
			else if (valor > aux.getInfo()) {
				aux = aux.getRight();
			} else if(valor < aux.getInfo()) {
				aux = aux.getLeft();
			}
		}while(aux != null);
		return null;
	}
}
