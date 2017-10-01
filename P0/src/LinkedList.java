// LinkedList.java
// Author: Sadie Henry
// Date: 8/26/2014
// Class: cs200
// P0 

public class LinkedList {

	private Node head;
	private int size; 

	public LinkedList()
	{
		head=null;
		size=0;
	}

	public void add(String s)
	{
		//increment size of LinkedList
		size+=1;
		Node n = new Node(s);
		//case: LinkedList is Empty
		if(head == null)
		{
			head = n;

		}
		//otherwise add to tail of LinkedList
		else
		{
			Node temp = head;
			while(temp.getNext()!=null)
			{
				//this iterates to the next Node in the LinkedList
				temp=temp.getNext();
			}
			//This sets the pointer of temp to the Node that we want to add.
			temp.setNext(n);
		}
	}
	//Removes the Node containing String, s. If found, it returns the String from the node. Otherwise, it returns null.
	public String remove(String s){

		String a = "";
		Node temp = head;

		//if the list is empty, return null
		if(temp == null){
			return null;
		}

		//otherwise, go through the list and return the object that matches s
		else{

			if(temp.getData().equals(s)){
				a = temp.getData();
				head = temp.getNext();
				size--;

			}

			else{

				while(temp.getNext() != null){
					if(temp.getNext().getData().equals(s)){
						a = s;
						temp.setNext(temp.getNext().getNext());
						size--;
					}
					else temp = temp.getNext();	
				}
		
			}
			
			return a;
		}

	}

	public String toString()
	{
		Node temp=head;
		String concat="";
		while(temp!=null)
		{
			concat+=temp.getData();
			if (temp.getNext()!=null)
				concat+=",";
			temp=temp.getNext();	
		}
		return concat;

	}


	public static void main(String[] args) {
		LinkedList l = new LinkedList();
		l.add("This");
		l.add("will");
		l.add("test");
		l.add("P0");
		System.out.println(l.size);
		l.remove("P0");
		System.out.println(l.size);
		System.out.println(l);



	}

}
