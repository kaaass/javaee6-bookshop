package bookshop.controller.page;

import bookshop.dao.Pageable;
import bookshop.util.Constants;
import org.jboss.resteasy.annotations.interception.DecoderPrecedence;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.util.List;

/**
 * 分页参数解析
 *
 * @author kaaass
 */
@Provider
@ServerInterceptor
@DecoderPrecedence
public class PagePreProcessInterceptor implements PreProcessInterceptor {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(PagePreProcessInterceptor.class);
    @Inject
    private PageInfo pageInfo;

    @Override
    public ServerResponse preProcess(HttpRequest request, ResourceMethod method) throws Failure, WebApplicationException {
        final MultivaluedMap<String, String> queryParams = request.getUri().getQueryParameters();
        String size = null;
        String page = null;

        if (queryParams.containsKey(Constants.PARAM_PAGE)) {
            final List<String> list = queryParams.get(Constants.PARAM_PAGE);
            if (!list.isEmpty()) {
                page = list.get(0);
            }
        }

        if (queryParams.containsKey(Constants.PARAM_SIZE)) {
            final List<String> list = queryParams.get(Constants.PARAM_SIZE);
            if (!list.isEmpty()) {
                size = list.get(0);
            }
        }

        // 不传入参数则忽视
        if (page == null && size == null) {
            pageInfo.setPageable(null);
            return null;
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
