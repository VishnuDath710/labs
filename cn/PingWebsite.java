
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PingWebsite {
    // method for finding the ping statics of website
    static void commands(ArrayList<String> commandList) throws Exception {
        // creating the sub process, execute system command
        ProcessBuilder build = new ProcessBuilder(commandList.toArray(new String[0]));
        Process process = build.start();

        // to read the output
        BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader Error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String s = null;

        System.out.println("Standard output: ");
        while ((s = input.readLine()) != null) {
            System.out.println(s);
        }
        System.out.println("error (if any): ");
        while ((s = Error.readLine()) != null) {
            System.out.println(s);
        }
    }

    public static void main(String args[]) throws Exception {
        // creating list for commands
        ArrayList<String> commandList = new ArrayList<String>();

        commandList.add("ping");
        // can be replaced by IP
        commandList.add("www.google.com");
        // commandList.add("www.localhost");

        PingWebsite.commands(commandList);
    }
}
