package DrSlurp.Mathcastles;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MathcastleParser 
{
	public ObjectMapper mapper;

	public MathcastleParser()
	{
		mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	public Mathcastle parse(String JSONInput) throws JsonParseException, JsonMappingException, IOException
	{
		Mathcastle castle = mapper.readValue(JSONInput, Mathcastle.class);
		return castle;
	}
}
