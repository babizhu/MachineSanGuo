package experiment.netty.protobuf;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Formatter;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Pattern;

/**
 * user         LIUKUN
 * time         2014/5/25 0025 14:32
 */

public class WorldClockClientHandler extends SimpleChannelInboundHandler<WorldClockProtocol.LocalTimes>{

    private static final Pattern DELIM = Pattern.compile( "/" );

    // Stateful properties
    private volatile Channel channel;
    private final BlockingQueue<WorldClockProtocol.LocalTimes> answer = new LinkedBlockingQueue<>();

    public WorldClockClientHandler() {
        super(false);
    }

    public List<String> getLocalTimes(Collection<String> cities) {
        WorldClockProtocol.Locations.Builder builder = WorldClockProtocol.Locations.newBuilder();

        for (String c: cities) {
            String[] components = DELIM.split(c);
            builder.addLocation( WorldClockProtocol.Location.newBuilder().
                    setContinent( WorldClockProtocol.Continent.valueOf( components[0].toUpperCase() )).
                    setCity(components[1]).build());
        }

        channel.writeAndFlush(builder.build());

        WorldClockProtocol.LocalTimes localTimes;
        boolean interrupted = false;
        for (;;) {
            try {
                localTimes = answer.take();
                break;
            } catch (InterruptedException ignore) {
                interrupted = true;
            }
        }

        if (interrupted) {
            Thread.currentThread().interrupt();
        }

        List<String> result = new ArrayList<>();
        for (WorldClockProtocol.LocalTime lt: localTimes.getLocalTimeList()) {
            result.add(
                    new Formatter().format(
                            "%4d-%02d-%02d %02d:%02d:%02d %s",
                            lt.getYear(),
                            lt.getMonth(),
                            lt.getDayOfMonth(),
                            lt.getHour(),
                            lt.getMinute(),
                            lt.getSecond(),
                            lt.getDayOfWeek().name()).toString());
        }

        return result;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        channel = ctx.channel();
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, WorldClockProtocol.LocalTimes times) {
        answer.add(times);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}