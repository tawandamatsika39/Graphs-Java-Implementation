import java.util.*;
public class Edge
{
	private Vertex source;
	private Vertex destination;
	private String data;
	private int sourceIndex;
	private int destIndex;
	private int  weight;
	
	public Edge(Vertex a, Vertex b, String data, int i, int j)
	{
		source = a;
		destination = b;
		this.data = data;
		sourceIndex = i;
		destIndex = j;
	}
	public Edge(Vertex a, Vertex b, String data)
	{
		source = a;
		destination = b;
		this.data = data;
		sourceIndex =0;
		destIndex =0 ;
		a.incidentEdges().add(this);						
	}
	public Edge(Vertex a, Vertex b, String data, int w)
	{
		source = a;
		destination = b;
		weight = w;
		this.data = data;
		sourceIndex =0;
		destIndex = 0;
		a.incidentEdges().add(this);						
	}
	public String toString()
	{
		return source.getName() + data+ destination.getName() +"   ";
	}
	
	public boolean isDirected()
	{
		return data.equals("->");
	}
	public Vertex getOrigin()
	{
		return source;
	}
	public Vertex getDestination()
	{
		return destination;
	}
	public String getData()
	{
		return data;
	}
	public int getWeight()
	{
		return weight;
	}
	public int oIndex()
	{
		return sourceIndex;
	}
	public int dIndex()
	{
		return destIndex;
	}
	public void setWeight(int w)
	{
		weight = w;
	}
	public void setOrigin(Vertex u)
	{
		source = u;
	}
	public void setDestination(Vertex v)
	{
		destination = v;
	}
	public void setData(String o)
	{
		data = o;
	}
	public void setOIndex(int num)
	{
	    sourceIndex = num;
	}
	public void setDIndex(int num)
	{
		destIndex = num;
	}
}
