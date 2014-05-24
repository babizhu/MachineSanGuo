package experiment.netty.lingkong.request;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * user         LIUKUN
 * time         2014-5-23 19:46
 */
@Data
@AllArgsConstructor
public class AbstractResponse{
    /**
     * 命令是否执行成功
     */
    private boolean success;

    private int result;
}
