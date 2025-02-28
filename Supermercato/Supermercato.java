import java.util.ArrayList;
import java.util.Scanner;

public class Supermercato{
	String nome;
	ArrayList<Prodotto> scaffali;

	final static Scanner scanner = new Scanner (System.in);
	public Supermercato supermercato = new Supermercato("Sodini's Market");

	Supermercato(String nome){
		this.nome = nome;
		this.scaffali = new ArrayList<Prodotto>();
	}
	
	public static String prendiString(String frase){
		System.out.print(frase);
		return scanner.nextLine();
	}
	
	public static int prendiInt(String frase){
		System.out.print(frase);
		return scanner.nextInt();
	}
	
	public static double prendiDouble(String frase){
		System.out.print(frase);
		return scanner.nextDouble();
	}
	
	public void aggiungiProdotto(){
		supermercato.scaffali.add(new Prodotto(prendiString("Inserire il nome del prodotto: "), prendiInt("Inserire la quantita' del prodotto presente: "), prendiDouble("Inserire il costo del prodotto: ")));
	}
	
	public boolean rimuoviProdotto(){
		int i = ricercaIndiceProdotto(prendiString("Inserisci il nome del prodotto da rimuovere: "));
		if (i != -1){
			supermercato.scaffali.remove(i);
			return true;
		}
		else{
			return false;
		}
	}
	
	public int ricercaIndiceProdotto(String nome){
		for (int i = 0; i < supermercato.scaffali.size(); i++){
			if (supermercato.scaffali.get(i).nome.equals(nome)){
				return i;
			}
		}
		return -1;
	}
	
	public String prodotti(){
		String stampa = "";
		for (int i = 0; i < supermercato.scaffali.size(); i++){
			stampa = stampa + "Nome: " + supermercato.scaffali.get(i).nome + 
			"\nQuantita': " + supermercato.scaffali.get(i).quantita + 
			"\nPrezzo: " + supermercato.scaffali.get(i).prezzo;
		}
		return stampa;
	}

	public void aggiungiProdottoConParametro(Prodotto p){
		supermercato.scaffali.add(p);
	}
}