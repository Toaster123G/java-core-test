package features;

import modules.HomePage;

import java.io.IOException;
import java.util.Scanner;

public class Utils {
    public static void ClearConsole(){
        try {
            new ProcessBuilder("cmd", "/c", "cls")
                    .inheritIO()
                    .start()
                    .waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("ERROR: clear script is corrupted");
        }
    }

//    --- IF SOME COMPONENT IN DEVELOPING PROCESS, USE THIS CLASS ---
    public static void SeeSoon(){
        Scanner inputData = new Scanner(System.in);


        int convertedData = -1;

        do{
            String convLine = inputData.nextLine().trim();

            if(convLine.matches("\\d+")){
                convertedData = Integer.parseInt(convLine);
                System.out.println("[SUCCESS]\n");
            } else{
                System.out.println("[WRONG DATA, TRY AGAIN]\n");
                System.out.println("0 - go back\n");
            }

            ClearConsole();
        }while (convertedData != 0);

        ClearConsole();
        HomePage.Home();
    }
}
