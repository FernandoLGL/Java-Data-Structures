package menu;
//imports
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import excecoes.ListaCheiaException;
import excecoes.ListaVaziaException;
import excecoes.ValorInvalidoException;
import lista.*;
//
public class Menu {

	public static void main(String[] args) throws InterruptedException, IOException {
		Scanner sc = new Scanner(System.in);
		ListaArray la = new ListaArray();
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
					la.adicionar(numero);
					break;
					
					case 2: System.out.println("Digite o numero que deseja remover");
					numero = sc.nextInt();
					la.remover(numero);
					break;
					
					case 3: System.out.println("Digite o valor que deseja consultar");
					numero = sc.nextInt();
					System.out.println(la.consultar(numero));
					break;
					
					case 4: System.out.println("Digite a posicao do elemento");
					numero = sc.nextInt();
					System.out.println(la.acharIesimo(numero));
					break;
					
					case 5: la.esvaziar();
					break;
					
					case 6: la.imprimir();
					break;
					
					default: System.out.println("Opcao invalida!");
					break;
				}
			}catch(InputMismatchException e){
				System.out.println("Comando invalido!");
				break;
			} catch (ListaCheiaException e) {
				System.out.println(e.getMessage());
			} catch (ListaVaziaException e) {
				System.out.println(e.getMessage());
			} catch (ValorInvalidoException e) {
				System.out.println(e.getMessage());
			}catch(RuntimeException e){
				System.out.println(e.getMessage());
			}
		}
	}
}
