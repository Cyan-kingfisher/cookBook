package qit.qjl.cookbook.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
public interface ImageUploadService {
    /**
     * 将图片转存为链接
     * @param multipartFiles
     * @return
     * @throws IOException
     */
    public List<String> storageImage(MultipartFile... multipartFiles) throws IOException;
}
