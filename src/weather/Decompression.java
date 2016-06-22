package weather;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;

public class Decompression {

	public static FileOutputStream decompressBZip2(InputStream in)
	{
		try {
			FileOutputStream out = new FileOutputStream(new File("uncompressed.tar"));
			BZip2CompressorInputStream bzIn = new BZip2CompressorInputStream(in);
			final byte[] buffer = new byte[1000000];
			int n = 0;
			while (-1 != (n = bzIn.read(buffer))) {
				out.write(buffer, 0, n);
			}
			out.flush();
			out.close();
			bzIn.close();
			
			return out;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void decompressGZip(InputStream in)
	{
		try {
			FileOutputStream out = new FileOutputStream(new File("uncompressedScan"));
			GzipCompressorInputStream gzIn = new GzipCompressorInputStream(in);
			final byte[] buffer = new byte[1000000];
			int n = 0;
			while (-1 != (n = gzIn.read(buffer))) {
				out.write(buffer, 0, n);
			}
			out.close();
			gzIn.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
