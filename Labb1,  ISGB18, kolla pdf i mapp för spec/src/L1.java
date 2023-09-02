import java.util.Random;
import java.util.Scanner;

public class L1
{
	public void sort()
	{
		int[] vektor=new int[100];
		int tal,i,j,byt,sum=0;
		Random r=new Random();
		for(i=0;i<100;i++) 
		{
			tal=r.nextInt(100)+1; //jag vill att alla slupade tal ska vara mellan 1 och 1000 
			sum=sum+tal;
			vektor[i]=tal;
		}
		
		for(i=0;i<100;i++) 
		{
			System.out.print(vektor[i]+" ");
		}
		
		System.out.print("\n");
		
		for(i=0;i<100;i++)
			for(j=99;j>i;j--)
				if(vektor[j-1]>vektor[j])
				{
					byt=vektor[j-1];
					vektor[j-1]=vektor[j];
					vektor[j]=byt;
				}
		
		for(i=0;i<100;i++) 
		{
			System.out.print(vektor[i]+" ");
		}
		
		System.out.print("\n");
		
		
		System.out.print("Ange 1 f�r s�kning, 2 f�r st�rsta och minsta tal,3 f�r medev�rde ");
		int val2;
		Scanner input=new Scanner(System.in);
		val2=input.nextInt();
		switch (val2)
		{
		case 1:
			{
				int sk;
				System.out.print("Skriv vilket nummer vill du s�ka p� ");
				Scanner inp=new Scanner(System.in);
				sk=inp.nextInt();
				
			       int v=0,h=101,mitt;
			        while ((h-v) > 1)
			            {
			                mitt=(v+h)/2;
			                    if(sk>=vektor[mitt])
			                        v=mitt;
			                    else
			                        h=mitt;
			            }
			        if (sk==vektor[v])
			        	System.out.println("talet finns p� plats "+ (v+1));
			        
			        else
			        	System.out.print("finns ej");
			        break;
			}
			
		case 2:
			{
				System.out.println("st�rsta tal �r"+ vektor[99]);
				System.out.print("st�rsta tal �r"+ vektor[0]);
				break;
			}
		case 3:
			{
				System.out.println("medelv�rde �r"+ sum/100);
				break;
			}	
		}

	}
	
	public static void main(String[] args) 
	{
		int val;
		System.out.print("Ange 1 f�r att slumpa,sortera och s�ka och 0 f�r avsluta programmet  ");
		Scanner input=new Scanner(System.in);
		val=input.nextInt();
		if (val == 0) 
		{
			System.out.print("Du har valt att avsluta");
		}
		
		if (val == 1) 
		{
			L1 s=new L1();
			s.sort();		
		}
		
	}

}
