package dont;
import java.util.*;
class  pulka2
{
    public static void main(String[] args) 
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of nodes:");
        int n=sc.nextInt();
        System.out.println("Enter the distance matrix:");
        int matrix[][]=new int[n][n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                matrix[i][j]=sc.nextInt();
            }           
        }
        for(int i=0;i<n;i++)
        {
            System.out.println("Router info for router: "+i+1);
            System.out.println("Destination\t NextHop\t Dist\t");
            for(int j=0;j<n;j++)
            {
                System.out.println(j+"\t\t"+j+"\t\t"+matrix[i][j]+"\t\t");  
            }
        }      
        sc.close();
    }
}


// Enter number of nodes:
// 3
// Enter the distance matrix:
// 1 0 99
// 0 2 99
// 3 2 0