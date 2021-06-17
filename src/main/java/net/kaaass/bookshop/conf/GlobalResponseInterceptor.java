package net.kaaass.bookshop.conf;

import lombok.val;
import net.kaaass.bookshop.controller.response.GlobalResponse;
import org.jboss.resteasy.annotations.interception.EncoderPrecedence;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.interception.PostProcessInterceptor;

import javax.ws.rs.ext.Provider;

/**
 * 统一输出拦截器
 * @author kaaass
 */
@EncoderPrecedence
@Provider
@ServerInterceptor
public class GlobalResponseInterceptor implements PostProcessInterceptor {
    @Override
    public void postProcess(ServerResponse response) {
        val resp = response.getEntity();
        if (resp instanceof GlobalResponse) {
            return;
        }

        val wrapperResp = GlobalResponse.success(resp);
        response.setEntity(wrapperResp);
        response.setResourceClass(wrapperResp.getClass());
        response.setGenericType(wrapperResp.getClass().getGenericSuperclass());
    }
}
