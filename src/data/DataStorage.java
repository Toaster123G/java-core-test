package data;

import features.Utils;
import jdk.jshell.execution.Util;
import modules.HomePage;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public class DataStorage {
//     String[] localData = {"DO TO SLEEP AT 10PM"};
//    private Collection<String> getData = new ArrayList<String>(Arrays.asList(localData));
//    --- 14 & 15 strokes is wrong, why: ---
//    1. I don't combine default array and dynamic array to once dynamic array
//    2. I take data from default array to collection and drop theme after that
//    3. All data saving to the temporary memory, which clearing after I use
//    ClearConsole() & without static I get data from localData, save that
//    at Temporary Memory in JVM, but new output (doesn't matter what it is)
//    drop that data at the Collection
//    ---> static = saved data by program lifecycle
//    ---> ArrayList = concrete array

//     contain static array with collection and add some changings with data once
    private final static ArrayList<String> getData = new ArrayList<>();

    static {
        getData.add("Go to sleep at 10PM");
    }

    public void ShowData(){
        if(getData.isEmpty()){
            System.out.println("[NOTES "+ getData.toArray().length+"]\n");
            AddToData();
        }
        else {
            System.out.println("[NOTES "+ getData.toArray().length+"]\n");

//             !!! contained cycles = x2 duplicate
                for(int i = 0; i < getData.toArray().length; i++){
                    System.out.println("Task #"+i+" | "+getData.get(i)+"\n");
                }


            Scanner goBack = new Scanner(System.in);
            int goBackNum;

            do{
                String convLine = goBack.nextLine().trim();

                if(convLine.matches("\\d+")){
                    goBackNum = Integer.parseInt(convLine);
                    System.out.println("[SUCCESS]\n");
                } else{
                    System.out.println("[WRONG DATA, TRY AGAIN]\n");
                    System.out.println("0 - go back\n");
                }

                goBackNum = goBack.nextInt();

                System.out.println("[SUCCESS]\n");

                Utils.ClearConsole();
            }while (goBackNum != 0);

            HomePage.Home();
        }
    }

    public void AddToData(){
        Scanner inputData = new Scanner(System.in);
        System.out.println("[✧ WRITE TASK TITLE]\n");
        String convertedData = inputData.nextLine();

        getData.add(convertedData);

        System.out.println("[✔ DATA WRITED]\n");

        Scanner goBack = new Scanner(System.in);
        int goBackNum = -1;

        do{
            System.out.println("0 - go back\n");

            String convLine = goBack.nextLine().trim();

            if(convLine.matches("\\d+")){
                goBackNum = Integer.parseInt(convLine);
                System.out.println("[SUCCESS]\n");
            } else{
                System.out.println("[WRONG DATA, TRY AGAIN]\n");
                System.out.println("0 - go back\n");
            }


            Utils.ClearConsole();
        } while(goBackNum != 0);

        HomePage.Home();
    }

    public void DelTask(){
        Scanner taskID = new Scanner(System.in);
        System.out.println("[✶ WRITE NUMBER OF TASK]\n");
        int convertedTaskID;

       do{
           convertedTaskID = taskID.nextInt();

           getData.remove(convertedTaskID);

           System.out.println("[SUCCESS]\n");
       }while (convertedTaskID != 0);

        Utils.ClearConsole();
        HomePage.Home();
    }


    public void EditTask(){
        Scanner taskID = new Scanner(System.in);
        System.out.println("[✶ WRITE NUMBER OF TASK]\n");
        int convertedTaskID;

        do{
            convertedTaskID = taskID.nextInt();

            Scanner newTask = new Scanner(System.in);
            System.out.println("[✶ WRITE NEW TASK]\n");
            String convertedNewTask = newTask.nextLine();

            getData.add(convertedTaskID, convertedNewTask);

            System.out.println("[SUCCESS]\n");

            Utils.ClearConsole();

//            return algorithm
            Scanner goBack = new Scanner(System.in);
            int goBackNum = -1;

            do{
                String convLine = goBack.nextLine().trim();

                if(convLine.matches("\\d+")){
                    goBackNum = Integer.parseInt(convLine);
                    System.out.println("[SUCCESS]\n");
                } else{
                    System.out.println("[WRONG DATA, TRY AGAIN]\n");
                    System.out.println("0 - go back\n");
                }

                Utils.ClearConsole();
            }while (goBackNum != 0);

            HomePage.Home();
        }while (!taskID.hasNextInt());
    }

    public void SaveAsFile(){
//        --- SAVE-WINDOW MODULE ---
//        create save-window for data & set file name
        JFileChooser window = new JFileChooser(new File(System.getProperty("user.home"), "Downloads"));
        window.setDialogTitle("[TASK SAVE OPTION]");
        window.setSelectedFile(new File("SAVED_TASKS.txt"));

//        set canceled option var
        int isSaved = window.showSaveDialog(null);

//        scanning canceled operation execution
        if(isSaved != JFileChooser.APPROVE_OPTION){
            System.out.println("[SAVED CANCELED]\n");
        }

//        safe file path from lib to File object
        File tasks = window.getSelectedFile();

//        Write array data to lib path
        try(FileWriter selectedData = new FileWriter(tasks)){
            selectedData.write(getData.toString());

            Utils.ClearConsole();
            HomePage.Home();
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("ERROR: JFileChooser logic error\n");
        }

    }

    //    public void GoBackFunc(){
//        Scanner goBack = new Scanner(System.in);
//        int goBackNum;
//
//        do{
//            System.out.println("0 - go back\n");
//
//            goBackNum = goBack.nextInt();
//
//            System.out.println("[SUCCESS]\n");
//
//            Utils.ClearConsole();
//        }while (goBackNum != 0);
//
//        HomePage.Home();
//    }

}
