package arvore;

import exceptions.*;

public class Tree {
	//ATRIBUTO SHOW
	public BinaryTreeNode raiz;

	//METODOS SHOWS
	public void insert(int valor) throws ArvoreVaziaException, ElementoExistenteException{
		BinaryTreeNode aux = raiz;
		BinaryTreeNode valorNode = new BinaryTreeNode(valor);
		//quando a arvore estiver vazia
		if(raiz == null){
			raiz = valorNode;
			return;
		}

		else if(exists(valor)) throw new ElementoExistenteException("O elemento ja existe.");

		else{
			//loop
			while(true){
				if(valor > aux.getInfo()){
					if(aux.getRight() != null) aux = aux.getRight();
					else{
						aux.setRight(valorNode);
						return;
					}
				}else if (valor < aux.getInfo()){
					if(aux.getLeft() != null ) aux = aux.getLeft();
					else{ 
						aux.setLeft(valorNode);
						return;
					}
				}
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
				} else {
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

		while(aux != null){
			// Mesmo esquema do metodo "find()", porem dessa vez, se encontrado, retornara "true"
			if(valor == aux.getInfo()) return true;
			if (valor > aux.getInfo()) {
				aux = aux.getRight();
			} else if(valor < aux.getInfo()) {
				aux = aux.getLeft();
			}
		}
		return false;
	}

	public void remove(int valor) throws ArvoreVaziaException, NaoExisteException{
		BinaryTreeNode aux = find(valor);

		if(!exists(valor)) throw new NaoExisteException("O valor nao existe.");

		// se tiver dois filhos
		if( (aux.getRight() != null) && (aux.getLeft() != null) ){
			//BinaryTreeNode auxMenor = getMenor();
			// pai do menor, para nao perder os possiveis filhos do menor
		//	BinaryTreeNode auxFather = getFather(getMenor().getInfo());
			// pai do no a ser excluido
			BinaryTreeNode auxFather2 = getFather(aux.getInfo());

		}

		// se for folha
		else if( (aux.getLeft() == null) && (aux.getRight() == null) ){
			//auxiliar2
			BinaryTreeNode pai = getFather(aux.getInfo());
			// caso seja o filho da direita que queremos remover
			if( (pai.getRight() != null) && (pai.getRight().getInfo() == aux.getInfo()) ){
				pai.setRight(null);
			}else{
				pai.setLeft(null);
			}

			return;
			// quando tiver apenas um filho e ele estiver na esquerda
		}else if( ((aux.getRight() == null) && (aux.getLeft() != null)) ){
			//variaveis auxiliares
			BinaryTreeNode auxValor = raiz;
			BinaryTreeNode auxFather;
			BinaryTreeNode auxFilho;
			//loop
			do{
				if(valor == auxValor.getInfo()){
					auxFather = getFather(auxValor.getInfo());
					if(auxFather != null){
						if(auxFather.getInfo() > auxValor.getInfo()) auxFather.setLeft(auxValor.getLeft());
						else if(auxFather.getInfo() < auxValor.getInfo()) auxFather.setRight(auxValor.getLeft());
						auxValor = null;
						// tentou deletar a raiz
					}else{
						raiz = raiz.getLeft();
					}
				}else if (valor > auxValor.getInfo()) {
					auxValor = auxValor.getRight();
				} else{
					auxValor = auxValor.getLeft();
				}
			}while(auxValor != null);
			return;
			//quando tiver apenas um filho e ele estiver na direita
		}else if( (aux.getRight() != null) && (aux.getLeft() == null) ){
			BinaryTreeNode auxValor = raiz;
			BinaryTreeNode auxFather;
			BinaryTreeNode auxFilho;
			do{
				if(valor == auxValor.getInfo()){
					auxFather = getFather(auxValor.getInfo());
					if(auxFather != null){
						if(auxFather.getInfo() > auxValor.getInfo()) auxFather.setLeft(auxValor.getRight());
						else if(auxFather.getInfo() < auxValor.getInfo()) auxFather.setRight(auxValor.getRight());
						auxValor = null;
						//foi tentado deletar a raiz
					}else{
						raiz = raiz.getRight();
					}
				}else if (valor > auxValor.getInfo()) {
					auxValor = auxValor.getRight();
				} else if(valor < auxValor.getInfo()) {
					auxValor = auxValor.getLeft();
				}
			}while(auxValor != null);
			return;
		}
	}

	private BinaryTreeNode getFather(int valor) throws NaoExisteException, ArvoreVaziaException{

		if(!exists(valor)) throw new NaoExisteException("O valor nao existe.");
 		BinaryTreeNode aux = raiz;
 		if(aux.getInfo() == valor) return null;
		do {

			if( (aux.getRight() != null) && (aux.getLeft() != null) ){
				if((aux.getRight().getInfo() == valor) || (aux.getLeft().getInfo() == valor)) return aux;
			}
			else if( (aux.getRight() != null) && (aux.getLeft() == null)){
				if(aux.getRight().getInfo() == valor) return aux;
			}
			else if( (aux.getRight() == null) && (aux.getLeft() != null ) ){
				if(aux.getLeft().getInfo() == valor) return aux;
			}

			if (valor > aux.getInfo()) {
				aux = aux.getRight();
			} else if(valor < aux.getInfo()) {
				aux = aux.getLeft();
			}	

		}while(aux != null);
		return null;
	}

//	private BinaryTreeNode getMaior(){
//
//	}
//
//	private BinaryTreeNode getMenor(){
//
//	}

/* EXPLICACAO PARA CAIO!! 

	Ha 3 situacoes para o remover. Sendo elas quando o no eh uma folha, quando o no tem um filho apenas
	e quando ele tem dois filhos.

	Eh necessario implementar esses metodos getMaior() e getMenor() para usar o remover. Para fazermos um caso geral,
	pois do jeito que eu estava fazendo antes, nao funcionava quando o no era uma raiz.

	Seria bom tambem utilizar o getMaior() e getMenor() mesmo quando o no possui apenas um filho, dai poderia fazer um if
	para checar em qual lado estaria o filho.

	Quanto ao getMaior() e getMenor(), o primeiro pega o maior da esquerda e o segundo pega o menor a direita.

	Tenta implementar eles o quanto antes, pois ai vai ficar facil. E assim que terminarmos o remover, ja sabemos
	como fazer o segundo topico do projeto. E ai eh soh ficar pensando no terceiro.

	Valeu.
	
*/

}
