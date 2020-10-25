package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Author:Young
 * Date:2020/10/1-20:33
 */

//Scattering:将数据写入到buffer时，可以采用buffer数组，依次写入【分散】
//Gathering：从buffer读取数据时，可以采用buffer数组，依次读【聚集】
public class ScatteringAndGathering {

    public static void main(String[] args) throws IOException {
        //使用ServerSocketChannel和SocketChannel网络
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(6666);

        //绑定端口到socket，并启动
        serverSocketChannel.socket().bind(inetSocketAddress);

        //创建buffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        //等客户端连接（telnet）
        SocketChannel socketChannel = serverSocketChannel.accept();
        int messageLength = 8;
        while (true){

            int byteRead = 0;
            while (byteRead < messageLength){
                long l = socketChannel.read(byteBuffers);
                byteRead += l;//累计读取的字节数
                System.out.println("byteRead=" + byteRead);
                //使用流打印，看看当前的这个buffer的position和limite
                Arrays.asList(byteBuffers).stream().map(buffer -> "position=" + buffer.position() + ",limite=" + buffer.limit()).forEach(System.out::println);
            }
            //将所有的buffer，进行flip
            Arrays.asList(byteBuffers).forEach(ByteBuffer::flip);

            //将数据显示到客户端
            long byteWrite = 0;
            while (byteWrite < messageLength){
                long l = socketChannel.write(byteBuffers);
                byteWrite += l;
            }

            //将所有的buffer，进行clear
            Arrays.asList(byteBuffers).forEach(ByteBuffer::clear);
            System.out.println("byteRead=" + byteRead + ",byteWrite=" + byteWrite + ",messageLength" + messageLength);
        }
    }

}
