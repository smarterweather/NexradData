package weather;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

// 11553825 chunks in S3 bucket
public class S3Accessor {

	private AmazonS3 client;
	
	public S3Accessor()
	{
		client = new AmazonS3Client(new ProfileCredentialsProvider());
	}
	
	public List<String> getBucketKeys()
	{
		List<String> keys = new ArrayList<String>();
		
		final ListObjectsV2Request req = new ListObjectsV2Request().withBucketName(NexradBucketInfo.BUCKET_NAME);
        ListObjectsV2Result result;
        do {               
           result = client.listObjectsV2(req);
           
           for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
        	   keys.add(objectSummary.getKey());
        	   //System.out.println(objectSummary.getKey());
           }
           
           req.setContinuationToken(result.getNextContinuationToken());
        } while(result.isTruncated() == true ); 
		
		return keys;
	}
	
	public File uncompressChunk(String key)
	{
		Decompression.decompressBZip2(getData(key));
		
		return null;
	}
	
	public InputStream getData(String key)
	{
		// sample key: FOP1/100/20160604-032953-002-I
		S3Object object = client.getObject(new GetObjectRequest(NexradBucketInfo.BUCKET_NAME, key));
		
		return object.getObjectContent();
	}
}
