/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package annuitpayments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Math.pow;
import java.math.BigDecimal;
/**
 *
 * @author Линдт Светлана
 */
public class AnnuitPayments {
 
    private static int N; //Количество месяцев, на которые заемщик хочет взять кредит
    private static BigDecimal S0;//Сумма кредита
    private static final double P = 0.01;
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        calculateValues (); 
    }
    
    private static BigDecimal inputSum(String str, String str_e) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BigDecimal value=null;
        boolean allRight;
        do{
            System.out.print(str);  
            value = new BigDecimal(reader.readLine().replace(',','.'));
            if (value.signum()==-1||value.signum()==0){
                allRight=false;
                System.out.print(str_e);
                System.out.println();
            }
            else allRight=true;    
        } while (!allRight);
        value=roundValue(value);
        return value;
    }
    
    private static void inputMonth() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean allRight=false;
        do{
            System.out.print("Срок кредитования, мес.: ");
            N = Integer.parseInt(reader.readLine());
            if (N>0)
                allRight=true;
            else{ 
                System.out.print("Срок кредитования должен быть целым положительным числом. Пожалуйста, повторите попытку.");
                System.out.println();
            }
        } while (!allRight);
    }
    
    private static void calculateValues () throws IOException{
        try{
            inputMonth();
        }
         catch(NumberFormatException e){ 
            System.out.print("Срок кредитования должен быть целым числом. Пожалуйста, повторите попытку.");
            System.out.println();
            inputMonth();
         }
        try{
            S0=inputSum("Сумма кредита, руб.: ","Сумма кредита должна быть положительным числом. Пожалуйста, повторите попытку.");
        }
        catch(NumberFormatException e){ 
            System.out.print("Сумма кредита должна быть числом. Пожалуйста, повторите попытку.");
            System.out.println();
            S0=inputSum("Сумма кредита, руб.: ","Сумма кредита должна быть положительным числом. Пожалуйста, повторите попытку.");
        }    
        BigDecimal[] X= new BigDecimal[N+2];//Общий ежемесячный платеж
        BigDecimal[] p_n= new  BigDecimal[N+2];//начисленные проценты, на момент n-ой выплаты
        BigDecimal[] Sn= new  BigDecimal[N+2];//остаток задолженности на период
        BigDecimal[] S= new  BigDecimal[N+2];//часть выплаты, идущая на погашение долга
        BigDecimal[] R= new  BigDecimal[N+2];//Размер внесенных средств
        BigDecimal[] extra= new  BigDecimal[N+2];
        System.out.println("Процентная ставка: 12%.");
        Sn[0]=S0;
        X[0]= S0.multiply(BigDecimal.valueOf(P+P/(pow(1+P,N)-1))); //Общий ежемесячный платеж
        X[0]=roundValue(X[0]);
        printValue(X[0],"Общий ежемесячный платеж, руб: ");
        for(int i=0; i<N; i++){
            System.out.println();
            System.out.print(i+1); 
            System.out.print(" месяц");
            System.out.println();
            try{
                R[i] = inputSum("Размер внесенных средств, руб.: ","Размер внесенных средств должен быть положительным  числом. Пожалуйста, повторите попытку.");
                if (R[i].compareTo(X[i])==-1){
                    System.out.println("Внесенных средств недостаточно для погашения общего ежемесячного платежа.");
                    System.out.println("Внесите, пожалуйста, достаточную сумму и повторите попытку.");
                    R[i] = inputSum("Размер внесенных средств, руб.: ","Размер внесенных средств должен быть положительным  числом. Пожалуйста, повторите попытку.");
                }
            }
             catch(NumberFormatException e){ 
                System.out.print("Размер внесенных средств должен быть числом. Пожалуйста, повторите попытку.");
                System.out.println();
                R[i] = inputSum("Размер внесенных средств, руб.: ","Размер внесенных средств должен быть положительным  числом. Пожалуйста, повторите попытку.");
            } 
            try{
            extra[i]=R[i].subtract(X[i]);
            extra[i]=roundValue(extra[i]);
            p_n[i] = Sn[i].multiply(new BigDecimal("0.01"));
            p_n[i]=roundValue(p_n[i]);
            S[i]=(X[i].subtract(p_n[i])).add(extra[i]);
            S[i]=roundValue(S[i]);
            Sn[i+1]=(Sn[i].subtract(S[i])).subtract(extra[i]);
            Sn[i+1]=roundValue(Sn[i+1]);
            X[i+1] =  Sn[i+1].multiply(BigDecimal.valueOf(P+P/(pow(1+P,N-(i+1))-1)));
            X[i+1]=roundValue(X[i+1]);
            
            printValue(Sn[i+1],"Остаток осн. долга после совершения текущего платежа, руб: ");
            printValue(X[i+1],"Общий ежемесячный платеж, руб: ");
            printValue(S[i],"Платеж в счет погашения основного долга, руб: ");
            printValue(p_n[i],"Проценты по кредиту, руб: ");
            System.out.println();}
            catch(NumberFormatException e){ 
            System.out.println("Ваш кредит выплачен.");
            break;
            }
        }
    }  
    private static void printValue(BigDecimal value, String title){
        System.out.print(title);
        System.out.print(value);
        System.out.println();
    }
    private static BigDecimal roundValue(BigDecimal x){
        x = x.setScale(2, BigDecimal.ROUND_HALF_EVEN);   
        return x;
    }
   
 
}
    