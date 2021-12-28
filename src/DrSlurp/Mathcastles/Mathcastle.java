package DrSlurp.Mathcastles;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder
({
	"tokenId",
    "level",
    "biome",
    "elevation",
    "zoneName",
    "xCoordinate",
    "yCoordinate",
    "seedValue",
    "structureSpaceX",
    "structureSpaceY",
    "structureSpaceZ",
    "chroma",
    "mode",
    "questionMarks"
})


public class Mathcastle 
{
	@JsonProperty("tokenId")
	public int tokenID;
	
	@JsonProperty("level")
	public int level;
	
	@JsonProperty("biome")
	public int biome;
	
	@JsonProperty("elevation")
	public int elevation;
	
	@JsonProperty("zoneName")
	public String zone;
	
	@JsonProperty("xCoordinate")
	public int xCoordinate;
	
	@JsonProperty("yCoordinate")
	public int yCoordinate;
	
	@JsonProperty("seedValue")
	public int seedValue;
	
	@JsonProperty("structureSpaceX")
	public int structureSpaceX;
	
	@JsonProperty("structureSpaceY")
	public int structureSpaceY;
	
	@JsonProperty("structureSpaceZ")
	public int structureSpaceZ;
	
	@JsonProperty("chroma")
	public String chroma;
	
	@JsonProperty("mode")
	public String mode;
	
	@JsonProperty("questionMarks")
	public int questionMarks;
	
	
	String urlPrefix = "https://opensea.io/assets/0x4e1f41613c9084fdb9e34e11fae9412427480e56/";
	
	public void print()
	{
		System.out.println("math castle # "+tokenID);
		System.out.println("\tlevel "+level+" at ("+xCoordinate+", "+yCoordinate+")");
		System.out.println("\tbiome: "+biome);
		System.out.println("\tzone: "+zone);
		System.out.println("\tchroma: "+chroma);
		System.out.println("\t???: "+questionMarks);
		System.out.println("\tseed: "+seedValue);
		System.out.println("\televation: "+elevation);



		System.out.println("\tglobal coordinates: ");
		System.out.println("\t\tX: "+structureSpaceX);
		System.out.println("\t\tY: "+structureSpaceY);
		System.out.println("\t\tZ: "+structureSpaceZ);
		System.out.println("\tURL: "+urlPrefix+tokenID);
		
	}
	
	
	
	
}
