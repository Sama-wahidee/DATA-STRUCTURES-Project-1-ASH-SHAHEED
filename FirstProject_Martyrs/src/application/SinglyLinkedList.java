package application;

public class SinglyLinkedList {
	SingelNode first;
	SingelNode last;
	int count;

	public SinglyLinkedList() {

	}

	public Object getfirst() {
		if (count != 0) {
			return first.element;
		}
		return null;

	}

	public Object getLast() {
		if (count != 0) {
			return last.element;
		}
		return null;

	}

	public void addFirst(Object o) {
		if (count == 0) {
			first = last = new SingelNode(o);
		} else {
			SingelNode temp = new SingelNode(o);
			temp.next = first;
			first = temp;
		}
		count++;
	}

	public void addLast(Object o) {
		if (count == 0) {
			first = last = new SingelNode(o);
		} else {
			SingelNode temp = new SingelNode(o);
			last.next = temp;
			last = temp;
		}
		count++;
	}

	public void add(Object o, int index) {
		if (index == 0) {
			addFirst(o);
		} else {
			if (index >= count) {
				addLast(o);
			} else {
				SingelNode temp = new SingelNode(o);
				SingelNode current = first;
				for (int i = 0; i < index - 1; i++) {
					current = current.next;
				}
				temp.next = current.next;
				current.next = temp;
				count++;
			}
		}
	}

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

	public boolean removeLast() {
		if (count == 0) {
			return false;
		} else {
			if (count == 1) {
				first = last = null;
			} else {
				SingelNode current = first;
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
				SingelNode current = first;
				for (int i = 0; i < index - 1; i++) {
					current = current.next;
				}
				current.next = (current.next).next;
				count--;
				return true;
			}
		}
	}

	public boolean remove(Object o) {
		if (count == 0) {
			return false;
		} else {
			if (first.element.equals(o)) {
				return removeFirst();
			} else {
				SingelNode previous = first;
				SingelNode current = first.next;
				while (current != null && !current.element.equals(o)) {
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

	public void PrintList() {
		if (count > 0) {
			SingelNode current = first;
			while (current != null) {
				System.out.println(current.element);
				current = current.next;
			}
		}
	}

	public SingelNode getNode(Object o) {
		if (count == 0) {
			return null;
		} else {
			SingelNode current = first;
			while ((current != null) && (!current.element.equals(o))) {
				current = current.next;
			}
			if (current != null) {
				return current;
			}

		}
		return null;

	}

	public SingelNode getNode(int index) {
		if (count == 0) {
			return null;
		} else {
			SingelNode current = first;
			int count = 0;
			while (current != null) {
				if (count == index) {
					return current;
				}
				count++;
				current = current.next;
			}
		}

		return null;
	}

	public Object searchList(Object o) {
		if (count > 0) {
			SingelNode current = first;
			while ((current != null) && (!current.element.equals(o))) {
				current = current.next;
			}

			return current.element;
		}

		else {
			return null;
		}

	}

}
