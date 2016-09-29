//*******************************************
// CIS 265 Fall, 2015 ‐  Section 50     
// Homework #6 – Postfix Tree
// NAME: Mario Muscarella                      
// CSU ID: 2478702       
// DATE & TIME: 12/2 3PM           
//**************************************
import java.io.*;
import java.util.*;

class PostfixTree{
	private AbcNode root;			//first node of tree
	private Stack<AbcNode> charStack;
	
	public PostfixTree(String initString)
		{ 
			//read initString and generate a tree to 
			//represent the algebraic expression
			charStack = new Stack<AbcNode>();
			
			for(int i = 0; i < initString.length(); i++)
			{
				if(initString.charAt(i) == '+'
						|| initString.charAt(i) == '-'
						|| initString.charAt(i) == '*'
						|| initString.charAt(i) == '/')
				{
					AbcNode operatorTemp = new AbcNode();
					operatorTemp.sData = Character.toString(initString.charAt(i));
					operatorTemp.rightChild = charStack.pop();
					operatorTemp.leftChild = charStack.pop();
					charStack.push(operatorTemp);
				}
				else
				{
					AbcNode operandTemp = new AbcNode();
					operandTemp.sData = Character.toString(initString.charAt(i));
					charStack.push(operandTemp);
				}
			}
			
			//pop the root of the completed tree into Postfixtree.root
			root = charStack.pop();
			
		}
	
	public void traverse(int traverseType)
	{
		switch(traverseType)
		{
		case 1: System.out.print("\nPreorder traversal: ");
				preOrder(root);
				break;
		case 2: System.out.print("\nInorder traversal: ");
				inOrder(root);
				break;
		case 3: System.out.print("\nPostorder traversal: ");
				postOrder(root);
				break;
		}
		System.out.println("");
	}
	
	private void preOrder(AbcNode localRoot)
	{
		if(localRoot != null)
		{
			System.out.print(localRoot.sData + " ");
			preOrder(localRoot.leftChild);
			preOrder(localRoot.rightChild);
		}
	}
	
	private void inOrder(AbcNode localRoot)
	{
		if(localRoot != null)
		{
			System.out.print("(");
			inOrder(localRoot.leftChild);
			System.out.print(localRoot.sData + " ");
			inOrder(localRoot.rightChild);
			System.out.print(")");
		}
	}
	
	private void postOrder(AbcNode localRoot)
	{
		if(localRoot != null)
		{
			postOrder(localRoot.leftChild);
			postOrder(localRoot.rightChild);
			System.out.print(localRoot.sData + " ");
		}
	}
	
	public void displayTree()
	{
		Stack<AbcNode> globalStack = new Stack<AbcNode>();
		globalStack.push(root);
		int nBlanks = 32;
		boolean isRowEmpty = false;
		System.out.println(
		".......................................................");
		while(isRowEmpty==false)
		{
			Stack<AbcNode> localStack = new Stack<AbcNode>();
			isRowEmpty = true;
			
			for(int j = 0; j < nBlanks; j++)
				System.out.print(" ");
			
			while(globalStack.isEmpty() == false)
			{
				AbcNode temp = globalStack.pop();
				if(temp != null)
				{
					System.out.print(temp.sData);
					localStack.push(temp.leftChild);
					localStack.push(temp.rightChild);
					
					if(temp.leftChild != null ||
							temp.rightChild != null)
						isRowEmpty = false;
				}
				else
				{
					System.out.print("--");
					localStack.push(null);
					localStack.push(null);
				}
				for(int j = 0; j < nBlanks*2 - 2; j++)
					System.out.print(" ");
			} //end while globalStack not empty
			System.out.println();
			nBlanks /= 2;
			while(localStack.isEmpty() == false)
				globalStack.push( localStack.pop() );
		} //end while isRowEmpty is false
		System.out.println(
		".......................................................");
	} //end displayTree()
} //end class AbcTree

class PostfixTreeApp
{
	public static void main(String[] args) throws IOException
	{
		int value;
		String expression;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter expression: ");
        expression = br.readLine();
		String initString = expression;
		AbcTree theTree = new AbcTree(initString);
		
		while(true)
		{
			System.out.print("Enter first letter of show"
					+ " or traverse: ");
			int choice = getChar();
			switch(choice)
			{
			case 's':
				theTree.displayTree();
				break;
			case 't':
				System.out.print("Enter type 1 for Preorder, 2 for Inorder, or 3 for Post Order: ");
				value = getInt();
				theTree.traverse(value);
				break;
			default:
				System.out.print("Invalid entry!\n");
			} // end switch
		} // end while
	} // end main()
	
	public static String getString() throws IOException
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
	
	public static char getChar() throws IOException
	{
		String s = getString();
		return s.charAt(0);
	}
	
	public static int getInt() throws IOException
	{
		String s = getString();
		return Integer.parseInt(s);
	}
}