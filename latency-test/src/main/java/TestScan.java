import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TestScan {

  public static void main(String[] args) throws IOException {
    if (args.length > 0) {
      Path file = Paths.get(args[0]);
      FileChannel fileChannel = FileChannel.open(file, StandardOpenOption.READ);
      long blockSize = Util.getLength(args[1]);
      ByteBuffer buffer = ByteBuffer.allocate((int) blockSize);
      long position = 128;
      fileChannel.position(position);
      long time = System.currentTimeMillis();
      fileChannel.read(buffer, position);
      time = System.currentTimeMillis() - time;
      System.out.println(time);
      fileChannel.close();
    }


  }
}
