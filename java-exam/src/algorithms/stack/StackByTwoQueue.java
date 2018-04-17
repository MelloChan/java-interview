package algorithms.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 队列->栈
 *
 * @author MelloChan
 * @date 2018/4/17
 */
public class StackByTwoQueue {
    public static void main(String[] args) throws Exception {
        StackByTwoQueue qs=new StackByTwoQueue();
//        qs.poll();
        for (int i=0;i<10;i++){
            qs.push(i);
        }
        for (int i=0;i<10;i++){
            System.out.print(qs.poll()+" ");
        }
    }

    Queue<Integer> q1 = new LinkedList<>();
    Queue<Integer> q2 = new LinkedList<>();

    void push(int val) {
        if (q1.isEmpty()) {
            q2.add(val);
        }
        if (q2.isEmpty()) {
            q1.add(val);
        }
    }

    int poll() throws Exception {
       if(q1.isEmpty()&&q2.isEmpty()){
           throw new Exception("空栈");
       }
       if(q1.isEmpty()){
           while (q2.size()>1){
               q1.add(q2.poll());
           }
           return q2.poll();
       }
       if(q2.isEmpty()){
           while (q1.size()>1){
               q2.add(q1.poll());
           }
           return q1.poll();
       }
       return -1;
    }

}
