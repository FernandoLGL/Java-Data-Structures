public class ListaLigada {
	private Node inicio;

	public ListaLigada() {

	}

	public ListaLigada(int info) {
		Node novo = new Node(info);
		inicio = novo;
	}

	public void adicionar(int info) throws ListaVaziaException, NodeJaExistenteException {
		// Node da info
		Node novo = new Node(info);
		// ver se já existe um node com essa informação
		if(consultarBoolean(info)){
			throw new NodeJaExistenteException("Um Node com essa informação já existe na Lista");
		}
		// ver se a lista ta vazia
		if (inicio == null) {
			inicio = novo;
		}
		// se a lista tiver apenas 1 elemento
		else if (inicio.getNext() == null) {
			inicio.setNext(novo);
		}
		// se a lista tiver +1 elemento
		else {
			Node aux = inicio.getNext();
			// percorrendo a lista atras do ultimo elemento
			while (aux.getNext() != null) {
				aux = aux.getNext();
			}
			aux.setNext(novo);
		}
		ordenar();
	}

	public void remover(int k) throws ListaVaziaException, NodeInexistenteException {
		// se a lista estiver vazia
		if (inicio == null) {
			throw new ListaVaziaException("A lista esta vazia");
		}
		// se ela tiver apenas o inicio
		else if (inicio.getNext() == null) {
			inicio = null;
		} else {
			// removendo o node(garbage fazendo seu trabalho)
			Node teste1;
			Node teste2;
			int aux = consultarPosicao(k);
			teste1 = acharIesimo(aux - 1);
			teste2 = acharIesimo(aux + 1);
			// se o elemento for o ultimo da lista
			if (teste2 == null) {
				teste1.setNext(null);
			}
			// removido
			else {
				teste1.setNext(teste2);
			}
		}
	}

	public void imprimir() throws ListaVaziaException {
		// se a lista for vazia
		if (inicio == null) {
			throw new ListaVaziaException("A lista esta vazia");
		} else {
			Node aux = inicio.getNext();
			System.out.println(inicio.getInfo());
			// percorrendo a lista atras do ultimo elemento
			while (aux != null) {
				System.out.println(aux.getInfo());
				aux = aux.getNext();
			}
		}

	}

	public void esvaziar() throws ListaVaziaException {
		// se a lista for vazia
		if (inicio == null) {
			throw new ListaVaziaException("A lista esta vazia");
		} else {
			// esvaziando(garbage fazendo seu trabalho)
			inicio = null;
		}

	}

	public Node acharIesimo(int k) throws ListaVaziaException, NodeInexistenteException {
		// se a lista for vazia
		if (inicio == null) {
			throw new ListaVaziaException("A lista esta vazia");
		} else {
			Node aux = inicio;
			int contador = 1;
			// percorrendo a lista atras do ultimo elemento
			while (aux != null && contador != k) {
				aux = aux.getNext();
				// se a posiçao for maior que o da lista
				if (aux == null) {
					throw new NodeInexistenteException("essa posição esta vazia");
				}
				contador++;
			}
			// voltando Node
			return aux;
		}
	}

	public void consultar(int k) throws ListaVaziaException, NodeInexistenteException {
		if (inicio == null) {
			//lista vazia
			throw new ListaVaziaException("A lista esta vazia");
		} else {
			Node aux = inicio;
			int aux2 = inicio.getInfo();
			int contador = 1;
			// percorrendo a lista atras do ultimo elemento
			while (aux != null && aux2 != k) {
				aux = aux.getNext();
				// se o Node n estiver na lista
				if (aux == null) {
					throw new NodeInexistenteException("esse Node nao esta na lista");
				}
				aux2 = aux.getInfo();
				contador++;
			}
			// consultando
			System.out.println("O nó esta na posição: " + contador + "\nSua informação é: " + aux2 + "\nO proximo é: "
					+ aux.getNext());
		}
	}

	private void ordenar() throws ListaVaziaException {
		if (inicio == null) {
			//lista vazia
			throw new ListaVaziaException("A lista esta vazia");
		} else {
			Node aux = inicio;
			Node aux2 = inicio;
			//pegando o ultimo elemento da lista
			while (aux.getNext() != null) {
				aux = aux.getNext();
			}
			//pegando o começo e comparando com o ultimo e trocando posições das informações
			while (aux2 != aux) {
				int aux3;
				//fazendo as mudanças de posição
				if (aux2.getInfo() > aux.getInfo()) {
					aux3 = aux2.getInfo();
					aux2.setInfo(aux.getInfo());
					aux.setInfo(aux3);
				}
				aux2 = aux2.getNext();
			}

		}
	}
	
	private int consultarPosicao(int k) throws ListaVaziaException, NodeInexistenteException{
		if (inicio == null) {
			//lista vazia
			throw new ListaVaziaException("A lista esta vazia");
		} else {
			Node aux = inicio;
			int aux2 = inicio.getInfo();
			int contador = 1;
			// percorrendo a lista até o ultimo elemento
			while (aux != null && aux2 != k) {
				aux = aux.getNext();
				//caso o elemente n exista 
				if (aux == null) {
					throw new NodeInexistenteException("esse Node nao esta na lista");
				}
				aux2 = aux.getInfo();
				contador++;
			}
			//retornando a posicao
			return contador;
			
		}
	}
	
	private boolean consultarBoolean(int k) throws ListaVaziaException{
			if (inicio == null) {
				//lista vazia
				throw new ListaVaziaException("A lista esta vazia");
			} else {
				Node aux = inicio;
				int aux2 = inicio.getInfo();
				// percorrendo a lista até o ultimo elemento
				while (aux != null && aux2 != k) {
					aux = aux.getNext();
					//caso o elemente n exista 
					if (aux == null) {
						return false;
					}
					aux2 = aux.getInfo();
				}
				//retornando se tiver um objeto com a informação 
				return true;
			}
		}

	public static void main(String[] args) {
		ListaLigada l1 = new ListaLigada(30);
		try {
			l1.adicionar(60);
			l1.adicionar(0);
			l1.adicionar(90);
			l1.adicionar(70);
			l1.adicionar(50);
			l1.adicionar(100);
			l1.adicionar(10);
			l1.adicionar(80);
			l1.adicionar(40);
			l1.adicionar(20);
			l1.imprimir();
			//l1.adicionar(30);
			l1.remover(30);
			l1.imprimir();
			// l1.remover(14);
			l1.consultar(100);
			// l1.consultar(52);
			System.out.println(l1.acharIesimo(7));
			// System.out.println(l1.acharIesimo(15));
			l1.esvaziar();
			l1.imprimir();
		} catch (ListaVaziaException | NodeInexistenteException | NodeJaExistenteException e) {
			System.out.println(e.getMessage());
		}
	}
}
