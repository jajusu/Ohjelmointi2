package luokat;

public class TyontekijaOhjelma {
	static Lukija lukija=new Lukija();
	public static void main(String[] args) {
		Tyontekija tyontekija=new Tyontekija();
		String nimi=lukija.lueTeksti("Anna nimi: "); 
		String osoite=lukija.lueTeksti("Anna osoite: ");
		double tuntipalkka=lukija.lueDesimaaliluku("Anna tuntipalkka: ");
		tyontekija.setNimi(nimi);
		tyontekija.setOsoite(osoite);
		tyontekija.setTuntipalkka(tuntipalkka);
		System.out.println();
		System.out.println("Nimi: "+tyontekija.getNimi());
		System.out.println("Osoite: "+tyontekija.getOsoite());
		System.out.println("Tuntipalkka: "+tyontekija.getTuntipalkka());
	}
}
