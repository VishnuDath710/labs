import java.util.*;

class Subnetting {

    public static void Subnets(int IP[], int sub) {
        int temp = IP[0];
        int mask;
        
        // Determine the subnet mask based on the first octet of the IP address
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
        
        // Calculate the remaining bits in the IP address after the subnet mask
        int rem = 32 - mask;
        
        // Calculate the increment for each subnet
        long incr = ((long) Math.round(Math.pow(2, rem))) / sub;
        long[] startIP = new long[4];
        long[] endIP = new long[4];

        // Print the subnet ranges
        for (int j = 0; j < sub; j++) {
            // Calculate the starting IP address of the subnet
            startIP[0] = IP[0];
            startIP[1] = IP[1] + ((incr * j) / (256 * 256));
            startIP[2] = IP[2] + ((incr * j) / 256);
            startIP[3] = IP[3] + ((incr * j) % 256);
            
            // Calculate the ending IP address of the subnet
            endIP[0] = IP[0];
            endIP[1] = IP[1] + (((incr * (j + 1)) - 1) / (256 * 256));
            endIP[2] = IP[2] + (((incr * (j + 1)) - 1) / 256);
            endIP[3] = IP[3] + (((incr * (j + 1)) - 1) % 256);

            // Print the subnet range
            System.out.print(startIP[0] + "." + startIP[1] + "." + startIP[2] + "." + startIP[3]);
            System.out.print("\t-TO-\t");

            System.out.println(endIP[0] + "." + endIP[1] + "." + endIP[2] + "." + endIP[3]);
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
        }
        System.out.println("Enter the number of Subnets");
        int sub = sc.nextInt();
        Subnets(IP, sub);
        sc.close();
    }
}
