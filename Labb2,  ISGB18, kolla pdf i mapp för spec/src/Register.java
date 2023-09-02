import java.util.*;

public class Register
{
	private LinkedList<Kund> lista;
	private int lopNummer;

	public Register() 
	{
		lista=new LinkedList<Kund>();
		lopNummer=0;
	}
	
	public int beraknaSaldo() 
	{
		int i,tb=0;
		for(i=0;i<lista.size();i++) 
		{
			tb=((Kund)lista.get(i)).nyBalans(tb);
		}
		return tb;
	}
	
	public void regKund(Kund nyKund) 
	{
		lista.add(nyKund);
	}
	
	public int getLopnummer() 
	{
		lopNummer++;
		return lopNummer;
	}
	
	public String getLista() 
	{
		String s="";
		Kund k;
		for(int i=0;i<lista.size();i++) 
		{
			k=(Kund)lista.get(i);
			s=s+k.toString()+"\n";
		}
		return s;
	}
	
	public int listStorlek() 
	{
		return lista.size();
	}
	
	
	
	
	public static void main(String[] args)
	{
		Register r=new Register();
		Kund k=new Normal();
		k.setBelopp(100);
		k.setDatum("13:19");
		r.regKund(k);
		k=new Student();
		k.setBelopp(50);
		k.setDatum("13:21");
		if(r.beraknaSaldo()>=50)
			r.regKund(k);
		else
			System.out.println("Sorry, inga pengar i kassan. Studenten ej registerad");
		
		k=new Foretag();
		k.setBelopp(0);
		k.setDatum("13:23");
		k.setLopNummer(r.getLopnummer());
		r.regKund(k);
		System.out.println(r.getLista());
		System.out.println("I kassan finns nu"+r.beraknaSaldo()+"Kr");
		k=new Student();
		k.setBelopp(50);
		k.setDatum("13:26");
		if(r.beraknaSaldo()>=50)
			r.regKund(k);
		else
			System.out.println("Sorry, inga pengar i kassan. Studenten ej registerad");
		
		k=new Normal();
		k.setBelopp(100);
		k.setDatum("13:28");
		r.regKund(k);
		k=new Student();
		k.setBelopp(50);
		k.setDatum("13:32");
		if(r.beraknaSaldo()>=50)
			r.regKund(k);
		else
			System.out.println("Sorry, inga pengar i kassan. Studenten ej registerad");
		
		k=new Foretag();
		k.setBelopp(0);
		k.setDatum("13:35");
		k.setLopNummer(r.getLopnummer());
		r.regKund(k);
		k=new Normal();
		k.setBelopp(100);
		k.setDatum("13:38");
		r.regKund(k);
		System.out.println(r.getLista());
		System.out.println("I kassan finns nu"+r.beraknaSaldo()+"Kr");
	}
}
