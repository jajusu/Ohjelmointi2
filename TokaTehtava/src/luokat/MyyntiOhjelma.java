package luokat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MyyntiOhjelma {
	
	private Connection con=null;
	private ResultSet rs = null;
	private PreparedStatement stmtPrep=null; 
	private String sql;
	private String db ="Myynti.sqlite";
	private Lukija lukija = new Lukija();
	
	private Connection yhdista(){		
    	Connection con = null;    	
    	String path = System.getProperty("user.dir")+"/";    	
    	String url = "jdbc:sqlite:"+path+db;    	
    	try {	       
    		Class.forName("org.sqlite.JDBC");
	        con = DriverManager.getConnection(url);	
//	        System.out.println("Yhteys avattu.");
	     }catch (Exception e){	
	    	 System.out.println("Yhteyden avaus ep?onnistui.");
	        e.printStackTrace();	         
	     }
	     return con;
	}
	
	
	private void naytaValikko() {
		System.out.println("1. N?yt? kaikki");
		System.out.println("2. Lis?? ");
		System.out.println("3. Muuta");
		System.out.println("4. Poista");
		System.out.println("0. Lopeta");
		switch (lukija.lueKokonaisluku("Valintasi: ")) {
		case 1:
			listaa();
			break;
		case 2:
			lisaa();
			break;
		case 3:
			muuta();
			break;
		case 4:
			poista();
			break;
		case 0:
			System.exit(0);
			break;
		default:
			System.out.println("V??r? valinta!");
			break;
		}
		naytaValikko();		
	}
	
	private void listaa(){
		sql = "SELECT * FROM asiakkaat";       
		try {
			con=yhdista();
			if(con!=null){ //jos yhteys onnistui
				stmtPrep = con.prepareStatement(sql);        		
        		rs = stmtPrep.executeQuery();   
				if(rs!=null){ //jos kysely onnistui					
					System.out.println();
					System.out.print("ID\t");
					System.out.print("Etunimi\t\t");
					System.out.print("Sukunimi\t");
					System.out.print("Puhelin\t\t");
					System.out.println("S?hk?posti\t\t");

					while(rs.next()){
						System.out.print(rs.getString(1) +"\t");
						System.out.print(rs.getString(2)+"\t\t");
						System.out.print(rs.getString(3)+"\t\t");	
						System.out.print(rs.getString(4)+"\t\t");
						System.out.print(rs.getString(5)+"\t\t");
						System.out.println();
					}
					System.out.println();
				}
				con.close();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private void lisaa() {
		String etunimi = lukija.lueTeksti("Etunimi: ");
		String sukunimi = lukija.lueTeksti("Sukunimi: ");
		String puh = lukija.lueTeksti("Puhelin: ");
		String sposti = lukija.lueTeksti("Sposti: ");
		if(etunimi.length()>1 && sukunimi.length()>1 && puh.length()>1 && sposti.length()>1){
			sql="INSERT INTO asiakkaat (etunimi, sukunimi, puhelin, sposti) VALUES (?,?,?,?);";						  
			try {
				con = yhdista();
				stmtPrep=con.prepareStatement(sql); 
				stmtPrep.setString(1, etunimi);
				stmtPrep.setString(2, sukunimi);
				stmtPrep.setString(3, puh);
				stmtPrep.setString(4, sposti);
				stmtPrep.executeUpdate();
		        con.close();
		        System.out.println("Lis??minen onnistui.");
		        listaa();
			} catch (SQLException e) {	
				System.out.println("Lis??minen ep?onnistui.");
				e.printStackTrace();
			}
		}		
	}
	
	private void muuta() {
		listaa();
		String luku = lukija.lueTeksti("Anna muutettavan asiakkaan ID: ");
		int asiakas_id=Integer.parseInt(luku);
		sql = "SELECT * FROM asiakkaat WHERE asiakas_id=?";       
		try {
			con=yhdista();
			if(con!=null){ //jos yhteys onnistui
				stmtPrep = con.prepareStatement(sql); 
				stmtPrep.setInt(1, asiakas_id);
        		rs = stmtPrep.executeQuery();  
        		if(rs.isBeforeFirst()){ //jos kysely tuotti dataa, eli id on k?yt?ss?
        			String etunimi = lukija.lueTeksti("Anna uusi etunimi (enter ohittaa): ");
        			String sukunimi = lukija.lueTeksti("Anna uusi sukunimi (enter ohittaa): ");
        			String puhelin = lukija.lueTeksti("Anna uusi puhelin (enter ohittaa): ");
        			String sposti = lukija.lueTeksti("Anna uusi s?hk?postiosoite (enter ohittaa): ");
					if(etunimi.equals("")) {
						etunimi=rs.getString("etunimi");
					}
					if(sukunimi.equals("")) {
						sukunimi=rs.getString("sukunimi");
					}
					if(puhelin.equals("")) {
						puhelin=rs.getString("puhelin");
					}
					if(sposti.equals("")) {
						sposti=rs.getString("sposti");
					}
					sql="UPDATE asiakkaat SET etunimi=?, sukunimi=?, puhelin=?, sposti=? WHERE asiakas_id=?";	        				
					stmtPrep=con.prepareStatement(sql);
					stmtPrep.setString(1, etunimi);
					stmtPrep.setString(2, sukunimi);
					stmtPrep.setString(3, puhelin);
					stmtPrep.setString(4, sposti);
					stmtPrep.setInt(5, asiakas_id);
					stmtPrep.executeUpdate();					
				}else{
					System.out.println("Antamallasi ID:ll? ei l?ydy asiakkaita.\n");
				}
        		con.close();
    			listaa();
			}				
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private void poista() {
		listaa();
		String luku = lukija.lueTeksti("Anna poistettavan asiakkaan ID: ");
		int asiakas_id=Integer.parseInt(luku);
		sql = "SELECT * FROM asiakkaat WHERE asiakas_id=?";      
		try {
			con=yhdista();
			if(con!=null){ //jos yhteys onnistui
				stmtPrep = con.prepareStatement(sql); 
				stmtPrep.setInt(1, asiakas_id);
        		rs = stmtPrep.executeQuery();  
        		if(rs.isBeforeFirst()){ //jos kysely tuotti dataa, eli rekisterinumero on k?yt?ss?
        			rs.next(); //siirryt??n 1. tietueriville
        			if(lukija.lueTeksti("Haluatko varmasti poistaa asiakkaan "
        					+asiakas_id+" "+rs.getString("etunimi")+" "+rs.getString("sukunimi")
        					+ " (k/e): ").equalsIgnoreCase("k")){        				
        				sql="DELETE FROM asiakkaat WHERE asiakas_id=?";	        				
    					stmtPrep=con.prepareStatement(sql);     					
    					stmtPrep.setInt(1, asiakas_id);
    					System.out.println(asiakas_id);    					
    					stmtPrep.executeUpdate(); 
        			}	       			
				}else{
					System.out.println("Antamasi ID:ll? ei l?ydy asiakasta.\n");
				}				
			}	
			con.close();
			listaa();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
	
	public static void main(String[] args) {
		MyyntiOhjelma ohjelma = new MyyntiOhjelma();
		ohjelma.naytaValikko();	
	}

}
