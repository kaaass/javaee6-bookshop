package bookshop.exception;

import bookshop.util.StatusEnum;

/**
 * 服务器暂不可用
 */
public class ServiceUnavailableException extends BaseException {

    StatusEnum status = StatusEnum.SERVICE_UNAVAILIBLE;

    public ServiceUnavailableException(String message) {
        super(message);
    }

    public StatusEnum getStatus() {
        return this.status;
    }
}
