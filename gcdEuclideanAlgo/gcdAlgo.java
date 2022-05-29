import java.util.*;  

/*
Euclid's algorithm to compute greatest common divisor 
of two non-negative numbers.
*/

public class gcdAlgo {
    public static void main (String[] args) {
        Scanner sc= new Scanner(System.in);  
        System.out.print("Enter first number- ");  
        int p = sc.nextInt();  
        System.out.print("Enter second number- ");  
        int q = sc.nextInt();  

        System.out.printf("Gcd using Eclidean algotithm is: %d\n", gcd(p, q));  
        

    }

    public static int gcd (int p, int q){
        if(q == 0) return p;{
            int r = p % q;
            return gcd(q, r);
        }

    }
}