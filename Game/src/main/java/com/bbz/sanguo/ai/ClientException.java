package com.bbz.sanguo.ai;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * user         LIUKUN
 * time         14-3-26 下午7:51
 * <p/>
 * 需要通知到到客户端的异常
 */

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class ClientException extends RuntimeException{

    private final ErrorCode code;
    private final String msg;

}
