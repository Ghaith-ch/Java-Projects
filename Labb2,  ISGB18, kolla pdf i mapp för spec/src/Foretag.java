public class Foretag extends Kund
{
	private int lopnummer;
	
	public Foretag() 
	{
		lopnummer=0;
	}
	public String toString() 
	{
		return("Foretag\t\t"+this.getBelopp()+"\t"+this.getDatum()+"\t"+this.getLopNummer());
	}
	public int getLopNummer() 
	{
		return lopnummer;
	}
	public void setLopNummer(int lopNummerIn) 
	{
		lopnummer=lopNummerIn;
	}
}
