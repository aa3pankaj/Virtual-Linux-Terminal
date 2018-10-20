package com;
import java.util.ArrayList;
import java.util.List;
/**
* Node class to implement tree of directory
* @author  Pankaj Singh
* @version 1.0
*/
public class Node<T> {
    private List<Node<T>> children = new ArrayList<Node<T>>();
    private Node<T> parent = null;
    private T data = null;
    
    
   public void removeChild(T data){   
	   this.children.remove(this.getChildren(data));
	   
   }
   public Node<T> getParent(){
	   return parent;
   }
    public Node(T data) {
        this.data = data;
    }

    public Node(T data, Node<T> parent) {
        this.data = data;
        this.parent = parent;
    }

    public List<Node<T>> getChildren() {
        return children;
    }
   
    public void setParent(Node<T> parent) {
        parent.addChild(this);
        this.parent = parent;
    }

    public void addChild(T data) {
        Node<T> child = new Node<T>(data);
        child.setParent(this);
        this.children.add(child);
    }
    public void setParentInternal(Node<T> parent){
    	 this.parent = parent;
    	
    }
    public void addChild(Node<T> child) {
        child.setParentInternal(this);
        this.children.add(child);
    }

    public T getData() {
        return this.data;
    }
    public void setData(T data) {
        this.data = data;
    }

    public boolean isRoot() {
        return (this.parent == null);
    }

    public boolean isLeaf() {
        return this.children.size() == 0;
    }

    public void removeParent() {
        this.parent = null;
    }
    
    public Node<T> getChildren(T child){
    	
    	for(int i=0;i<children.size();i++){
    		if(children.get(i).getData().equals(child)){
    			return children.get(i);
    		}
    		
    	}
    	
    
    return null;
    }
}
