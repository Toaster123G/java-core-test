package modules;

import data.DataStorage;
import features.Utils;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Scanner;

public class HomePage {
    public static void Home(){
        System.out.println("+==============+");
        System.out.println("| TASK MANAGER |");
        System.out.println("+==============+");

        LocalDate currentDate = LocalDate.now();
        System.out.println("###"+currentDate+"###\n");
        Scanner mainInput = new Scanner(System.in);
        System.out.println("0 - add task\n1 - show tasks\n2 - edit tasks\n3 - save task\n4 - delete task\n5 - exit");

        int mainInputNum = mainInput.nextInt();

        DataStorage taskData = new DataStorage();

        switch (mainInputNum){
            case 0:
                Utils.ClearConsole();
                taskData.AddToData();
            case 1:
                Utils.ClearConsole();
                taskData.ShowData();
            case 2:
                Utils.ClearConsole();
                taskData.EditTask();
            case 3:
//                save task
                Utils.ClearConsole();
//                Utils.SeeSoon();
                taskData.SaveAsFile();
            case 4:
                Utils.ClearConsole();
                taskData.DelTask();
            case 5:
                EndProgram.StopProgramFunc();
            default:
                Utils.ClearConsole();
                Home();
        }
    }
}
