package DrSlurp.Mathcastles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class LoadMathcastleMetadata 
{
	public static ArrayList<Mathcastle> get(String pathToJSON, Boolean print) throws JsonParseException, JsonMappingException, IOException
	{
		ArrayList<Mathcastle> mathcastles = new ArrayList<Mathcastle>();
		MathcastleParser jsonParser = new MathcastleParser();
		Scanner in = new Scanner(new File(pathToJSON));
		in.nextLine(); // skip initial [
		String json = "";
		int previousTokenID = -1;

		Mathcastle castle = null;
		
		while(in.hasNextLine())
		{
			String currentLine = in.nextLine();
			if(currentLine.contains("{"))
			{
				json = json + currentLine +"\n";
			}
			else if(currentLine.contains("},")||currentLine.contains("}"))
			{
				json = json + "}";
				castle = jsonParser.parse(json);
				mathcastles.add(castle);
				if(print)
				{
					castle.print();
				}
				json = "";
			}
			else
			{
				json = json + currentLine + "\n";
			}
		}
		
		return mathcastles;
	}
}
