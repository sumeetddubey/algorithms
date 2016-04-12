import java.io.*;
import java.util.*;

public class RedBlack {
	
	// static variables
	public static int arrayListLength;
	public static ArrayList<Integer> numberList = new ArrayList<Integer>();
	public static MyRBNode RBRoot;
	public static MyRBNode nilNode = null;
	public final static String BLACK = "BLACK";
	public final static String RED = "RED";
	 
	public static void treeLeftRotate(MyRBNode x){
		if(x.getRightChild() == null){
			System.out.println ("cant rotate left");
		}
		else{
			MyRBNode y = x.getRightChild();
			x.setRightChild(y.getLeftChild());
			
			if (y.getLeftChild() != nilNode){ 
				y.getLeftChild().setParent(x);
			}
			y.setParent(x.getParent());
			if(x.getParent() == nilNode){
				RBRoot = y;
			}
			else if(x == x.getParent().getLeftChild()){
				x.getParent().setLeftChild(y);
			}
			else{
				x.getParent().setRightChild(y);
			}
			y.setLeftChild(x);
			x.setParent(y);
		}
	}
	
	public static void treeRightRotate(MyRBNode x){
		if(x.getLeftChild() == null){
			System.out.println ("cant rotate left");
		}
		else{
			MyRBNode y = x.getLeftChild();
			x.setLeftChild(y.getRightChild());
			
			if (y.getRightChild() != nilNode){ 
				y.getRightChild().setParent(x);
			}
			y.setParent(x.getParent());
			if(x.getParent() == nilNode){
				RBRoot = y;
			}
			else if(x == x.getParent().getRightChild()){
				x.getParent().setRightChild(y);
			}
			else{
				x.getParent().setLeftChild(y);
			}
			y.setRightChild(x);
			x.setParent(y);
		}
	}
	
	public static void RBInsertFixup(MyRBNode RBNode){
		while(RBNode.getParent().getColor().equals(RED)){
			if(RBNode.getParent() == RBNode.getParent().getParent().getLeftChild()){
				MyRBNode y = RBNode.getParent().getParent().getRightChild();
				if(y.getColor().equals(RED)){
					RBNode.getParent().setColor(BLACK);
					y.setColor(BLACK);
					RBNode.getParent().getParent().setColor(RED);
					RBNode = RBNode.getParent().getParent();
				}
				else if(RBNode == RBNode.getParent().getRightChild()){
					RBNode = RBNode.getParent();
					treeLeftRotate(RBNode);
					RBNode.getParent().setColor(BLACK);
					RBNode.getParent().getParent().setColor(RED);
					treeRightRotate(RBNode.getParent().getParent());
				}	
			}
			else{
				MyRBNode y = RBNode.getParent().getParent().getLeftChild();
				if(y.getColor().equals(RED)){
					RBNode.getParent().setColor(BLACK);
					y.setColor(BLACK);
					RBNode.getParent().getParent().setColor(RED);
					RBNode = RBNode.getParent().getParent();
				}
				else if(RBNode == RBNode.getParent().getLeftChild()){
					RBNode = RBNode.getParent();
					treeLeftRotate(RBNode);
					RBNode.getParent().setColor(BLACK);
					RBNode.getParent().getParent().setColor(RED);
					treeRightRotate(RBNode.getParent().getParent());
				}
			}
		}
		RBRoot.setColor(BLACK);
	}
	
	public static void insert(int key){
		MyRBNode obj = new MyRBNode(key);
		MyRBNode y = nilNode;
		if(RBRoot == null){
			RBRoot = obj;
			RBRoot.setColor(BLACK);
			//System.out.println("inserted node with key " +rbtree.get(0).getKey());
		}
		else{
			MyRBNode root = RBRoot;
			while (root != nilNode){
				y = root;
				if(obj.getKey() < root.getKey()){
					root = root.getLeftChild();
				}
				else{
					root = root.getRightChild();
				}	
			}
			obj.setParent(y);
			if(y == nilNode){
				RBRoot = obj;
			}
			else if(obj.getKey() < y.getKey()){
				y.setLeftChild(obj);
			}
			else{
				y.setRightChild(obj);
			}
			obj.setLeftChild(nilNode);
			obj.setRightChild(nilNode);
			obj.setColor("red");
			RBInsertFixup(obj);
		}
	}
	
	public static void sort(MyRBNode node){
		if(node != nilNode){
			sort(node.getLeftChild());
			System.out.print(node.getKey() +" ");
			sort(node.getRightChild());
		}
	}
	
	public static MyRBNode search(MyRBNode node, Integer key){
		if (node==nilNode || node.getKey()==key){
			return node;
		}
		else if(key < node.getKey()){
			return search(node.getLeftChild(), key);
		}
		else{
			return search(node.getRightChild(), key);
		}
	}
	
	public static MyRBNode treeMinimum(MyRBNode root){
		MyRBNode node  = root;
		while(node.getLeftChild() != nilNode){
			node = node.getLeftChild();
		}
		return node;
	}
	
	public static MyRBNode treeMaximum(MyRBNode root){
		MyRBNode node = root;
		while(node.getRightChild() != nilNode){
			node = node.getRightChild();
		}
		return node;
	}
	
	public static MyRBNode treeSuccessor(MyRBNode node){
		if (node.getRightChild() != nilNode){
			return treeMinimum(node.getRightChild());
		}
		else{
			MyRBNode obj = node.getParent();
			while(obj != nilNode && node==obj.getRightChild()){
				node = obj;
				obj = obj.getParent();
			}
			return obj;
		}
	}
	
	public static MyRBNode treePredecessor(MyRBNode node){
		if (node.getLeftChild() != nilNode){
			return treeMaximum(node.getLeftChild());
		}
		else{
			MyRBNode obj = node.getParent();
			while(obj != nilNode && node==obj.getLeftChild()){
				node = obj;
				obj = obj.getParent();
			}
			return obj;
		}
	}
	
	public static int treeHeight(MyRBNode node){
		int height = 1;
		MyRBNode obj = node;
		if(obj == null){
			height = 0;
			return height;
		}
		else if(obj.getLeftChild()==null && obj.getRightChild()==null){
			return height;
		}
		else{
			height = height + Math.max(treeHeight(obj.getLeftChild()), treeHeight(obj.getRightChild()));
			return height;
		}
		
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new File("src/numbers.txt"));
		while(scan.hasNextInt()){
			numberList.add(scan.nextInt());
		}
		scan.close();
		arrayListLength = numberList.size();
		Iterator<Integer> i = numberList.iterator();
		System.out.print("\n" +"values inserted in tree are: ");
		while(i.hasNext()){
			Integer element = i.next();
			insert(element);
			System.out.print(element +" ");	
		}
		
		System.out.println("\n" +"height is " +treeHeight(RBRoot));
		
		String operation ="";
		MyRBNode obj, node;
		int key;
		System.out.println("\n" +"possible operations: sort, search, min, max, successor, predecessor, insert");
		Scanner scan2 = new Scanner(System.in);
		Scanner scan3 = new Scanner(System.in);
		while(true){
			System.out.println("enter operation: ");
			operation = scan2.nextLine();
			switch (operation){
			case "sort": 
				sort(RBRoot);
				System.out.println("");
				System.out.println("tree height: " +treeHeight(RBRoot));
				break;
			
			case "search": 
				System.out.println("enter value: ");
				key = scan3.nextInt();
				obj = search(RBRoot, key);
				if(obj == nilNode){
					System.out.println("Not Found");
				}
				else{
					System.out.println("Found");
				}
				System.out.println("tree height: " +treeHeight(RBRoot));
				break;
			
			case "min":
				obj = treeMinimum(RBRoot);
				System.out.println("minimum key in tree is: " +obj.getKey());
				System.out.println("tree height: " +treeHeight(RBRoot));
				break;
			
			case "max":
				obj = treeMaximum(RBRoot);
				System.out.println("maximum key in tree is: " +obj.getKey());
				System.out.println("tree height: " +treeHeight(RBRoot));
				break;
			
			case "successor":
				System.out.println("enter key: ");
				key = scan3.nextInt();
				node = search(RBRoot, key);
				obj = treeSuccessor(node);
				if (obj==null)
					System.out.println("no successor");
				else
					System.out.println("successor of given node is: " +obj.getKey());
				System.out.println("tree height: " +treeHeight(RBRoot));
				break;
				
			case "predecessor":
				System.out.println("enter key: ");
				key = scan3.nextInt();
				node = search(RBRoot, key);
				obj = treePredecessor(node);
				if(obj == null)
					System.out.println("no predecessor");
				else
					System.out.println("predecessor of given node is: " +obj.getKey());
				System.out.println("tree height: " +treeHeight(RBRoot));
				break;
			
			case "insert":
				System.out.println("enter key: ");
				key = scan3.nextInt();
				insert(key);
				System.out.println("node inserted ");
				System.out.println("tree height: " +treeHeight(RBRoot));
				break;
				
			case "quit":
				scan2.close();
				scan3.close();
				System.exit(0);
				
			default:
				System.out.println("invalid input ");
				break;
			}			
		}
	}	
}
