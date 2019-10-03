import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;

public class Challenge1
{
	//This function reads a line of text that the user inputs
	public static String readInput() throws IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); //Create a buffered reader that reads input from the command window
		String input = reader.readLine(); //The user types in the target ID
		return input;
	}
	
	//Uses the target's url to fetch the source code, read the line of it with the target's name and outputs the target's name
	public static String readNameFromUrl(URL webpage) throws IOException
	{
		BufferedReader webReader = new BufferedReader(new InputStreamReader(webpage.openStream())); //Creates a buffered reader to read the source code
		
		//This for loop goes through the source code until it reaches the line with the target's name, line 85
		for(int i=1; i<85; i++)
		{
			webReader.readLine();
		}
		
		String targetLine = webReader.readLine();
		String name;
		
		int startPos = targetLine.indexOf("property=\"name\">"); //Index of where the target's name starts
		int endPos = targetLine.indexOf("<em property=\"honorificSuffix\">"); //Index of where the target's name ends
		
		if(startPos < 0 || endPos < 0)
		{
			name = "Error: you have entered an invalid ID or the target URL is not public"; //This prints if the startPos or endPos have a value of -1, meaning the name of the target doesn't exist on the line
		}
		else
		{
			name = new String(targetLine.substring(startPos+16, endPos)); //The target's name is read from the line and saved to a variable
		}
		return name;
	}
	
	public static void main(String[] args) throws IOException
	{
		System.out.println("Please enter email ID");
		String id = readInput();
		
		URL url = new URL(new String("https://www.ecs.soton.ac.uk/people/"+id)); //The url is constructed from the user's input
		
		System.out.println(readNameFromUrl(url));
	}
}