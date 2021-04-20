package luokat;

import static org.junit.Assert.*;

import org.junit.Test;

public class JUnitTestaaTyöntekija {

	@Test
	public void testGetTuntipalkka() {
		Tyontekija t=new Tyontekija("Pekka","Mannerheimintie 1",50.90);
		assertEquals(50.90,t.getTuntipalkka(),0.01);
	}

	@Test
	public void testGetNimi() {
		Tyontekija t=new Tyontekija("Pekka","Mannerheimintie 1",50.90);
		assertEquals("Pekka",t.getNimi());
	}

	@Test
	public void testGetOsoite() {
		Tyontekija t=new Tyontekija("Pekka","Mannerheimintie 1",50.90);
		assertEquals("Mannerheimintie 1",t.getOsoite());
	}

	@Test
	public void testGetKoko() {
		Tyontekija t=new Tyontekija("Pekka","Mannerheimintie 1",50.90);
		Koko k=new Koko(1.90,100);
		t.setKoko(k);
		assertEquals(k,t.getKoko());
	}

}
