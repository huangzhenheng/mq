package com.tianque.testThread;

/**
 * @Description 模仿死锁的情况发生
 * @auther hzh
 * @create 2018-10-30 9:53
 */
public class TestDeadLock implements Runnable{

    public int flag=1;
    static  Object o1=new Object(),o2=new Object();
    @Override
    public void run() {
        System.out.println("flag:"+flag);
        if (flag==1){
            synchronized (o1){
                try{
                    Thread.sleep(500);
                }catch (Exception e){
                    e.printStackTrace();
                }
                synchronized (o2){
                    System.out.println("1");
                }
            }
        }

        if (flag==0){
            synchronized (o2){
                try{
                    Thread.sleep(500);
                }catch (Exception e){
                    e.printStackTrace();
                }
                synchronized (o1){
                    System.out.println("0");
                }
            }
        }

    }


    public static void main(String[] args) {
        TestDeadLock testDeadLock1=new  TestDeadLock();
        TestDeadLock testDeadLock2=new  TestDeadLock();
        testDeadLock1.flag=1;
        testDeadLock2.flag=0;

        Thread t1=new Thread(testDeadLock1);
        Thread t2=new Thread(testDeadLock2);
        t1.start();
        t2.start();

    }
}
