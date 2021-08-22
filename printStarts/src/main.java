public class main {
    public static void main(String[] args) {

        int n = 4;
        int x = 1;
        String chr = "* ";

        while (n > 0) {

            System.out.printf("%" + n + "s", " ");

            for (int i = 0; i < x; i++) {
                System.out.print(chr);
            }

            System.out.println();

            x++;
            n--;
        }

    }
}
