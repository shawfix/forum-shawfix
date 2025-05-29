package pub.shawfix.forum.api.request.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author shawfix
 * @create 2025/5/29 16:49
 * @desc
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadImgRequest implements Serializable {

    private byte[] base64;

    private String fileName;

}
