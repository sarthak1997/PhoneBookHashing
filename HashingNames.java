import java.util.ArrayList;
import java.util.Scanner;

class PBNode{
	private String name="";
	
	public String getName()
	{
		return name;
	}
	public void add(ArrayList<PBNode> arr, String name2) {
		for(PBNode node : arr)
		{
			if(node.name.equals(name2))
				return;
		}
		this.name=name2;
		arr.add(0,this);
	}
	public String find(ArrayList<PBNode> arrayList,String name2) {
		
		for(PBNode node : arrayList)
		{
			if(node.name.equals(name2))
				return "yes";
		}
		return "no";
	}
public int findIndex(String name2, ArrayList<PBNode> arrayList) {
		int i=0;
		for(PBNode node : arrayList)
		{
			if(node.name.equals(name2))
				return i;
			i++;
		}
		return -1;
	}
	public void delete(String name2, ArrayList<PBNode> arrayList) {
		int i=findIndex(name2, arrayList);
		if(i==-1)
			return;
		arrayList.remove(i);
	}
	
	
		
}



public class HashingNames {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int m;
                long n;
		Scanner sc=new Scanner(System.in);
		m=sc.nextInt();
		n=sc.nextLong();
		sc.nextLine();
		String str;
		
		ArrayList<PBNode> arr[] = new ArrayList[m];
		
		for(int i=0;i<m;i++)
			arr[i]=new ArrayList<PBNode>();
		
		for(long t=0;t<n;t++)
		{
			str=sc.nextLine();
			
			int findex=str.indexOf(' ');
			String name="";
			String op=str.substring(0,findex);
			int hashVal=0;
			PBNode node=new PBNode();
			
			if(op.equals("add"))
			{
				name=(str.substring(findex+1));
				
				hashVal=getPolyHash(name,m);
                                
				node.add(arr[hashVal],name);
				
			}
			else if(op.equals("find"))
			{
				name=(str.substring(findex+1));
				hashVal=getPolyHash(name,m);
				String status=node.find(arr[hashVal],name);
				System.out.println(status);
			}
			else if(op.equals("del"))
			{
				name=(str.substring(findex+1));
				hashVal=getPolyHash(name,m);
				node.delete(name,arr[hashVal]);
			}
			else
			{
				int index=Integer.parseInt(str.substring(findex+1));
				String content="";
				for(PBNode node1 : arr[index])
				{
					content+=node1.getName()+" ";
				}
				if(!content.equals(""))
					content=content.trim();
				System.out.print(content+"\n");
			}
		}
                
		
	}
	
	private static int getPolyHash(String name,int m) {
		
		long hashVal=0;
                long p=(long)1000000007;
		for(int i=name.length()-1;i>=0;i--)
			hashVal = (hashVal*(263) + (long)name.charAt(i))%p;
		
		return (int)((hashVal%p)%m);
	}

}
