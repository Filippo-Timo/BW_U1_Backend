package filippotimo;

import filippotimo.dao.*;
import filippotimo.entities.*;

import filippotimo.menu.Menu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Application {

    private static EntityManagerFactory entityManagerFactory;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("bw1backendpu");
        }
        return entityManagerFactory;
    }

    public static void shutdown() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
            entityManagerFactory = null;
        }
    }


    private static long readLong(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String line = scanner.nextLine().trim();
                return Long.parseLong(line);
            } catch (Exception e) {
                System.out.println("Valore non valido, riprova.");
            }
        }
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String line = scanner.nextLine().trim();
                return Integer.parseInt(line);
            } catch (Exception e) {
                System.out.println("Valore non valido, riprova.");
            }
        }
    }

    private static LocalDate readDate(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String line = scanner.nextLine().trim();
                return LocalDate.parse(line);
            } catch (Exception e) {
                System.out.println("Formato data non valido. Usa YYYY-MM-DD.");
            }
        }
    }

    private static String readString(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static durataAbbonamento readDurataAbbonamento(Scanner scanner) {
        while (true) {
            System.out.println(Menu.MenuTitle("Durata abbonamento"));
            int selected = Menu.MenuEntries(Arrays.asList(
                    "Exit",
                    "SETTIMANALE",
                    "MENSILE"
            ));


            switch (selected) {
                case 0:
                    return null;
                case 1:
                    return durataAbbonamento.SETTIMANALE;
                case 2:
                    return durataAbbonamento.MENSILE;
                default:
                    // non dovrebbe mai succedere
                    break;
            }
        }
    }

    private static tipoMezzo readTipoMezzo(Scanner scanner) {
        while (true) {
            System.out.println("Tipo mezzo:");
            System.out.println("1) AUTOBUS");
            System.out.println("2) TRAM");
            int choice = readInt(scanner, "> ");
            if (choice == 1) return tipoMezzo.AUTOBUS;
            if (choice == 2) return tipoMezzo.TRAM;
            System.out.println("Scelta non valida.");
        }
    }

    public static void main(String[] args) {
        EntityManager em = getEntityManagerFactory().createEntityManager();
        Scanner scanner = new Scanner(System.in);

        MezzoPubblicoDAO md = new MezzoPubblicoDAO(em);
        ProdottoDAO pd = new ProdottoDAO(em);
        RivenditoriDAO rd = new RivenditoriDAO(em);
        TrattaDAO td = new TrattaDAO(em);
        UtentiDAO ud = new UtentiDAO(em);

        new Menu(scanner);

        boolean running = true;
        while (running) {
            System.out.println(Menu.MenuTitle("Sistema Trasporti"));
            int menuIndex = Menu.MenuEntries(
                    Arrays.asList(
                            "Exit",
                            "Emetti biglietto",
                            "Emetti abbonamento (tessera)",
                            "Crea utente",
                            "Crea tessera per utente",
                            "Verifica abbonamento per tessera",
                            "Vidima biglietto su mezzo",
                            "Conteggio prodotti per rivenditore (periodo)",
                            "Biglietti vidimati per mezzo (periodo)",
                            "Set distributore automatico in servizio",
                            "Manda mezzo in manutenzione",
                            "Chiudi manutenzione (data fine)",
                            "Crea percorrenza tratta/mezzo",
                            "Tempo medio tratta per mezzo",
                            "Numero percorrenze tratta per mezzo"
                    )
            );

            switch (menuIndex) {
                case 0 -> {
                    System.out.println("Uscita...");
                    running = false;
                }
                    case 1 -> {
                        System.out.println(Menu.MenuTitle("Emetti biglietto"));
                        long idRivenditore = readLong(scanner, "Id rivenditore: ");
                        Rivenditore r = rd.findById(idRivenditore);
                        if (r == null) {
                            System.out.println("Rivenditore non trovato.");
                            break;
                        }
                        tipoMezzo tm = readTipoMezzo(scanner);
                        LocalDate data = readDate(scanner, "Data emissione (YYYY-MM-DD): ");
                        pd.createAndSaveBiglietto(data, r, tm, null);
                    }
                    case 2 -> {
                        System.out.println(Menu.MenuTitle("Emetti abbonamento"));
                        long idRivenditore = readLong(scanner, "Id rivenditore: ");
                        Rivenditore r = rd.findById(idRivenditore);
                        if (r == null) {
                            System.out.println("Rivenditore non trovato.");
                            break;
                        }
                        long numeroTessera = readLong(scanner, "Numero tessera: ");
                        Tessera tessera = ud.findTesseraByNumero(numeroTessera);
                        LocalDate data = readDate(scanner, "Data emissione (YYYY-MM-DD): ");
                        durataAbbonamento durata = readDurataAbbonamento(scanner);
                        if (durata == null) {
                            break;
                        }
                        pd.createAndSaveAbbonamento(data, r, null, durata, tessera);
                    }
                    case 3 -> {
                        System.out.println(Menu.MenuTitle("Crea utente"));
                        String nome = readString(scanner, "Nome: ");
                        String cognome = readString(scanner, "Cognome: ");
                        ud.createAndSaveUtente(nome, cognome);
                    }
                    case 4 -> {
                        System.out.println(Menu.MenuTitle("Crea tessera"));
                        long idUtente = readLong(scanner, "Id utente: ");
                        Utenti u = ud.findUtenteById(idUtente);
                        LocalDate data = readDate(scanner, "Data emissione (YYYY-MM-DD): ");
                        ud.createAndSaveTessera(u, data);
                    }
                    case 5 -> {
                        System.out.println(Menu.MenuTitle("Verifica abbonamento"));
                        long numeroTessera = readLong(scanner, "Numero tessera: ");
                        Abbonamento a = ud.verifyAbbonamentoByTessera(numeroTessera);
                        System.out.println(a);
                        if (a.getDataScadenza() != null && a.getDataScadenza().isBefore(LocalDate.now())) {
                            System.out.println("ATTENZIONE: abbonamento scaduto.");
                        } else {
                            System.out.println("Abbonamento valido (in base alla data scadenza).");
                        }
                    }
                    case 6 -> {
                        System.out.println(Menu.MenuTitle("Vidima biglietto"));
                        long idBiglietto = readLong(scanner, "Id biglietto: ");
                        long idMezzo = readLong(scanner, "Id mezzo: ");
                        pd.validateATicketBySetter(idBiglietto, idMezzo);
                    }
                    case 7 -> {
                        System.out.println(Menu.MenuTitle("Conteggio prodotti per rivenditore"));
                        long idRivenditore = readLong(scanner, "Id rivenditore: ");
                        LocalDate inizio = readDate(scanner, "Data inizio (YYYY-MM-DD): ");
                        LocalDate fine = readDate(scanner, "Data fine (YYYY-MM-DD): ");
                        long count = pd.countAllProductsInAPeriodOfTime(idRivenditore, inizio, fine);
                        System.out.println("Totale prodotti emessi: " + count);
                    }
                    case 8 -> {
                        System.out.println(Menu.MenuTitle("Biglietti vidimati per mezzo"));
                        long idMezzo = readLong(scanner, "Id mezzo: ");
                        LocalDate inizio = readDate(scanner, "Data inizio (YYYY-MM-DD): ");
                        LocalDate fine = readDate(scanner, "Data fine (YYYY-MM-DD): ");
                        Long count = md.getNumeroBigliettiVidimatiPerMezzo(idMezzo, inizio, fine);
                        System.out.println("Biglietti vidimati: " + count);
                    }
                    case 9 -> {
                        System.out.println(Menu.MenuTitle("Distributore automatico"));
                        long idRivenditore = readLong(scanner, "Id distributore: ");
                        System.out.println("Stato:");
                        System.out.println("1) In servizio");
                        System.out.println("2) Fuori servizio");
                        int stato = readInt(scanner, "> ");
                        boolean inServizio = stato == 1;
                        rd.setInServizioRivenditoreAutomatico(idRivenditore, inServizio);
                        System.out.println("Aggiornato.");
                    }
                    case 10 -> {
                        System.out.println(Menu.MenuTitle("Manutenzione - avvio"));
                        long idMezzo = readLong(scanner, "Id mezzo: ");
                        md.mandaInManutenzione(idMezzo);
                    }
                    case 11 -> {
                        System.out.println(Menu.MenuTitle("Manutenzione - fine"));
                        long idManutenzione = readLong(scanner, "Id manutenzione: ");
                        LocalDate dataFine = readDate(scanner, "Data fine (YYYY-MM-DD): ");
                        md.setDataFineManutenzione(idManutenzione, dataFine);
                    }
                    case 12 -> {
                        System.out.println(Menu.MenuTitle("Crea percorrenza"));
                        long idTratta = readLong(scanner, "Id tratta: ");
                        long idMezzo = readLong(scanner, "Id mezzo: ");
                        int tempo = readInt(scanner, "Tempo percorrenza effettivo (minuti): ");
                        Tratta tratta = td.findTrattaById(idTratta);
                        MezzoPubblico mezzo = md.findMezzoById(idMezzo);
                        td.createAndSavePercorrenza(tempo, tratta, mezzo);
                    }
                    case 13 -> {
                        System.out.println(Menu.MenuTitle("Tempo medio tratta/mezzo"));
                        long idMezzo = readLong(scanner, "Id mezzo: ");
                        long idTratta = readLong(scanner, "Id tratta: ");
                        Double avg = td.tempoMedioPerMezzoETratta(idMezzo, idTratta);
                        System.out.println("Tempo medio: " + avg);
                    }
                    case 14 -> {
                        System.out.println(Menu.MenuTitle("Numero percorrenze tratta/mezzo"));
                        long idMezzo = readLong(scanner, "Id mezzo: ");
                        long idTratta = readLong(scanner, "Id tratta: ");
                        Long count = td.numeroPercorrenzePerMezzoETratta(idMezzo, idTratta);
                        System.out.println("Numero percorrenze: " + count);
                    }
                    default -> System.out.println("Funzione non implementata.");
                }

                System.out.println("Premi INVIO per continuare...");
                scanner.nextLine();
            }

            scanner.close();
            em.close();
            shutdown();
    }
}
