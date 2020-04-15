import applicationFacade.ApplicationFacade;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Testing? [y/n]");
        if ("y".equals(scanner.nextLine())) {
            ApplicationFacade.startTestApplication(args);
        } else {
            ApplicationFacade.startConsoleApplication(args);
        }
    }

}

