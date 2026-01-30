package filippotimo.menu;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Menu {
	private static Scanner sc;

	public Menu(Scanner s) {
		Menu.sc = s;
	}

	private static Scanner scanner() {
		if (sc == null) {
			sc = new Scanner(System.in);
		}
		return sc;
	}

	private static String FmtTitle(String title) {
		return "***   " + title + "   ***";
	}

	private static String Separator(int n) {
		return " " + "-".repeat(n);
	}

	private static void ShowMessagge(String alert, String content) {
		System.out.printf("#### %s ####%n", alert);
		System.out.printf("%s%n", content);
	}

	private static void clearScreen() {
		System.out.print("\n".repeat(128));
	}

	public static String MenuTitle(String title) {
		/* XXX sicurezza fare check su title per evitare null excpetion
		 * oppure usare blocco try / catch
		 * ma per il momento e' gestito internamente
		 */
		int len = title.length();

		String sep = Separator(len + 9);
		return sep + "\n" + FmtTitle(title) + "\n" + sep;
	}

	public static int MenuEntries(List<String> entries) {
		/* https://www.geeksforgeeks.org/java/intstream-maptoobj-java/
		 * volevo na roba tipo (i, elem) => `{i}) {elem}` in javascript :(
		 */
		final String entriesStr = IntStream.range(0, entries.size())
				.mapToObj(i -> (i + 1) + ") " + entries.get(i))
				.collect(Collectors.joining("\n"));

		int choice;
		String errStr = "";
		while (true) {
			try {
				/* pulisci lo schermo */

				/* se c'e messaggio di errore stampalo */
				if (!errStr.equals("")) {
					ShowMessagge("error", errStr);
				}

				/* stampa lista entries */
				System.out.println(entriesStr);

				/* legge choice dallo scanner */
				choice = scanner().nextInt();
				scanner().nextLine();

				clearScreen();

				if (choice > 0 && choice <= entries.size()) {
					return choice - 1;
				}
			} catch (Exception e) {
				errStr = e.toString();
				try {
					scanner().nextLine();
				} catch (Exception ignored) {
					/* XXX system.err.println() */
				}
				continue;
			}
		}
	}
}
