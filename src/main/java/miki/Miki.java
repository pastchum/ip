package miki;

import java.util.Scanner;

public class Miki {
    private static String logo = "      __  _________ __ ____\n" + //
            "     /  |/  /  _/ //_//  _/\n" + //
            "    / /|_/ // // ,<   / /  \n" + //
            "   / /  / // // /| |_/ /   \n" + //
            "  /_/  /_/___/_/ |_/___/   "; //

    public static void main(String[] args) {
        System.out.println("____________________________________________________________\n" + //
                "Hello from \n" + logo + "\n" + "Your local ChatBot assistant.");

        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            System.out.println("____________________________________________________________");
            if (input.toLowerCase().contains("bye")) {
                System.out.println("Terminating Session. Goodbye.\n");
                System.out.println("____________________________________________________________\n");
                break;
            }
            System.out.println(input);

            System.out.println("____________________________________________________________\n");
        }
    }
}
