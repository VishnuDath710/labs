import java.util.*;

class CRCExample {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int n,k;
        System.out.println("Enter the n of the data array: ");
        n = scan.nextInt();
        int data[] = new int[n];
        System.out.println("Enter data bits in the array one by one: ");
        for (int i = 0; i < n; i++) {
            System.out.println("Enter bit " + (n - i) + ":");
            data[i] = scan.nextInt();
        }
        System.out.println("Enter the k of the divisor array:");
        k = scan.nextInt();
        int divisor[] = new int[k];
        System.out.println("Enter divisor bits in the array one by one: ");
        for (int i = 0; i < k; i++) {
            System.out.println("Enter bit " + (k - i) + ":");
            divisor[i] = scan.nextInt();
        }
        int rem[] = crc_function(data, divisor);
        for (int i = 0; i < rem.length - 1; i++) {
            System.out.print(rem[i]);
        }
        System.out.println("\nGenerated CRC code is: ");

        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]);
        }
        for (int i = 0; i < rem.length - 1; i++) {
            System.out.print(rem[i]);
        }
        System.out.println();
        int sentData[] = new int[data.length + rem.length - 1];
        System.out.println("Enter bits in the array which you want to send: ");
        for (int i = 0; i < sentData.length; i++) {
            System.out.println("Enter bit " + (sentData.length - i) + ":");
            sentData[i] = scan.nextInt();
        }
        receiveData(sentData, divisor);

        scan.close();
    }

    public static int[] crc_function(int oldData[], int divisor[]) {
        // Initialize remainder array
        int rem[] = new int[divisor.length];
        
        // Copy oldData into data array
        int data[] = new int[oldData.length + divisor.length];
        for (int i = 0; i < oldData.length; i++) {
            data[i] = oldData[i];
        }
        
        // Copy data into rem array
        for (int i = 0; i < divisor.length; i++) {
            rem[i] = data[i];
        }
        
        // Iterate over each bit of oldData
        for (int i = 0; i < oldData.length; i++) {
            System.out.println((i + 1) + ".) First data bit is : " + rem[0]);
            System.out.print("Remainder : ");
            
            // Check if the first bit of remainder is 1
            if (rem[0] == 1) {
                // Perform XOR operation with divisor
                for (int j = 0; j < divisor.length - 1; j++) {
                    rem[j] = xor(rem[j + 1], divisor[j + 1]);
                    System.out.print(rem[j]);
                }
            } else {
                // Shift remainder bits left by 1
                for (int j = 0; j < divisor.length - 1; j++) {
                    rem[j] = xor(rem[j + 1], 0);
                    System.out.print(rem[j]);
                }
            }
            
            
            // Copy next data bit into last bit of remainder
            rem[divisor.length - 1] = 0; // Default value if the index is out of bounds
            if (i + divisor.length < data.length) {
                rem[divisor.length - 1] = data[i + divisor.length];
            }
            
            System.out.println(rem[divisor.length - 1]);
        }
        
        // Return the remainder
        return rem;
    }
    
    static int xor(int x, int y) {
        // This simple function returns the exor of two bits
        if (x == y) {
            return 0;
        }
        return 1;
    }

    static void receiveData(int data[], int divisor[]) {

        int rem[] = crc_function(data, divisor);
        for (int i = 0; i < rem.length; i++) {
            if (rem[i] != 0) {
                System.out.println("Currupted data received...");
                return;
            }
        }
        System.out.println("Data received without any error.");
    }
}
