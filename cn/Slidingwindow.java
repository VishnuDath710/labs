import java.util.*;
class Slidingwindow{
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter window size: ");
		int ws=sc.nextInt();
		System.out.print("Enter number of frames to transmit: ");
		int fs=sc.nextInt();
		System.out.print("Enter "+fs+" frames: ");
		int f[]=new int[fs];
		for(int i=0 ; i<fs; i++){
		    f[i]=sc.nextInt();
		}
		List<Integer> l=new ArrayList<>();
		for(int i=0; i<fs; i++){
		    if(l.size()==ws){
	      System.out.println("After sending "+ws+" at each stage sender waits for acknowledgement sent by the receiver");
		        System.out.println(l);
		        System.out.println("Acknowledgement of above frames sent is received by sender");
		        l.clear();
		    }
		    l.add(f[i]);
		    if(i==fs-1){
		        System.out.println(l);
		        System.out.println("Acknowledgement of above frames sent is received by sender");
		    }
		}
        sc.close();
	}
}