import java.util.HashSet;
import java.util.Set;
 
public class StringPermutation {
 
    public static void main(String[] args) {
        StringBuilder string = new StringBuilder("aaa");
        StringBuilder string2 = new StringBuilder("aab");
        StringBuilder string3 = new StringBuilder("abc");
        StringBuilder string4 = new StringBuilder("xyz");
        StringBuilder string5 = new StringBuilder("12345");
 
        Set<String> set = new HashSet<>();
 
        //permuting and printing first String
        perm(string, 0, string.length(), set);
        System.out.println(set);
        set.clear();
 
        //permuting and printing second String
        perm(string2, 0, string2.length(), set);
        System.out.println(set);
        set.clear();
 
        //permuting and printing third String
        perm(string3, 0, string3.length(), set);
        System.out.println(set);
        set.clear();
        
      //permuting and printing fourth String
        perm(string4, 0, string4.length(), set);
        System.out.println(set);
        set.clear();
        
      //permuting and printing fifth String
        perm(string5, 0, string5.length(), set);
        System.out.print(set);
        set.clear();
        
    }

    static void exchange(StringBuilder input, int i, int j) {
        String temp;
        temp = input.substring(i, i + 1);
        input.replace(i, i + 1, input.substring(j, j + 1));
        input.replace(j, j + 1, temp);
    }
 
    static void perm(StringBuilder input, int i, int length, Set<String> set) {
        if (i == length - 1) {
            set.add(input.toString());
        } else {
            for (int j = i; j < length; ++j) {
                exchange(input, i, j);
                perm(input, i + 1, length, set);
                exchange(input, i, j);
            }
        }
    }
}