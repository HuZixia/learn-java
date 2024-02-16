package src;

/**
 * @Author: Huzixa
 * @Date: 2019/5/2 15:02
 */
public class Sort {
    /**
     * 冒泡排序, 实现数组从小到大的排序
     * 冒泡排序是一种稳定的排序方法, 平均时间复杂度为O(n^2)
     */
    public static int[] bubble_sort(int[] arr){
        for (int i=0;i<arr.length-1;i++) { //外层循环控制排序趟数, arr.length-1指数组的最大索引值
            for(int j=0;j<arr.length-1-i;j++){ //内层循环控制每一趟排序多少次
                if(arr[j]>arr[j+1]){
                    int tmp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=tmp;
                }
            }
        }
        return arr;
    }

    /**
     * 快速排序,实现数组从小到大的排序
     * 快速排序是不稳定的排序, 时间复杂度为O(nlogn)
     */
    public static int[] quick_sort(int[] arr,int low,int high){
        if(low>high) return arr;
        int i=low;
        int j=high;
        int key=arr[low];  //固定的切分方式,key为基准位
        while(i<j){
            while(arr[j]>=key && j>i) j--; //从后半部分向前扫描,如果列表右边的数,比基准数大或相等,则前移一位直到有比基准数小的数出现
            while(arr[i]<=key && j>i) i++; //从前半部分向后扫描,如果列表左边的数,比基准数小或相等,则前移一位直到有比基准数大的数出现
            //如果满足条件则交换ij数字
            if(i<j){
                int tmp=arr[j];
                arr[j]=arr[i];
                arr[i]=tmp;
            }
        }
        //最后将基准位和(low和high)相等位置的数字交换
        arr[low]=arr[i];
        arr[i]=key;
        quick_sort(arr,low,j-1); //递归调用左半数组
        quick_sort(arr,j+1,high); //递归调用右半数组
        return arr;
    }


    /**
     * 对给定的n个数的序列，返回序列中的最大和最小的数
     * 最简单的方法: 逐个比较，返回最大的数和最小的数。此时需要进行2n次的比较。
     */
    public static int getMax_1(int[] arr){
        int max=arr[0];
        for(int i=0;i<=arr.length-1;i++){
            if(arr[i]>max) max=arr[i];
        }
        return max;
    }

    public static int getMin_1(int[] arr){
        int min=arr[0];
        for(int i=0;i<=arr.length-1;i++){
            if(arr[i]<min) min=arr[i];
        }
        return min;
    }

    /**
     * 对给定的n个数的序列，返回序列中的最大和最小的数
     * 先对各数据两两比较，将大的放在一起，小的放在一起，然后从大数集合中找最大值，从小数集合中找最小值。此时共需3n/2=1.5n次的比较。
     */
    public static int[] exchange(int[] arr){
        for(int i=0;i<=(arr.length-1)/2;i++){
            if(arr[i]>arr[arr.length-1-i]){
                int tmp=arr[i];
                arr[i]=arr[arr.length-1-i];
                arr[arr.length-1-i]= tmp;
            }
        }
        return arr;
    }

    public static int getMax_2(int[] arr){
        int max=arr[arr.length-1];
        for(int i=arr.length-1;i>=(arr.length-1)/2;i--) {
            if (max < arr[i]) max = arr[i];
        }
        return max;
    }

    public static int getMin_2(int[] arr){
        int min=arr[0];
        for(int i=0;i<=(arr.length-1)/2;i++){
            if (min>arr[i]) min=arr[i];
        }
        return min;
    }


    /**
     * 对给定的n个数的序列，返回序列中的最大和最小的数
     * 前两个数比较,大的为最大值, 小的为最小值, 用掉一次比较
     * 后面n-2个数, 每两个比较, 大的同最大值比较, 小的同最小值比较, 3*(n-2)/2次比较, 共(1.5n-2)次比较。
     */
    public static void getMaxMin(int[] arr){
        int max=0;
        int min=0;
        if(arr[0]>arr[1]) {
            max=arr[0];min=arr[1];
        }else{
            max=arr[1];min=arr[0];
        }
        for(int i=2;i<=arr.length-1;i++){
            if(arr[i]>arr[arr.length-1-i]){
                if(arr[i]>max) max=arr[i];
                if(arr[arr.length-1-i]<min) min=arr[arr.length-1-i];
            }else{
                if(arr[arr.length-1-i]>max) max=arr[arr.length-1-i];
                if(arr[i]<min) min=arr[i];
            }
        }
        System.out.println("最大值是: "+max);
        System.out.println("最小值是: "+min);
    }


    public static void main(String[] args) {
        long begin = System.currentTimeMillis(); // 这段代码放在程序执行前
        int[] arr={22,1,34,2,0,61,5,7,99};
//        System.out.println(getMax_1(arr));
//        System.out.println(getMin_1(arr));

        arr=exchange(arr);
        System.out.println(getMax_2(arr));
        System.out.println(getMin_2(arr));

//        arr=bubble_sort(arr);
//        arr=quick_sort(arr,0,arr.length-1);
//        for (int i=0;i<=arr.length-1;i++) System.out.println(arr[i]);
//        getMaxMin(arr);
        long end = System.currentTimeMillis() - begin; // 这段代码放在程序执行后
        System.out.println("耗时：" + end + "毫秒");

    }



}
