import java.util.ArrayList;
import java.util.Scanner;

public class Cliente{
	ArrayList<Prodotto> carrello;
	
	final static Scanner scanner = new Scanner (System.in);
	Supermercato supermercato = new Supermercato ();
	
	Cliente (){
		this.carrello = new ArrayList<>();
	}
	

	public boolean aggiungiProdotto(){
		String nome = prendiString("Inserire il nome del prodotto da aggiungere al carrello: ");
		int i = supermercato.ricercaIndiceProdotto(nome);
		if (i != -1){
			int j = ricercaIndiceProdotto(nome);
			if(j != -1){
				System.out.println("Prodotto gia' presente nel carrello");
				int q = prendiInt("Inserire la quantita' da aggiungere al carrello: ");
				if ((this.carrello.get(j).quantita + q) >= supermercato.scaffali.get(i).quantita){
					System.out.println("Nel supermercato sono presenti solo " + supermercato.scaffali.get(i).quantita + " pezzi di " + nome);
					return false;
				}
				else{
					this.carrello.get(j).quantita += q;
					supermercato.scaffali.get(i).quantita -= this.carrello.get(j).quantita;
				}
			}
			else{
				int q = prendiInt("Inserire la quantita' del prodotto da acquistare: ");
				if (q > supermercato.scaffali.get(i).quantita){
					System.out.println("Nel supermercato sono presenti solo " + supermercato.scaffali.get(i).quantita + " pezzi di " + nome);
					return false;
				}
				else{
					this.carrello.add(new Prodotto(nome, q, supermercato.scaffali.get(i).prezzo));
					supermercato.scaffali.get(i).quantita -= this.carrello.get((this.carrello.size())-1).quantita;
				}
			}
			return true;
		}
		else{
			return false;
		}
		
	}
	
	public boolean rimuoviProdotto(){
		String nome = prendiString("Inserire il nome del prodotto da rimuovere dal carrello: ");
		int i = supermercato.ricercaIndiceProdotto(nome);
		if (i != -1){
			int j = this.ricercaIndiceProdotto(nome);
			if (j != -1){
				int q = prendiInt("Inserire la quantita' di prodotto da rimuovere dal carrello: ");
				if (q > this.carrello.get(j).quantita){
					System.out.println("Nel carrello sono presenti solo " + this.carrello.get(j).quantita + " pezzi di " + this.carrello.get(j).nome);
					return false;
				}
				else{
					supermercato.scaffali.get(i).quantita += this.carrello.get(j).quantita;
					if (q == this.carrello.get(j).quantita){
						this.carrello.remove(j);
					}
					else{
						this.carrello.get(j).quantita -= q;
					}
					return true;
				}
			}
			else{
				System.out.println("Prodotto non presente nel carrello");
				return false;
			}
		}
		else{
			System.out.println("Prodotto non presente nel supermercato");
			return false;
		}
		
	}

	public void stampaCarrello(){
		if (this.carrello.isEmpty()){
			System.out.println("Carrello vuoto");
		}
		else{
			System.out.println("Contenuto attuale del carrello:");
			for (int i = 0; i < this.carrello.size(); i++){
				System.out.println(this.carrello.get(i).nome + ": " + this.carrello.get(i).quantita + " pezzi (costo: " + this.carrello.get(i).prezzo + " euro al pezzo)");
			}
		}
	}

	public static void stampaScontrino(ArrayList<Prodotto> prodotti) {
		// Inizializza la variabile 'totale' che conterrà il totale dell'importo di tutti i prodotti
		double totale = 0.0;
	
		// Intestazione dello scontrino
		System.out.println("**********************************"); // Disegna una linea di asterischi per l'intestazione
		System.out.println("         SCONTRINO TOTALE");      // Stampa il titolo dello scontrino
		System.out.println("**********************************"); // Disegna una linea di asterischi sotto il titolo
	
		// Dettaglio dei prodotti
		// Ciclo for-each che itera sull'ArrayList 'prodotti'
		for (Prodotto prodotto : prodotti) {
			// Calcola il totale per il prodotto corrente (quantità * prezzo)
			double totaleProdotto = prodotto.getTotale();
	
			// Stampa i dettagli del prodotto, formattando l'output per una visualizzazione chiara
			// %-20s allinea il nome del prodotto a sinistra e lo rende lungo 20 caratteri
			// x %d stampa la quantità come numero intero
			// %.2f stampa il prezzo unitario con 2 decimali
			// %.2f€ stampa il totale del prodotto con 2 decimali e l'aggiunta del simbolo €
			System.out.printf("%-20s x %d     %.2f€   %.2f€\n", 
							  prodotto.nome, prodotto.quantita, prodotto.prezzo, totaleProdotto);
	
			// Aggiunge il totale del prodotto al totale generale
			totale += totaleProdotto;
		}
	
		// Linea separatrice
		System.out.println("----------------------------------"); // Stampa una linea separatrice
	
		// Totale finale
		// %.2f stampa il totale complessivo con 2 decimali e l'aggiunta del simbolo €
		System.out.printf("Totale:                       %.2f€\n", totale);
	
		// Informazioni aggiuntive
		// Stampa un messaggio di cortesia
		System.out.println("Grazie per aver acquistato da noi!");
	
		// Chiusura dello scontrino con una linea di asterischi
		System.out.println("**********************************\n");
	}
	

	private static String prendiString(String frase){
		System.out.print(frase);
		return scanner.nextLine();
	}
	
	private static int prendiInt(String frase){
		System.out.print(frase);
		int risultato = scanner.nextInt();  // Leggi il numero
		scanner.nextLine();  // Consuma il carattere di fine riga rimanente
		return risultato;
	}
	
	private static double prendiDouble(String frase){
		System.out.print(frase);
		double risultato = scanner.nextDouble();  // Leggi il numero decimale
		scanner.nextLine();  // Consuma il carattere di fine riga rimanente
		return risultato;
	}
	
	public int ricercaIndiceProdotto(String nome){
		for (int i = 0; i < this.carrello.size(); i++){
			if (this.carrello.get(i).nome.equals(nome)){
				return i;
			}
		}
		return -1;
	}
}