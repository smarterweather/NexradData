package weather;


public class KeyParser {

	public static NexradChunkInformation parseKey(String key)
	{
		// Sample key: FOP1/100/20160604-032953-002-I
		
		String[] slashElements = key.split("/");
		String[] hyphenElements = slashElements[2].split("-");
		
		String site = slashElements[0];
		int volumeNumber = Integer.parseInt(slashElements[1]);
		
		String yearString = "";
		String monthString = "";
		String dayString = "";
		
		int i = 0;
		for (char c : hyphenElements[0].toCharArray())
		{
			if (i < 4)
			{
				yearString += c;
			}
			else if (i < 6)
			{
				monthString += c;
			}
			else
			{
				dayString += c;
			}
			
			i++;
		}
		
		int year = Integer.parseInt(yearString);
		int month = Integer.parseInt(monthString);
		int day = Integer.parseInt(dayString);
		
		String hourString = "";
		String minuteString = "";
		String secondString = "";
		
		i = 0;
		for (char c : hyphenElements[1].toCharArray())
		{
			if (i < 2)
			{
				hourString += c;
			}
			else if (i < 4)
			{
				minuteString += c;
			}
			else
			{
				secondString += c;
			}
			
			i++;
		}
		
		int hour = Integer.parseInt(hourString);
		int minute = Integer.parseInt(minuteString);
		int second = Integer.parseInt(secondString);
		
		int chunkNumber = Integer.parseInt(hyphenElements[2]);
		ChunkType chunkType = ChunkType.getType(hyphenElements[3]);
		
		return new NexradChunkInformation(site, 
				volumeNumber, 
				year, 
				month, 
				day, 
				hour, 
				minute, 
				second, 
				chunkNumber, 
				chunkType);
	}
	
	public enum ChunkType
	{
		Start ("S"),
		Intermediate ("I"),
		End ("E"),
		Unknown ("U");
		
		private String code;
		
		ChunkType(String code)
		{
			this.code = code;
		}
		
		public String code()
		{
			return code;
		}
		
		public static ChunkType getType(String code)
		{
			switch(code)
			{
			case "S":
				return ChunkType.Start;
			case "I":
				return ChunkType.Intermediate;
			case "E":
				return ChunkType.End;	
			default:
				return ChunkType.Unknown;
			}
		}
	}
}
