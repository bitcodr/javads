public class main {

    public static void main(String[] args){

        int n = 12345;

        int x = 0;

        while (n > 0){
            int remainder = n % 10;
            x *= 10;
            x += remainder;
            n /= 10;
        }

        System.out.println(x);

    }
}
