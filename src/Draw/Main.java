package Draw;
import java.util.Scanner;
import Setup.*;
public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Digite a populacao inicial: ");
		Population p = new Population(scan.nextInt());
		System.out.print("Digite a taxa de mutacao: ");
		p.setIndiceMutacao(scan.nextDouble());
		System.out.println("Digite o numero de geracoes: ");
		int geracoes = scan.nextInt();
		for (int i = 0; i < geracoes; i++) {
			p.selection();
			p.evaluate();
		}
		scan.close();
		System.out.println(p.toString());
	}

}
