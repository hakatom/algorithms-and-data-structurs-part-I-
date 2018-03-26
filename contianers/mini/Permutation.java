import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * A client program that
 * takes a command-line integer 
 * reads in a sequence of strings from standard input using
 *      StdIn.readString();
 * prints out exactly {@code k} of them, uniformly at random.
 * 
 * Each item from the sequence can be printed out at most once. You may assume
 * that  where  is the number of string on standard
 * input.
 *
 * by doron vaida
 */
public class Permutation {

    public static void main(String[] args) {
        final int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        String[] tokens = StdIn.readAllStrings();
        for (String token : tokens) {
            queue.enqueue(token);
        }
        for (int i = 0; i < k; i++) {
            StdOut.println(queue.dequeue());
        }
    }
}