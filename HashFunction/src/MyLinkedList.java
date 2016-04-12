public class MyLinkedList {
	public String key;
	public int value;
	public MyLinkedList next;
	
	MyLinkedList(String key, int value){
		this.key = key;
		this.value = value;
		this.next = null;
	}
	
	public String getKey(){
		return key;
	}
	
	public void setKey(String key){
		this.key = key;
	}
	
	public int getValue(){
		return value;
	}
	
	public void setValue(int value){
		this.value = value;
	}
	
	public MyLinkedList getNext(){
		return next;
	}
	
	public void setNext(MyLinkedList next){
		this.next = next;
	}

}
