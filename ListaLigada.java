
public class ListaLigada {
	private Node inicio;
	private int contador = 0;
	
	public ListaLigada(){
		
	}

	public ListaLigada(int info){
		Node novo = new Node(info);
		inicio = novo;
		contador++;
	}

	public void adicionar(int info){
		Node novo = new Node(info);

		if(contador == 0){
			inicio = novo;
			contador++;
		}else{
			inicio.next = novo;
			contador++;
		}
	}
	public void remover(){
		
	}
	public void imprimir(){
		for(int i = 0; i < contador; i++){
			System.out.println();
		}
	}
	public void esvaziar(){
		
	}
	public void acharIesimo(){
		
	}
	public void consultar(){
		
	}
}
