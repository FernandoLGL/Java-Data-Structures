
public class Tree {
	// ATRIBUTO SHOW
	public NodeTree raiz;

	// METODOS SHOWS
	public void insert(int number) {
		// AUXILIAR SHOW
		NodeTree galho = new NodeTree(number);
		NodeTree aux = raiz;
		// CHECAGEM SHOW
		if (raiz == null) {
			raiz = galho;
			System.out.println(galho.getInfo() +" raiz");
		} else {
			// #PARTIUPERCORRERARVORE
			do {
				if (number > aux.getInfo()) {
					// #INDOPRADIREITA
					if (aux.getRightSide() == null) {
						aux.setRightSide(galho);
						System.out.println(aux.getInfo() + " direita ->" + galho.getInfo() + " fim");
						return;
					} else {
						// VOLTANDO PRO LOOP
						System.out.println(aux.getInfo() + " direita ->" + aux.getRightSide().getInfo());
						aux = aux.getRightSide();
					}
				} else {
					// #INDOPRAESQUERDA(ECA FORA PT)
					if (aux.getLeftSide() == null) {
						aux.setLeftSide(galho);
						System.out.println(aux.getInfo() + " esquerda ->" + galho.getInfo()+ " fim");
						return;
					} else {
						// VOLTANDO PRO LOOP
						System.out.println(aux.getInfo() + " esquerda ->" + aux.getLeftSide().getInfo());
						aux = aux.getLeftSide();
					}
				}
			} // LOOP INFINITO SHOW
			while (true);
		}
	}

	public void remove(int node) {
		NodeTree dad;
		NodeTree filho;
		dad = raiz;
		if (node == dad.getInfo()) {
			if (dad.getLeftSide() == null && dad.getRightSide() == null) {
				dad = null;
			}
		} else if (dad.getInfo() > node) {
		}
	}

