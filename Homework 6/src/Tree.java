import java.io.*;
import java.util.*;

class AbcNode
{
	public String sData;			//data item (key)
	public AbcNode leftChild;
	public AbcNode rightChild;
	
	public void displayNode()
	{
		System.out.print("{" + sData + "}");
	}
}

class AbcTree
{
	private AbcNode root;			//first node of tree
	
	public AbcTree(String initString)
		{ 
			root = null;
			
			//Parse the input string into an array of single-letter nodes
			AbcNode[] nodeArray = new AbcNode[initString.length()];
			for(int i = 0; i < initString.length(); i++)
			{
				nodeArray[i] = new AbcNode();
				nodeArray[i].sData = String.valueOf(initString.charAt(i));
			}
			
			//Generate "+" nodes and add children iteratively
			AbcNode plusNode = new AbcNode();
			plusNode.sData = "+";
			plusNode.leftChild = nodeArray[0];
			plusNode.rightChild = nodeArray[1];
			
			for(int j = 2; j < nodeArray.length; j++)
			{
				AbcNode newNode = new AbcNode();
				newNode.sData = "+";
				newNode.leftChild = plusNode;
				newNode.rightChild = nodeArray[j];
				plusNode = newNode;
			}
			root = plusNode;
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
			inOrder(localRoot.leftChild);
			System.out.print(localRoot.sData + " ");
			inOrder(localRoot.rightChild);
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

class AbcTreeApp
{
	public static void main(String[] args) throws IOException
	{
		int value;
		String initString = "ABCDEFG";
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
				System.out.print("Enter type 1, 2, or 3: ");
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