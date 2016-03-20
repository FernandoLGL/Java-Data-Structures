
public class Teste {
	private int[] repositorio;
	private int contador;

	public Teste() {
		repositorio = new int[150];
		contador = 0;
	}

	public void incluir(int teste) {
		// verificar se Identificavel recebido não é nulo
		if (teste != 0) {
			// inserindo o Identificavel
			repositorio[contador] = teste;
			contador++;
		} else {
			// Caso o Identificavel recebido seja nulo
			System.out.println("Identificavel Invalido");
		}
		ordernar();

	}
	public void buscarTodos() {
		// Cria novo repositorio do tipo identificavel
		int c[] = new int[contador];
		// Pegando o Conteudo do repositorio
		for (int i = 0; i < contador; i++) {
			// preenchendo o repositorio destino
			c[i] = repositorio[i];
			System.out.println(c[i]);
		}
		// retornando o repositorio;
	}

	public void ordernar() {
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Teste test = new Teste();
		int a = 2;
		int b = 53;
		int c = 42;
		int d = 56;
		int e = 67;
		int f = 9;
		int g = 81;
		int h = 58;
		int i = 32;
		int j = 10;
		test.incluir(j);
		test.incluir(b);
		test.incluir(c);
		test.incluir(d);
		test.incluir(e);
		test.incluir(f);
		test.incluir(g);
		test.incluir(h);
		test.incluir(i);
		test.incluir(a);
		test.buscarTodos();
	}

}
