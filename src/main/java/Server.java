import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Server extends Thread {
    @Override
    public void run() {
        try {
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.bind(new InetSocketAddress("127.0.0.1", 23334));

            while (true) {
                try (SocketChannel socketChannel = serverChannel.accept()) {
                    ByteBuffer buffer = ByteBuffer.allocate(2 << 10);

                    while (socketChannel.isConnected()) {
                        int bytesCount = socketChannel.read(buffer);

                        if (bytesCount == -1) {
                            break;
                        }

                        String userLine = new String(buffer.array(), 0, bytesCount, StandardCharsets.UTF_8).trim();
                        buffer.clear();

                        String convertedLine = SpaceRemover.remove(userLine);
                        socketChannel.write(ByteBuffer.wrap(convertedLine.getBytes(StandardCharsets.UTF_8)));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
