import java.util.*;
public class Graph
{
	private ArrayList<Vertex> V;
	private ArrayList<Edge> E;
	
	public Graph()
	{
		V = new ArrayList<Vertex>();
		E = new ArrayList<Edge>();
	}
	public ArrayList<Vertex> vertices()
	{
		return V;
	}
	public ArrayList<Edge> edges()
	{
		return E;
	}
	public Edge edge(Vertex u, Vertex v)
	{
		for(Edge e: u.incidentEdges())
		{
			if(e.getDestination().equals(v))
			{
				return e;
			}
		}
		return null;
	}
	
	public Vertex opposite(Vertex v, Edge e)
	{
		if(v.equals(e.getDestination()))
		{
			return e.getOrigin();
		}
		return e.getDestination();
	}
	public Vertex[] endVertices(Edge e)
	{
		Vertex[] toReturn  = new Vertex[2];
		toReturn[0] = e.getOrigin();
		toReturn[1] = e.getDestination();
		return toReturn;
	}
	public boolean dfs(Vertex current, Set<Vertex> whiteSet, Set<Vertex> graySet, Set<Vertex> blackSet)
	{
		moveVertice(current, whiteSet, graySet);
		for(Vertex neighbor : getAdjacentVertices(current))
		{
			if(blackSet.contains(neighbor))
			{
				continue;
			}
			if(graySet.contains(neighbor))
			{
				return true;
			}
			if(dfs(neighbor, whiteSet, graySet, blackSet))
			{
				return true;
			}
			
		}
		moveVertice(current, graySet, blackSet);
		return false;
	}
	public boolean areAdjacent(Vertex u, Vertex v)
	{
		for(Edge e : u.incidentEdges())
		{
			if(opposite(u, e).equals(v))
			{
				return true;
			}
		}
		return false;
	}
	public boolean hasCycle()
	{
		Set<Vertex> whiteSet = new HashSet<>(),
		              graySet = new HashSet<>(),
		              blackSet = new HashSet<>();
		for(Vertex v : V)
		{
			whiteSet.add(v);
		}
		while(whiteSet.size() > 0)
		{
			Vertex current = whiteSet.iterator().next();
			if(dfs(current, whiteSet, graySet, blackSet))
			{
				return true;
			}
		}
		return false;
	}
	private void moveVertice(Vertex v, Set<Vertex> source, Set<Vertex> destination)
	{
		source.remove(v);
		destination.add(v);
	}
    public void insertVertix(Vertex v)
	{
		V.add(v);
		
	}
	public void insertEdge(Vertex o, Vertex d, String dat, int w)
	{
		insertEdge(new Edge(o,d,dat, w));
	}
	
	
	public ArrayList<Vertex> getAdjacentVertices(Vertex v)
	{
		ArrayList<Vertex> object = new ArrayList<Vertex>();
		for(Edge e : v.incidentEdges())
		{
			if(v.equals(e.getOrigin()))
			{
				object.add(e.getDestination());
			}
		}
		return object;
	}
	
	public Stack<Vertex> topologicalSort()
	{
		Stack<Vertex> stack = new Stack<Vertex>();
		Set<Vertex> visited = new HashSet<>();
		for(Vertex v : V)
		{
			if(visited.contains(v))
			{
				continue;
			}
			sortHelper(v, stack, visited);
		}
		return stack;
		
	}
	private void sortHelper(Vertex v, Stack<Vertex> stack, Set<Vertex> visited)
	{
		visited.add(v);
		for(Vertex child : getAdjacentVertices(v))
		{
			if(visited.contains(child))
			{
				continue;
			}
			sortHelper(child, stack, visited);
		}
		stack.push(v);
	}
	public void insertEdge(Edge e)
	{
	    boolean originIn = false, destIn = false;
		E.add(e);
		for(Vertex v : V)
		{
			if(v.getName().equals(e.getOrigin().getName()))
			{
				e.setOrigin(v);
				e.setOIndex(v.incidentEdges().size());
				v.incidentEdges().add(e);
				originIn = true;
			}
			if(v.getName().equals(e.getDestination().getName()))
			{
				e.setDestination(v);
				e.setDIndex(v.incidentEdges().size());
				v.incidentEdges().add(e);
				v.setIndegree(v.indegree() + 1);
				destIn = true;
			}
		}
		if(!originIn)
		{
			V.add(e.getOrigin());
			
			e.setOIndex(0);
		}
		if(!destIn)
		{
			V.add(e.getDestination());
			
			e.setDIndex(0);
			e.getDestination().setIndegree(e.getDestination().indegree() + 1);
		}
		
	}
	public void displayShortestPath()
	{
		for(Vertex v: V)
		{
			System.out.println(("Start-->"+v.getName() +"                     ").substring(0, 20) +"Path Length: "+ v.getLabel());
		}
	}
	public void display()
	{
	    System.out.println("Edges :");
		for(Edge e : E)
		{
			System.out.println(e);
		}
	}
	public void run()
	{
	    
		if(hasCycle())
		{
			System.out.println("ERROR !");
			display();
			
		}
		else
		{
		    System.out.println("TOPOLOGICAL SORTING :");
			Stack<Vertex> object = topologicalSort();
			while(!object.empty())
			{
				System.out.print(object.pop() + "   ");
			}
			System.out.println();
			
		    Dijkstra(V.get(0));
			displayShortestPath();
		}
		
	}
	public boolean isDirected(Edge e)
	{
		return e.getData().equals("->");
	}
	public void Dijkstra(Vertex v)
	{
		v.setLabel(0);
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		
		for(Vertex vertex : V)
		{
			pq.add(vertex);
		}
		Set<Vertex> cloud = new HashSet<>();
		while(!pq.isEmpty())
		{
			Vertex u = pq.poll();
			for(Vertex z : getAdjacentVertices(u))
			{
				if(u.getLabel() + edge(u, z).getWeight() < z.getLabel())
				{
					z.setLabel(u.getLabel() + edge(u, z).getWeight());
				}
			}
		}	
	}
	public static void main(String[] args)
	{
		Graph object = new Graph();
		object.insertEdge(new Vertex("start", new ArrayList<Edge>()), new Vertex("A", new ArrayList<Edge>()), "->",9);
		object.insertEdge(new Vertex("start", new ArrayList<Edge>()), new Vertex("B", new ArrayList<Edge>()), "->",14);
		object.insertEdge(new Vertex("start", new ArrayList<Edge>()), new Vertex("C", new ArrayList<Edge>()), "->",15);
		object.insertEdge(new Vertex("A", new ArrayList<Edge>()), new Vertex("E", new ArrayList<Edge>()), "->", 24);
		object.insertEdge(new Vertex("B", new ArrayList<Edge>()), new Vertex("E", new ArrayList<Edge>()), "->", 18);
		object.insertEdge(new Vertex("B", new ArrayList<Edge>()), new Vertex("D", new ArrayList<Edge>()), "->", 30);
		object.insertEdge(new Vertex("B", new ArrayList<Edge>()), new Vertex("C", new ArrayList<Edge>()), "->", 5);
		object.insertEdge(new Vertex("D", new ArrayList<Edge>()), new Vertex("F", new ArrayList<Edge>()), "->", 11);
		object.insertEdge(new Vertex("E", new ArrayList<Edge>()), new Vertex("D", new ArrayList<Edge>()), "->", 2);
		object.insertEdge(new Vertex("C", new ArrayList<Edge>()), new Vertex("D", new ArrayList<Edge>()), "->",20);
	    	object.insertEdge(new Vertex("C", new ArrayList<Edge>()), new Vertex("Goal", new ArrayList<Edge>()), "->", 44);
	    	object.insertEdge(new Vertex("D", new ArrayList<Edge>()), new Vertex("Goal", new ArrayList<Edge>()), "->", 16);
		object.insertEdge(new Vertex("E", new ArrayList<Edge>()), new Vertex("Goal", new ArrayList<Edge>()), "->", 19);
		object.insertEdge(new Vertex("F", new ArrayList<Edge>()), new Vertex("Goal", new ArrayList<Edge>()), "->", 6);
	    	object.run();
	 }
	
}
