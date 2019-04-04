package Draw;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Escritor {

	public File resumo;
	public FileWriter escritor;
	public BufferedWriter wr;

	public Escritor(int populacao, double mutacao, int geracoes, int exec) throws IOException {

		String resumu = "G:/Meu Drive/Matérias 5º Semestre/IA/EP/res/" + populacao + "_pop_"
				+ (int) mutacao + "_mut_" + geracoes + "_ger"+"/"+exec+".csv";
		try {
			new File("G:/Meu Drive/Matérias 5º Semestre/IA/EP/res/" + populacao + "_pop_"
					+ (int) mutacao + "_mut_" + geracoes + "_ger" + "/").mkdir();
			resumo = new File(resumu);
			escritor = new FileWriter(resumu);


			escritor.append("ger,avgFit,bestFit\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void escrever(int geracao, int i, int j) {

		try {
			escritor.append(geracao + "," + i + "," + j + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void encerra() throws IOException {

		escritor.flush();
		escritor.close();
	}

}
