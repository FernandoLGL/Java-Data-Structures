
public class ListaTesteCaio {
	// começo da lista
	Node inicio;

	public ListaTesteCaio() {
	}

	public ListaTesteCaio(int info) {
		inicio.setInfo(info);
	}

	public void adicionar(int info) {
		//Node da info
		Node novo = new Node(info);
		//ver se a lista ta vazia
		if (inicio == null) {
			inicio = novo;
			System.out.println("funcionou");
		}
		//se a lista tiver apenas 1 elemento
		else if (inicio.getNext() == null) {
			inicio.setNext(novo);
			System.out.println("ta funcionando yeah");
		} 
		// se a lista tiver +1 elemento
		else {
				Node aux = inicio.getNext();
				//percorrendo a lista atras do ultimo elemento
				while (aux.getNext() != null) {
					aux = aux.getNext();
				}
				aux.setNext(novo);
				System.out.println("uhuuuu ta funcionando");

		}

	}

	public int percorrer() {
		// node pra ajudar
		Node aux;
		//lista vazia
		if (inicio == null) {
			System.out.println("lista vazia");
			return 0;
		} 
		//lista com apenas 1 elemento
		else if (inicio.getNext() == null) {
			System.out.println("Lista possui apenas 1 elemento");
			return 1;
		}
		//lista com +1 elemento
		else {
			aux = inicio.getNext();
			int cont = 2;
			//percorrendo a lista
			while (aux.getNext() != null) {
				aux = aux.getNext();
				cont++;
			}
			return cont;
		}
	}

	public static void main(String[] args) {

		ListaTesteCaio teste2 = new ListaTesteCaio();
		teste2.adicionar(15);
		teste2.adicionar(25);
		teste2.adicionar(35);
		teste2.adicionar(45);
		teste2.adicionar(55);
		int i = teste2.percorrer();
		System.out.println(i);
		
	}
}
