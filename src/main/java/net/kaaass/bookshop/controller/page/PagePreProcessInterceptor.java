package net.kaaass.bookshop.controller.page;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.kaaass.bookshop.dao.Pageable;
import net.kaaass.bookshop.util.Constants;
import org.jboss.resteasy.annotations.interception.DecoderPrecedence;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;

/**
 * 分页参数解析
 * @author kaaass
 */
@Slf4j
@Provider
@ServerInterceptor
@DecoderPrecedence
public class PagePreProcessInterceptor implements PreProcessInterceptor {

    @Inject
    private PageInfo pageInfo;

    @Override
    public ServerResponse preProcess(HttpRequest request, ResourceMethod method) throws Failure, WebApplicationException {
        val queryParams = request.getUri().getQueryParameters();
        String size = null;
        String page = null;

        if (queryParams.containsKey(Constants.PARAM_PAGE)) {
            val list = queryParams.get(Constants.PARAM_PAGE);
            if (!list.isEmpty()) {
                page = list.get(0);
            }
        }

        if (queryParams.containsKey(Constants.PARAM_SIZE)) {
            val list = queryParams.get(Constants.PARAM_SIZE);
            if (!list.isEmpty()) {
                size = list.get(0);
            }
        }

        if (page == null) {
            page = "0";
        }
        if (size == null) {
            size = Constants.DEFAULT_PAGE_SIZE;
        }

        pageInfo.setPageable(new Pageable(Integer.parseInt(page), Integer.parseInt(size)));

        return null;
    }
}
