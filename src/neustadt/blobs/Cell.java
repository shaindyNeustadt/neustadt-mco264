package neustadt.blobs;

public class Cell<T> implements Settable <T>{
   private T data;
   private boolean visited;
   private int row;
   private int col;
   
   public Cell(T data, int row, int col){
	   this.data = data;
	   this.visited = false;
	   this.row = row;
	   this.col = col;
   }
   
   public Cell(int row, int col){
	   this(null,row,col);
   }
   
   
   public void setData(T data){
	   this.data =data;
   }
   
   
   
   public void setVisited(){
	   this.visited = true;
   }
   
   public String toString (){
	   if (data == null)
	   {
		   return "-";
	   }
	   return data.toString();
   }
   public void resetVisited(){
	   this.visited = false;
   }
   
   public boolean hasData(){
	   return (this.data != null);
   }
   
   public boolean isVisited(){
	   return this.visited;
   }
   
   public void reset (){
	   this.data = null;
	  this.visited = false;
   }

@Override
public void resetData() {
	this.data = null;
	
}

public int getRow(){
	return row;
}

public int getCol(){
	return col;
}

 
}
