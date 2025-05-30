package pub.shawfix.forum.portal.support;

import lombok.Data;

/**
 * @author shawfix
 * @create 2025/5/30 10:40
 * @desc
 **/
@Data
public class ViewException extends RuntimeException {

    private String message;

    public static ViewException build(String message) {
        ViewException viewException = new ViewException();
        viewException.setMessage(message);
        return viewException;
    }
}
