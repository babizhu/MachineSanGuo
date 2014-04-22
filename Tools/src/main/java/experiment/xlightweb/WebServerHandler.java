package experiment.xlightweb;

import org.xlightweb.*;
import org.xlightweb.server.HttpServer;

import java.io.IOException;
import java.util.Set;

/**
 * user         LIUKUN
 * time         2014-4-24 16:35
 */


class WebServerHandler implements IHttpRequestHandler{

    public static void main( String[] args ){
        try {
            HttpServer server = new HttpServer( 80, new WebServerHandler() );
            server.setServerName( "liukun" );

            server.start();
        } catch( IOException e ) {
            e.printStackTrace();
        }
    }

    public void onRequest( IHttpExchange exchange ) throws IOException{

        IHttpRequest req = exchange.getRequest();

        String body = "方法名:";
        body += req.getPathInfo() + "\r\n";

        Set<String> parameterNameSet = req.getParameterNameSet();
        for( String s : parameterNameSet ) {
            body += s + ":";
            body += req.getParameter( s ) + "\r\n";
        }


        IHttpResponse resp = new HttpResponse( "text/plan", body );
//        header.setServer( ServerUtils.getComponentInfo());

        resp.setHeader( "Server", "likun" );
        exchange.send( resp );
    }

}

