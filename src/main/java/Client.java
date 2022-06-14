import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Client extends Thread {
    private static final String END_WORD = "end";
    private static final int SLEEP_TIME = 2000;

    @Override
    public void run() {
        try {
            InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 23334);
            SocketChannel channel = SocketChannel.open();
            channel.connect(socketAddress);

            ByteBuffer buffer = ByteBuffer.allocate(2 << 10);
            String userInput = "";

            while (!userInput.equals(END_WORD)) {
                userInput = InputReader.getUserInput();

                channel.write(ByteBuffer.wrap(userInput.getBytes(StandardCharsets.UTF_8)));

                Thread.sleep(SLEEP_TIME);

                int bytesCount = channel.read(buffer);
                System.out.println(new String(buffer.array(), 0, bytesCount, StandardCharsets.UTF_8).trim());
                buffer.clear();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
