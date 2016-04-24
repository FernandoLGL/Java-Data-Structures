package arvore;

import exceptions.ArvoreVaziaException;
import exceptions.NaoExisteException;

public class Tree {
	// ATRIBUTO SHOW
	public BinaryTreeNode raiz;

	// METODOS SHOWS
	public void insert(int number) {
		// AUXILIAR SHOW
		BinaryTreeNode galho = new BinaryTreeNode(number);
		BinaryTreeNode aux = raiz;
		// CHECAGEM SHOW
		if (raiz == null) {
			raiz = galho;
			System.out.println(galho.getInfo() + " raiz");
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
						System.out.println(aux.getInfo() + " esquerda ->" + galho.getInfo() + " fim");
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

	public BinaryTreeNode find(int valor) throws ArvoreVaziaException, NaoExisteException {
		// se nao existe o valor na arvore
		if (!exists(valor))
			throw new NaoExisteException("O elemento nao existe.");
		// se a arvore estiver vazia
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
				} else if (valor < aux.getInfo()) {
					aux = aux.getLeft();
				}
			} while (aux != null);
			return null;
		}
	}

	public boolean exists(int valor) throws ArvoreVaziaException {
		// se a arvore estiver vazia, nao tem pra que procurar pela existencia.
		if (raiz == null)
			throw new ArvoreVaziaException("A arvore esta vazia");
		// criando no auxiliar
		BinaryTreeNode aux = raiz;
		do {
			// Mesmo esquema do metodo "find()", porem dessa vez, se encontrado,
			// retornara "true"
			if (valor == aux.getInfo())
				return true;
			else if (valor > aux.getInfo()) {
				aux = aux.getRight();
			} else if (valor < aux.getInfo()) {
				aux = aux.getLeft();
			}
		} while (aux != null);
		// se chegou no ponto em que aux == null, o valor nao existe na arvore.
		return false;
	}

	public BinaryTreeNode substitute(int a) throws ArvoreVaziaException, NaoExisteException {
		BinaryTreeNode aux = this.find(a);
		boolean verif1 = true;
		boolean verif2 = true;
		do {
			if (verif1 && verif2) {
				if (aux.getLeft() != null) {
					aux = aux.getLeft();
					verif1 = false;
				} else if (aux.getRight() != null) {
					aux = aux.getRight();
					verif2 = false;
				} else {
					return aux;
				}
			} else if (verif2) {
				if (aux.getRight() != null) {
					aux = aux.getRight();
				} else {
					return aux;
				}
			} else if (verif1) {
				if (aux.getLeft() != null) {
					aux = aux.getLeft();
				} else {
					return aux;
				}
			}
		} while (true);

	}

	public BinaryTreeNode findDad(int valor) throws ArvoreVaziaException, NaoExisteException {
		// se nao existe o valor na arvore
		if (!exists(valor))
			throw new NaoExisteException("O elemento nao existe.");
		// se a arvore estiver vazia
		else {
			// criando no auxiliar
			BinaryTreeNode aux = raiz;
			BinaryTreeNode dad = aux;
			do {
				// verificando se o valor atual eh igual ao valor recebido como
				// parametro
				if (valor == aux.getInfo())
					return dad;
				// andando pela arvore em busca do valor, que existe.
				else if (valor > aux.getInfo()) {
					dad = aux;
					aux = aux.getRight();
				} else if (valor < aux.getInfo()) {
					dad = aux;
					aux = aux.getLeft();
				}
			} while (aux != null);
			return null;
		}
	}

	public void remove(int a) throws ArvoreVaziaException, NaoExisteException {
		BinaryTreeNode aux = find(a);
		BinaryTreeNode dad = findDad(a);
		BinaryTreeNode auxS = substitute(a);
		BinaryTreeNode dadS = findDad(auxS.getInfo());
		// se for Raiz
		if (a == raiz.getInfo()) {
			System.out.println(" entrou 1");
			if (auxS.getLeft() != null)
				dadS.setRight(auxS.getLeft());
			else if (auxS.getRight() != null)
				dadS.setLeft(auxS.getRight());
			auxS.setLeft(raiz.getLeft());
			auxS.setRight(raiz.getRight());
			raiz = auxS;
		}
		// se for Folha
		else if (aux.getLeft() == null && aux.getRight() == null) {
			System.out.println(" entrou 2");
			if (dad.getLeft() == aux)
				dad.setLeft(null);
			else if (dad.getRight() == aux)
				dad.setRight(null);
		}
		// se for iqual ao substituto
		else if (aux == auxS) {
			System.out.println(" entrou 3");
			if (aux.getLeft() != null)
				dad.setRight(aux.getLeft());
			else if (aux.getRight() != null)
				dad.setLeft(aux.getRight());
		}
		// se tiver um filho(direita)
		else if (aux.getLeft() == null) {
			System.out.print(" entrou 4");
			if (aux.getRight() == auxS) {
				System.out.println(".1");
				if (dad.getLeft() == aux)
					dad.setLeft(auxS);
				else if (dad.getRight() == aux)
					dad.setRight(auxS);
			} else {
				System.out.println(".2");
				if (auxS.getRight() != null) {
					dadS.setLeft(auxS.getRight());
				} else {
					dadS.setLeft(null);
				}
				auxS.setRight(aux.getRight());
				if (dad.getLeft() == aux)
					dad.setLeft(auxS);
				else if (dad.getRight() == aux)
					dad.setRight(auxS);
			}
		}
		// se tiver um filho(esquerda)(1) ou 2 filhos (2)
		else {
			System.out.print(" entrou 5");
			if (aux.getLeft() == auxS) {
				System.out.println(".1");
				if (dad.getRight() == aux)
					dad.setRight(auxS);
				else if (dad.getLeft() == aux)
					dad.setLeft(auxS);
			} else {
				System.out.println(".2");
				if (auxS.getLeft() != null) {
					dadS.setRight(auxS.getLeft());
				} else {
					dadS.setRight(null);
				}
				auxS.setRight(aux.getRight());
				if (dad.getLeft() == aux)
					dad.setLeft(auxS);
				else if (dad.getRight() == aux)
					dad.setRight(auxS);
			}
		}
	}
}
