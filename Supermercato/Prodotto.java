public class Prodotto{
	String nome;
	int quantita;
	double prezzo;
	
	/** 
	 * Costruttore della classe Prodotto.
	 * @param nome Nome del prodotto.
	 * @param quantita Quantità disponibile.
	 * @param prezzo Prezzo unitario.
	 */
	Prodotto(String nome, int quantita, double prezzo){
		this.nome = nome;
		this.quantita = quantita;
		this.prezzo = prezzo;
	}

	/** 
	 * Restituisce una stringa con le informazioni del prodotto.
	 * @return Stringa formattata con nome, quantità e prezzo.
	 */
	@Override
	public String toString(){
		return 	this.nome + ", " + this.quantita + ", " + this.prezzo;
	}

	/** 
	 * Calcola il costo totale del prodotto in base alla quantità.
	 * @return Prezzo totale del prodotto.
	 */
	public double getTotale(){
		return this.quantita * this.prezzo;
	}

}