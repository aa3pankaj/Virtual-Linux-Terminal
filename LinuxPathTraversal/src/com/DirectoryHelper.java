package com;

import java.util.List;
/**
* DirectoryHepler to implement basic Linux commands
*
* @author  Pankaj Singh
* @version 1.0 
*/
public class DirectoryHelper {

	public DirectoryHelper() {

	}

	/**
	 * Returns list of children of current directory
	 * 
	 * @param currentNode
	 *            current working directory
	 * @return List of nodes of children for the working directory
	 */

	public static List<Node<String>> ls(Node<String> currentNode) {

		return currentNode.getChildren();
	}

	/**
	 * Adds children to the current node
	 * 
	 * @param currentNode
	 *            current working directory
	 * @param dir
	 *            Directory to be added in the current working node
	 * @param index
	 *            index of new directory
	 * @return Current working directory node
	 */

	public static Node<String> mkdir(String dir[], Node<String> currentNode,
			int index) {

		if (index >= dir.length)
			return currentNode;
        Node<String> child=currentNode.getChildren(dir[index]);
        
		if(child!=null){
			
			mkdir(dir, child, index + 1);
		}
		else
		{
			Node<String> child1 = new Node<String>(dir[index]);
			currentNode.addChild(child1);

			mkdir(dir, child1, index + 1);

		}
		
		return currentNode;

	}

	/**
	 * Removes children from the current node
	 * 
	 * @param currentNode
	 *            current working directory
	 * @param dir
	 *            Directory to be removed from the current working node
	 * @return Current working directory node
	 */
	public static Node<String> rm(Node<String> currentNode, String dir[]) {

		Boolean flag = false;
		Node<String> tempCurrentNode = currentNode;
		for (int i = 0; i < dir.length; i++) {
			Node<String> node = tempCurrentNode.getChildren(dir[i]);

			if (node == null) {
				flag = true;

				System.out.println("ERR: INVALID PATH");
				break;
			}

			tempCurrentNode = node;
		}

		if (flag == false) {
			tempCurrentNode = currentNode;
			for (int i = 0; i < dir.length; i++) {

				tempCurrentNode.removeChild(dir[i]);

			}

			System.out.println("SUCC: DELETED");

		}

		return currentNode;

	}

	/**
	 * Changes current working directory
	 * 
	 * @param currentNode
	 *            current working directory
	 * @param dir
	 *            New Working directory
	 * @return Current working directory node after change
	 */

	public static Node<String> cd(String dir[], Node<String> currentNode) {
		Boolean flag = false;
		Node<String> tempCurrentNode = currentNode;

		// check if directory exists or not
		for (int i = 0; i < dir.length; i++) {
			Node<String> node = tempCurrentNode.getChildren(dir[i]);

			if (node == null) {
				flag = true;

				System.out.println("ERR: INVALID PATH");
				break;
			}

			tempCurrentNode = node;
		}
		// change current directory if new directory exists
		if (flag == false) {

			for (int i = 0; i < dir.length; i++) {

				Node<String> node = currentNode.getChildren(dir[i]);

				currentNode = node;

			}

			System.out.println("SUCC: REACHED");

		}

		return currentNode;

	}

	/**
	 * Returns current working directory
	 * 
	 * @param currentNode
	 *            current node
	 * @return Current Working directory
	 */

	public static String pwd(Node<String> currentNode, String str) {

		if (currentNode.getParent() == null)
			return str;

		if (currentNode.getParent() != null)
			str = "/" + currentNode.getData() + str;
		return pwd(currentNode.getParent(), str);

	}

}
