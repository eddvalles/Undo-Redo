//**********************PUBLIC OPERATIONS******************************
// void push(x)         --> Insert x
// void pop()           --> Remove most recently inserted item
// AnyType top()        --> Return most recently inserted item
// AnyType topAndPop()  --> Return and remove most recent item
// boolean isEmpty()    --> Return true if empty; else false
// void makeEmpty()     --> Remove all items

public class LinkedListStack<AnyType> {
    public ListNode<AnyType> topOfStack;

    public LinkedListStack(){topOfStack = null;}

    // Check if the stack is logically empty
    public boolean isEmpty() {
        return topOfStack == null;
    }

    // Make the stack logically empty
    public void makeEmpty() {
        topOfStack = null;
    }

    // Insert a new item into the stack
    public void push(AnyType x) {
        topOfStack = new ListNode<AnyType>(x, topOfStack);
    }

    // Removes the top element of the stack
    public void pop() {
        if (isEmpty())
            throw new UnderflowException("ListStack pop");
        topOfStack = topOfStack.next;
    }

    // Gets the top element of the stack
    public AnyType top() {
        if (isEmpty())
            throw new UnderflowException("ListStack top");
        return topOfStack.element;
    }

    // Remove and return the top element of the stack
    public AnyType topAndPop() {
        if (isEmpty())
            throw new UnderflowException("ListStack topAndPop");

        AnyType topItem = topOfStack.element;
        topOfStack = topOfStack.next;
        return topItem;
    }
}
