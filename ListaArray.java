package lista;

//imports
import java.util.Scanner;

import excecoes.*;

public class ListaArray {
	//atributos
	private Integer[] repositorio;
	private int contador;
	private int tamanho;
	
	//construtor1
	public ListaArray(){
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Qual vai ser o tamanho do array?");
		tamanho = sc.nextInt();
		
		if(tamanho == 0){
			System.out.println("O seu array nao pode ser de tamanho 0! O tamanho padrao(5) foi selecionado.");
			tamanho = 5;
		}
		
		repositorio = new Integer[tamanho];
	}
	//construtor2
	public ListaArray(int info){
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Qual vai ser o tamanho do array?");
		tamanho = sc.nextInt();
		
		if(tamanho == 0){
			System.out.println("O seu array nao pode ser de tamanho 0! O tamanho padrao(5) foi selecionado.");
			tamanho = 5;
		}
		
		repositorio = new Integer[tamanho];
		repositorio[0] = info;
		contador++;
		
	}
	//algoritmo de ordenacao
	public void ordenar() {
		for (int z = 0; z < contador; z++) {
			int aux;
			for (int i = z; i != 0; i--) {
				if (repositorio[z - i] > repositorio[z]) {
					aux = repositorio[z - i];
					repositorio[z - i] = repositorio[z];
					repositorio[z] = aux;
				}

			}
		}
	}
	
	//metodo imprimir
	public void imprimir() throws ListaVaziaException{
		
		if(contador == 0){
			throw new ListaVaziaException("A lista se encontra vazia");
		}
		
		ordenar();
		System.out.println("A lista eh: ");
		for(int i = 0; i < contador; i++){
			if(repositorio[i] != null){
				System.out.print(repositorio[i] + " ");
			}
		}
		
	}
	//metodo esvaziar
	public void esvaziar() throws ListaVaziaException{
		
		if(contador == 0){
			throw new ListaVaziaException("A lista ja esta vazia.");
		}
		
		for(int i = 0; i < contador; i++){
			repositorio[i] = null;
		}
		
		contador = 0;
		System.out.println("Lista esvaziada.");
	}
	//metodo adicionar
	public void adicionar(int k) throws ListaCheiaException{
		
		if(contador == tamanho){
			throw new ListaCheiaException("A lista esta cheia.");
		}
		
		repositorio[contador] = k;
		contador++;
		ordenar();
	}
	
	//metodo consultar implementado com Busca Binaria
	public int consultar(int k) throws ValorInvalidoException{
		ordenar();
		int comeco = 0;
		int topo = contador - 1;
		
		while(comeco <= topo){
			if(repositorio[comeco] == k){
	        	return repositorio[comeco];
			}else if(repositorio[comeco] > k){
				topo--;
			}else if(repositorio[comeco] < k){
				comeco++;
			}
		}
		throw new ValorInvalidoException("O valor eh invalido.");
	}
	//metodo acharIesimo
	public int acharIesimo(int k) throws ValorInvalidoException{
		
		if( (k <= 0) || (k > tamanho) || (repositorio[k-1] == null) ){
			throw new ValorInvalidoException("Valor invalido!");
		}
		
		ordenar();
		
		return repositorio[k-1];
	}
	//metodo remover
	public void remover(int k) throws ListaVaziaException{
		ordenar();
		if(contador == 0){
			throw new ListaVaziaException("A lista esta vazia.");
		}
		
		for(int i = 0; i < contador; i++){
			if(repositorio[i] == k){
				for(int j = i; j < contador; j++){
					repositorio[j] = repositorio[j + 1];
				}
				return;
			}
		}
		System.out.println("Invalido!!");
		contador--;
	}
}	
