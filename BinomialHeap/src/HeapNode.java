
public class HeapNode {
	public int key;
	public HeapNode p;
	public HeapNode child;
	public HeapNode sibling;
	public int degree;
	
	//constructor
	public HeapNode(int key){
		this.key = key;
		this.p = null;
		this.child = null;
		this.sibling = null;
		this.degree = 0;
	}
	
	//getters and setters 
	public int getKey(){
		return key;
	}
	
	public void setKey(int key){
		this.key = key;
	}
	
	public HeapNode getParent(){
		return p;
	}
	
	public void setParent(HeapNode p){
		this.p = p;
	}
	
	public HeapNode getChild(){
		return child;
	}
	
	public void setChild(HeapNode child){
		this.child = child;
	}
	
	public HeapNode getSibling(){
		return sibling;
	}
	
	public void setSibling(HeapNode sibling){
		this.sibling = sibling;
	}
	
	public int getDegree(){
		return degree;
	}
	
	public void setDegree(int degree){
		this.degree = degree;
	}
	
	public HeapNode reverse(HeapNode s){
		HeapNode res;
		if (sibling != null){
			res = sibling.reverse(this);
		}
		else
			res = this;
		sibling = s;
		return res;
	}
	
	public HeapNode findMinNode(){
		HeapNode x = this;
		HeapNode y = this;
		int min = x.key;
		while(x != null){
			if(x.key < min){
				y=x;
				min = x.key;
			}
			x = x.sibling;
		} 
		return y;
	}
	
	public HeapNode findNode(int key){
		HeapNode curr = this;
		HeapNode node = null;
		while(curr != node){
			if (curr.key == key){
				node = curr;
				break;
			}
			if(curr.child == null){
				curr = curr.sibling;
			}
			else {
				node = curr.child.findNode(key);
				if (node == null){
					curr = curr.sibling;
				}
				else
					break;
			}
		}
		return node;
	}
}

