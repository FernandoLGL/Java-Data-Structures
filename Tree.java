
public class Tree {
	//ATRIBUTO SHOW
	public NodeTree raiz;

	//METODOS SHOWS
	public void insert(NodeTree galho) {
		//AUXILIAR SHOW
		NodeTree aux = raiz;
		//CHECAGEM SHOW
		if (raiz == null) {
			raiz = galho;
			System.out.println(raiz.getInfo());
		} else {
			//#PARTIUPERCORRERARVORE
			do {
				if (galho.getInfo() > aux.getInfo()) {
					//#INDOPRADIREITA
					if (aux.getRightSide() == null) {
						aux.setRightSide(galho);
						System.out.println(aux.getInfo() + " galho direito " + galho.getInfo());
						return;
					} else {
						//VOLTANDO PRO LOOP
						System.out.println(aux.getInfo() + " indo para no a direita " + aux.getRightSide().getInfo());
						aux = aux.getRightSide();
					}
				} else {
					//#INDOPRAESQUERDA(ECA FORA PT)
					if (aux.getLeftSide() == null) {
						aux.setLeftSide(galho);
						System.out.println(aux.getInfo() + " galho esquerdo " + galho.getInfo());
						return;
					} else {
						//VOLTANDO PRO LOOP
						System.out.println(aux.getInfo() + " indo para no a esquerda " + aux.getLeftSide().getInfo());
						aux = aux.getLeftSide();
					}
				}
			}//LOOP INFINITO SHOW 
			while (true);
		}
	}
}
