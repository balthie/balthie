package org.balthie.demo.jdk.nio.channel.file;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileChannelTest2
{
    // 将一个数据源通过FileChannel，转移到另一个数据源，还有一种更加简单的方法
    public static void main(String[] args) throws IOException
    {
        try (FileChannel inChannel = FileChannel.open(Paths.get("D://fileChannelIn.txt"), StandardOpenOption.READ);
                FileChannel outChannel = FileChannel.open(Paths.get("D://fileChannelOut.txt"), StandardOpenOption.WRITE);)
        {
            // 第二个参数表示，数据转移的起始位置，第三个参数表示转移的长度
            // channel.size()表示通道的长度
            outChannel.transferFrom(inChannel, 0, inChannel.size());
            // 以下方式也可
            inChannel.transferTo(0, inChannel.size(), outChannel);
        }
    }
}
