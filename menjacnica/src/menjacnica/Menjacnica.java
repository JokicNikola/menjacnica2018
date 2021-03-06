package menjacnica;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import menjacnica.sistemskeOperacije.SODodajValutu;
import menjacnica.sistemskeOperacije.SOIzvrsiTransakciju;
import menjacnica.sistemskeOperacije.SOObrisiValutu;
import menjacnica.sistemskeOperacije.SOSacuvajUFajl;
import menjacnica.sistemskeOperacije.SOUcitajIzFajla;

public class Menjacnica implements MenjacnicaInterface{
	
	public static LinkedList<Valuta> kursnaLista = new LinkedList<Valuta>();

	@Override
	public void dodajValutu(Valuta valuta) {
		
		SODodajValutu.dodajValutu(valuta, kursnaLista);
	}

	@Override
	public void obrisiValutu(Valuta valuta) {
		SOObrisiValutu.obrisiValutu(valuta, kursnaLista);
	}

	
	public double izvrsiTransakciju(Valuta valuta, boolean prodaja, double iznos) {
		return SOIzvrsiTransakciju.izvrsiTransakciju(valuta, prodaja, iznos);
	}

	@Override
	public LinkedList<Valuta> vratiKursnuListu() {
		return kursnaLista;
	}

	@Override
	public void ucitajIzFajla(String putanja) {
		kursnaLista =SOUcitajIzFajla.ucitajIzFajla(putanja);
	}

	@Override
	public void sacuvajUFajl(String putanja) {
		SOSacuvajUFajl.sacuvajUFajl(putanja, kursnaLista);

	
}
}
