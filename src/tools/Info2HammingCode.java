package tools;

import prepare.*;

import java.util.Scanner;

import static java.lang.Math.pow;

public class Info2HammingCode {
    private final String dataBin;
    private boolean parityModeOdd;
    private int k;

    public Info2HammingCode(Scanner stdin) {
        System.out.print("Please input your original binary number: ");

        dataBin = stdin.next();
    }

    public void findK() {
        int k = 1;

        while ((int) pow(2, k) < dataBin.length() + k + 1) {
            k++;
        }

        this.k = k;
    }

    public void setParityMode(Scanner stdin) {
        System.out.print("Please input your parity mode(Odd as 1, Even as 2): ");

        int choice = 0;
        Boolean selected = false;
        while(!selected){
            int num = stdin.nextInt();
            if(num == 1 || num == 2){
                choice = num;
                selected = true;
            }else{
                System.out.printf("Invalid selection.\nPlease input your parity mode(Odd as 1, Even as 2): ");
            }
        }

        parityModeOdd = choice == 1;
    }


    public void createHammingCode() {
        PendingC CGroup = new PendingC();
        StringBuilder finalCode = new StringBuilder();
        final int finalCodeLenth = dataBin.length() + k;

        finalCode.setLength(finalCodeLenth);

        for (int i = 0, j = 1, preLoc = 0;
             preLoc < dataBin.length();
             i++) {
            if (j <= k) {
                if (i + 1 == (int) pow(2, j - 1)) {
                    j++;
                    continue;
                }
            }

            finalCode.setCharAt(i, dataBin.charAt(preLoc));
            preLoc++;
        }


        for (int count = 1, cLoc;
             count <= k;
             count++) {

            cLoc = (int) pow(2, count - 1) - 1;
            boolean hasValue = Boolean.FALSE;
            boolean[] eCGroup = CGroup.getCGroup(count);
            char eCvalue = 0;

            for (int i = cLoc + 2;
                 i < finalCodeLenth + 1;
                 i++) {
                if (eCGroup[i]) {

                    if (!hasValue) {

                        eCvalue = finalCode.charAt(i - 1);
                        hasValue = Boolean.TRUE;

                    } else {

                        if (eCvalue == finalCode.charAt(i - 1)) {
                            eCvalue = '0';
                        } else {
                            eCvalue = '1';
                        }

                    }

                }
            }

            if (parityModeOdd) {
                eCvalue = eCvalue == '1' ? '0' : '1';
            }

            finalCode.setCharAt(cLoc, eCvalue);
        }

        System.out.println();
        System.out.println("The Converted Hamming Code is: " + finalCode);
    }
}

