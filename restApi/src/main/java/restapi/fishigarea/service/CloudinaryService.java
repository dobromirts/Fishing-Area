package restapi.fishigarea.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {
    String uploadImg(MultipartFile file) throws IOException;
}
