package thread;

import java.util.concurrent.TimeUnit;

/**
 * 触发死锁
 * @author MelloChan
 * @date 2018/4/14
 */
public class Deadlock {
    private Object resourceA=new Object();
    private Object resourceB=new Object();

    public static void main(String[] args) {
      Deadlock deadlock=new Deadlock();
      Task01 t1=deadlock.new Task01();
      Task02 t2=deadlock.new Task02();
      t1.start();
      t2.start();
    }

     class Task01 extends Thread{
         @Override
         public void run() {
             try {
                 doSomething1();
             } catch (InterruptedException e) {
                 System.out.println("获取资源失败");
             }
         }

         void doSomething1() throws InterruptedException {
            synchronized (resourceA){
                System.out.println("-- 获取资源A成功 -- ");
                System.out.println("-- 尝试获取资源B --");
                synchronized (resourceB){
                    System.out.println("-- 获取资源B成功 --");
                    TimeUnit.SECONDS.sleep(3);
                }
            }
        }
    }

     class Task02 extends Thread{
         @Override
         public void run() {
             try {
                 doSomething2();
             } catch (InterruptedException e) {
                 System.out.println("获取资源失败");
             }
         }

         void doSomething2() throws InterruptedException {
            synchronized (resourceB){
                System.out.println("-- 获取资源B成功 -- ");
                System.out.println("-- 尝试获取资源A --");
                synchronized (resourceA){
                    System.out.println("-- 获取资源A成功 --");
                    TimeUnit.SECONDS.sleep(1);
                }
            }
        }
    }
}
