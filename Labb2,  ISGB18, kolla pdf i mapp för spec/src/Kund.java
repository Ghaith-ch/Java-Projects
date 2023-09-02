public class Kund
{
	private int belopp;
	private String datum;
	
	public Kund() 
	{
		belopp=0;
		datum="";
	}
	public int getBelopp() 
	{
		return belopp;
	}
	public void setBelopp(int nyttBelopp) 
	{
		belopp=nyttBelopp;
	}
	
	public String getDatum() 
	{
		return datum;
	}
	public void setDatum(String nyttDatum) 
	{
		datum=nyttDatum;
	}
	public int nyBalans(int tidigareBalans) 
	{
		return this.getBelopp()+tidigareBalans;
	}
	public void setLopNummer(int lopNummer) 
	{
	
	}
}
