import java.util.*;

class Subnetting {

    public static void Subnets(int IP[],int sub) {
        int temp = IP[0];
        int mask;
        if (temp < 128) {
            mask = 8;
        } else if (temp < 192) {
            mask = 16;
        } else if (temp < 224) {
            mask = 24;
        } else {
            System.out.println("Invalid IP for subnetting");
            return;
        }
        int rem = 32 - mask;
        long incr1 = 0;
        long incr = ((long) Math.round(Math.pow(2, rem))) / sub;
        long incr2 = incr;
        long t1 = 0;
        // System.out.println(rem+" "+incr1+"\t"+incr2);
        for (int j = 0; j < sub; j++) {
            t1 = incr2;
            // System.out.println(incr2);
            System.out.print(IP[0]);
            System.out.print("." + (IP[1] + (incr1 / (256 * 256))) % 256);
            System.out.print("." + (IP[2] + (incr1 / 256)) % 256);
            System.out.print("." + (IP[3] + (incr1 % 256)));

            System.out.print("\t-TO-\t");
            System.out.print(IP[0]);
            System.out.print("." + (IP[1] + (t1 / (256 * 256)) - 1) % 256);

            System.out.print("." + (IP[2] + (t1 / 256) - 1) % 256);
            System.out.print("." + (IP[3] + ((t1 - 1) % 256)));
            System.out.println("\n");
            incr1 = incr2;
            incr2 = incr1 + incr;
        }
    }

    public static void main(String args[]) {
        System.out.println("Enter the Network address");
        Scanner sc = new Scanner(System.in);
        String ip = sc.next();
        String[] IPs = ip.split("\\.");
        int[] IP = new int[IPs.length];
        for (int i = 0; i < IPs.length; i++) {
            IP[i] = Integer.parseInt(IPs[i]);
            // System.out.println(IP[i]);
        }
        System.out.println("Enter the number of Subnets");
        int sub = sc.nextInt();
        Subnets(IP,sub);
        sc.close();
    }
}
