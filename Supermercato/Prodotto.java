public class Prodotto{
	String nome;
	int quantita;
	double prezzo;
	
	Prodotto(String nome, int quantita, double prezzo){
		this.nome = nome;
		this.quantita = quantita;
		this.prezzo = prezzo;
	}

	@Override
	public String toString(){
		return 	this.nome + ", " + this.quantita + ", " + this.prezzo;
	}

    public Prodotto() {
    }

	// Calcola il totale per un prodotto
	public double getTotale(){
		return this.quantita * this.prezzo;
	}

}