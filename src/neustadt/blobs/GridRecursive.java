package neustadt.blobs;

import java.util.Random;
import java.util.Stack;
import java.util.Vector;

public class GridRecursive<E> {
	//a Vector of Vectors of Cells
			private Vector<Vector<Cell<E>>> cells;
		
		
		
		public GridRecursive(int rows, int columns){
			cells = new Vector<Vector <Cell<E>>>();
			
			//initialize each cell of the grid
			for (int row = 0;row<rows;row++){
				//set up a Vector that will represent a new row in a two dimensional grid
				Vector<Cell<E>> tempVector = new Vector<Cell<E>>();
				
				for (int col=0;col<columns;col++){
					tempVector.add(new Cell<E>(row,col)); //add a Cell to this Vector
				}
				//add this new vector to the Vector of Vectors 
				cells.add(tempVector);
			}
			
		}
		
		public void setGrid(int percentage, E value){
			Random randomGenerator = new Random(System.currentTimeMillis());
			int randomNum;
		
			for(int row = 0;row< cells.size();row++){
				for (int col=0;col < cells.get(row).size();col++){
					randomNum = randomGenerator.nextInt(100);
					if (randomNum < percentage){
						//first get() , gets access to the Vector on a particular row
						//second get() , gets access to a particular Cell in the Vector on a particular row
						cells.get(row).get(col).setData(value);
					}
				}
			}
		}
		
		public int countBlobs(E value){
			
			int count =0;
			
			for (int row =0;row < cells.size();row++){
				for (int col=0;col< cells.get(row).size();col++){
					Cell<E>startCell = cells.get(row).get(col);
					
					if (!startCell.isVisited() && startCell.hasData()){
						count++;
						//mark the blob connected to starting cell
						markBlob(startCell); 
					}
				}
			}
			return count;
		}
		
		public void markBlob(Cell<E> currentCell){
			Cell<E> cellup, celldown, cellRight, cellLeft;
			//mark the current cell as visited	
			
			//went this route already ,no need to explore it again
			
			Stack<Cell<E>> stack = new Stack<Cell<E>>();
			stack.push(currentCell);
			
			while(!stack.isEmpty()){
			Cell<E> cell = stack.pop();
			
			//traverse up
			if (cell.getRow() > 0){ //end of grid
				cellup = cells.get(cell.getRow()-1).get(cell.getCol());
				
				if (!cellup.isVisited()){
							cellup.setVisited();
				if (cellup.hasData() ){
							stack.push(cellup);  
						}
			}
			}
				
    		//traverse down
			if (cell.getRow()< cells.size() -1){ //move down one row
				celldown = cells.get(cell.getRow()+1).get(cell.getCol());
				if (!celldown.isVisited()){
					celldown.setVisited();
				if (celldown.hasData()){
					stack.push(celldown);  
				}
				}	
			}
			
			//traverse left
    		 if (cell.getCol() > 0){ //end of grid
        	     //move over left one column
    			 cellLeft = cells.get(cell.getRow()).get(cell.getCol()-1);
    			 if (!cellLeft.isVisited()){
    				 cellLeft.setVisited();
 				if (cellLeft.hasData() ){
 					stack.push(cellLeft);  
 				}
 				}	
        		}
			
			//traverse right
			if (cell.getCol() < cells.get(cell.getRow()).size() -1){ 
    	    	 //move over right one column
				cellRight = cells.get(cell.getRow()).get(cell.getCol()+ 1);
				 if (!cellRight.isVisited()){
    				 cellRight.setVisited();
 				if (cellRight.hasData() ){
 					stack.push(cellRight);  
 				}    	   
    	    		}
			}
			}//end while loop when stack isEmpty
			
			
		}

			
			public String toString(){
				StringBuffer buffer = new StringBuffer();
				for(int row = 0;row< cells.size();row++){buffer.append("\n");
					for (int col=0;col < cells.get(row).size();col++){
						
					
					
						buffer.append(" " + cells.get(row).get(col).toString());
					}
				}
				return buffer.toString();
			}
		
		
		

		
		static public void main(String []  args){
			Character character = new Character('X');
			GridRecursive<Character> theGrid = new GridRecursive<Character>(5,5);
			theGrid.setGrid(40, 'X');
			System.out.println(theGrid);
			System.out.println(theGrid.countBlobs(character));
			
		}
}
