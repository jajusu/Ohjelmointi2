package luokat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class HenkiloMapSovellus {
	private static Lukija lukija=new Lukija();
	private static HashMap <String, Henkilo> lista= new HashMap<String,Henkilo>();
	
	public static void main(String[] args) {
		while (true){
			System.out.println("1. Lisää henkilö \n2. Näytä henkilön tiedot \n3. Muuta henkilön nimi ja osoite \n4. Muuta henkilön koko \n5. Näytä kaikki henkilöt \n0. Lopetus");
			int syote=lukija.lueKokonaisluku("Anna valintasi (0-5):");
			
			if (syote==0) {
				System.out.println("Moro!");
				break;
			}
			else if (syote==1) {
				lisaaHenkilo();
			}
			else if (syote==2) {
				naytaHenkilo();
			}
			else if (syote==3) {
				muutaHenkilo();
			}
			else if (syote==4) {
				muutaKoko();
			}
			else if (syote==5) {
				naytaKaikki();
			}
			else{
				System.out.println("Anna validi syöte.");
			}
			System.out.println("");

		}
	}
	
	private static void lisaaHenkilo() {
		Henkilo lisattava=new Henkilo();
		Koko lisattavanKoko=new Koko();
		lisattava.setNimi(lukija.lueTeksti("Anna nimi: "));
		lisattava.setOsoite(lukija.lueTeksti("Anna osoite: "));
		lisattavanKoko.setPituus(lukija.lueDesimaaliluku("Anna pituus: "));
		lisattavanKoko.setPaino(lukija.lueKokonaisluku("Anna paino: "));
		lisattava.setKoko(lisattavanKoko);
		//System.out.println(lisattava);
		lista.put(lisattava.getNimi(), lisattava);
		//System.out.println(lista);

	}
	
	private static void naytaHenkilo() {
		String naytettava=lukija.lueTeksti("Anna näytettävän henkilön nimi:");
		Henkilo apu=lista.get(naytettava);
		if (apu==null){
			System.out.println("Henkilöä ei ole");
		}
		else {
			System.out.println(apu);
			//System.out.println("Nimi: "+apu.getNimi());
			//System.out.println("Osoite: "+apu.getOsoite());
			//System.out.println("Pituus: "+apu.getKoko().getPituus());
			//System.out.println("Painoindeksi: "+apu.getKoko().getPainoindeksi());
		}
	}
	
	private static void muutaHenkilo() {
		Henkilo lisattava=new Henkilo();
		Koko lisattavanKoko=new Koko();
		String muutettava=lukija.lueTeksti("Anna perustietoja muutettavan henkilön nimi:");
		Henkilo apu=lista.get(muutettava);
		if (apu==null){
			System.out.println("Henkilöä ei ole");
		}
		else {
			lisattava.setNimi(lukija.lueTeksti("Anna nimi:"));
			lisattava.setOsoite(lukija.lueTeksti("Anna osoite:"));
			lisattavanKoko.setPituus(apu.getKoko().getPituus());
			lisattavanKoko.setPaino(apu.getKoko().getPaino());
			lisattava.setKoko(lisattavanKoko);
			lista.put(lisattava.getNimi(), lisattava);
			lista.remove(muutettava);
		}
	}
	
	private static void muutaKoko() {
		Henkilo lisattava=new Henkilo();
		Koko lisattavanKoko=new Koko();
		String muutettava=lukija.lueTeksti("Anna kokoa muutettavan henkilön nimi:");
		Henkilo apu=lista.get(muutettava);
		if (apu==null){
			System.out.println("Henkilöä ei ole");
		}
		else {
			lisattava.setNimi(apu.getNimi());
			lisattava.setOsoite(apu.getOsoite());
			lisattavanKoko.setPituus(lukija.lueDesimaaliluku("Anna pituus:"));
			lisattavanKoko.setPaino(lukija.lueKokonaisluku("Anna paino:"));
			lisattava.setKoko(lisattavanKoko);
			lista.remove(muutettava);
			lista.put(lisattava.getNimi(), lisattava);
		}
	}
	
	private static void naytaKaikki() {
		Set<String> lempinimet= lista.keySet();
		Iterator<String> i = lempinimet.iterator();
		while (i.hasNext()) { 
			System.out.println("");
			System.out.println(lista.get(i.next()));
		}		
	}

}
