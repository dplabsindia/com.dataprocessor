package com.data.process.aws;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

public class S3ServiceImpl implements S3Service{


	@Autowired
	private AmazonS3 amazonS3;
	@Value("${aws.s3.bucket}")
	private String bucketName;
	
	public void download() {
		byte[] content = null;
		final S3Object s3Object = amazonS3.getObject(bucketName, "keyname");
		final S3ObjectInputStream stream = s3Object.getObjectContent();
		try {
			content = IOUtils.toByteArray(stream);
			s3Object.close();
		} catch(final IOException ex) {
 		}
	}
	
}
