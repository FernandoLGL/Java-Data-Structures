package arvore;

import exceptions.*;

public class Tree {
	//atributo raiz
	private BinaryTreeNode raiz;

	// METODOS SHOWS
	public void insert(int valor) throws ArvoreVaziaException, ElementoExistenteException {
		BinaryTreeNode aux = raiz;
		BinaryTreeNode valorNode = new BinaryTreeNode(valor);
		// quando a arvore estiver vazia
		if (raiz == null) {
			raiz = valorNode;
			System.out.println("raiz: " + valor);
			return;
		}

		else if (exists(valor))
			throw new ElementoExistenteException("O elemento ja existe.");

		else {
			// loop
			while (true) {
				if (valor > aux.getInfo()) {
					if (aux.getRight() != null) {
						System.out.println(aux.getInfo() + " direita " + aux.getRight().getInfo());
						aux = aux.getRight();
					} else {
						System.out.println(aux.getInfo() + " direita " + valor + " fim");
						aux.setRight(valorNode);
						return;
					}
				} else if (valor < aux.getInfo()) {
					if (aux.getLeft() != null) {
						System.out.println(aux.getInfo() + " esquerda " + aux.getLeft().getInfo());
						aux = aux.getLeft();
					} else {
						System.out.println(aux.getInfo() + " esquerda " + valor + " fim");
						aux.setLeft(valorNode);
						return;
					}
				}
			}
		}
	}

	public BinaryTreeNode find(int valor) throws ArvoreVaziaException, NaoExisteException {
		// se nao existe o valor na arvore
		if (!exists(valor))
			throw new NaoExisteException("O elemento nao existe.");
		// se a arvore estiver vazia
		else if (raiz == null)
			throw new ArvoreVaziaException("A arvore esta vazia");
		else {
			// criando no auxiliar
			BinaryTreeNode aux = raiz;
			do {
				// verificando se o valor atual eh igual ao valor recebido como
				// parametro
				if (valor == aux.getInfo())
					return aux;
				// andando pela arvore em busca do valor, que existe.
				else if (valor > aux.getInfo()) {
					aux = aux.getRight();
				} else {
					aux = aux.getLeft();
				}
			} while (true);
		}
	}

	public boolean exists(int valor) throws ArvoreVaziaException {
		// se a arvore estiver vazia, nao tem pra que procurar pela existencia.
		if (raiz == null)
			throw new ArvoreVaziaException("A arvore esta vazia");
		// criando no auxiliar
		BinaryTreeNode aux = raiz;

		while (aux != null) {
			// Mesmo esquema do metodo "find()", porem dessa vez, se encontrado,
			// retornara "true"
			if (valor == aux.getInfo())
				return true;
			if (valor > aux.getInfo()) {
				aux = aux.getRight();
			} else if (valor < aux.getInfo()) {
				aux = aux.getLeft();
			}
		}
		return false;
	}
	
	public void remove(int a) throws ArvoreVaziaException, NaoExisteException {
		//auxiliares
		BinaryTreeNode aux = find(a);
		BinaryTreeNode dad = getFather(a);
		//auxiliares para fazerem a subistituição
		BinaryTreeNode auxSubs = getSubstitute(a);
		BinaryTreeNode dadSubs = getFather(auxSubs.getInfo());
		// se for Raiz
		if (aux == raiz) {
			//Caso o nó substituto seja uma folha
			if (auxSubs.getLeft() == null && auxSubs.getRight() == null) {
				//deixando nulo o local on a auxiliar que vai servir de substituta ficava e deixando null
				if (dadSubs.getLeft() == auxSubs)
					dadSubs.setLeft(null);
				else if (dadSubs.getRight() == auxSubs)
					dadSubs.setRight(null);
			} 
			//pegando filho da auxiliar que vai servir de substituta e botando como ramo do pai dela no lugar onde ela ficava
			else if (auxSubs.getLeft() != null)
				dadSubs.setRight(auxSubs.getLeft());
			else if (auxSubs.getRight() != null)
				dadSubs.setLeft(auxSubs.getRight());
			//botando auxiliar no lugar da raiz
			auxSubs.setLeft(raiz.getLeft());
			auxSubs.setRight(raiz.getRight());
			raiz = auxSubs;
		}
		// se for Folha
		else if (aux.getLeft() == null && aux.getRight() == null) {
			//apagando a folha
			if (dad.getLeft().getInfo() == a)
				dad.setLeft(null);
			else if (dad.getRight().getInfo() == a)
				dad.setRight(null);
		}
		// se tiver um filho(direita)
		else if (aux.getLeft() == null) {
			if (aux.getRight().getInfo() == auxSubs.getInfo()) {
				// se o substituto for filho do nó(ou seja se ele for para
				// direita e o filho dele não ter filhos a esquerda)
				if (dad.getLeft().getInfo() == a)
					dad.setLeft(auxSubs);
				else if (dad.getRight().getInfo() == a)
					dad.setRight(auxSubs);
			} else {
				// substituindo o auxiliar pelo seu filho direita ou nulo se n
				// tiver
				if (auxSubs.getRight() != null) {
					dadSubs.setLeft(auxSubs.getRight());
				} else {
					dadSubs.setLeft(null);
				}
				// caso o nó a ser removido tiver filho a direita
				auxSubs.setRight(aux.getRight());
				if (dad.getLeft().getInfo() == a)
					dad.setLeft(auxSubs);
				else if (dad.getRight().getInfo() == a)
					dad.setRight(auxSubs);
			}
		}
		// se tiver um filho(esquerda)
		else if (aux.getRight() == null) {
			// se o substituto for filho do nó(ou seja se ele for para esquerda
			// e o filho dele não ter filhos a direita)
			if (aux.getLeft().getInfo() == auxSubs.getInfo()) {
				if (dad.getRight() == aux)
					dad.setRight(auxSubs);
				else if (dad.getLeft() == aux)
					dad.setLeft(auxSubs);
			} else {
				// substituindo o auxiliar pelo seu filho esquerdo ou nulo se n
				// tiver
				if (auxSubs.getLeft() != null) {
					dadSubs.setRight(auxSubs.getLeft());
				} else {
					dadSubs.setRight(null);
				}
				// caso o nó a ser removido tiver filho a direita
				auxSubs.setLeft(aux.getLeft());
				if (dad.getLeft() == aux)
					dad.setLeft(auxSubs);
				else if (dad.getRight() == aux)
					dad.setRight(auxSubs);
			}
		}
		// se tiver 2 filhos
		else if (aux.getLeft() != null && aux.getRight() != null) {
			//ele vai pra esquerda pois esse é o padrão que vamos usar
			// se o substituto for filho do nó(ou seja se ele for para esquerda
			// e o filho dele não ter filhos a direita)
			if (aux.getLeft().getInfo() == auxSubs.getInfo()) {
			   // caso o filho a esquerda não tenha filhos a direita
				if (aux.getRight() != null) {
					//botando o filho a direita do nó a ser substituido no substituto dele
					auxSubs.setRight(aux.getRight());
				}
				if (dad.getRight() == aux)
					dad.setRight(auxSubs);
				else if (dad.getLeft() == aux)
					dad.setLeft(auxSubs);
			} else {
				// substituindo o auxiliar pelo seu filho esquerdo ou nulo se n
				// tiver
				if (auxSubs.getLeft() != null) {
					dadSubs.setRight(auxSubs.getLeft());
				} else {
					dadSubs.setRight(null);
				}
				//botando os dado do nó removido no substituto
				auxSubs.setLeft(aux.getLeft());
				auxSubs.setRight(aux.getRight());
				if (dad.getLeft() == aux)
					dad.setLeft(auxSubs);
				else if (dad.getRight() == aux)
					dad.setRight(auxSubs);
			}

		}
	}

	private BinaryTreeNode getFather(int valor) throws NaoExisteException, ArvoreVaziaException {

		if (!exists(valor))
			throw new NaoExisteException("O valor nao existe.");
		BinaryTreeNode aux = raiz;
		if (aux.getInfo() == valor)
			return null;
		do {

			if ((aux.getRight() != null) && (aux.getLeft() != null)) {
				if ((aux.getRight().getInfo() == valor) || (aux.getLeft().getInfo() == valor))
					return aux;
			} else if ((aux.getRight() != null) && (aux.getLeft() == null)) {
				if (aux.getRight().getInfo() == valor)
					return aux;
			} else if ((aux.getRight() == null) && (aux.getLeft() != null)) {
				if (aux.getLeft().getInfo() == valor)
					return aux;
			}

			if (valor > aux.getInfo()) {
				aux = aux.getRight();
			} else if (valor < aux.getInfo()) {
				aux = aux.getLeft();
			}

		} while (aux != null);
		return null;
	}

	public BinaryTreeNode getSubstitute(int a) throws ArvoreVaziaException, NaoExisteException {
		BinaryTreeNode aux = this.find(a);
		boolean verif1 = true;
		boolean verif2 = true;
		do {
			if (verif1 && verif2) {
				//veifica se o nó a esquerda não é nulo
				if (aux.getLeft() != null) {
					aux = aux.getLeft();
					verif1 = false;
				} 
				//veifica se o nó a direita não é nulo(a 1 verificação já falhou)
				else if (aux.getRight() != null) {
					aux = aux.getRight();
					verif2 = false;
				}
				//caso ele não tenha filhos
				else {
					return aux;
				}
			} 
			//pegando o menor filho a direita(getMenor())
			else if (verif2) {
				if (aux.getRight() != null) {
					aux = aux.getRight();
				} else {
					return aux;
				}
			} 
			//pegando o maior filho a esquerda(getMaior())
			else if (verif1) {
				if (aux.getLeft() != null) {
					aux = aux.getLeft();
				} else {
					return aux;
				}
			}
		} while (true);

	}

	public void printSobrinhos(int valor){
		BinaryTreeNode auxValor = find(valor);
		BinaryTreeNode aux = raiz;
		//faz o algoritmo pra percorrer a arvore, exceto que vai conter o seguinte "if":

		//caso a profundidade do node auxiliar atual seja maior que a do alvo e o node auxiliar atual nao seja filho do alvo.
		if( (getDepth(aux.getInfo()) >  getDepth(auxValor.getInfo())) && (!isSon(auxValor.getInfo(), aux.getInfo())) ){
			System.out.println(aux.getInfo() + ", ");
		}else{
			// percorre a arvore usando aux.
		}
	}

	public void getDepth(int valor){
		BinaryTreeNode auxValor = find(valor);
		BinaryTreeNode aux = raiz;
		int depth = 0;
		while(aux != null){
			if(valor > aux.getInfo()){
				aux = aux.getRight();
				depth++;
			}else if(valor < aux.getInfo()){
				aux = aux.getLeft();
				depth++;
			}
			// quando for igual
			else return depth;
		}
	}

	public boolean isSon(int valorPai, int valorFilho){
		BinaryTreeNode auxFilho = find(valorFilho);
		BinaryTreeNode auxPai = find(valorPai);
		
		if(  ( auxPai.getRight().getInfo() == auxFilho.getInfo() ) || (auxPai.getLeft().getInfo() == auxFilho.getInfo()) ){
			return true;
		}else false;

	}

}
