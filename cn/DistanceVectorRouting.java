import java.util.Scanner;

public class DistanceVectorRouting {

    static final int INF = 9999;
    static final int MAX_NODES = 10;

    // Function to initialize distance vector and routing table
    static void initialize(int numNodes, int[][] costMatrix, int[][] distVector, int[][] nextHop) {
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                
                distVector[i][j] = costMatrix[i][j];
                if (costMatrix[i][j] != INF && i != j) {
                    nextHop[i][j] = j;
                } else {
                    nextHop[i][j] = -1;
                }
            }
        }
    }

    // Function to print routing table for each node
    static void printRoutingTable(int numNodes, int[][] distVector, int[][] nextHop) {
        for (int i = 0; i < numNodes; i++) {
            System.out.println("Routing table for node " + i + ":");
            System.out.println("Destination\tNext Hop\tDistance");
            for (int j = 0; j < numNodes; j++) {
                if (distVector[i][j] == INF) {
                    System.out.println(j + "\t\t-\t\tINF");
                } else {
                    System.out.println(j + "\t\t" + nextHop[i][j] + "\t\t" + distVector[i][j]);
                }
            }
            System.out.println();
        }
    }

    // Function to implement Distance Vector Routing algorithm
    static void distanceVectorRouting(int numNodes, int[][] costMatrix, int[][] distVector, int[][] nextHop) {
        boolean updated;

        do {
            updated = false;
            for (int i = 0; i < numNodes; i++) {
                for (int j = 0; j < numNodes; j++) {
                    for (int k = 0; k < numNodes; k++) {
                        if (distVector[i][k] + distVector[k][j] < distVector[i][j]) {
                            distVector[i][j] = distVector[i][k] + distVector[k][j];
                            nextHop[i][j] = nextHop[i][k];
                            updated = true;
                        }
                    }
                }
            }
        } while (updated);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of nodes: ");
        int numNodes = scanner.nextInt();

        int[][] costMatrix = new int[MAX_NODES][MAX_NODES];
        int[][] distVector = new int[MAX_NODES][MAX_NODES];
        int[][] nextHop = new int[MAX_NODES][MAX_NODES];

        System.out.println("Enter the cost matrix (use " + INF + " for INF):");
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                costMatrix[i][j] = scanner.nextInt();
            }
        }

        initialize(numNodes, costMatrix, distVector, nextHop);
        distanceVectorRouting(numNodes, costMatrix, distVector, nextHop);
        printRoutingTable(numNodes, distVector, nextHop);

        scanner.close();
    }
}

// Enter the number of nodes: 4
// Enter the cost matrix (use 9999 for INF):
// 0 2 9999 1
// 2 0 3 9999
// 9999 3 0 1
// 1 9999 1 0



// Routing table for node 0:
// Destination    Next Hop    Distance
// 0              -           0
// 1              1           2
// 2              3           2
// 3              3           1

// Routing table for node 1:
// Destination    Next Hop    Distance
// 0              0           2
// 1              -           0
// 2              2           3
// 3              2           4

// Routing table for node 2:
// Destination    Next Hop    Distance
// 0              3           2
// 1              1           3
// 2              -           0
// 3              3           1

// Routing table for node 3:
// Destination    Next Hop    Distance
// 0              0           1
// 1              0           3
// 2              2           1
// 3              -           0
