package web.boot.action.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import web.boot.action.entity.JsonData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;


/**
 * @author z
 */
@RestController
public class FileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @Value("${app.upload.path}")
    private String filePath;

    @PostMapping("/single/upload")
    public JsonData upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new JsonData(-1, "上传文件为空");
        }
        String fileName = file.getOriginalFilename();
        String path = filePath + fileName;
        LOGGER.info("上传的文件名为：" + path);
        File dest = new File(path);
        try {
            file.transferTo(dest);
            return new JsonData(0, "上传成功", path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JsonData(1, "上传失败");
    }
    @PostMapping("/uploads")
    public JsonData uploads(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        String[] filesUpload = new String[files.size()];
        File[] uploadFiles = new File[files.size()];
        MultipartFile singleFile;
        for (int i = 0; i < files.size(); i++) {
            singleFile = files.get(i);
            if (singleFile.isEmpty()) {
                return new JsonData(1, "文件不能为空");
            }
            File currentUploadFile = new File(filePath + singleFile.getOriginalFilename());
            try {
                singleFile.transferTo(currentUploadFile);
                uploadFiles[i] = currentUploadFile;
                filesUpload[i] = currentUploadFile.getAbsolutePath();
            } catch (IOException e) {
                for (File file: uploadFiles) {
                    file.delete();
                }
                return new JsonData(1, "上传失败", e.getMessage());
            }
        }
        return new JsonData(0, "上传成功", filesUpload);
    }
    @GetMapping("/download/{file}")
    public JsonData download(HttpServletResponse response, @PathVariable String file) {
        if (file == null) {
            return new JsonData(-1, "下载文件不能为空");
        }
        File downFile = new File(filePath + file);
        if (!downFile.exists()) {
            return new JsonData(-1, "文件不存在");
        }
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" + file);
        try {
            try(BufferedInputStream fileInput =  new BufferedInputStream(new FileInputStream(downFile));
            OutputStream outputStream = response.getOutputStream()) {
                byte[] buffer = new byte[1024];
                int i = fileInput.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = fileInput.read(buffer);
                }
                return new JsonData(0, "下载成功", downFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JsonData(1, "下载失败");
    }
}
