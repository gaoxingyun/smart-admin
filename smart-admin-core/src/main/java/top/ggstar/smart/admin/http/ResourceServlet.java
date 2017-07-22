package top.ggstar.smart.admin.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.ggstar.smart.admin.common.util.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.spi.CharsetProvider;


public abstract class ResourceServlet extends HttpServlet {

    private final static Logger LOG = LoggerFactory.getLogger(ResourceServlet.class);
    protected  static String  resourcePath = null;

    private final static String GET = "GET";
    private final static String POST = "POST";
    private final static Integer BUFFER_SIZE = 1024;


    public ResourceServlet(String resourcePath){
        this.resourcePath = resourcePath;
    }

    public ResourceServlet() {
        super();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        String body = null;

        response.setCharacterEncoding("utf-8");

        if (contextPath == null) { // root context
            contextPath = "";
        }
        String uri = contextPath + servletPath;
        String path = requestURI.substring(contextPath.length() + servletPath.length());

        LOG.debug("ContentPath: {}, ServletPath: {}, RequestURI: {}, Path: {}, Method: {}", contextPath, servletPath, requestURI, path, method);

        if (path == null || "".equals(path) || "/".equals(path)) {
            response.sendRedirect(uri + "/index.html");
        }

        if (path.contains(".json") || path.contains(".api")) {
            String fullUrl = path;

            if(GET.equalsIgnoreCase(method)) {
                if (request.getQueryString() != null && request.getQueryString().length() > 0) {
                    fullUrl += "?" + request.getQueryString();
                }
            }else if(POST.equalsIgnoreCase(method)){
                byte[] buffer = new byte[BUFFER_SIZE];
                int bodySize = request.getContentLength();
                int size = request.getInputStream().read(buffer,0, bodySize);
                body = new String(buffer, Charset.defaultCharset()).trim();
                request.getInputStream().close();
                LOG.debug("Request Body: {}",body);
            }
            response.getWriter().print(process(method,fullUrl,body));
            return;
        }

        returnResourceFile(path, uri, response);
    }

    protected String getFilePath(String fileName) {
        return resourcePath + fileName;
    }

    protected void returnResourceFile(String fileName, String uri, HttpServletResponse response)
            throws ServletException,
            IOException {

        String filePath = getFilePath(fileName);
        LOG.debug("resource file path: {}",filePath);

        if (fileName.endsWith(".jpg")) {
            byte[] bytes = FileUtils.readByteArrayFromResource(filePath);
            if (bytes != null) {
                response.getOutputStream().write(bytes);
            }
            return;
        }

        String text = FileUtils.readFromResource(filePath);
        if (text == null) {
            response.sendRedirect(uri + "/index.html");
            return;
        }

        if (filePath.endsWith(".html")) {
            response.setContentType("text/html; charset=utf-8");
        } else if (fileName.endsWith(".css")) {
            response.setContentType("text/css;charset=utf-8");
        } else if (fileName.endsWith(".js")) {
            response.setContentType("text/javascript;charset=utf-8");
        }
        response.getWriter().write(text);
    }

    protected abstract String process(String method,String url, String body);
}
