package ATM;
/**
 * Represents a Queue data structure.
 */
public class Queue {
	private Transaction[] arr;
	private int end_pt, start_pt;
	public Queue() {
		//Constructs an empty Queue with a default capacity of 20.
		arr = new Transaction[20];
	}
	public boolean empty() {
		//Checks if the Queue is empty.
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != null) {
				return false;
			}
		}
		return true;
	}
	public void enqueue(Transaction t) {
		//Enqueues a transaction to the end of the Queue.
		if (empty()) {
			arr[0] = t;
			end_pt = start_pt = 0;
		} else {
			if (start_pt == 0 && end_pt == arr.length - 1) {
				// If both start and end pointers are at the boundaries,
				// create a new array with increased capacity
				Transaction[] new_arr = new Transaction[arr.length + 10];
				for (int i = 0; i < arr.length; i++) {
					new_arr[i] = arr[i];
				}
				new_arr[arr.length] = t;
				end_pt = arr.length;
				start_pt = 0;
				arr = new_arr;
			} else if (end_pt == start_pt - 1) {
				// If the end pointer is one position before the start pointer,
				// create a new array with increased capacity and shift elements accordingly
				Transaction[] new_arr = new Transaction[arr.length + 10];
				for (int i = start_pt; i < arr.length; i++) {
					new_arr[i - start_pt] = arr[start_pt];
				}
				for (int i = 0; i < start_pt; i++) {
					new_arr[arr.length - start_pt + i] = arr[i];
				}
				new_arr[arr.length] = t;
				start_pt = 0;
				end_pt = arr.length;
				arr = new_arr;
			} else if (end_pt == arr.length - 1) {
				// If the end pointer is at the boundary, wrap around and enqueue the transaction at the start
				arr[0] = t;
				end_pt = 0;
			} else {
				// Normal case: increment the end pointer and enqueue the transaction
				arr[end_pt + 1] = t;
				end_pt++;
			}
		}
	}
	public Transaction dequeue() {
		//Dequeues a transaction from the start of the Queue.
		if (start_pt == arr.length - 1) {
			if (arr[0] == null) {
				// If the start pointer is at the boundary and the next element is null,
				// dequeue the transaction and reset the pointers
				Transaction t = arr[start_pt];
				arr[start_pt] = null;
				start_pt = end_pt = -1;
				return t;
			} else {
				// If the start pointer is at the boundary and the next element is not null,
				// dequeue the transaction and wrap around to the start
				Transaction t = arr[start_pt];
				arr[start_pt] = null;
				start_pt = 0;
				return t;
			}
		} else {
			// Normal case: increment the start pointer and dequeue the transaction
			Transaction t = arr[start_pt];
			arr[start_pt] = null;
			start_pt++;
			return t;
		}
	}
}

