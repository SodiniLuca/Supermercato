public class Prodotto{
	String nome;
	int quantita;
	double prezzo;
	
	Prodotto(String nome, int quantita, double prezzo){
		this.nome = nome;
		this.quantita = quantita;
		this.prezzo = prezzo;
		Supermercato.aggiungiProdottoConParametro(this);
	}
}