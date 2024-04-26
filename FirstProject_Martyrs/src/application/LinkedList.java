//Name:sama wahidee
//Id:1211503
//Section:1
package application;

import java.security.PublicKey;
import java.util.Date;

public class LinkedList {
	private Node first;
	private Node last;
	private Node sorted;
	int count;

	public LinkedList() {

	}

//function to return the first node of the linked list
	public Node getfirst() {
		if (count != 0) {
			return first;
		}
		return null;

	}

	// function to return the last node of the linked list
	public Node getLast() {
		if (count != 0) {
			return last;
		}
		return null;

	}

	// function that add node into the head of the linked list that holds a martyr
	// object
	public void addFirst(Martyr m) {
		if (count == 0) {
			first = last = new Node(m);
		} else {
			Node temp = new Node(m);
			temp.next = first;
			first = temp;
		}
		count++;
	}

	// function that add node into the tail of the linked list that holds a martyr
	// object
	public void addLast(Martyr m) {
		if (count == 0) {
			first = last = new Node(m);
		} else {
			Node temp = new Node(m);
			temp.next = last;
			last = temp;
		}
		count++;
	}

	// function that add node after a specific index in the linked list that holds a
	// martyr object
	public void add(Martyr m, int index) {
		if (index == 0) {
			addFirst(m);
		} else {
			if (index >= count) {
				addLast(m);
			} else {
				Node temp = new Node(m);
				Node current = first;
				for (int i = 0; i < index - 1; i++) {
					current = current.next;
				}
				temp.next = current.next;
				current.next = temp;
				count++;
			}
		}
	}

	// function that removes the first node of the linked list
	public boolean removeFirst() {
		if (count == 0) {
			return false;
		} else {
			if (count == 1) {
				first = last = null;
			} else {
				first = first.next;
			}
			count--;
			return true;

		}

	}

	// function that removes the last node of the linked list
	public boolean removeLast() {
		if (count == 0) {
			return false;
		} else {
			if (count == 1) {
				first = last = null;
			} else {
				Node current = first;
				for (int i = 1; i <= count - 2; i++) {
					current = current.next;
				}
				last = current;
				current.next = null;
			}
			count--;
			return true;

		}

	}

	// function that removes a specific node in the linked list due to it's index
	public boolean remove(int index) {
		if (count == 0) {
			return false;
		} else {
			if (index == 1) {
				return removeFirst();
			} else if (index == count) {
				return removeLast();

			} else if (index <= 0 || index > count) {
				return false;
			} else {
				Node current = first;
				for (int i = 0; i < index - 1; i++) {
					current = current.next;
				}
				current.next = (current.next).next;
				count--;
				return true;
			}
		}
	}

	// function that removes a specific node in the linked list due to it's martyr
	// object
	public boolean remove(Martyr m) {
		if (count == 0) {
			return false;
		} else {
			if (first.martyr.equals(m)) {
				return removeFirst();
			} else {
				Node previous = first;
				Node current = first.next;
				while (current != null && !current.martyr.equals(m)) {
					previous = current;
					current = current.next;
				}
				if (current != null) {
					previous.next = current.next;
					count--;
					return true;
				} else {
					return false;
				}
			}
		}

	}

	// function that searchs for a specific node due to it's martyr object name
	public boolean search(String m) {
		if (count == 0) {
			return false;
		} else {
			Node current = first;
			while (current != null && !(current.martyr.getName().equals(m))) {
				current = current.next;
			}
			if (current != null) {
				return true;
			}

		}
		return false;

	}

	// function that returns a specific node due to it's martyr object
	public Node getNode(Object o) {
		if (count == 0) {
			return null;
		} else {
			Node current = first;
			while ((current != null) && (!current.martyr.getName().equals(o))) {
				current = current.next;
			}
			if (current != null) {
				return current;
			}

		}
		return null;

	}

	// function that return the number of martyrs in periods due to their age
	public String numberByAge() {
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		Node current = first;
		while (current != null) {
			if (((current.martyr.getAge()) / 10 == 0) || ((current.martyr.getAge()) / 10 == 1)) {
				count1++;
			}
			if (((current.martyr.getAge()) / 10 == 2) || ((current.martyr.getAge()) / 10 == 5)) {
				count2++;
			}
			if (((current.martyr.getAge()) / 10 >= 6)) {
				count3++;
			}
			current = current.next;
		}
		return "The number of martyr whose age is less than or equal 19 years old is: " + count1 + "\n"
				+ "The number of martyr whose age is between 20 and 59 years old is: " + count2 + "\n"
				+ "The number of martyr whose age is greater than or equal 60 years old is: " + count3;

	}

	// function that return the number of male martyrs and female martyrs
	public String numberByGender() {
		int countM = 0;
		int countF = 0;
		Node current = first;
		while (current != null) {
			if (current.martyr.getGender() == 'F') {
				countF++;
			} else {
				countM++;
			}
			current = current.next;
		}
		return "The number of female martyr " + countF + "\n" + "The number of male martyr " + countM;

	}

	// function that return the age average martyrs in a specific location
	public String avgForAge() {
		int sum = 0;
		int totalCount = 0;
		Node current = first;
		while (current != null) {
			sum += current.martyr.getAge();
			totalCount++;
			current = current.next;
		}
		int avg = sum / totalCount;
		return "The average age of martyrs is " + avg;

	}

	// function that return the date that has the maximum number of martyrs in a
	// specific location
	public String maxDate() {
		SinglyLinkedList dates = new SinglyLinkedList();
		Node current = first;
		while (current != null) {
			dates.addFirst(current.martyr.getDateOfDeath());
			current = current.next;
		}
		SingelNode current1 = dates.first;
		Date mostRepeated = null;
		int maxcount = 0;

		while (current1 != null) {
			int count = 0;
			SingelNode current2 = current1;
			while (current2 != null) {
				if (current2.element.equals(current1.element)) {
					count++;
				}
				current2 = current2.next;
			}
			if (count > maxcount) {
				maxcount = count;
				mostRepeated = (Date) current1.element;
			}
			current1 = current1.next;
		}
		return ""+ mostRepeated +"  "+maxcount;
	}

	// function to print the linkedlist
	public void PrintList() {
		Node current = first;
		for (int i = 0; i < count; i++)
			System.out.println(current.martyr);
		current = current.next; // move to next link

		System.out.println("");
	}

	// two function for sorting the linked list
	public void sortedInsert(Node newNode) {
		if (sorted == null || sorted.martyr.getDateOfDeath().compareTo(newNode.martyr.getDateOfDeath()) >= 0) {
			newNode.next = sorted;
			sorted = newNode;
		} else {
			Node current = sorted;
			while (current.next != null
					&& current.next.martyr.getDateOfDeath().compareTo(newNode.martyr.getDateOfDeath()) < 0) {
				current = current.next;
			}
			newNode.next = current.next;
			current.next = newNode;
		}
	}

	public void insertionSort(Node first) {
		sorted = null;
		Node current = first;
		Node next = null;
		while (current != null) {
			next = current.next;
			sortedInsert(current);
			current = next;
		}
		first = sorted;

	}

}
