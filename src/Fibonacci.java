package src;

import java.util.HashMap;

/**
 * 斐波那契数列实现方式
 * 0,1,1,2,3,5,8,13
 * 可以得出来结论
 * F(0)=0
 * F(1)=1
 * F(2)=F(0)+F(1)=1
 * F(3)=F(1)+F(2)=F(1)+( F(0)+F(1) ) = 2
 * ......
 * F(n)=F(n-2)+F(n-1)
 * 细化分解，最终会回归到只有F(0)和F(1)的问题里面去，也就是等号右边全是F(0)和F(1)
 *
 *常用时间复杂度所耗费的时间从小到大依次是o(1）<o(log2^n)<o(n)<o(nlog2^n)<o(n^2)<o(n^3)<o(2^n)<o(n!)<o(n^n).
 */
public class Fibonacci {
    /**
     *第一种: 递归方式
     * 时间复杂度为O(2^n), 空间复杂度就是树的高度 S(n)
     */
    public static long fibonacci_1 (int n ){
        if(n<0) return -1;
        if(n==0) return 0;
        if(n==1) return 1;
        return fibonacci_1(n-2)+fibonacci_1(n-1);
    }


    /**
     *第二种: 顺序递推-基于变量
     * 卸磨杀驴型 = 用完就给你覆盖上
     * 时间复杂度：O(n)，空间复杂度：S(1)
     */
    public static long fibonacci_2(int n){
//        if(n<0) throw new RuntimeException("输入参数小于1");
        if(n<0) return -1;
        if(n==0) return 0;
        if(n==1) return 1;
        long a=0;
        long b=1;
        long result=0;
        for(int i=2;i<=n;i++){
            result=a+b;
            a=b;
            b=result;
        }
        return result;
    }

    /**
     * 第三种: 顺序递推-基于数组
     * 如果需要的话，可以直接把整条斐波那契数列全带出来。直接返回数组就可以了
     * 时间复杂度：O(n)，空间复杂度：S(1)
     */
    public static long fibonacci_3(int n){
        if(n<0) return -1;
        if(n==0) return 0;
        if(n==1) return 1;
        long[] arr=new long[n+1];
        arr[0]=0;
        arr[1]=1;
        for(int i=2;i<=n;i++) arr[i]=arr[i-2]+arr[i-1];
        return arr[n];
    }

    /**
     * 第四种: 尾递归方式
     * 时间复杂度O(n)， 空间复杂度为S(n)—>优化S(1)
     * 尾递归指函数体内最后一句执行为一个递归，该次递归执行后不再改变函数体
     * 因此若编译器选择优化，只开辟一块函数体的空间
     */
    public static long fibonacci_4(int n,long num1,long num2){
        if(n<0) return -1;
        if(n==0) return 0;
        if(n==1) return num1;
        return fibonacci_4(n-1,num2,num1+num2);
    }


    /**
     * 第五种：递归+HashMap缓存
     * 时间复杂度为O（n），空间复杂度为S(n)
     */
    static HashMap<Integer,Long> map=new HashMap<Integer, Long>(); //缓存计算结果集
    public static long fibonacci_5(int n){
        if(n<0) return -1;
        if(n==0) return 0;
        if(n==1) return 1;
        if(!map.containsKey(n)) map.put(n,fibonacci_5(n-2)+fibonacci_5(n-1));
        return map.get(n);
    }


    /**
     * 第六种：递归+数组缓存
     * 时间复杂度为O（n），空间复杂度为S(n)
     */
    static long[] arr=new long[50+1];
    public static long fibonacci_6(int n){
        if(n<0) return -1;
        if(n==0) return 0;
        if(n==1) return 1;
        if(arr[n]==0) arr[n]=fibonacci_6(n-2)+fibonacci_6(n-1);
        return arr[n];
    }

    /**
     *第七种: 公式解法, 等比数列推算
     * 时间复杂度为O(1)
     */
    public static long fibonacci_7(int n ){
        double result = 0;
        double tmp=Math.sqrt(5.0);
        result=(Math.pow((1+tmp)/2,n)-Math.pow((1-tmp)/2,n))/tmp;
        return (long) result;
    }

    /**
     *第八种: 矩阵解法(n-2次方)
     * Fn=Fn-1+Fn-2
     * [Fn-1]       [1 1]^ n-2    [1]
     * |   |   =   |   |      *  | |
     *[Fn-2]       [1 0]        [0]
     */
    static long[][] initMatrix={{1,1},{1,0}};
    public static long fibonacci_8(int n){
        if(n<0) return -1;
        if(n==0) return 0;
        if(n==1) return 1;
        long[][] tmp=initMatrix;
        for(int i=1;i<n-2;i++) tmp=matrixMulti(tmp,initMatrix);
        return tmp[0][0]+tmp[1][0];
    }

    /**
     * 两个二阶矩阵相乘
     */
//    public static long[][] matrixMulti(long[][] a ,long[][] b){
//        long[][] tmp=new long[2][2];
//        tmp[0][0]=a[0][0]*b[0][0]+a[0][1]*b[1][0];
//        tmp[0][1]=a[0][0]*b[0][1]+a[0][1]*b[1][1];
//        tmp[1][0]=a[1][0]*b[0][0]+a[1][1]*b[1][0];
//        tmp[1][1]=a[1][0]*b[0][1]+a[1][1]*b[1][1];
//        return tmp;
//    }

    /**
     * 通用矩阵乘法
     */
    public static long[][] matrixMulti(long[][] a,long[][] b){
        int rows=a.length;  //行数
        int cols=b[0].length;  //列数
        long[][] matrix=new long[rows][cols];
        for(int i=0;i<a.length;i++){
            for(int j=0;j<b[0].length;j++) {
                for (int k=0;k<a[i].length;k++) matrix[i][j]+=a[i][k] * b[k][j];  // a[i].length是a[i]的列数
            }
        }
        return  matrix;
    }

    /**
     * 第九种: 矩阵解法(n-2次方)+快速幂(位移法)
     * 时间复杂度优化到O(logn), 空间复杂度：S(1)
     */
    static long[][] unitMatrix={{1,0},{0,1}}; //单位矩阵
    public static long fibonacci_9(int n){
        if(n<-1) return -1;
        if(n==0) return 0;
        if(n==1) return 1;
        long[][] result=unitMatrix;
        long[][] tmp=initMatrix;
        int m=n-2;
        while(m!=0){
            if((m & 1)==1)  { //& 表示"按位与"，这里的"位"是指二进制位（bit）,&计算中，只要有一个是0就算成0
                result=matrixMulti(tmp,result);
                //System.out.println(m); //3 1  存储结果数据
            }
            tmp=matrixMulti(tmp,tmp);
            m>>=1; //>>向右位移,先十进制转换成二进制, 再进行位移
        }
        return result[0][0]+result[1][0];
    }

    /**
     * 第十种: 矩阵解法(n次方)+快速幂(二分法)
     * [Fn+1   Fn]       [1 1]^ n
     * |        |   =   |   |
     *[Fn   Fn-1]       [1 0]
     * 时间复杂度优化到O(logn), 空间复杂度：S(1)
     */
    public static long fibonacci_10(int n ){
        if(n<0) return -1;
        if(n==0) return 0;
        long[][] result=power_dich(n);
        return result[0][1];
    }

    public static long[][] power_dich(int n){
        if(n==1) return initMatrix; //此行不能少
        if(n%2 == 0) {//偶数
            long[][] matrix=power_dich(n/2);
            return matrixMulti(matrix,matrix);
        }else{//奇数
            long[][] matrix=power_dich((n-1)/2);
            return matrixMulti(initMatrix,matrixMulti(matrix,matrix));
        }

    }


    public static void main(String[] args) {
        long begin = System.currentTimeMillis(); // 这段代码放在程序执行前
//        long result=new src.Fibonacci().fibonacci_1(50); //59103毫秒
//        long result=new src.Fibonacci().fibonacci_2(50); //16毫秒
//        long result=new src.Fibonacci().fibonacci_3(50); //1毫秒
//        long result=new src.Fibonacci().fibonacci_4(50,1,1); //0毫秒
//        long result=new src.Fibonacci().fibonacci_5(50); //2毫秒
//        long result=new src.Fibonacci().fibonacci_6(50); //1毫秒  12586269025
        long result=new Fibonacci().fibonacci_7(49); //0毫秒
//        long result=new src.Fibonacci().fibonacci_8(50); //0毫秒
//        long result=new src.Fibonacci().fibonacci_9(50); //0毫秒
//        long result=new src.Fibonacci().fibonacci_10(50); //0毫秒
        System.out.println(result);
        long end = System.currentTimeMillis() - begin; // 这段代码放在程序执行后
        System.out.println("耗时：" + end + "毫秒");

//        long[][] b={{1,1,2},{1,0},{1,1,0}};
//        System.out.println(b[0].length);//3
//        System.out.println(b[1].length);//2
//        System.out.println(b[2].length);//3


    }

}