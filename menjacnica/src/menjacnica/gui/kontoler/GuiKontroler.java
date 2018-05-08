package menjacnica.gui.kontoler;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import menjacnica.Menjacnica;
import menjacnica.MenjacnicaInterface;
import menjacnica.Valuta;
import menjacnica.gui.DodajKursGUI;
import menjacnica.gui.IzvrsiZamenuGUI;
import menjacnica.gui.MenjacnicaGUI;
import menjacnica.gui.ObrisiKursGUI;
import menjacnica.gui.models.MenjacnicaTableModel;

public class GuiKontroler {

	public static MenjacnicaInterface menjacnica = new Menjacnica();
	public static MenjacnicaGUI gp;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenjacnicaGUI frame = new MenjacnicaGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void sacuvajUFajl() {
		try {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showSaveDialog(null);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();

				menjacnica.sacuvajUFajl(file.getAbsolutePath());
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(gp, e1.getMessage(),
					"Greska", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void ugasiAplikaciju() {
		int opcija = JOptionPane.showConfirmDialog(null,
				"Da li ZAISTA zelite da izadjete iz aplikacije", "Izlazak",
				JOptionPane.YES_NO_OPTION);

		if (opcija == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	
	public static void ucitajIzFajla() {
		try {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(null);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				menjacnica.ucitajIzFajla(file.getAbsolutePath());
				gp.prikaziSveValute();
			}	
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(),
					"Greska", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void prikaziAboutProzor(){
		JOptionPane.showMessageDialog(null,
				"Autor: Nikola Jokic, Verzija 1.0", "O programu Menjacnica",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void prikaziDodajKursGUI() {
		DodajKursGUI prozor = new DodajKursGUI();
		prozor.setLocationRelativeTo(null);
		prozor.setVisible(true);
	}
	
public static void prikaziObrisiKursGUI() {
		
		if (MenjacnicaGUI.table.getSelectedRow() != -1) {
			MenjacnicaTableModel model = (MenjacnicaTableModel)(MenjacnicaGUI.table.getModel());
			ObrisiKursGUI prozor = new ObrisiKursGUI(
					model.vratiValutu(MenjacnicaGUI.table.getSelectedRow()));
			prozor.setLocationRelativeTo(gp);
			prozor.setVisible(true);
		}
	}

public static void prikaziIzvrsiZamenuGUI() {
	if (MenjacnicaGUI.table.getSelectedRow() != -1) {
		MenjacnicaTableModel model = (MenjacnicaTableModel)(MenjacnicaGUI.table.getModel());
		IzvrsiZamenuGUI prozor = new IzvrsiZamenuGUI(
				model.vratiValutu(MenjacnicaGUI.table.getSelectedRow()));
		prozor.setLocationRelativeTo(gp);
		prozor.setVisible(true);
	}
}

public static void obrisiValutu(Valuta valuta) {
	try{
		menjacnica.obrisiValutu(valuta);

		
		gp.prikaziSveValute();
		
	} catch (Exception e1) {
		JOptionPane.showMessageDialog(null, e1.getMessage(),
				"Greska", JOptionPane.ERROR_MESSAGE);
	}
}

public static void izvrsiZamenu(Valuta valuta, boolean prodaja, double iznos){
	try{
		double konacniIznos = 
				menjacnica.izvrsiTransakciju(valuta, prodaja, iznos);
	
		IzvrsiZamenuGUI.textFieldKonacniIznos.setText(""+konacniIznos);
	} catch (Exception e1) {
	JOptionPane.showMessageDialog(gp, e1.getMessage(),
			"Greska", JOptionPane.ERROR_MESSAGE);
	}
}

public static void unesiKurs(int sifra, String naziv, double prodajni, double kupovni, double srednji,
		String skraceno) {
	try {
		Valuta valuta = new Valuta();

		
		valuta.setNaziv(naziv);
		valuta.setSkraceniNaziv(skraceno);
		valuta.setSifra((sifra));
		valuta.setProdajni(prodajni);
		valuta.setKupovni(kupovni);
		valuta.setSrednji(srednji);
		
		
		menjacnica.dodajValutu(valuta);

		
		gp.prikaziSveValute();
		
		
		
	} catch (Exception e1) {
		JOptionPane.showMessageDialog(gp, e1.getMessage(),
				"Greska", JOptionPane.ERROR_MESSAGE);
	}
}

	
	
}
