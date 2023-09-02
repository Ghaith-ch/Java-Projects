public class Student extends Kund
{
	public String toString() 
	{
		return("Student\t\t"+this.getBelopp()+"\t"+this.getDatum());
	}
	public int nyBalans(int tidigareBalans) 
	{
		return this.getBelopp()+tidigareBalans;
	}
}
