/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mavenproject3;
import java.util.*;

public class UndoRedoStack<E> extends Stack<E> {
    private Stack undoStack;
    private Stack redoStack;

    // post: constructs an empty UndoRedoStack
    public UndoRedoStack() {
        undoStack = new Stack();
        redoStack = new Stack();
    }

    // post: pushes and returns the given value on top of the stack
    public E push(E value) {
        super.push(value);
        undoStack.push("push");
        redoStack.clear();
        return value;
    }

    // post: pops and returns the value at the top of the stack
    public E pop() {
        E value = super.pop();
        undoStack.push(value);
        undoStack.push("pop");
        redoStack.clear();
        return value;
    }

    // post: returns whether or not an undo can be done
    public boolean canUndo() {
        boolean result=false;
        if(undoStack.size()<1){
            System.out.println("--Unable to undo--");
            undoStack.push("push");
            //redoStack.push("");
          
            result=false;
        }else{
            result= true;
        }
        return result;
    }
    
    // pre : canUndo() (throws IllegalStateException if not)
    // post: undoes the last stack push or pop command
    public void undo() {
        if (!canUndo()) {
      
             E empty=(E)"";
            super.push(empty);
            
        }
        Object action = undoStack.pop();
        if (action.equals("push")) {
            E value = super.pop();
            redoStack.push(value);
            redoStack.push("push");
        } else {
            E value = (E) undoStack.pop();         
            super.push(value);
            redoStack.push("pop");
        }
    }

    // post: returns whether or not a redo can be done
    public boolean canRedo() {
        return !redoStack.isEmpty();
    }

    // pre : canRedo() (throws IllegalStateException if not)
    // post: redoes the last undone operation
    public void redo() {
        
        if (!canRedo()) {
            System.out.println("--Unable to redo--");
            Object action = undoStack.pop();       
            E value = super.pop();
            redoStack.push(value);
            redoStack.push("push");
           
        }
        Object action = redoStack.pop();
        if (action.equals("push")) {
            E value = (E) redoStack.pop();
            super.push(value);
            undoStack.push("push");
        } else {
            E value = super.pop();
            undoStack.push(value);
            undoStack.push("pop");
        }
    }
}