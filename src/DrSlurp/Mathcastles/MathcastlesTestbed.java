//writen by Dr Slurp aka Josh Eisenberg
//www.research-josh.com


package DrSlurp.Mathcastles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class MathcastlesTestbed {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException 
	{
		
		ArrayList<Mathcastle> mathcastles = LoadMathcastleMetadata.get("metadata/mathcastles_metadata.json", false);
		
		HashMap<Integer, ArrayList<Mathcastle>> levelMap = getLevelMap(mathcastles);
		for(int i=1; i<21; i++)
		{
			System.out.println("level "+i+": "+levelMap.get(i).size());
		}
		
		
		for(int i=1; i<21; i++)
		{
			getCorners(levelMap.get(i));
		}
		
		
		HashMap<Integer, HashMap<Integer, HashMap<Integer, Mathcastle>>> coordinateMap = getCoordinateMap(levelMap);
		
		Mathcastle sixSixSix = coordinateMap.get(6).get(6).get(6);
		sixSixSix.print();
	}
	
	
	public static HashMap<Integer, HashMap<Integer, HashMap<Integer, Mathcastle>>> getCoordinateMap(HashMap<Integer, ArrayList<Mathcastle>> levelMap)
	{
		//ArrayList<ArrayList<ArrayList<Mathcastle>>> coordinateMap = new ArrayList<ArrayList<ArrayList<Mathcastle>>>();
		
		HashMap<Integer, HashMap<Integer, HashMap<Integer, Mathcastle>>> coordinateMap = new HashMap<Integer, HashMap<Integer, HashMap<Integer, Mathcastle>>>();
		for(int level = 1; level<21; level++)
		{
//			System.out.println(level);
			ArrayList<Mathcastle> castlesOnLevel = levelMap.get(level);
			HashMap<Integer, ArrayList<Mathcastle>> xColumns = new HashMap<Integer, ArrayList<Mathcastle>>();
			
			for(int i=0; i<castlesOnLevel.size(); i++)
			{
				Mathcastle castle = castlesOnLevel.get(i);
				
				if(xColumns.containsKey(castle.xCoordinate))
				{
					ArrayList<Mathcastle> column = xColumns.get(castle.xCoordinate);
					column.add(castle);
					xColumns.put(castle.xCoordinate, column);
				}
				else
				{
					ArrayList<Mathcastle> column = new ArrayList<Mathcastle>();
					column.add(castle);
					xColumns.put(castle.xCoordinate, column);
				}
			}
			
			
			
			HashMap<Integer, HashMap<Integer, Mathcastle>> xMap = new HashMap<Integer, HashMap<Integer, Mathcastle>>();
			for(int columnID : xColumns.keySet())
			{
				HashMap<Integer, Mathcastle> yMap = new HashMap<Integer, Mathcastle>();
//				System.out.println("\tcolumn: "+ columnID);
				ArrayList<Mathcastle> column = xColumns.get(columnID);
				for(int y=0; y<column.size(); y++)
				{
//					System.out.println("\t\t("+column.get(y).xCoordinate+", "+column.get(y).yCoordinate+")");
					yMap.put(column.get(y).yCoordinate, column.get(y));
				}
				
//				System.out.println(yMap);
				xMap.put(columnID, yMap);
			}
			coordinateMap.put(level, xMap);
			
		}
		
		
//		for(int level : coordinateMap.keySet())
//		{
//			System.out.println("level: "+level);
//			coordinateMap.get(level);
//			HashMap<Integer, HashMap<Integer, Mathcastle>> xMap = coordinateMap.get(level);
//			for(int x : xMap.keySet())
//			{
//				System.out.println("row "+x);
//				HashMap<Integer, Mathcastle> yMap = xMap.get(x);
//				for(int y : yMap.keySet())
//				{
//					Mathcastle castle = yMap.get(y);
//					System.out.println("\t("+castle.xCoordinate+", "+castle.yCoordinate+")");
//				}
//				
//			}
//		}
		
		return coordinateMap;
		
	}
	
	public static void getCorners(ArrayList<Mathcastle> castles)
	{
		int minX=-1;
		int maxX=-1;
		int minY=-1;
		int maxY=-1;
		
		for(int i=0; i<castles.size(); i++)
		{
//			castles.get(i).print();
			Mathcastle castle = castles.get(i);
			
			if(i==0)
			{
				minX = castle.xCoordinate;
				maxX = castle.xCoordinate;
				minY = castle.yCoordinate;
				maxY = castle.yCoordinate;
			}
			else
			{
				if(castle.xCoordinate < minX)
				{
					minX = castle.xCoordinate;
				}
				if(castle.xCoordinate > maxX)
				{
					maxX = castle.xCoordinate;
				}
				if(castle.yCoordinate < minY)
				{
					minY = castle.yCoordinate;
				}
				if(castle.yCoordinate > maxY)
				{
					maxY = castle.yCoordinate;
				}
			}
		}
		
		
		System.out.println("corners for level "+castles.get(0).level);
		System.out.println("("+minX+", "+maxY+")\t\t("+maxX+", "+maxY+")");
		System.out.println("("+minX+", "+minY+")\t\t("+maxX+", "+minY+")");

		
	}
	
	
	public static HashMap<Integer, ArrayList<Mathcastle>> getLevelMap(ArrayList<Mathcastle> mathcastles)
	{
		HashMap<Integer, ArrayList<Mathcastle>> levelMap = new HashMap<Integer, ArrayList<Mathcastle>>();
		
		for(int i=0; i<mathcastles.size(); i++)
		{
			Mathcastle castle = mathcastles.get(i);
			if(levelMap.containsKey(castle.level))
			{
				ArrayList<Mathcastle> castles = levelMap.get(castle.level);
				castles.add(castle);
				levelMap.put(castle.level, castles);
			}
			else
			{
				ArrayList<Mathcastle> castles = new ArrayList<Mathcastle>();
				castles.add(castle);
				levelMap.put(castle.level, castles);
			}
		}
		
		return levelMap;
	}

}
