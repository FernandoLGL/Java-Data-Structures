import arvore.Tree;
import exceptions.ArvoreVaziaException;
import exceptions.NaoExisteException;

public class TesteTree {
	public static void main(String[] args) {
		Tree teste = new Tree();

	    teste.insert(50);
		teste.insert(23);
		teste.insert(42);
		teste.insert(13);
		teste.insert(76);
		teste.insert(88);
		teste.insert(64);
		teste.insert(31);
		teste.insert(48);
		teste.insert(46);
		teste.insert(80);
		teste.insert(78);
		teste.insert(77);
		teste.insert(73);
		teste.insert(75);
		teste.insert(90);
		teste.insert(95);
		teste.insert(100);
		teste.insert(99);
		teste.insert(98);

		try {
			System.out.println(teste.substitute(46));
			System.out.println(teste.findDad(46));
			System.out.println(teste.findDad(teste.substitute(46).getInfo()));
			teste.remove(46);
			teste.insert(49);
			teste.insert(45);
			//teste.insert(45);
		} catch (ArvoreVaziaException | NaoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
