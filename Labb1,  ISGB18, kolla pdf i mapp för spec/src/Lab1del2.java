import java.util.*;

public class Lab1del2 
{ 
	private int vektor[];
	private boolean slumpflagga;
	private boolean sortflagga;

	public Lab1del2() 
	{
		vektor=new int [100];
		slumpflagga=false;
		sortflagga=false;
	}
	
	public void slumpa()
	{ 
		Random r=new Random();
		for(int i=0;i<100;i++)
			vektor[i]=r.nextInt(345);
		slumpflagga=true;
	}
	
	public void sortera() 
	{ 
		if(slumpflagga==true) 
		{
			for(int i=0;i<100;i++)
				for(int j=99;j>i;j--)
					if(vektor[j-1]>vektor[j])
					{ 
						int slask=vektor[j-1];
						vektor[j-1]=vektor[j];
						vektor[j]=slask;
					}
			sortflagga=true;
		}
	}
	
	public int binsok(int tal) 
	{
		if(sortflagga==true) 
		{ 
			int v=0,h=100,mitt;
			while((h-v)>1)
			{
				mitt=(v+h)/2;
				if(tal>=vektor[mitt])
					v=mitt;
				else
					h=mitt;
			}
			
			if(tal==vektor[v])
				return v+1;
			else
				return -1;
		}
		else
			return -1;
	}

	
	public String storsMinst()
	{ 
		if(sortflagga==true)
			return "Det största talet är "+vektor[99]+" och det minsta "+vektor[0];
		else
			return "Vektorn måste sorteras först";
	}
	
	public String skrivut() 
	{ 
		String s="";
		for(int i=0;i<100;i++) 
		{ 
			s=s+vektor[i]+" ";
			if((i+1)%10==0)
				s=s+"\n";
		}
		return s;
	}

	public static void main(String[] args) 
	{ 
		Lab1del2 l=new Lab1del2();
		l.slumpa();
		System.out.println(l.skrivut());
		l.sortera();
		System.out.println(l.skrivut());
		System.out.println(l.storsMinst());

		Scanner tgb=new Scanner(System.in);
		System.out.print("Ange söktal ");
		int tal=tgb.nextInt();
		int plats=l.binsok(tal);
		if(plats<0)
			System.out.println("Talet fanns ej");
		else
			System.out.println("Talet fanns på plats "+plats);
		
		tgb.close();
}

}
