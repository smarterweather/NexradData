package weather;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;

import weather.KeyParser.ChunkType;

public class KeyParserTest {

	@Test
	public void validateParsing() {
		String key = "FOP1/100/20160604-032953-002-I";
		String site = "FOP1";
		int volumeNumber = 100;
		int year = 2016;
		int month = 6;
		int day = 4;
		int hour = 3;
		int minute = 29;
		int second = 53;
		int chunkNumber = 2;
		ChunkType chunkType = ChunkType.Intermediate;
		
		NexradChunkInformation info = KeyParser.parseKey(key);
		
		assertEquals(site, info.getSite());
		assertEquals(volumeNumber, info.getVolumeNumber());
		assertEquals(year, info.getTime().get(Calendar.YEAR));
		assertEquals(month, info.getTime().get(Calendar.MONTH));
		assertEquals(day, info.getTime().get(Calendar.DAY_OF_MONTH));
		assertEquals(hour, info.getTime().get(Calendar.HOUR));
		assertEquals(minute, info.getTime().get(Calendar.MINUTE));
		assertEquals(second, info.getTime().get(Calendar.SECOND));
		assertEquals(chunkNumber, info.getChunkNumber());
		assertEquals(chunkType.code(), info.getChunkType().code());
	}

}
