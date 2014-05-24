package experiment.netty.lingkong.request;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * user         LIUKUN
 * time         2014-5-23 15:44
 * 请求基类
 * 总长度8+4;
 */
@Data
@AllArgsConstructor
public class AbstractRequest{
    public static final int MAX_SIZE = 10;//8+2

    private long serverId = 0;

    /**
     * 操作码
     */
    private short opCode = 0;
}
