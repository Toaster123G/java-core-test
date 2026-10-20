package features;

import modules.HomePage;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Utils {
    public static void ClearConsole(){
        String os = System.getProperty("os.name").toLowerCase();

        try {
            ProcessBuilder osProcess;

            if(os.contains("win")){
                osProcess = new ProcessBuilder("cmd", "cls");
            } else {
                osProcess = new ProcessBuilder("clear");
            }

            osProcess.inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("ERROR: clear script error");
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
                System.out.println("[WRONG OUTPUT, TRY AGAIN]\n");
                System.out.println("0 - go back\n");
            }

            ClearConsole();
        }while (convertedData != 0);

        ClearConsole();
        HomePage.Home();
    }
}
