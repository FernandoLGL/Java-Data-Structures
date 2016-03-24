import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuLigada {

	public static void main(String[] args) throws InterruptedException, IOException {
		Scanner sc = new Scanner(System.in);
		ListaLigada ll = new ListaLigada();
		int escolha;
		int numero;
		while(true){
			System.out.println("\n\n1 - Adicionar");
			System.out.println("2 - Remover");
			System.out.println("3 - Consultar");
			System.out.println("4 - Achar I-esimo elemento");
			System.out.println("5 - Esvaziar");
			System.out.println("6 - Imprimir");
			try{
				escolha = sc.nextInt();
				switch(escolha){
					case 1: System.out.println("Digite o numero que deseja adicionar");
					numero = sc.nextInt();
					ll.adicionar(numero);
					break;
					
					case 2: System.out.println("Digite o numero que deseja remover");
					numero = sc.nextInt();
					ll.remover(numero);
					break;
					
					case 3: System.out.println("Digite o valor que deseja consultar");
					numero = sc.nextInt();
					ll.consultar(numero);
					break;
					
					case 4: System.out.println("Digite a posicao do elemento");
					numero = sc.nextInt();
					System.out.println(ll.acharIesimo(numero));
					break;
					
					case 5: ll.esvaziar();
					break;
					
					case 6: ll.imprimir();
					break;
					
					default: System.out.println("Opcao invalida!");
					break;
				}
			}catch(InputMismatchException | ListaVaziaException | NodeJaExistenteException | NodeInexistenteException e){
				System.out.println(e.getMessage());
			} 
		}
	}

}
