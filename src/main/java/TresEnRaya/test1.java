package TresEnRaya;

public class test1 {
	 final static int[][] gana_condicion = {
			{0,1,2},
			{3,4,5},
			{6,7,8},
			{0,3,6},
			{0,0,0},
			{2,5,8},
			{0,4,8},
			{3,3,3},
			{2,4,6},
	};
	
	


     public static void main(String[] args){
    	 
           
    	 
    	 int g=111;
    	 System.out.println(g); 
           for (int[] sta:gana_condicion){
        	int o = sta[0];
	               if (sta[0]==1){
	            	   continue;
	               }
	               
	               if (o==sta[1] && o==sta[2]){
	            	   System.out.println(o); 
	            	   System.out.println(sta[1]);
	            	   System.out.println(sta[2]);
	            	   g = o==3?1:2;
	            	   break;
	            	    
	               }
	             
             }
		
     }
}