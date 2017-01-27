/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quick_sort;

/**
 *
 * @author Линдт Светлана
 */
public class Quick_Sort {
    
    private static final int[] ARRAY0 = {1,53,4,3,41,56,45,68,15,46,64,5,6,8,51,33,54};
    private static final int ARRAY0_LENGTH=ARRAY0.length;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        printArray ();
        quickSortArray();
        printArray ();   
    }
    
    private static void quickSortArray(){
        sortArray(0,ARRAY0_LENGTH-1 );
    }
    private static void sortArray(int start, int end) {
        if (end-start<=0)
            return;
        int l=start, r=end;
        int mid = (ARRAY0[l]+ARRAY0[r]+ARRAY0[l+(r-l)/2])/3;
        while (r>l){
            while((ARRAY0[l]<=mid)&&(l<r) ){
                l++;
            }
            while((ARRAY0[r]>mid)&&(l<r) ){
                r--;
            }
            
            if (l!=r){
                int temp = ARRAY0[l];
                ARRAY0[l]=ARRAY0[r];
                ARRAY0[r]=temp;
            }    
        } 
        sortArray(start,l-1);
        sortArray(l, end);
    }
   
    private static void printArray() {
        for (int i=0; i<ARRAY0_LENGTH; i++) {
            System.out.print(ARRAY0[i] + " ");
        }
        System.out.println();
    }
}
