import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class PBNode{
	long phn;
	String name;
	public void add(long phn2, ArrayList<PBNode> arr, String name2) {
		
		int i=0;
		for(PBNode node : arr)
		{
			if(node.phn==phn2)
			{
				if(node.name==name2)
					return;
				else
				{
					arr.get(i).name=name2;;
					return;
				}
			}
			i++;
		}
		this.phn=phn2;
		this.name=name2;
		
		arr.add(this);
	}
	public String find(long phn2, ArrayList<PBNode> arrayList) {
		
		for(PBNode node : arrayList)
		{
			if(node.phn==phn2)
				return node.name;
		}
		return "not found";
	}
public int findIndex(long phn2, ArrayList<PBNode> arrayList) {
		int i=0;
		for(PBNode node : arrayList)
		{
			if(node.phn==phn2)
				return i;
			i++;
		}
		return -1;
	}
	public void delete(long phn2, ArrayList<PBNode> arrayList) {
		int i=findIndex(phn2, arrayList);
		if(i==-1)
			return;
		arrayList.remove(i);
	}
	
	
		
}



public class PhoneBook {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n;
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		sc.nextLine();
		String str;
		
		ArrayList<PBNode> arr[] = new ArrayList[100];
		
		for(int i=0;i<100;i++)
			arr[i]=new ArrayList<PBNode>();
		
		for(int t=0;t<n;t++)
		{
			str=sc.nextLine();
			
			int findex=str.indexOf(' ');
			int lindex=str.lastIndexOf(' ');
			long phn;
			String name="not found";
			
			String op=str.substring(0,findex);
			int hashVal;
			PBNode node=new PBNode();
			
			if(op.equals("add"))
			{
				phn=Integer.parseInt(str.substring(findex+1,lindex));
				name=str.substring(lindex+1);
				hashVal=getHash(phn);
				node.add(phn,arr[hashVal],name);
				
			}
			else if(op.equals("find"))
			{
				phn=Integer.parseInt(str.substring(findex+1,str.length()));
				hashVal=getHash(phn);
				String contact=node.find(phn,arr[hashVal]);
				System.out.println(contact);
			}
			else
			{
				phn=Integer.parseInt(str.substring(findex+1,str.length()));
				hashVal=getHash(phn);
				node.delete(phn,arr[hashVal]);
			}
			
		}
		
		sc.close();
	}
	
	public static int getHash(long phn)
	{
		return (int)((34*phn+2)%10000003)%100;
	}

}
