/*
A "text document" that allows you to perform actions such as Add, Delete, Undo, Redo by using
LinkedList stacks.
*/

import java.util.Scanner;

public class WordReader {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args){
        System.out.print("-- Welcome to your Text Document --");
        System.out.printf("%nAvailable Actions:%nAdd%nDelete%nUndo%nRedo%nprint%nquit%n");

        ListNode<String> stringStart = createString();

        // Undo and Redo stacks
        LinkedListStack<String> undoStack = new LinkedListStack<>();
        LinkedListStack<String> redoStack = new LinkedListStack<>();

        boolean quit_program = false;

        while (!quit_program){
            System.out.print("Enter an action > ");
            String userInput = input.nextLine(); // Expects a variation of "Add quickly", "delete", "undo"
            String[] myAction = userInput.split(" "); // Produces "{"Add", "quickly"} or {"delete"} or {"undo"}

            switch(myAction[0]){
                case "add":
                    // simple implementation to help prevent an IndexOutOfBounds exception
                    if (myAction.length != 2){
                        System.out.println("No word to add to string was provided.");
                        break;
                    }

                    // Make the redo stack empty
                    redoStack.makeEmpty();

                    String myWord = myAction[1];

                    // Push on to the undo stack, because you should be unable to undo an add
                    undoStack.push(userInput);

                    // Add the word to the end of the sentence
                    addToSentence(stringStart, myWord);
                    break;
                case "delete":
                    try{
                        // Make the redo stack empty
                        redoStack.makeEmpty();

                        // Remove and return the last word from the sentence
                        String deletedWord = removeFromSentence(stringStart);
                        System.out.println("Deleted word: " + deletedWord);
                        String fullCommand = "delete " + deletedWord; // "delete am"

                        // Push on to the undo stack to be able to "undo" the delete
                        undoStack.push(fullCommand);
                    }
                    catch (NullPointerException e){
                        System.out.println("Nothing to delete.");
                    }
                    break;
                case "undo":
                    try {
                        String undoAction = undoStack.topAndPop();
                        String[] undoActionSplit = undoAction.split(" ");

                        // Operations that will "undo" an add by removing from sentence
                        if (undoActionSplit[0].equals("add")){
                            removeFromSentence(stringStart);
                            redoStack.push(undoAction);
                        }
                        // Operations that will "undo" a delete by adding back to the sentence
                        else if (undoActionSplit[0].equals("delete")){
                            addToSentence(stringStart, undoActionSplit[1]);
                            redoStack.push(undoAction);
                        }
                    }
                    catch (Exception e){
                        System.out.println("No action to undo.");
                    }
                    break;
                case "redo":
                    try{
                        String redoAction = redoStack.topAndPop();
                        String[] redoActionSplit = redoAction.split(" ");

                        // Operations that will "redo" an add by adding to the sentence
                        if (redoActionSplit[0].equals("add")){
                            addToSentence(stringStart, redoActionSplit[1]);
                            undoStack.push(redoAction);
                        }
                        // Operations that will "redo" a delete by removing from sentence
                        else if (redoActionSplit[0].equals("delete")){
                            removeFromSentence(stringStart);
                            undoStack.push(redoAction);
                        }
                    }
                    catch (Exception e){
                        System.out.println("No action to redo.");
                    }
                    break;
                case "print":
                    printString(stringStart);
                    break;
                case "quit":
                    System.out.println("Ending program");
                    quit_program = true; // Will terminate the do-while loop
                    break;
                default:
                    System.out.println("Invalid action. Please try again.");
                    break;
            }
        }
    }

    // Adds a word to the end of the LinkedList sentence
    public static void addToSentence(ListNode<String> head, String word){
        ListNode<String> cursor = head;

        // Move cursor to the end of the Linked List
        while (cursor.next != null){
            cursor = cursor.next;
        }

        cursor.next = new ListNode<String>(word, null);
    }

    public static String removeFromSentence(ListNode<String> head){
        // Move cursor to the second to last node of the LinkedList
        ListNode<String> cursor = head;

        while (cursor.next.next != null){
            cursor = cursor.next;
        }
        ListNode<String> tmp = cursor.next;
        String returnValue = tmp.element;

        cursor.next = null;
        return returnValue;
    }

    // Takes a sentence from the user and creates a Linked List from it
    public static ListNode<String> createString(){
        ListNode<String> head = new ListNode<>(null, null);
        ListNode<String> cursor = head;

        System.out.print("Please enter a sentence: ");
        String mySentence = input.nextLine();
        String[] words = mySentence.split(" ");

        for (int i = 0; i < words.length; i++){
            cursor.next = new ListNode<>(words[i], null);
            cursor = cursor.next;
        }

        return head;
    }

    // Outputs the entire sentence that is held in the LinkedList
    public static void printString(ListNode<String> start){
        ListNode<String> cursor = start;

        while (cursor.next != null){
            ListNode<String> tmp = cursor.next;
            System.out.print(tmp.element + " ");
            cursor = cursor.next;
        }
        System.out.println();
    }
}
