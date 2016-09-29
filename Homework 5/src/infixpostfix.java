import java.util.*;
public class infixpostfix {

	private int count=0;
 
	private static Scanner scan;
 
	public static void main(String []args) {
   
		System.out.println("Enter string to convert to Postfix Expression :");
		scan = new Scanner(System.in);
		String infix = scan.nextLine();
		infix = infix.replaceAll("\\s","");
		infixpostfix obj = new infixpostfix();
		obj.convert(infix);
 
	}
	
	public void convert(String infix) {
		
		Stack<String> stack = new Stack<String>();
		Queue<String> queue = new LinkedList<String>();
		Queue<String> queue1 = new LinkedList<String>();
		infixpostfix obj = new infixpostfix();
		while(obj.count < infix.length()) {
       
			String token= obj.gettoken(obj,infix);
			if(obj.isoperand(token)) {
          
				queue.add(token);
       
			}
       
			else if(obj.isoperator(token)) {
          
				if(stack.isEmpty()) {
             
					stack.push(token);
          
				}
          
				else {
             
					while(!stack.isEmpty()&&(precedence((String)stack.peek())>=precedence(token))) {
                
						String mystring = (String) stack.peek();
						stack.pop();
						queue.add(mystring);
             
					}
             
					stack.push(token);
          
				}
			}
       
			else if(token.equals(")")) {
           
				while((String)stack.peek()!="(")	{
               
					String mystr =(String)stack.peek();
					stack.pop();
					queue.add(mystr);
           
				}
           
				stack.pop();
        
			}
        
			else {
           
				stack.push(token);
		
			}
		}
     
		while(!stack.empty()) {
        
			queue.add((String)stack.peek());
			stack.pop();
     
		}
     
		while(!queue.isEmpty()) {
        
			System.out.print(queue.peek()+" ");
			queue1.add(queue.peek());
			queue.remove();
    
		}
     
		boolean result = obj.evaluate(queue1);
     
		if(result)	{
        
			System.out.println("\nTrue");
		}
		
        else {
        
        	System.out.println("\nFalse");    
        }
	}
 
		public boolean evaluate(Queue<String> queue1) {
    
			int count;
			String temp[] = new String[1000];
			int valid[] = new int[1000];
			int i=0,j,k;
			int size = queue1.size();
			count=size;
    
			for(i=0;i<size;i++) {
       
				temp[i]=queue1.element();
				valid[i]=1;
				queue1.remove();
    
			}
    
			int precede;
    
			int no1=0 ,no2=0,result=0;
    
			String myresult = null;
    
			while(count>1) {
       
				for(i=0;i<size;i++) {
         
					if(isoperator(temp[i])&&valid[i]==1&&temp[i].length()==1) {
            
						precede = precedence(temp[i]);
             
						if(precede == 5) {
                
							for(j=i-1;j>=0;j--) {
                   
								if(valid[j]==1) {
                      
									no2 = Integer.parseInt(temp[j]);
                      
									break;
                   
								}
							}
                
							for(k=j-1;k>=0;k--) {
                   
								if(valid[k]==1) {
                      
									no1 = Integer.parseInt(temp[k]);
                      
									break;
                   
								}
							}
                
							valid[j]=0;
							valid[k]=0;
							valid[i]=1;
							count = count -2;
                
							if(temp[i].equals(">")) {
                   
								if(no1>no2){
                      
									myresult = "true";
                   
								}
                   
								else {
                      
									myresult = "false";
                   
								}
							}
               
							else if(temp[i].equals("<")) {
								
								if(no1<no2){
                      
									myresult = "true";
                  
								}
                   
								else {
                      
									myresult = "false";
                   
								}
							}
                
							temp[i]=myresult; 
            
						}  
            
						else {
               
							for(j=i-1;j>=0;j--) {
                  
								if(valid[j]==1) { 
                     
									no2 = Integer.parseInt(temp[j]);
									break;
                  
								}
							}
               
							for(k=j-1;k>=0;k--) {
                  
								if(valid[k]==1) {
                     
									no1 = Integer.parseInt(temp[k]); 
									break;
                  
								} 
							}
               
							if(precede == 10) {
                  
								valid[j]=0;      
								valid[k]=0;   
								valid[i]=1;
								count = count - 2 ;
								result = no1 % no2;
               }
               else if(precede == 9)
               {
                  valid[j]=0;
                  valid[k]=0;
                  valid[i]=1;
                  count = count -2;
                  if(temp[i].equals("/"))
                     result = no1 / no2;
                  if(temp[i].equals("*"))
                     result = no1 * no2;
               }
               else if(precede == 7)
               {
                  valid[j]=0;
                  valid[k]=0;
                  valid[i]=1;
                  count = count -2;
                  
                  if(temp[i].equals("+"))
                     
                	  result = no1 + no2;
                  
                  if(temp[i].equals("-"))
                     
                	  result = no1 - no2;
               
               }
               
							temp[i]= Integer.toString(result);
            
						}     
					}
       
					else if (isoperator(temp[i])&&valid[i]==1&&temp[i].length()==2) {
           
						precede = precedence(temp[i]); 
						
						if(precede == 5)	{ 
              
							for(j=i-1;j>=0;j--){
                 
								if(valid[j]==1){
                    
									no2 = Integer.parseInt(temp[j]);
									break;
                 
								}
							}
              
							for(k=j-1;k>=0;k--) {
                 
								if(valid[k]==1) {
                    
									no1 = Integer.parseInt(temp[k]);
									break;
                
								}
							}
              
							valid[j]=0;
							valid[k]=0;
							valid[i]=1;
							count = count -2;
              
							if(temp[i].equals(">=")) {
                 
								if(no1>=no2) {
                    
									myresult = "true";
                 
								}
                 
								else {
                    
									myresult = "false";
                
								}
							}
              
							else if(temp[i].equals("<=")) {
                 
								if(no1<=no2) {
                    
									myresult = "true";
             
								}
                 
								else {
                    
									myresult = "false";
                 
								}
							}
              
							temp[i]=myresult;
          
						}
           
						else if(precede == 4) {
              
							for(j=i-1;j>=0;j--) {
                 
								if(valid[j]==1) {
                    
									no2 = Integer.parseInt(temp[j]);
									break;
                 
								}
							}
              
							for(k=j-1;k>=0;k--) {
                 
								if(valid[k]==1) {
                    
									no1 = Integer.parseInt(temp[k]);
									break;
                 
								}
							}
             
							valid[j]=0;  
							valid[k]=0;   
							valid[i]=1;
							count = count -2;
              
							if(temp[i].equals("==")) {
                 
								if(no1==no2) {
                    
									myresult = "true";
                 
								}
                 
								else {
                    
									myresult = "false";
                 
								}
							}
              
							else if(temp[i].equals("!=")) {
                 
								if(no1!=no2) {
                    
									myresult = "true";
                
								}
                 
								else {
                    
									myresult = "false";
                 
								}
							}
              
							temp[i]=myresult;
          
						}
           
						else if(precede == 3) {
              
							String value1 = new String();
							String value2 = new String();
              
							for(j=i-1;j>=0;j--) {
                 
								if(valid[j]==1) {
                    
									value2 = temp[j];
									break;
                 
								}
							}
              
							for(k=j-1;k>=0;k--) {
                 
								if(valid[k]==1) {
                    
									value1 = temp[k];
									break;
                 
								}
							}
              
							valid[j]=0;  
							valid[k]=0;    
							valid[i]=1;
							count = count -2;
              
							if(value1.equals(value2) && value1.equals("true")) {
                 
								myresult = "true";
              
							}
             
							else {
                 
								myresult = "false";
              
							}
              
							temp[i]=myresult;
           
						}
           
						else if(precede == 2) {
              
							String value1 = new String();
							String value2 = new String();
							
							for(j=i-1;j>=0;j--) {
                 
								if(valid[j]==1) {
                   
									value2 = temp[j];
									break;
								}
							}
              
							for(k=j-1;k>=0;k--) {
                 
								if(valid[k]==1) {
									
									value1 = temp[k];
									break;
								}
							}
              
							valid[j]=0;
							valid[k]=0;
							valid[i]=1;
							count = count -2;
              
							if(value1.equals("true") || value2.equals("true")) {
               
								myresult = "true";
             
							}
              
							else {
                
								myresult = "false";
              
							}
              
							temp[i]=myresult;
           
						}
					}
				}
			}
  
			if(temp[size-1]=="true")
    
				return true;
  
			else 
    
				return false;
 
		}
 
		public int precedence(String token) {
    
			if(token=="%") {
       
				return 10;
    
			}
    
			if(token=="/") {
       
				return 9;
    
			}
    
			if(token=="*") {
       
				return 9;
    
			}
    
			if(token=="+") {
       
				return 7;
    
			}
    
			if(token=="-") {
       
				return 7;
    
			}
    
			if(token==">=" || token=="<=" || token=="<" || token==">" ) {
       
				return 5;
    
			}
    
			if(token=="!="|| token=="==" ) {
       
				return 4;
    
			}
   
			if(token=="&&") {
       
				return 3;
   
			} 
			
			if(token=="||") {
       
				return 2;

			}
    
			return 0;
 
		}
 
		public boolean isoperand(String token) {
    
			int i=0;
			char t[] = new char[100];
			t = token.toCharArray();
    
			for(i=0;i<token.length();i++) {
       
				if((t[i]>=65 &&t[i]<=90)||(t[i]>=97&&t[i]<=122)||t[i]==':'||((t[i]>=48)&&(t[i]<=57))) {
          
					continue;
       
				}
       
				else
          
					return false;
    
			}
    
			return true;
 
		}
 
		public boolean isoperator(String token) {

			if((token.equals("+"))||(token.equals("%"))||(token.equals(">"))||
					(token.equals("<"))||(token.equals("-"))||(token.equals("*"))||
					(token.equals("/"))||(token.equals("=="))||(token.equals(">="))||
					(token.equals("<="))||(token.equals("!="))||(token.equals("&&"))||
					(token.equals("||")))
				
				return true;
 
			else
				
				return false;
 
		}

		public String gettoken(infixpostfix obj,String infix) {
    
			int i=obj.count;
			int j,k;
			char str[] = new char[100];
			str = infix.toCharArray();
    
			if((str[i]>=65 &&str[i]<=90)||(str[i]>=97&&str[i]<=122)||((str[i]>=48)&&(str[i]<=57))) {
       
				for(j=i;j<infix.length();j++) {
          
					if((str[j]>=65 &&str[j]<=90)||(str[j]>=97&&str[j]<=122)||str[j]==':'||((str[j]>=48)&&(str[j]<=57))) {
             
						continue;
          
					}
         
					else {
             
						break;
          
					} 
       
				}
       
				obj.count=j;
				char str1[] = new char[j-i];
       
				for(k=i;k<j;k++) {
          
					str1[k-i]=str[k];
       
				}
       
				String mystring = new String(str1);
				return mystring;
    
			}
    
			else if(str[i]=='(') {
       
				obj.count++;
				return "(";
    
			}
    
			else if(str[i]==')') {
       
				obj.count++;
				return ")";
    
			}
    
			else if(str[i]=='+') {
       
				obj.count++;
				return "+";
    
			}
    
			else if(str[i]=='-') {
				
				obj.count++;
				return "-";
    
			}
    
			else if(str[i]=='*') {
       
				obj.count = obj.count +1;
				return "*";
    
			}
    
			else if(str[i]=='%') {
       
				obj.count = obj.count +1;
				return "%";
    
			}
   
			else if(str[i]=='/') {
       
				obj.count = obj.count +1;
				return "/";
    
			}
    
			else if(str[i]=='=' && str[i+1]=='=') {
       
				obj.count = obj.count +2;
				return "==";
    
			}
    
			else if(str[i]=='&' && str[i+1]=='&') {
       
				obj.count = obj.count +2;
				return "&&";
    
			}
   
			else if(str[i]=='|' && str[i+1]=='|') {
       
				obj.count = obj.count +2;
				return "||";
   
			}
   
			else if(str[i]=='>' && str[i+1]=='=') {
      
				obj.count = obj.count +2;
				return ">=";
    
			}
    
			else if(str[i]=='!' && str[i+1]=='=') {
       
				obj.count = obj.count +2;
				return "!=";
    
			}
    
			else if(str[i]=='<' && str[i+1]=='=') {
       
				obj.count = obj.count +2;
				return "<=";
			
			}
    
			else if(str[i]=='>') {
      
				obj.count++;
				return ">";
   
			}
    
			else if(str[i]=='<') {
       
				obj.count++; 
				return "<";
    
			}
   
			return null;
  
		}
}