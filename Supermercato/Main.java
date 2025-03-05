import java.util.Scanner;

public class Main{

    final static Scanner scanner = new Scanner(System.in);

    // Metodo per il menu principale (scegli ruolo)
    public static String menu() {
        System.out.println("Benvenuto nel nostro supermercato!");
        System.out.println("Scegli il ruolo con il quale utilizzare questa applicazione:");
        System.out.println("1) Cliente");
        System.out.println("2) Gestore");
        return scanner.nextLine();
    }

    // Metodo per il menu del cliente
    public static String menuCliente() {
        System.out.println("\n--- MENU CLIENTE ---");
        System.out.println("1) Aggiungi al carrello");
        System.out.println("2) Rimuovi dal carrello");
        System.out.println("3) Stampa contenuto carrello");
		System.out.println("4) Vista scaffali");
        System.out.println("0) Genera scontrino");
        System.out.print("Scegli un'opzione: ");
        return scanner.nextLine();
    }

    // Metodo per il menu del gestore
    public static String menuGestore() {
        System.out.println("\n--- MENU GESTORE ---");
        System.out.println("1) Aggiungi prodotto");
        System.out.println("2) Rimuovi prodotto");
        System.out.println("3) Stampa lista prodotti");
        System.out.println("0) Esci");
        System.out.print("Scegli un'opzione: ");
        return scanner.nextLine();
    }
    
    public static void main(String[] args) {
        // Scegli il ruolo (Cliente o Gestore)
        String ruolo = menu();
		Supermercato supermercato = new Supermercato();
        switch (ruolo) {
            case "1":
                // Modalità Cliente
                String sceltaCliente = menuCliente();
                Cliente cliente = new Cliente();
                while (!sceltaCliente.equals("5")) {
                    switch (sceltaCliente) {
                        case "1":
                            if (cliente.aggiungiProdotto()) {
                                System.out.println("Operazione riuscita");
                            } else {
                                System.out.println("Operazione non riuscita");
                            }
                            break;
                        case "2":
                            if (cliente.rimuoviProdotto()) {
                                System.out.println("Operazione riuscita");
                            } else {
                                System.out.println("Operazione non riuscita");
                            }
                            break;
                        case "3":
                            cliente.stampaCarrello();
                            break;
						case "4":
							Supermercato.stampaDaArrayList(supermercato.scaffali);
							break;
                        case "0":
                            // Prima stampa lo scontrino
                            Cliente.stampaScontrino(cliente.carrello);
                            // Poi esci dal ciclo
                            sceltaCliente = "5";  // Imposta sceltaCliente su 5 per uscire dal ciclo
                            break;
                        default:
                            System.out.println("Scelta non valida. Riprova.");
                            break;
                    }
                    // Dopo ogni switch, chiedi di nuovo la scelta
                    if (!sceltaCliente.equals("5")) {
                        sceltaCliente = menuCliente();
                    }
                }
                break;
            case "2":
                // Modalità Gestore
                String sceltaGestore = menuGestore();
                while (!sceltaGestore.equals("0")) {
                    switch (sceltaGestore) {
                        case "1":
                            supermercato.aggiungiProdotto();
                            System.out.println("Prodotto aggiunto agli scaffali");
                            break;
                        case "2":
                            if(supermercato.rimuoviProdotto()){
                                System.out.println("Operazione riuscita");
                            }
                            else{
                                System.out.println("Prodotto non trovato");
                            }
                            break;
                        case "3":
                            Supermercato.stampaDaArrayList(supermercato.scaffali);
                            break;
                        case "0":
                            System.out.println("Uscita dal programma...");
                            break;
                        default:
                            System.out.println("Scelta non valida. Riprova.");
                    }
                    sceltaGestore = menuGestore();
                }   break;
            default:
                System.out.println("Scelta non valida. Uscita dal programma");
                break;
        }
        scanner.close();
    }
}