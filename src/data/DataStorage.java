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
import java.util.*;

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

//    task status list
    enum status {
        TODO,
        IN_PROGRESS,
        DONE
    }


    static {
        getData.add("Go to sleep at 10PM");
    }

    public void ShowData(){
        if(getData.isEmpty()){
            System.out.println("[NOTES "+ getData.toArray().length+"]\n");
            AddToData();
        }
        else {

//             !!! contained cycles = x2 duplicate
                for(int i = 0; i < getData.size(); i++){
                    String currentElem = getData.get(i);
                    int oldIndex = getData.indexOf(currentElem);

                    if(i == getData.size() - 1){
                        System.out.println("TASK #"+i+" | "+currentElem+" | STATUS - "+status.TODO+"\n");
                    } else if(i != getData.size() - 1){
                        System.out.println("TASK #"+i+" | "+currentElem+" | STATUS - "+status.IN_PROGRESS+"\n");
                    }
//                    else if(getData.remove(Optional.of(i))){
//                        System.out.println("OLD TASK #"+oldIndex+" | STATUS - "+status.DONE+"\n");
//                    }
                }


            Scanner goBack = new Scanner(System.in);
            int goBackNum = -1;

            do{
                System.out.println("0 - go back\n");

                String convLine = goBack.nextLine().trim();

                if(convLine.matches("\\d+")){
                    goBackNum = Integer.parseInt(convLine);
                    System.out.println("[SUCCESS]\n");
                } else{
                    System.out.println("[WRONG OUTPUT, TRY AGAIN]\n");
                    System.out.println("0 - go back\n");
                }

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
                System.out.println("[WRONG OUTPUT, TRY AGAIN]\n");
                System.out.println("0 - go back\n");
            }


            Utils.ClearConsole();
        } while(goBackNum != 0);

        HomePage.Home();
    }

    public void DelTask(){
        Scanner taskID = new Scanner(System.in);
        System.out.println("[✶ WRITE NUMBER OF TASK]\n");
        int convertedTaskID = -1;


       do{
           System.out.println("0 - go back\n");

           String convLine = taskID.nextLine().trim();

           if(convLine.matches("\\d+")){
               convertedTaskID = Integer.parseInt(convLine);
               System.out.println("[SUCCESS]\n");
               getData.remove(convertedTaskID);
           } else{
               System.out.println("[WRONG OUTPUT, TRY AGAIN]\n");
               System.out.println("0 - go back\n");
           }

           Utils.ClearConsole();
       }while (convertedTaskID != 0);

        HomePage.Home();
    }


    public void EditTask(){
        Scanner taskID = new Scanner(System.in);
        System.out.println("[✶ WRITE NUMBER OF TASK]\n");
        int convertedTaskID;

        do{
            convertedTaskID = taskID.nextInt();

            Scanner newTask = new Scanner(System.in);
            System.out.println("[✶ WRITE A NEW TASK]\n");
            String convertedNewTask = newTask.nextLine();

//            replace new data to deleted data & place to the old index
            getData.add(convertedTaskID, convertedNewTask);

            Utils.ClearConsole();

            System.out.println("[SUCCESS]\n");

//            return algorithm
            Scanner goBack = new Scanner(System.in);
            int goBackNum = -1;

            do{
                System.out.println("0 - go back\n");

                String convLine = goBack.nextLine().trim();

                if(convLine.matches("\\d+")){
                    goBackNum = Integer.parseInt(convLine);
                    System.out.println("[SUCCESS]\n");
                } else{
                    System.out.println("[WRONG OUTPUT, TRY AGAIN]\n");
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


}
