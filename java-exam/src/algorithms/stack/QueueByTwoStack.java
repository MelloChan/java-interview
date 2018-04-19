package algorithms.stack;

import java.util.Stack;

/**
 * 栈->队列
 *
 * @author MelloChan
 * @date 2018/4/19
 */
public class QueueByTwoStack {
    public static void main(String[] args) throws Exception {
        QueueByTwoStack qs = new QueueByTwoStack();
        qs.add(1);
        qs.add(2);
        qs.add(3);
        System.out.println(qs.poll());
        System.out.println(qs.poll());
        qs.add(4);
        System.out.println(qs.poll());
        System.out.println(qs.poll());
        System.out.println(qs.poll());
    }

    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();

    public void add(int val) {
        s1.push(val);
    }

    public int poll() throws Exception {
        if (s2.size() <= 0) {
            while (s1.size() > 0) {
                s2.push(s1.pop());
            }
        }
        if (s2.size() == 0) {
            throw new Exception("队列为空");
        }
        return s2.pop();
    }
}
