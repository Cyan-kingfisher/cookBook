package qit.qjl.cookbook.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;
import qit.qjl.cookbook.common.Content;
import qit.qjl.cookbook.exception.ParamNullException;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author: Cyan-K
 * @date: 2021/5/18
 */
@Slf4j
@Service
public class ImageUploadServiceImpl implements ImageUploadService {

    /**
     * 将图片转存为链接
     * @param multipartFiles 文件
     * @return List
     * @throws IOException IOException
     */
    @Override
    public List<String> storageImage(MultipartFile... multipartFiles) throws IOException {

        String path = Content.IMG_SAVE_ADDRESS;

        log.info("Resource.static.path:" + path);

        int length = multipartFiles.length;
        List<String> list = new ArrayList<>(length);

        for (int i = 1; i <= length; i++) {
            MultipartFile multipartFile = multipartFiles[i - 1];
            if (multipartFile.isEmpty()) {
                throw new ParamNullException();
            }
            String fileName = String.valueOf(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            String nature = fileName.substring(fileName.lastIndexOf("."));
            fileName = (char) (int) (Math.random() * 26 + 97) + String.valueOf(Math.random()*fileName.hashCode() + Math.random()).replace(".", "-") + nature;

            String savePath = path + fileName;

            log.info("图片存储URL:"+savePath);
            File file = new File(savePath);
            if (!file.exists()){
                file.mkdirs();
            }
            multipartFile.transferTo(file);
            log.info("图片访问URL:" + Content.IMG_URL + "image" + "/" + fileName);
            list.add(fileName);
        }

        return list;
    }
}
