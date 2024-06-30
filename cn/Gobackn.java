import java.util.*;
public class Gobackn {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("please enter the window size: ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println("frame " + i + " has been transmitted");
        }
        System.out.print("please enter the last acknowledgement received: ");
        int ack = sc.nextInt();
        for (int i = ack; i < ack + n; i++) {
            System.out.println("frame " + i + " has been transmitted");
        }
        sc.close();
    }
}
