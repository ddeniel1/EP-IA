package Draw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class Escritor {

	public Escritor(int populacao, int mutacao, int geracoes) {
		File resumo = new File(populacao+" pop "+ mutacao+ " mut "+ geracoes+ " ger.csv");
		try {
			PrintWriter escritor = new PrintWriter(resumo);
			
			
			
			escritor.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
