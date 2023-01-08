public class ListNode<AnyType> {
    public AnyType element;
    public ListNode<AnyType> next;

    public ListNode(AnyType theElement, ListNode<AnyType> n) {
        element = theElement;
        next = n;
    }

    public ListNode(AnyType theElement) {
        this(theElement,null);
    }
}
