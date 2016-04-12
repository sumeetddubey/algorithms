import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BinomialHeap {
	
	//arguments and constants
	public HeapNode root;
	public int size;
	public final int negativeInf = (int) Double.NEGATIVE_INFINITY;
	
	//constructor 
	public BinomialHeap(){
		root = null;
		size = 0;
	}
	
	public int findMin(){
		return root.findMinNode().getKey();
	}
	
	public void merge(HeapNode root2){
		HeapNode node1 = root;
		HeapNode node2 = root2;
		while(node1 != null && node2 != null){
			if(node1.degree == node2.degree){
				HeapNode temp = node2;
				node2 = node2.sibling;
				temp.sibling = node1.sibling;
				node1.sibling = temp;
				node1 = temp.sibling;
			}
			else{
				if(node1.degree < node2.degree){
					if((node1.sibling == null) || (node1.sibling.degree > node2.degree)){
						HeapNode temp  = node2;
						node2 = node2.sibling;
						temp.sibling = node1.sibling;
						node1.sibling = temp;
						node1 = temp.sibling;
					}
					else{
						node1 = node1.sibling;
					}
				}
				else{
					HeapNode temp = node1;
					node1 = node2;
					node2 = node2.sibling;
					node1.sibling = temp;
					if(temp == root){
						root = node1;
					}
				}
			}
		}
		if(node1 == null){
			node1 = root;
			while(node1.sibling != null){
				node1 = node1.sibling;
			}
			node1.sibling = node2;
		}
	}
	
	public void union(HeapNode node){
		merge(node);
		
		HeapNode temp = root;
		HeapNode prevNode = null;
		HeapNode nextNode = root.sibling;
		
		while(nextNode != null){
			if((temp.degree != nextNode.degree) || (nextNode.sibling != null) && (nextNode.sibling.degree == temp.degree)){
				prevNode = temp;
				temp = nextNode;
			}
			else{
				if(temp.key <= nextNode.key){
					temp.sibling = nextNode.sibling;
					nextNode.p = temp;
					nextNode.sibling = temp.child;
					temp.child = nextNode;
					temp.degree++;
				}
				else{
					if(prevNode == null){
						root = nextNode;
					}
					else{
						prevNode.sibling = nextNode;
					}
					temp.p = nextNode;
					temp.sibling = nextNode.child;
					nextNode.child = temp;
					nextNode.degree++;
					temp = nextNode;
				}
			}	
			nextNode = temp.sibling;
		}
	}
	
	public void insert(int key){
		if(key > 0){
			HeapNode temp  = new HeapNode(key);
			if(root==null){
				root=temp;
				size=1;
			}
			else{
				union(temp);
				size++;
			}
		}
	}
	
	public int extractMin(){
		if(root==null){
			// no min  
			return -1;
		}
		
		HeapNode temp = root;
		HeapNode prev = null;
		HeapNode min = root.findMinNode();
		//System.out.println("MIN NODE VAL "+min.key);
		
		while(temp.key != min.key){
			prev=temp;
			temp = temp.sibling;
		}
		
		if(prev==null){
			root = temp.sibling;
		}
		
		else{
			prev.sibling = temp.sibling;
		}
		temp = temp.child;
		HeapNode temp2 = temp;
		while(temp != null){
			temp.p = null;
			temp = temp.sibling;
		}
		
		if(root == null && temp2==null){
			size=0;
		}
		else{
			if(root==null && temp2!=null){
				root = temp2.reverse(null);
			}
			else{
				if(!(root != null && temp2 == null)){
					union(temp2.reverse(null));
				}
			}
		}
		//System.out.println("MIN NODE VAL "+min.key);
		System.out.println("extracted min value " +min.key);
		return min.key;
	}
	
	public void decreaseKeyValue(int oldVal, int newVal){
		if(newVal > oldVal){
			System.out.println("new key has to be smaller");
			return;
		}
		HeapNode temp = root.findNode(oldVal);
		if(temp == null){
			return;
		}
		temp.key = newVal;
		HeapNode parentOfTemp = temp.p;
		while(parentOfTemp != null && temp.key < parentOfTemp.key){
			int key = temp.key;
			temp.key = parentOfTemp.key;
			parentOfTemp.key = key;
			temp = parentOfTemp;
			parentOfTemp = parentOfTemp.p;
		}
	}
	
	public void delete(int key){
		if(root!=null && root.findNode(key)!=null){
			decreaseKeyValue(key, negativeInf);
			extractMin();
		}
	}
	
	public void display(){
		//System.out.println("\n");
		System.out.print("Current Heap: ");
		displayHelper(root);
		System.out.println("\n");
	}
	
	public void displayHelper(HeapNode node){
		if(node != null){
			displayHelper(node.child);
			System.out.print(node.key +" ");
			displayHelper(node.sibling);
		}
	}
	public static void main(String[] args) throws IOException{
		BinomialHeap BH = new BinomialHeap();
		
		ArrayList<Integer> ip = new ArrayList<Integer>();
		Scanner scan = new Scanner(new File("input.txt"));
		while(scan.hasNext()){
			ip.add(scan.nextInt());
		}
		scan.close();
		
		for(int obj: ip){
			BH.insert(obj);
		}
		
		// tests 
		BH.display();
		
		BH.insert(1);
		BH.insert(6);
		BH.display();
		System.out.println("min is " +BH.findMin());
		
		BH.extractMin();
		System.out.println("min is " +BH.findMin()); 
		BH.display();
		
		BH.extractMin();
		System.out.println("min is " +BH.findMin()); 
		BH.display();
		
		BH.extractMin();
		System.out.println("min is " +BH.findMin()); 
		BH.display();
		
		BH.extractMin();
		System.out.println("min is " +BH.findMin()); 
		BH.display();
		
		BH.extractMin();
		System.out.println("min is " +BH.findMin()); 
		BH.display();
		
		BH.extractMin();
		System.out.println("min is " +BH.findMin()); 
		BH.display();
			
		BH.delete(24);
		BH.display();
		
		BH.extractMin();
		System.out.println("min is " +BH.findMin());
		
		BH.decreaseKeyValue(35, 11);
		BH.display();
		System.out.println("min is " +BH.findMin());
		
		HeapNode node = new HeapNode(40);
		BH.union(node);
		BH.display();
	}
	
}
 