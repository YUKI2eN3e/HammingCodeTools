/*
* Author: None_id
* Date: 2021-04-14
*/

import tools.HammingCode2Info;
import tools.Info2HammingCode;

import java.util.Scanner;

public class HammingCodeTools {
    public static void main(String[] args) {
        System.out.printf("------------Menu------------\n"
                + "1. Convert binary number to Hamming Code\n"
                + "2. Check Hamming Code and Convert to binary number\n"
                + "----------------------------\n"
                + "Your choice: ");

        Scanner stdin = new Scanner(System.in);
        int choice = 0;
        Boolean selected = false;
        while(!selected){
            int num = stdin.nextInt();
            if(num == 1 || num == 2){
                choice = num;
                selected = true;
            }else{
                System.out.printf("Invalid selection.\nYour choice: ");
            }
        } 

        System.out.println();

        if (choice == 1) {
            info2HammingCode(stdin);
        } else {
            hammingCode2Info(stdin);
        }

        stdin.close();
    }

    public static void info2HammingCode(Scanner stdin) {
        Info2HammingCode fin = new Info2HammingCode(stdin);
        fin.setParityMode(stdin);
        fin.findK();
        fin.createHammingCode();
    }

    public static void hammingCode2Info(Scanner stdin) {
        HammingCode2Info fin = new HammingCode2Info(stdin);
        fin.setParityMode(stdin);
        fin.findK();
        fin.convertHammingCode2Info();
        fin.outputOriInfo();
    }
}
