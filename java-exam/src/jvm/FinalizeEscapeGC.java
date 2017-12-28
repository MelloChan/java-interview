package jvm;

/**
 * 关于finalize方法 实际上是个历史遗留问题 并不提倡使用
 * Created by MelloChan on 2017/12/28.
 */
public class FinalizeEscapeGC {
    private static FinalizeEscapeGC SAVE_HOOK=null;
    void isAlive(){
        System.out.println("yes i am still alive :) ");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println(" finalize method executed! ");
        FinalizeEscapeGC.SAVE_HOOK=this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK=new FinalizeEscapeGC();

        SAVE_HOOK=null;
        System.gc();

        Thread.sleep(500);
        if(SAVE_HOOK!=null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("no i am deed :( ");
        }
        SAVE_HOOK=null;
        System.gc();
        Thread.sleep(500);
        if(SAVE_HOOK!=null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("no i am deed :( ");
        }

    }
}
