package edu.dlnu.Vod.utils;

public class test2{
    public static int partition(int []a,int low,int high){
        int position = a[low];
        while(low<high){
            while(low<high && a[high]>=position) high--;
            a[low]=a[high];
            while(low<high && a[low]<=position) low++;
            a[high]=a[low];
        }//while end
        a[low] = position;
        return high;
    }//partition end

    public static void quicksort(int[] a,int low,int high){
        if(low<high){
            int position = partition(a,low,high);
            quicksort(a,0,position-1);
            quicksort(a,position+1,high);
        }
    }//quickSort end

    //冒泡排序
    public static void Bubble(int[] a){
        for(int i=0;i<a.length-1;++i)
            for(int j=0;j<a.length-i-1;++j){
                if(a[j]>a[j+1]){
                    int temp=a[j+1];
                    a[j+1]=a[j];
                    a[j]=temp;
                }
            }
    }
    public static void main(String args[]){
        int a[] = {6,5,2,8,1};
        quicksort(a,0,4);
        for(int i=0;i<a.length;++i){
            System.out.println(a[i]+",");
        }
        int b[]={2,4,1,3,4,5,10,9};
        Bubble(b);
        for(int t=0;t<b.length;t++){
            System.out.print(b[t]+",");
        }
    }//main end
}//test end
