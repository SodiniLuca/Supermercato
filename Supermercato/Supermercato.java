import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Supermercato{
	ArrayList<Prodotto> scaffali;

	final static Scanner scanner = new Scanner (System.in);
	String nomeFile = "./prodotti.txt";

	Supermercato(){
		this.scaffali = new ArrayList<>();
		this.scaffali = leggiDaFile(nomeFile);
	}

    /**
     * Legge un file di prodotti e li carica in un ArrayList di oggetti Prodotto.
     * 
     * @param nomeFile Il percorso del file da leggere.
     * @return Un ArrayList contenente tutti i prodotti letti dal file.
     */
    public static ArrayList<Prodotto> leggiDaFile(String nomeFile) {
        ArrayList<Prodotto> listaProdotti = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nomeFile))) {
            String linea;
            br.readLine(); // Salta l'intestazione "Nome,Prezzo,Quantità"

            while ((linea = br.readLine()) != null) { // Legge ogni riga del file
                String[] dati = linea.split(","); // Divide la riga in base alla virgola
                if (dati.length == 3) { // Controlla che ci siano esattamente 3 campi
                    String nome = dati[0].trim(); // Nome del prodotto
                    double prezzo = Double.parseDouble(dati[1].trim()); // Prezzo (convertito in double)
                    int quantita = (int) Double.parseDouble(dati[2].trim()); // Quantità (convertita in int)
                    
                    listaProdotti.add(new Prodotto(nome, quantita, prezzo)); // Aggiunge il prodotto alla lista
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Errore nella lettura del file: " + e.getMessage());
        }
        return listaProdotti; // Restituisce la lista di prodotti letti dal file
    }

    /**
     * Scrive una lista di prodotti su un file di testo.
     * 
     * @param nomeFile Il percorso del file su cui scrivere.
     * @param listaProdotti L'ArrayList di oggetti Prodotto da salvare nel file.
     */
    public static void scriviSuFile(String nomeFile, ArrayList<Prodotto> listaProdotti) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeFile))) {
            bw.write("Nome,Prezzo,Quantità\n"); // Scrive l'intestazione del file

            for (Prodotto p : listaProdotti) {
                bw.write(p.nome + "," + p.prezzo + "," + p.quantita + "\n"); // Scrive ogni prodotto nel file
            }

            System.out.println("File salvato con successo!");
        } catch (IOException e) {
            System.err.println("Errore nella scrittura del file: " + e.getMessage());
        }
    }

    /**
     * Legge e stampa il contenuto di un file di prodotti.
     * 
     * @param nomeFile Il percorso del file da stampare.
     */
    public static void stampaDaFile(String nomeFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeFile))) {
            String linea;    
            while ((linea = br.readLine()) != null) { // Legge ogni riga del file
                System.out.println(linea); // Stampa la riga letta
            }
        } catch (IOException e) {
            System.err.println("Errore nella lettura del file: " + e.getMessage());
        }
    }

    /**
     * Stampa direttamente i prodotti presenti in un ArrayList.
     * 
     * @param listaProdotti L'ArrayList di prodotti da stampare.
     */
    public static void stampaDaArrayList(ArrayList<Prodotto> listaProdotti) {
        if (listaProdotti.isEmpty()) { // Controlla se la lista è vuota
            System.out.println("L'elenco dei prodotti è vuoto.");
        } else {
            System.out.println("Elenco dei prodotti:");
            for (Prodotto p : listaProdotti) {
                System.out.println(p); // Usa il metodo toString() della classe Prodotto per stampare
            }
        }
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
	
	private static double prendiDouble(String frase) {
		double risultato = 0.0;
		boolean valido = false;
		while (!valido) {
			System.out.print(frase);
			String input = scanner.nextLine();  // Leggi l'input come stringa
			try {
				risultato = Double.parseDouble(input);  // Converte la stringa in un double
				valido = true;  // Se la conversione va a buon fine, esci dal ciclo
			} catch (NumberFormatException e) {
				System.out.println("Errore: devi inserire un numero decimale valido.");
			}
		}
		return risultato;
	}
	
	
public void aggiungiProdotto() {
    String nome = prendiString("Inserire il nome del prodotto: ");
    
    // Controlla la quantità
    int quantita = prendiInt("Inserire la quantità del prodotto presente: ");
    
    // Controlla il costo
    double costo = prendiDouble("Inserire il costo del prodotto: ");
    
    // Aggiungi il prodotto agli scaffali
    this.scaffali.add(new Prodotto(nome, quantita, costo));
    
    // Salva gli scaffali nel file
    scriviSuFile(nomeFile, this.scaffali);
}
	
	public boolean rimuoviProdotto(){
		int i = ricercaIndiceProdotto(prendiString("Inserisci il nome del prodotto da rimuovere: "));
		if (i != -1){
			this.scaffali.remove(i);
			scriviSuFile(nomeFile, this.scaffali);
			return true;
		}
		else{
			return false;
		}
	}
	
	public int ricercaIndiceProdotto(String nome){
		for (int i = 0; i < this.scaffali.size(); i++){
			if (this.scaffali.get(i).nome.equals(nome)){
				return i;
			}
		}
		return -1;
	}

	public void aggiungiProdottoConParametro(Prodotto p){
		this.scaffali.add(p);
	}
}