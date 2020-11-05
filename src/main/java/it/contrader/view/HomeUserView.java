package it.contrader.view;


import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;

public class HomeUserView extends AbstractView{
	
	String choice;

	@Override
	public void showResults(Request request) {
		//System.out.println("\n-----Purtroppo in questo sample l'utente non pu� fare nulla, ci scusiamo per il disagio.-----");

	}

	@Override
	public void showOptions() {
		System.out.println("-------------MENU------------\n");
		System.out.println("Scegli un opzione da effettuare:\n[C]rea nuova shortUrl [E]sci");
		//System.out.println("\n Esatto, puoi solo uscire...");
		choice = this.getInput();

	}

	@Override
	public void submit() {

		switch (choice.toLowerCase()) {
		
		case "c":
			MainDispatcher.getInstance().callAction("ShortUrl", "doControl", null);
			break;
		case "e":
			MainDispatcher.getInstance().callAction("Login", "doControl", null);
			break;

		default:
			MainDispatcher.getInstance().callAction("Login", "doControl", null);
		}
	}

}
