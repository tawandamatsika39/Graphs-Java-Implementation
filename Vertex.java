import java.util.*;
public class Vertex implements Comparable<Vertex>
{
	private String name;
	private int indegree;
	private int  label;
	private ArrayList<Edge> incidentEdges;
	
	
	public Vertex(String d, ArrayList<Edge> object)
	{
		name = d;
		incidentEdges =object;
		indegree = 0;
		label =(int)Integer.MAX_VALUE ;	
	}
	
	public int compareTo(Vertex n)
	{
		int result=0;
		if(label > n.label)
		{
			 result=1;
		}
		if(label < n.label) 
		{
			result= -1;
		}
		return result;
	}
	public int getLabel()
	{
		return label;
	}
	public void setLabel(int n)
	{
		label =n;
	}
	public boolean equals(Vertex v)
	{
		return name.equals(v.name);
	}
	public ArrayList<Edge> incidentEdges()
	{
		return incidentEdges;
	}
	public String getName()
	{
		return name;
	}
	 
	public void setName(String newName)
	{
		name = newName;
	}
	public int indegree()
	{
		return indegree;
	}
	public void setIndegree(int n)
	{
		indegree = n;
	}
	public String toString()
	{
		return name;
	}
	
}
