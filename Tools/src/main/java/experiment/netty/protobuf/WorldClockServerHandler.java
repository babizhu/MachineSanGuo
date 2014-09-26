package experiment.netty.protobuf;


import experiment.protocolgen.WorldClockProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Calendar;
import java.util.TimeZone;

import static experiment.protocolgen.WorldClockProtocol.*;
import static java.util.Calendar.*;

/**
 * user         LIUKUN
 * time         2014/5/25 0025 14:12
 */

public class WorldClockServerHandler extends SimpleChannelInboundHandler<WorldClockProtocol.Locations>{


    private static String toString( Continent c ){
        return c.name().charAt( 0 ) + c.name().toLowerCase().substring( 1 );
    }

    @Override
    public void messageReceived( ChannelHandlerContext ctx, Locations locations ){
        long currentTime = System.currentTimeMillis();

        LocalTimes.Builder builder = LocalTimes.newBuilder();
        for( Location l : locations.getLocationList() ) {
            TimeZone tz = TimeZone.getTimeZone(
                    toString( l.getContinent() ) + '/' + l.getCity() );
            Calendar calendar = getInstance( tz );
            calendar.setTimeInMillis( currentTime );

            builder.addLocalTime( LocalTime.newBuilder().
                    setYear( calendar.get( YEAR ) ).
                    setMonth( calendar.get( MONTH ) + 1 ).
                    setDayOfMonth( calendar.get( DAY_OF_MONTH ) ).
                    setDayOfWeek( DayOfWeek.valueOf( calendar.get( DAY_OF_WEEK ) ) ).
                    setHour( calendar.get( HOUR_OF_DAY ) ).
                    setMinute( calendar.get( MINUTE ) ).
                    setSecond( calendar.get( SECOND ) ).build() );
        }

//        System.out.println( "WorldClockServerHandler.messageReceived" );
        ctx.write( builder.build() );
    }

    @Override
    public void channelReadComplete( ChannelHandlerContext ctx ){
        ctx.flush();
    }

    @Override
    public void exceptionCaught( ChannelHandlerContext ctx, Throwable cause ){
        cause.printStackTrace();
        ctx.close();
    }
}
