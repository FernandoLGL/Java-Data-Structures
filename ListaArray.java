import java.util.Scanner;


public class ListaArray {
	
	private Integer[] repositorio;
	private int contador;
	
	public ListaArray(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Qual vai ser o tamanho do array?");
		int tamanho = sc.nextInt();
		repositorio = new Integer[tamanho];
	}
	
	public ListaArray(int info){
		Scanner sc = new Scanner(System.in);
		System.out.println("Qual vai ser o tamanho do array?");
		int tamanho = sc.nextInt();
		repositorio = new Integer[tamanho];
		repositorio[0] = info;
		contador++;
	}
	
	public void ordenar() {
		for (int z = 0; z < 15; z++) {
			int aux;
			if (repositorio[z] > repositorio[z + 1]) {
				if (repositorio[z + 1] == 0) {
				} else {
					aux = repositorio[z];
					repositorio[z] = repositorio[z + 1];
					repositorio[z + 1] = aux;
					for (int i = z; i != 0; i--) {
						if (repositorio[z - i] > repositorio[z]) {
							aux = repositorio[z - i];
							repositorio[z - i] = repositorio[z];
							repositorio[z] = aux;
						}

					}

				}
			}
		}
	}
	
	public int buscaBinaria(int k){
		return 0;
	}
	
	public void imprimir(){
		if(contador == 0){
			System.out.println("A lista esta vazia.");
		}
		ordenar();
		for(int i = 0; i <= contador; i++){
			if(repositorio[i] != null){
				System.out.println(repositorio[i]);
			}
		}
	}
	
	public void esvaziar(){
		if(contador == 0){
			System.out.println("A lista ja esta vazia.");
		}
		for(int i = 0; i < contador; i++){
			repositorio[i] = null;
		}
		contador = 0;
		System.out.println("Esvaziou");
	}
	
	public void adicionar(int k){
		repositorio[contador] = k;
		contador++;
		ordenar();
	}
	
	public void consultar(int k){
		ordenar();
		System.out.println("O valor dessa posicao eh: " + repositorio[k - 1]);
		System.out.println("O proximo valor eh: " + repositorio[k]);
		System.out.println("O valor anterior eh: " + repositorio[k - 2]);
	}
	
	public int acharIesimo(int k){
		ordenar();
		return repositorio[k-1];
	}
	
	public void remover(int k){
		ordenar();
		if(contador == 0){
			System.out.println("A lista esta vazia.");
			return;
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
	
	public static void main(String[] args){
		ListaArray la = new ListaArray(2);
		la.adicionar(5);
		la.adicionar(7);
		la.adicionar(4);
		la.remover(7);
		la.consultar(3);
		la.imprimir();
	}
}
