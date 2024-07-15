import java.util.Scanner;

public class Broadcast {

    // Function to convert an IP address to a 32-bit integer
    public static int ipToInt(String ip) {
        String[] parts = ip.split("\\.");
        int a = Integer.parseInt(parts[0]);
        int b = Integer.parseInt(parts[1]);
        int c = Integer.parseInt(parts[2]);
        int d = Integer.parseInt(parts[3]);
        return (a << 24) | (b << 16) | (c << 8) | d;
    }

    // Function to convert a 32-bit integer to an IP address
    public static String intToIp(int ip) {
        return String.format("%d.%d.%d.%d", (ip >> 24) & 0xFF, (ip >> 16) & 0xFF, (ip >> 8) & 0xFF, ip & 0xFF);
    }

    // Function to calculate the subnet mask from a prefix length
    public static int calculateSubnetMask(int prefixLength) {
        return prefixLength == 0 ? 0 : ~((1 << (32 - prefixLength)) - 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input IP address and prefix length
        System.out.print("Enter IP address (e.g., 192.168.1.0): ");
        String ip = scanner.nextLine();
        System.out.print("Enter current prefix length (e.g., 24): ");
        int prefixLength = scanner.nextInt();
        System.out.print("Enter the number of subnets: ");
        int numberOfSubnets = scanner.nextInt();

        // Calculate the new prefix length for the specified number of subnets
        int bitsForSubnets = (int) Math.ceil(Math.log(numberOfSubnets) / Math.log(2));
        int newPrefixLength = prefixLength + bitsForSubnets;

        // Convert IP address to integer
        int ipInt = ipToInt(ip);

        // Calculate original subnet mask and new subnet mask
        int subnetMask = calculateSubnetMask(prefixLength);
        int newSubnetMask = calculateSubnetMask(newPrefixLength);

        // Calculate the number of hosts per subnet
        int hostsPerSubnet = (1 << (32 - newPrefixLength)) - 2; // subtract 2 for network and broadcast addresses

        System.out.println("\nNumber of subnets: " + numberOfSubnets);
        System.out.println("Number of hosts per subnet: " + hostsPerSubnet);

        // Generate subnets
        for (int i = 0; i < numberOfSubnets; i++) {
            int subnetNetwork = (ipInt & subnetMask) | (i << (32 - newPrefixLength));
            int subnetBroadcast = subnetNetwork | ~newSubnetMask;
            int firstHost = subnetNetwork + 1;
            int lastHost = subnetBroadcast - 1;

            System.out.println("\nSubnet " + (i + 1) + ":");
            System.out.println("Network Address: " + intToIp(subnetNetwork));
            System.out.println("Broadcast Address: " + intToIp(subnetBroadcast));
            System.out.println("Subnet Mask: " + intToIp(newSubnetMask));
            System.out.println("First Host: " + intToIp(firstHost));
            System.out.println("Last Host: " + intToIp(lastHost));
        }

        scanner.close();
    }
}
