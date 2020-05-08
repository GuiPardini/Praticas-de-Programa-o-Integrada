package model;

import java.io.File;
import java.io.IOException;

public class Arquivo {

	public File file;

	public static void main(String[] args){
		try {
			File arquivo = new File("C:\\Users\\gmpar_000\\Desktop\\log\\logs","arquivo.log");
			if(arquivo.isFile()){
				//fazer coisas com o arquivo
			} else if(arquivo.isDirectory()){
				//fazer coisa com o diretorio

			}
			else {
				new File("C:\\Users\\gmpar_000\\Desktop\\log\\logs").mkdir();
				arquivo.createNewFile();
			}

			String[] arquivos = arquivo.list();

			long tempo = arquivo.lastModified();
			System.out.println(tempo);
			long tamanho = arquivo.length();
			System.out.println(tamanho);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
	public Arquivo(String path, String filename) throws IOException {
		initArquivo(path,filename);
	}

	public Arquivo(String filename) throws IOException {
		initArquivo("C:\\Users\\gmpar_000\\Desktop\\log\\logs",filename);

	}
	public void initArquivo(String path, String filename) throws IOException {
		File arquivo = new File(path,filename);
		if(arquivo.isFile()){
			//fazer coisas com o arquivo
		} else if(arquivo.isDirectory()){
			//fazer coisa com o diretorio			
		}
		else {
			new File(path).mkdir();
			this.file = new File(path,filename);
			this.file.createNewFile();
		}
	}
}
