package com.example.demo.helper;

import java.io.File;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageUploadHelper {

	public boolean imageUpload(MultipartFile image, String uploadDir) throws Exception {

		boolean flag = false;
//		final String uploadDirs= "D:\\Vinay\\FileMangementSystem\\src\\main\\resources\\static\\image";

		// read
//			InputStream is= image.getInputStream();
//			byte data[]= new byte[is.available()];
//			is.read(data);
//			
//		//write
//			FileOutputStream fos = new FileOutputStream(uploadDir+File.separator+image.getOriginalFilename());
//			fos.write(data);
//			fos.flush();
//			fos.close();

		File f = new File(uploadDir);
		if (!f.exists()) {
			f.mkdir();
		}

		String name = image.getOriginalFilename();
//		String randomId = UUID.randomUUID().toString();
//		String filename = randomId.concat(name.substring(name.lastIndexOf(".")));
		try {
			Files.copy(image.getInputStream(), Paths.get(uploadDir + File.separator + name),
					StandardCopyOption.REPLACE_EXISTING);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}
}
