package weather;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.TimeZone;

import ucar.ma2.Array;
import ucar.nc2.Attribute;
import ucar.nc2.Dimension;
import ucar.nc2.VariableSimpleIF;
import ucar.nc2.dt.RadialDatasetSweep;

public class Main {
	
	public static void main(String[] args) {
//		S3Accessor accessor = new S3Accessor();
//		
//		String key = "KABR/100/20160607-121548-007-I";
//		
//		accessor.uncompressChunk(key);
		
		test();
	}
	
	public static void test() {
		String filename = "C:/Users/Alex/Volume_Scan_Files/KMLB20160618_230637_V06";
		
		RadialDatasetSweep rds = NetCdfFactory.getRadialDatasetSweep(filename);
		
		if (rds.isStationary())
		{
			System.out.println("Radar is stationary");
		}
		else
		{
			System.out.println("Radar is moving");
		}
		
		if (rds.isVolume())
		{
			System.out.println("Radar is volume");
		}
		else
		{
			System.out.println("Radar is not volume");
		}
		
		System.out.println("Radar location: " + rds.getCommonOrigin().getLatitude() + ", " + rds.getCommonOrigin().getLongitude());
		
		for (VariableSimpleIF variable : rds.getDataVariables())
		{
			System.out.println(variable.getFullName() + ": " + variable.getDescription());

			for (Attribute attribute : variable.getAttributes())
			{
				System.out.println(attribute.getFullName());
				Array values = attribute.getValues();
				
				System.out.println(values.getDataType().getPrimitiveClassType().getCanonicalName());
			}
			
			System.out.println();
		}
	}
	
	public String getBucketAddress(double lat, double lon)
	{
		String host = getBucketHost();
		String query = getBucketQuery(lat, lon);
		
		return String.format("%s%s", host, query);
	}
	
	private String getBucketHost()
	{
		return "http://unidata-nexrad-level2-chunks.s3.amazonaws.com";
	}
	
	private String getBucketQuery(double lat, double lon)
	{
		String site = getNearestStation(lat, lon);
		String volumeNumber = getVolumeNumber();
		String date = getDateString();
		String time = getTimeString();
		String chunkNum = getChunkNumber();
		String chunkType = getChunkType();
		
		return String.format("/%s/%s/%s-%s-%s-%s", site, volumeNumber, date, time, chunkNum, chunkType);
	}
	
	private String getNearestStation(double lat, double lon)
	{
		// Hard coded to chicago station
		
		return "KLOT";
	}
	
	private String getVolumeNumber()
	{
		return "0";
	}
	
	private String getDateString()
	{
		Calendar date = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		int year = date.get(Calendar.YEAR);
		int month = date.get(Calendar.MONTH);
		int day = date.get(Calendar.DATE);
		
		return String.format("%04d%02d%02d", year, month, day);
	}
	
	private String getTimeString()
	{
		Calendar time = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		int hour = time.get(Calendar.HOUR);
		int minute = time.get(Calendar.MINUTE);
		int second = time.get(Calendar.SECOND);
		
		return String.format("%02d%02d%02d", hour, minute, second);
	}
	
	private String getChunkNumber()
	{
		return "05";
	}
	
	private String getChunkType()
	{
		return "I";
	}
	
	
}

