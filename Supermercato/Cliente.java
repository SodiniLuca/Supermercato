import java.util.ArrayList;
import java.util.Scanner;

public class Cliente{
	String nome;
	ArrayList<Prodotto> carrello = new ArrayList<>();
	
	public Cliente cliente = new Cliente("Luca");
	final static Scanner scanner = new Scanner (System.in);
	// String prodotti = ;
	
	Cliente (String nome){
		this.nome = nome;
	}
	
	public void aggiungiProdotto(){
		String nome = prendiString("Inserire il nome dle prodotto da acquistare: ");
		int i = supermercato.ricercaIndiceProdotto(nome);
		cliente.carrello.add(new Prodotto(nome, prendiInt("Inserire la quantita' del prodotto da acquistare: "), Supermercato.supermercato.scaffali.get(i).prezzo));
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
}