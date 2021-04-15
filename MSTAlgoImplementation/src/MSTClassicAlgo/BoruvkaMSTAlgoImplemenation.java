package MSTClassicAlgo;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BoruvkaMSTAlgoImplemenation
{
  static int parent[] = new int[7];
  static int Min[] = new int[7];
 
  public static void main(String args[]) throws FileNotFoundException 
  {
	  long start= System.currentTimeMillis();
  // No. of vertices in graph.
	  String file="src/inputFile/new.txt";
		Scanner scn=new Scanner(new File(file));
int n = scn.nextInt();
  //int n=6; 
int edge =scn.nextInt();
  Graph_Edge g[]=new Graph_Edge[edge+1];
  System.out.println("Graph has "+n +" vertices "+edge+" edges");
  System.out.println("Printing Graph");
  
  // Creating the graph with source, end and cost of each edge
	for (int i=1;i<=edge;i++)
	{
		 int u = scn.nextInt(), v = scn.nextInt(), w = scn.nextInt();
		 System.out.println("Edge " +u+ " --> "+ v +" weight "+w);
		g[i]=new Graph_Edge(u, v, w);
		
	}
	System.out.println("Boruka Implementation MST : ");
	

 

  
  // Initializes parent of all nodes.
  init(n);
  
  int edges = g.length-1;
  
  int components = n;
  int ans_MST=0;
  
  while(components>1)
  {
      // Initialize Min for each component as -1.
      for(int i=1;i<=n;i++)
      {
          Min[i]=-1;
      }
      for(int i=1;i<=edges;i++)
      {
          // If both source and end are from same component we don't process them.
          if(root(g[i].v)==root(g[i].u))
          continue;
          
          int r_v=root(g[i].v);
          if(Min[r_v]==-1 || g[i].cost < g[Min[r_v]].cost)
          Min[r_v]=i;
          
          int r_u=root(g[i].u);
          if(Min[r_u]==-1 || g[i].cost < g[Min[r_u]].cost)
          Min[r_u]=i;
          
      }
      
      for(int i=1;i<=n;i++)
      {
    	  
          if(Min[i]!=-1)
          {
              if(merge(g[Min[i]].v,g[Min[i]].u))
              {
                  ans_MST+=g[Min[i]].cost;
                  System.out.println("Edge "+g[Min[i]].v+" -->"+" "+g[Min[i]].u+" has weight "+g[Min[i]].cost);
                  components--;
              }
          }
      }
      
  }
  
 
  System.out.println("The Total Weight of Minimum Spanning Tree is : "+ans_MST);
  long end = System.currentTimeMillis();
	System.out.println("Exection time ="+(end-start) +"millisec");
  }
  static int root(int v)
  {
      if(parent[v]==v)
      return v;
      
      return parent[v]=root(parent[v]);
  }
  
  static boolean merge(int v,int u)
  {
      v=root(v);
      u=root(u);
      if(v==u)
      return false;
      parent[v]=u;
      return true;
  }
 
  static void init(int n)
  {
      for(int i=1;i<=n;i++)
      {
          parent[i]=i;
          //System.out.println("parent"+parent[i]);
      }
  }
  
}