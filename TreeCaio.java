package arvore;

import exceptions.*;
import java.util.concurrent.*;

public class Tree {
	// raiz
	public BinaryTreeNode raiz;

	public void insert(int valor) throws ArvoreVaziaException, ElementoExistenteException {
		BinaryTreeNode aux = raiz;
		BinaryTreeNode valorNode = new BinaryTreeNode(valor);
		// quando a arvore estiver vazia
		if (raiz == null) {
			raiz = valorNode;
			return;
		}
		// se o elemento ja existir na arvore
		else if (exists(valor))
			throw new ElementoExistenteException("O elemento ja existe.");

		else {
			// loop
			while (true) {
				// se o valor for maior que o atual valor do auxiliar
				if (valor > aux.getInfo()) {
					// nao for nulo, procede pra direita
					if (aux.getRight() != null) {
						aux = aux.getRight();
					//se for nulo, usa o set.
					} else {
						aux.setRight(valorNode);
						return;
					}
				//mesma logica, desta vez para a esquerda.
				} else if (valor < aux.getInfo()) {
					if (aux.getLeft() != null) {
						aux = aux.getLeft();
					} else {
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
			// Mesmo esquema do metodo "find()", porem dessa vez, se encontrado, retornara "true"
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
		// auxiliares
		BinaryTreeNode aux = find(a);
		BinaryTreeNode dad = getFather(a);
		// auxiliares para fazerem a subistituição
		BinaryTreeNode auxSubs = getSubstitute(a);
		BinaryTreeNode dadSubs = getFather(auxSubs.getInfo());
		// se for Raiz
		if (aux == raiz) {
			// Caso o nó substituto seja uma folha
			if (auxSubs.getLeft() == null && auxSubs.getRight() == null) {
				// deixando nulo o local on a auxiliar que vai servir de
				// substituta ficava e deixando null
				if (dadSubs.getLeft() == auxSubs)
					dadSubs.setLeft(null);
				else if (dadSubs.getRight() == auxSubs)
					dadSubs.setRight(null);
			}
			// pegando filho da auxiliar que vai servir de substituta e botando
			// como ramo do pai dela no lugar onde ela ficava
			else if (auxSubs.getLeft() != null)
				dadSubs.setRight(auxSubs.getLeft());
			else if (auxSubs.getRight() != null)
				dadSubs.setLeft(auxSubs.getRight());
			// botando auxiliar no lugar da raiz
			auxSubs.setLeft(raiz.getLeft());
			auxSubs.setRight(raiz.getRight());
			raiz = auxSubs;
		}
		// se for Folha
		else if (aux.getLeft() == null && aux.getRight() == null) {
			// apagando a folha
			if (dad.getLeft().getInfo() == a)
				dad.setLeft(null);
			else if (dad.getRight().getInfo() == a)
				dad.setRight(null);
		}
		// se tiver um filho(direita)
		else if (aux.getLeft() == null) {

			// se o substituto for filho do nó(ou seja se ele for para
			// direita e o filho dele não ter filhos a esquerda)
			if (dad.getLeft() != null && dad.getLeft().getInfo() == aux.getInfo())
				dad.setLeft(auxSubs);
			else if (dad.getRight() != null && dad.getRight().getInfo() == aux.getInfo())
				dad.setRight(auxSubs);

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
			// ele vai pra esquerda pois esse é o padrão que vamos usar
			// se o substituto for filho do nó(ou seja se ele for para esquerda
			// e o filho dele não ter filhos a direita)
			if (aux.getLeft().getInfo() == auxSubs.getInfo()) {
				// caso o filho a esquerda não tenha filhos a direita
				if (aux.getRight() != null) {
					// botando o filho a direita do nó a ser substituido no
					// substituto dele
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
				// botando os dado do nó removido no substituto
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
		//se o valor requisitado nao existir na arvore
		if (!exists(valor))
			throw new NaoExisteException("O valor nao existe.");
		BinaryTreeNode aux = raiz;
		//se o valor for a raiz, nao tem pai.
		if (aux.getInfo() == valor)
			return null;
		do {
			//checando
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
			// percorrendo
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
		boolean verif = false;
		do {
			if (!verif) {
				// veifica se o nó a esquerda não é nulo
				if (aux.getLeft() != null) {
					aux = aux.getLeft();
					verif = true;
				}
				// veifica se o nó a direita não é nulo(a 1 verificação já
				// falhou)
				else if (aux.getRight() != null) {
					aux = aux.getRight();
					return aux;
				}
				// caso ele não tenha filhos
				else {
					return aux;
				}
			}
			// pegando o maior filho a direita(getMaior())
			else if (verif) {
				if (aux.getRight() != null) {
					aux = aux.getRight();
				} else {
					return aux;
				}
			}
		} while (true);

	}

	public int getDepth(int valor) throws ArvoreVaziaException, NaoExisteException {
		// começando a percorrer pela raiz
		BinaryTreeNode aux = raiz;
		int depth = 0;
		while (aux != null) {
			//percorrendo e somando depth ate o valor ser igual
			if (valor > aux.getInfo()) {
				aux = aux.getRight();
				depth++;
			} else if (valor < aux.getInfo()) {
				aux = aux.getLeft();
				depth++;
			}
			// quando for igual
			else
				return depth;
		}
		return 0;
	}

	public boolean isSon(int valorPai, int valorFilho) throws ArvoreVaziaException, NaoExisteException {
		BinaryTreeNode auxFilho = find(valorFilho);
		BinaryTreeNode auxPai = find(valorPai);
		//checando se é filho
		if ((auxPai.getRight().getInfo() == auxFilho.getInfo()) || (auxPai.getLeft().getInfo() == auxFilho.getInfo())) {
			return true;
		} else
			return false;

	}
//metodo para pegar altura do nó
	public int getHeight(int valor) throws ArvoreVaziaException, NaoExisteException {
		//metodo para pegar altura do nó
		BinaryTreeNode node = find(valor);

		if (node == null) {
			return -1;
		}
		//vendo se o nó é folha
		if (node.getLeft() == null && node.getRight() == null)
			return 0;
		//atributos
		int left = 0;
		int right = 0;
		//percorrendo arvore em ordem
		if (node.getLeft() != null)
			//indo para esquerda e somando enquanto não for nulo
			left = getHeight(node.getLeft().getInfo()) + 1;
		if (node.getRight() != null)
			//indo para direita e somando enquanto não for nulo
			right = getHeight(node.getRight().getInfo()) + 1;
		//retornando o maior entre os dois atributos
		return left > right ? left : right;
	}
    //metodo para pegar a altura maxima
	public int maxHeight() throws ArvoreVaziaException, NaoExisteException {
		return getHeight(raiz.getInfo());
	}
   //metodo para imprimir a partir da profundidade com nó auxiliar
	public String printLineTree(int valor, int valor3, int valor2) throws ArvoreVaziaException, NaoExisteException {
		//Atributos
		BinaryTreeNode nodePrincipal = find(valor);
		BinaryTreeNode auxNode = find(valor3);

		if (nodePrincipal == null) {
			return "";
		}
		//atributos
		String auxRetorno = "";
		String auxSecundaria = "";
		//condição para pega o linha e retirar os filhos do nó escolhido Usado no getSobrinho
		if (getDepth(valor) == valor2
				&& (auxNode.getLeft().getInfo() != valor && auxNode.getRight().getInfo() != valor))
			auxRetorno += nodePrincipal.getInfo() + " ";
		//percorrendo a arvore em ordem
		if (nodePrincipal.getLeft() != null) {
			//recursividade para percorer a arvore pela esquerda
			auxSecundaria += printLineTree(nodePrincipal.getLeft().getInfo(), valor3, valor2);
		}
		if (nodePrincipal.getRight() != null) {
			//recursividade para percorer a arvore pela esquerda
			auxSecundaria += printLineTree(nodePrincipal.getRight().getInfo(), valor3, valor2);
		}
		//concatenando
		auxRetorno += auxSecundaria;
		return auxRetorno;
	}
	//Retorna os sobrinhos
	public String getSobrinhos(int valor) throws ArvoreVaziaException, NaoExisteException{

		if(raiz != null){
			//raiz não tem sobrinhos
			if(valor == raiz.getInfo()){
				return "não tem sobrinhos";
			}
			//usando o metodo recursivo para pegar os sobrinhos
			return printLineTree(raiz.getInfo(),valor,getDepth(valor)+1);
		}else throw new ArvoreVaziaException("A arvore esta vazia.");
	}
	
//metodo para imprimir a partir da profundidade Usado no Imprimir
	public String printLineTree(int valor, int valor2) throws ArvoreVaziaException, NaoExisteException {
		//atributos
		BinaryTreeNode node = find(valor);
		
		if (node == null) {
			return "";
		}
		//atributo
		String aux = "";
		//imprimindo a linha em questão
		if (getDepth(valor) == valor2){
			aux = node.getInfo() +"";
		}String aux2 = " ";
		//percorrendo a arvore em ordem
		if (node.getLeft() != null) {
			//indo pra esquerda 
			aux2 += printLineTree(node.getLeft().getInfo(), valor2);
		}
		if (node.getRight() != null) {
			//indo pra direita
			aux2 += printLineTree(node.getRight().getInfo(), valor2);
		}
		//concatenando
		aux += aux2;
		return aux;
	}
	
	
	public void imprimirTree() throws ArvoreVaziaException, NaoExisteException {
		int aux = maxHeight();

		// fazendo a matriz
		for (int i = 0; i<= aux; i++) {
			for ( int j = 0; j < aux-i; j++) {
				System.out.print("   ");
			}
			//printando
			System.out.println(printLineTree(this.raiz.getInfo(), i));
			
		}
	}
}
