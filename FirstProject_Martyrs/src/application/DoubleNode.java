//Name:sama wahidee
//Id:1211503
//Section:1
package application;

public class DoubleNode {
	String location;
	DoubleNode next;
	DoubleNode previous;
	LinkedList linkedList;

//Double linked list node Constructor 
	public DoubleNode(String location, LinkedList linkedList) {
		this.location = location;
		this.linkedList = linkedList;
	}

	// getter for location
	public String getLocation() {
		return location;
	}

	// setter for location
	public void setLocation(String location) {
		this.location = location;
	}

}
