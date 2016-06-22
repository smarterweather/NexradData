package weather;

import java.util.Calendar;
import java.util.TimeZone;

import weather.KeyParser.ChunkType;

public class NexradChunkInformation {

	private String site;
	private int volumeNumber;
	private Calendar time;
	private int chunkNumber;
	private ChunkType chunkType;
	
	public NexradChunkInformation(String site, int volumeNumber, Calendar time, int chunkNumber, ChunkType chunkType)
	{
		this.site = site;
		this.volumeNumber = volumeNumber;
		this.time = time;
		this.chunkNumber = chunkNumber;
		this.chunkType = chunkType;
	}
	
	public NexradChunkInformation(String site, 
			int volumeNumber, 
			int year, 
			int month, 
			int date, 
			int hour, 
			int minute, 
			int second, 
			int chunkNumber, 
			ChunkType chunkType)
	{
		this.site = site;
		this.volumeNumber = volumeNumber;
		this.chunkNumber = chunkNumber;
		this.chunkType = chunkType;
		
		Calendar time = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		
		time.set(Calendar.YEAR, year);
		time.set(Calendar.MONTH, month);
		time.set(Calendar.DAY_OF_MONTH, date);
		time.set(Calendar.HOUR, hour);
		time.set(Calendar.MINUTE, minute);
		time.set(Calendar.SECOND, second);
		
		this.time = time;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public int getVolumeNumber() {
		return volumeNumber;
	}

	public void setVolumeNumber(int volumeNumber) {
		this.volumeNumber = volumeNumber;
	}

	public Calendar getTime() {
		return time;
	}

	public void setTime(Calendar time) {
		this.time = time;
	}

	public int getChunkNumber() {
		return chunkNumber;
	}

	public void setChunkNumber(int chunkNumber) {
		this.chunkNumber = chunkNumber;
	}

	public ChunkType getChunkType() {
		return chunkType;
	}

	public void setChunkType(ChunkType chunkType) {
		this.chunkType = chunkType;
	}
	
}
