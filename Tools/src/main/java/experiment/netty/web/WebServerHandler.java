package experiment.netty.web;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.util.List;
import java.util.Map;

import static io.netty.handler.codec.http.HttpHeaders.Names.*;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

//import io.netty.handler.codec.http.HttpHeaderUtil;import static io.netty.handler.codec.http.HttpVersion.*;


/**
 * user         LIUKUN
 * time         2014-5-20 16:48
 */

public class WebServerHandler extends ChannelHandlerAdapter{

    private static final byte[] CONTENT = {'H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r', 'l', 'd'};

    /**
     * Returns {@code true} if and only if the connection can remain open and
     * thus 'kept alive'.  This methods respects the value of the
     * {@code "Connection"} header first and then the return value of
     * {@link HttpVersion#isKeepAliveDefault()}.
     */
    public static boolean isKeepAlive( HttpMessage message ){
        String connection = message.headers().get( HttpHeaders.Names.CONNECTION );
        if( connection != null && AsciiString.equalsIgnoreCase( HttpHeaders.Values.CLOSE, connection ) ) {
            return false;
        }

        if( message.getProtocolVersion().isKeepAliveDefault() ) {
            return !AsciiString.equalsIgnoreCase( HttpHeaders.Values.CLOSE, connection );
        } else {
            return AsciiString.equalsIgnoreCase( HttpHeaders.Values.KEEP_ALIVE, connection );
        }
    }

    @Override
    public void channelActive( ChannelHandlerContext ctx ) throws Exception{
        System.out.println( ctx.channel().remoteAddress() + " connected" );
    }

    @Override
    public void channelInactive( ChannelHandlerContext ctx ) throws Exception{
        System.out.println( ctx.channel().remoteAddress() + " disConnected" );
    }

    @Override
    public void channelReadComplete( ChannelHandlerContext ctx ) throws Exception{
        ctx.flush();
    }

    @Override
    public void channelRead( ChannelHandlerContext ctx, Object msg ) throws Exception{

        if( msg instanceof HttpRequest ) {
            HttpRequest req = (HttpRequest) msg;
            for( Map.Entry<String, String> h : req.headers() ) {
                System.out.println( "HEADER: " + h.getKey() + " = " + h.getValue() );
            }
            // 解析请求参数
            QueryStringDecoder queryStringDecoder = new QueryStringDecoder( req.getUri() );
            Map<String, List<String>> params = queryStringDecoder.parameters();
            if( !params.isEmpty() ) {
                for( Map.Entry<String, List<String>> p : params.entrySet() ) {
                    String key = p.getKey();
                    List<String> vals = p.getValue();
                    System.out.println( vals.size() );
                    for( String val : vals ) {
                        System.out.println( "PARAM: " + key + " = " + val );
                    }
                }
            }

            boolean keepAlive = isKeepAlive( req );
            FullHttpResponse response = new DefaultFullHttpResponse( HTTP_1_1, OK, Unpooled.wrappedBuffer( CONTENT ) );

            Unpooled.copiedBuffer( CONTENT );
            response.headers().set( CONTENT_TYPE, "text/plain" );
            response.headers().set( CONTENT_LENGTH, response.content().readableBytes() );

            if( !keepAlive ) {
                ctx.write( response ).addListener( ChannelFutureListener.CLOSE );
            } else {
                System.out.println( "keepAlive:" + keepAlive );
                response.headers().set( CONNECTION, HttpHeaders.Values.KEEP_ALIVE );
                ctx.write( response );
            }
        }
    }

    private static class AsciiString{
        static boolean equalsIgnoreCase( CharSequence str1, CharSequence str2 ){
            return str1.toString().equalsIgnoreCase( str2.toString() );
        }
    }
}
