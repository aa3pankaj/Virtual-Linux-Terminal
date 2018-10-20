package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
* LinuxMain Driver Program to implement basic Linux commands
*
* @author  Pankaj Singh
* @version 1.0 
*/
public class LinuxMain {

	public static void main(String[] args) {
		System.out.println("APPLICATION STARTED...");

		List<String> commandList = new ArrayList<>();
		//Add valid commands
		commandList.add("cd");
		commandList.add("mkdir");
		commandList.add("rm");
		commandList.add("pwd");
		commandList.add("ls");
		commandList.add("session");
		commandList.add("exit");

		Scanner sc = new Scanner(System.in);
		String command = null;
		//root node
		Node<String> currentNode = new Node<String>("/");

		while (true) {
			System.out.println("");
			System.out.print("$>");
          try{
        	  command = sc.nextLine();
  			String commands[] = command.split(" ");
  			if (commandList.contains(commands[0])) {
  				if (commands[0].equals("exit")) {
  					sc.close();
  					break;
  				}
  				if (commands[0].equals("rm")) {
  					for (int i = 1; i < commands.length; i++) {
  						
  					
  						if (commands[i].startsWith("/")) 
  						{
  							String folders[] = commands[i].split("/");
  	  						DirectoryHelper.rm(currentNode, Arrays
  									.copyOfRange(folders, 1, folders.length));
  						}
  						else{
  							String folders[] = commands[i].split("/");
  	  						DirectoryHelper.rm(currentNode, folders);
  						}

  					}

  				}
  				if (commands[0].equals("mkdir")) {

  					for (int i = 1; i < commands.length; i++) {

  						if (commands[i].startsWith("/")) {
  							String folders[] = commands[i].split("/");
  							currentNode = DirectoryHelper.mkdir(Arrays
  									.copyOfRange(folders, 1, folders.length),
  									currentNode, 0);
  							System.out.println("SUCC: CREATED DIRECTORY IF NOT AVAILABLE:"
  									+ commands[i]);
  						} else {
  							String folders[] = commands[i].split("/");
  							currentNode = DirectoryHelper.mkdir(folders,
  									currentNode, 0);
  							System.out.println("SUCC: CREATED DIRECTORY IF NOT AVAILABLE:"
  									+ commands[i]);
  						}

  					}
  				}

  				if (commands[0].equals("cd")) {
  					String folders[] = null;
  					if (!commands[1].equals("")) {

  						if (commands[1].startsWith("/")) {
  							folders = commands[1].split("/");

  							currentNode = DirectoryHelper.cd(Arrays
  									.copyOfRange(folders, 1, folders.length),
  									currentNode);
  						} else {
  							folders = commands[1].split("/");

  							currentNode = DirectoryHelper.cd(folders,
  									currentNode);
  						}

  					} else {

  						throw new Exception("INVALID COMMAND FORMAT");

  					}
  				}

  				if (commands[0].equals("pwd")) {
  					if (currentNode.isRoot())
  						System.out.println("PATH: /");
  					else
  						System.out.println("PATH: "
  								+ DirectoryHelper.pwd(currentNode, ""));

  				}
  				if (commands[0].equals("ls")) {
  					List<Node<String>> children = null;
  					children = DirectoryHelper.ls(currentNode);
  					System.out.print("DIRS: ");
  					for(Node<String> node:children)
  					{
  						System.out.print(node.getData() + " ");

  					}
  					System.out.println("");

  				}
  				if (commands[0].equals("session")) {

  					if (commands[1].equals("clear")) {
  						currentNode = new Node<String>("/");
  						System.out.println("SUCC: CLEARED: RESET TO ROOT");
  					} else {

  						throw new Exception("INVALID COMMAND FORMAT");

  					}

  				}

  			} else {

  				throw new Exception("ERR: CANNOT RECOGNIZE INPUT");

  			} 
          }
          catch(Exception e){
        	  System.out.println(e.getMessage());
        	  
          }
			

		}
	}

}
