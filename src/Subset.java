/* Sun Zhicheng
 * coursera course PA2
 * A test unit
 */

import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Subset {

    public static void main(String[] args) {
        
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);
        int n = 0;

        while (!StdIn.isEmpty()) {
            if (k == 0) break;
            n++;
            String s = StdIn.readString();
            if (n > k) {
                //the prob of a new object into the queue is about k/n
                //try with simple example
                //prove with mathematical induction
                int r = StdRandom.uniform(n);
                //StdOut.println(r);
                if (r <= k - 1) { //k/n
                    q.dequeue();
                    q.enqueue(s);
                }                    
            }
            else  q.enqueue(s);            
        }
        
        //StdOut.println();
        //StdOut.println(q.size());
        
        Iterator<String> i = q.iterator();
        while (i.hasNext()) {
            StdOut.println(i.next());        
        }
        //for(int j = 0; j < 10; j++)
            //StdOut.println(StdRandom.uniform(10));
    }
}
