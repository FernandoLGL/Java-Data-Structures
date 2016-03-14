
public class ListaLigada {
	private Node inicio;
	
	public ListaLigada(){
		
	}

	public ListaLigada(int info){
		Node novo = new Node(info);
		inicio = novo;
	}

	public void adicionar(int info){
		//Node da info
		Node novo = new Node(info);
		//ver se a lista ta vazia
		if (inicio == null) {
			inicio = novo;
		}
		//se a lista tiver apenas 1 elemento
		else if (inicio.getNext() == null) {
			inicio.setNext(novo);
		} 
		// se a lista tiver +1 elemento
		else {
				Node aux = inicio.getNext();
				//percorrendo a lista atras do ultimo elemento
				while (aux.getNext() != null) {
					aux = aux.getNext();
				}
				aux.setNext(novo);

		}
	}
	public void remover(int k){
		
	}
	public void imprimir(){
		
		if(inicio == null){
			System.out.println("A lista esta vazia!!");
		}
		else{
			Node aux = inicio.getNext();
			System.out.println(inicio.getInfo());
			//percorrendo a lista atras do ultimo elemento
			while (aux != null) {
				System.out.println(aux.getInfo());
				aux = aux.getNext();
			}
		}

	}
	public void esvaziar(){
		
		if(inicio == null){
			// Pra quem estiver lendo isso, vai ter um Kirby novo com robô
			System.out.println("A lista ja esta vazia!!");
		}
		else{
			// Matando a jararaca pela cabeça
			inicio = null;
		}
		
	}
	public int acharIesimo(int k){
		return 0;
	}
	public void consultar(int k){
		
	}
	
	public static void main(String[] args) {
		ListaLigada l1 = new ListaLigada(2);
		l1.adicionar(60);
		l1.adicionar(3);
		l1.adicionar(5);
		l1.adicionar(8);
		l1.adicionar(9);
		l1.imprimir();
		l1.esvaziar();
		l1.imprimir();
	}
}
