package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.mail.simplemail.SimpleEmailServiceMailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;

@Configuration
public class SesConfig {

	//	private CustomPropertyConfig customPropertyConfig;
	//
	//	public SesConfig(final CustomPropertyConfig customPropertyConfig) {
	//		this.customPropertyConfig = customPropertyConfig;
	//	}
	//
	//	@Bean
	//	public AmazonSimpleEmailService amazonSimpleEmailService() {
	//
	//		BasicAWSCredentials credentials = new BasicAWSCredentials(customPropertyConfig.awsAccessKey,
	//				customPropertyConfig.awsSecretKey);
	//
	//		return AmazonSimpleEmailServiceClientBuilder.standard()
	//				.withCredentials(new AWSStaticCredentialsProvider(credentials))
	//				.withRegion(Regions.AP_NORTHEAST_1)
	//				.build();
	//	}

	@Value("${cloud.aws.credentials.access-key}")
	private String accessKey;

	@Value("${cloud.aws.credentials.secret-key}")
	private String secretKey;

	@Value("${cloud.aws.region.static}")
	private String region;

	@Bean
	public AmazonSimpleEmailService amazonSimpleEmailService() {
		BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		return AmazonSimpleEmailServiceClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(region)
				.build();
	}

	@Bean
	public MailSender mailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
		return new SimpleEmailServiceMailSender(amazonSimpleEmailService);
	}

	//	@Bean
	//	public JavaMailSender javaMailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
	//		return new SimpleEmailServiceJavaMailSender(amazonSimpleEmailService);
	//	}
}