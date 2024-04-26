//Name:sama wahidee
//Id:1211503
//Section:1
package application;

public class DoubleLinkedList {
	private DoubleNode first;
	private DoubleNode last;
	int count;

	public DoubleLinkedList() {
	}

	// function to return the first node of the linked list
	public DoubleNode getfirst() {
		if (count != 0) {
			return first;
		}
		return null;

	}

	// function to return the last node of the linked list
	public DoubleNode getLast() {
		if (count != 0) {
			return last;
		}
		return null;

	}

	// function that add double node into the head of the double linked list
	public void addFirst(String s, LinkedList linkedList) {
		if (count == 0) {
			first = last = new DoubleNode(s, linkedList);
		} else {
			DoubleNode temp = new DoubleNode(s, linkedList);
			first.previous = temp;
			temp.next = first;
			first = temp;
		}
		count++;
	}

	// function that add double node into the tail of the double linked list
	public void addLast(String s, LinkedList linkedList) {
		if (count == 0) {
			
			first = last = new DoubleNode(s, linkedList);
		} else {
			DoubleNode temp = new DoubleNode(s, linkedList);

			last.next = temp;
			last = temp;
			last.previous = temp;
		}
		count++;
	}

	// function that add doublenode after a specific index in the double linked list
	public void add(String s, int index, LinkedList linkedList) {
		if (index == 0) {
			addFirst(s, linkedList);
		} else {
			if (index >= count) {
				addLast(s, linkedList);
			} else {
				DoubleNode temp = new DoubleNode(s, linkedList);
				DoubleNode current = first;
				for (int i = 0; i < index - 1; i++) {
					current = current.next;
				}
				temp.next = current.next;
				temp.previous = current;
				current.next = temp;
				temp.next.previous = temp;
				count++;
			}
		}
	}

	// function that removes the first doublenode of the linked list
	public boolean removeFirst() {
		if (count == 0) {
			return false;
		} else {
			if (count == 1) {
				first = last = null;
			} else {
				first = first.next;
				first.previous = null;
			}
			count--;
			return true;

		}

	}

	// function that removes the last doublenode of the linked list
	public boolean removeLast() {
		if (count == 0) {
			return false;
		} else {
			if (count == 1) {
				first = last = null;
			} else {
				DoubleNode current = first;
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

	// function that removes a specific doublenode in the linked list due to it's
	// object
	public boolean remove(Object o) {
		if (count == 0) {
			return false;
		} else {
			if (first.location.equals(o)) {
				return removeFirst();
			} else {
				DoubleNode current = first;
				for (int i = 1; i < count; i++) {
					current = current.next;
					if (current.location.equals(o)) {
						current.previous.next = current.next;
						current.next.previous = current.previous;
						count--;
						return true;
					}
				}
				return false;
			}
		}
	}

	// function that searches for a specific doublenode due to it's location
	public boolean search(String l) {
		if (count == 0) {
			return false;
		} else {
			DoubleNode current = first;
			while ((current != null) && (!(current.getLocation().equals(l)))) {
				current = current.next;
			}
			if (current != null) {
				return true;
			}

		}
		return false;

	}

	// function that returns a specific doublenode due to it's martyr object
	public DoubleNode getNode(Object o) {
		if (count == 0) {
			return null;
		} else {
			DoubleNode current = first;
			while ((current != null) && (!(current.location.equals(o)))) {
				current = current.next;
			}
			if (current != null) {
				return current;
			}

		}
		return null;

	}

	// function to update the location
	public void update(DoubleNode old, String neww) {
		old.setLocation(neww);
	}

	// function to print the double linkedlist
	public void PrintList() {
		if (count > 0) {
			DoubleNode current = first;
			while (current != null) {
				System.out.println(current.location);
				current = current.next;
			}

		} else {
			System.out.println("The list is empty!!");
		}
	}

	// function to find a martyr by his name in all locations
	public String searchForMartyr(String s) {
		if (count == 0) {
			return "The martyr record you are searching for does not exist";
		} else {
			DoubleNode current = first;
			while ((current != null)) {
				if (current.linkedList.search(s)) {
					return current.linkedList.getNode(s).martyr.toString() + "Martyr Event location: "
							+ current.location;
				}
				current = current.next;
			}
			return "The martyr record you are searching for does not exist";
		}
	}

	// function that sort the double linhedlist
	public void sortList() {
		if (first == null) {
			return;
		} else {

			DoubleNode current = first;
			while (current.next != null) {
				DoubleNode index = current.next;
				while (index != null) {
					if (current.location.compareTo(index.location) > 0) {
						String temp = current.location;
						current.location = index.location;
						index.location = temp;
					}
					index = index.next;
				}
				current = current.next;
			}
		}
	}

	// function that sort the sigle linked list in the double one
	public void singleSort() {
		DoubleNode curNode = first;
		while (curNode != null) {
			curNode.linkedList.insertionSort(curNode.linkedList.getfirst());
			curNode = curNode.next;
		}

	}
}
