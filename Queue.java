package fila;
import exceptions.*;

public class Queue{
	private Node inicio;
	private Node fim;
	private int size;

	public void enqueue(int valor) throws ValorExistenteException{
		Node novo = new Node(valor);
		Node aux = inicio;
		//se tiver vazio
		if(inicio == null){
			inicio = novo;
			fim = inicio;
			size++;
			return;
		}else{
			while(true){
				if(aux.getNext() != null){ 
					if(aux.getInfo() == valor) throw new ValorExistenteException("O valor ja existe na fila");
					aux = aux.getNext();
				}
				else{
					aux.setNext(novo);
					fim = aux.getNext();
					size++;
					return;
				}
			}
		}
	}

	public int dequeue() throws UnderflowException{
		if(inicio == null) throw new UnderflowException("A fila esta vazia.");
		Node novo = inicio;
		inicio = inicio.getNext();
		size--;
		return novo.getInfo();
	}

	public int getSize(){
		return this.size;
	}

	public void print(){
		Node aux = inicio;
		while(aux != null){
			System.out.println(aux.getInfo());
			aux = aux.getNext();
		}
	}

	public Node getFim(){
		return this.fim;
	}

	public Node getInicio(){
		return this.inicio;
	}
}