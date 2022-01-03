package com.boulow.mono.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.boulow.mono.config.BoulowProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("fileStorage-service")
public class FileStorageService {
	
	private static final Logger log = LoggerFactory.getLogger(FileStorageService.class);

    @Autowired
    private AmazonS3 s3client;
    
    @Autowired
    BoulowProperties boulowProperties;
    
    @Async
    public List<String> saveFiles(List<MultipartFile> files, int entityTypeCd) {
		List<String> filesUrls = new ArrayList<String>();
		String folder = "";
		if(files == null || files.size() < 1)
			throw new IllegalArgumentException("No document to be uploaded. Please provide some documents");
		else {
			switch (entityTypeCd) {
			case 0:
				folder = boulowProperties.getCloudFolders().getAvatars();
				break;
			case 1:
				folder = boulowProperties.getCloudFolders().getHints();
				break;
			case 2:
				folder = boulowProperties.getCloudFolders().getAds();
				break;
			case 3:
				folder = boulowProperties.getCloudFolders().getContracts();
				break;
			case 4:
				folder = boulowProperties.getCloudFolders().getReviews();
				break;
			case 5:
				folder = boulowProperties.getCloudFolders().getIds();
				break;
			default:
				folder = boulowProperties.getCloudFolders().getOthers();
				break;
			}
		}
    	for(MultipartFile file: files) {
    		filesUrls.add(uploadFile(file, folder));
    	}
    	return filesUrls;
    }

    @Async
    public String uploadFile(MultipartFile multipartFile, String folder) {
    	String fileUrl = "";
    	try {
        	File file = convertMultiPartToFile(multipartFile);
            String fileName = folder + "/" + generateFileName(multipartFile);
            fileUrl = generateObjectUrl(fileName);
            uploadFileTos3bucket(fileName, file);
            Files.delete(file.toPath());
        } catch (AmazonServiceException e) {
        	log.error("Error {} occurred while uploading file", e.getLocalizedMessage());
        } catch (IOException ex) {
        	log.error("Error {} occurred while deleting temporary file", ex.getLocalizedMessage());
        }
    	return fileUrl;
    }
    
    @Async
    public S3ObjectInputStream findByName(String fileName) {
        log.info("Downloading file with name {}", fileName);
        return s3client.getObject(boulowProperties.getAmazonS3Props().getBucketName(), fileName).getObjectContent();
    }
    
    private File convertMultiPartToFile(final MultipartFile multipartFile) {
        final File file = new File(multipartFile.getOriginalFilename());
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (IOException e) {
            log.error("Error {} occurred while converting the multipart file", e.getLocalizedMessage());
        }
        return file;
    }

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }

    private void uploadFileTos3bucket(String fileName, File file) {
        s3client.putObject(new PutObjectRequest(boulowProperties.getAmazonS3Props().getBucketName(), fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public String deleteFileFromS3Bucket(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        s3client.deleteObject(new DeleteObjectRequest(boulowProperties.getAmazonS3Props().getBucketName(), fileName));
        return "Successfully deleted";
    }
    
    public String generateObjectUrl(String filename) {
    	return String.format("https://%s.s3.amazonaws.com/%s", boulowProperties.getAmazonS3Props().getBucketName(), filename); 
    }
}