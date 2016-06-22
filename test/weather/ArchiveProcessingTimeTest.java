package weather;

import org.junit.Test;

import com.amazonaws.services.sns.model.SubscribeRequest;

public class ArchiveProcessingTimeTest {

	@Test
	public void test() {
		// Tests how long it takes amazon to process the chunks of real time 
		// Nexrad level II data. If it is sufficient, we can use the archive
		// files and avoid processing the data chunks ourselves.
		SubscribeRequest realTimeRequest = new SubscribeRequest();
		realTimeRequest.setTopicArn("arn:aws:sns:us-east-1:684042711724:NewNEXRADLevel2Object");
		
		SubscribeRequest archiveRequest = new SubscribeRequest();
		archiveRequest.setTopicArn("arn:aws:sns:us-east-1:811054952067:NewNEXRADLevel2Archive");
		
		
	}

}
