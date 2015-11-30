package neustadt.blobs;

public interface Settable<T> {
  public void reset();
  
  public void setVisited();
  
  public void setData(T data);
  
  public void resetData();
  
  public void resetVisited();
  
  public int getRow();
  
  public int getCol();
}
