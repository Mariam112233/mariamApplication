public class TypeConversions{
    public static void main(String[] args){
        float f = 5.0f;
        long newF = (long) f;
        System.out.println("Cast float to long = " + newF);
        System.out.println("Cast double  to int = " + (int) 123.345);
        System.out.println("Cast int to float = " + (float) 100);
         
        float n1 = 10.12f;
         System.out.println("n1 is float = " + n1);
         System.out.println("Cast float to double = " + (double) n1);
         
        double n2 = 10.12f;
         System.out.println("n2 is double = " + n1);
         System.out.println("Cast double to float = " + (float) n2); 

    }
}