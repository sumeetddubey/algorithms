
public class MyRBNode {
	public String color;
	public Integer key;
	public MyRBNode left;
	public MyRBNode right;
	public MyRBNode p;
	
	//constructor
	MyRBNode(int key){
		this.key = key;
		this.left = null;
		this.right = null;
		this.color = null;
		this.p = null;
	}
	
	//getters and setters
	public Integer getKey(){
		return key;
	}
	
	public void setKey(Integer key){
		this.key = key;
	}
	
	public MyRBNode getLeftChild(){
		return left;
	}
	
	public void setLeftChild(MyRBNode left){
		this.left = left;
	}
	
	public MyRBNode getRightChild(){
		return right;
	}
	
	public void setRightChild(MyRBNode right){
		this.right = right;
	}
	
	public MyRBNode getParent(){
		return p;
	}
	
	public void setParent(MyRBNode p){
		this.p = p;
	}
	
	public String getColor(){
		return color;
	}
	
	public void setColor(String color){
		this.color = color;
	}
	
	
}
