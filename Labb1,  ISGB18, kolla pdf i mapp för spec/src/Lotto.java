import java.util.*;
public class Lotto
{
	private String rad;
	/*
	public String getRad2() 
	{
		rad="";
		Vector<Integer> v=new Vector<Integer>();
		for(int i=0;i<35;i++)
			v.add(i+1);
		Collections.shuffle(v);
		
		Vector<Integer> v2=new Vector<Integer>();
		for(int i=0;i<7;i++)
			v2.add(v.get(i));
		
		Collections.sort(v2);
		
		for(int i=0;i<7;i++)
			rad=rad+v2.get(i)+" ";
		
		return rad;
	}
	*/
	public String getRad() 
	{
		rad="";
		boolean koll[]=new boolean[35];
		int tal,i;
		Random r=new Random();

		for(i=0;i<7;i++) 
		{
			tal=r.nextInt(35) +1;
			while(koll[tal]==true) 
			{
				tal=r.nextInt(35) +1;
			}
			
			koll[tal]=true;
		}
		
		for(i=0;i<35;i++)
			if(koll[i]==true)
				rad=rad+(i)+" ";
		
		return rad;
	}
	
	public static void main(String[] args) 
	{
		Lotto l=new Lotto();
		System.out.println(l.getRad());
		//System.out.println(l.getRad2());
	}
	
}