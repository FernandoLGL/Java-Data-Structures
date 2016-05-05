package menu;
import arvore.*;

import java.util.Scanner;

import exceptions.ArvoreVaziaException;
import exceptions.ElementoExistenteException;
import exceptions.NaoExisteException;

public class Menu {
	
	public static void main(String[] args){
		Tree t = new Tree();
		Scanner sc = new Scanner(System.in);
		int escolha;
		int valor;
		while(true){
			System.out.println("\n1-Adicionar\n2-Remover\n3-Buscar\n4-Verificar Existencia\n5-Imprimir Sobrinhos\n6-Imprimir Arvore\n");
			escolha = sc.nextInt();

			switch(escolha){
				case 1: System.out.println("\nDiga o valor do no que quer inserir:");
				valor = sc.nextInt();
				try {
					t.insert(valor);
				} catch (ArvoreVaziaException | ElementoExistenteException e) {
					System.out.println(e.getMessage());
				}
				break;

				case 2: System.out.println("\nDiga o valor que quer remover:");
				valor = sc.nextInt();
				try {
					t.remove(valor);
				} catch (ArvoreVaziaException | NaoExisteException e) {
					System.out.println(e.getMessage());
				}
				break;

				case 3: System.out.println("\nDiga o valor que quer buscar: ");
				valor = sc.nextInt();
				try {
					System.out.println("\n\nFoi procurado o no que contem o valor " + valor + ". O no e: " + t.find(valor));
				} catch (ArvoreVaziaException | NaoExisteException e) {
					System.out.println(e.getMessage());
				}
				break;

				case 4: System.out.println("\n Diga o valor que voce quer checar a existencia: ");
				valor = sc.nextInt();
				try {
					if(t.exists(valor)) System.out.println("\n\nO valor existe na arvore.");
					else System.out.println("\n\nO valor nao existe na arvore.");
				} catch (ArvoreVaziaException e) {
					System.out.println(e.getMessage());
				}
				break;

				case 5: System.out.println("\nDiga o valor do no que voce quer imprimir os sobrinhos.");
				valor = sc.nextInt();
				try {
					t.printSobrinhos(valor);
				} catch (ArvoreVaziaException | NaoExisteException e) {
					System.out.println(e.getMessage());
				}
				break;

				case 6: t.printTree();
				break;

				default: System.out.println("Valor invalido.");
			}
		}
	}
}