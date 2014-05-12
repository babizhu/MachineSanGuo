package experiment.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * user         LIUKUN
 * time         2014-5-12 22:26
 * <p/>
 * 原始的select（poll）模式的同步多路复用例子
 */

public class NioEchoServer{

    public static void main( String[] args ) throws IOException{
        new NioEchoServer().server( 8000 );
    }

    public void server( int port ) throws IOException{
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        ServerSocket ss = serverChannel.socket();
        InetSocketAddress address = new InetSocketAddress( port );

        ss.bind( address );

        serverChannel.configureBlocking( false );

        Selector selector = Selector.open();

        serverChannel.register( selector, SelectionKey.OP_ACCEPT );
        int count = 0;

        while( true ) {
            selector.select();

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while( iterator.hasNext() ) {
                SelectionKey key = iterator.next();
                iterator.remove();
                ;

                if( key.isAcceptable() ) {
                    SocketChannel client = serverChannel.accept();
                    System.out.println( "client" + client.getRemoteAddress() + " is connected, Total count is " + count++ );
                    client.configureBlocking( false );
                    client.register( selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE, ByteBuffer.allocate( 100 ) );

                }

                if( key.isReadable() ) {
                    SocketChannel client = (SocketChannel) key.channel();
                    ByteBuffer attachment = (ByteBuffer) key.attachment();
                    client.read( attachment );
                    //System.out.println( attachment );
                }

                if( key.isWritable() ) {
                    SocketChannel client = (SocketChannel) key.channel();
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    buffer.flip();
                    client.write( buffer );
//                    System.out.println( buffer );
                    buffer.compact();
//                    System.out.println( buffer );
                }

            }
        }


    }
}
