import java.util.*;

class DisjointData{
    
    public static int root(int Arr[ ],int i)
    {
        while(Arr[ i ] != i)           
        {
            i = Arr[ i ];
        }
        return i;
    }

    public static boolean find(int A,int B,int[] arr)
    {
        if( root(arr,A)==root(arr,B) )       
            return true;
        else
            return false;
    }

    public static void initialize( int Arr[ ], int[] size,int N)
    {
        for(int i = 0;i<N;i++)
        {
            Arr[ i ] = i ;
            size[ i ] = 1;
        }
    }
    
    public static void weighted_union(int Arr[ ],int size[ ],int A,int B,int[] energy)
    {
        int root_A = root(Arr,A);
        int root_B = root(Arr,B);
    
        if(size[root_A] < size[root_B ])
        {
            Arr[ root_A ] = Arr[root_B];
            size[root_B] += size[root_A];
            energy[root_B]+=energy[root_A];
        }
        else
        {
            Arr[ root_B ] = Arr[root_A];
            size[root_A] += size[root_B];
            energy[root_A]+=energy[root_B];
        }
    }

    public static void main(String args[]){

	   Scanner in=new Scanner(System.in);

	   int n=in.nextInt();
	   int q=in.nextInt();

	   int[] energy=new int[n];
	
	   for(int i=0; i<n; i++){

	       energy[i]=in.nextInt();
	   }

	   int[] result=new int[q];
	   int[] arr=new int[n];
	   int[] size=new int[n];
	
	   int x,a,b;
	
	   initialize(arr,size,n);

	   for(int i=0; i<q; i++){

	       x=in.nextInt();
	       a=in.nextInt();
	       b=in.nextInt();
        
           result[i]=-1;
        
	       if(x==1){

		      if(find(a,b,arr)){

		          continue;
		      }

		      weighted_union(arr,size,a,b,energy);

	       }

	       else{

		      int eA=energy[root(arr,a)];
		      int eB=energy[root(arr,b)];

		      result[i]=Math.max(eA,eB)-Math.min(eA,eB);

	       }
	   }

	   for(int i=0; i<q; i++){

            if(result[i]>=0)
	        System.out.println(result[i]);
	   }
        
    }

}
