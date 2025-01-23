package miki;

import java.util.Scanner;

public class Miki {
    private static String logo = "      __  _________ __ ____\n" + //
            "     /  |/  /  _/ //_//  _/\n" + //
            "    / /|_/ // // ,<   / /  \n" + //
            "   / /  / // // /| |_/ /   \n" + //
            "  /_/  /_/___/_/ |_/___/   "; //

    private static String intro = "____________________________________________________________\n" + //
            "Hello from \n"
            + logo +
            "\n" +
            "Your ChatBot assistant. \n"
            +
            "\n"
            + "Please enter a command to start: \n";

    public static void main(String[] args) {
        System.out.println(intro);

        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            System.out.println("____________________________________________________________");
            if (input.toLowerCase().contains("bye")) {
                System.out.println("Terminating Session. Goodbye.\n");
                System.out.println("____________________________________________________________\n");
                break;
            }
            System.out.println(input + "\n");

            System.out.println("____________________________________________________________\n");
        }
        sc.close();
    }
}
