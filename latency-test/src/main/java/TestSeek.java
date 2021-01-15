import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TestSeek {

  private static void testSequentialSeek(File file, long blockSize, int iterationNum)
      throws IOException {
    long fileLength = file.length();
    long sum = 0;
    long counter = 0;
    FileChannel fileChannel = FileChannel
        .open(Paths.get(file.getAbsolutePath()), StandardOpenOption.READ);
    long position = 0;
    fileChannel.position(position);
    long start;
    while (iterationNum-- > 0) {
      position += blockSize;
      if (position > fileLength) {
        position %= fileLength;
        fileChannel.position(position);
        continue;
      }
      start = System.currentTimeMillis();
      fileChannel.position(position);
      sum += (System.currentTimeMillis() - start);
      counter++;
    }
    double averageTime = (double) sum / counter;
    double speed = (double) blockSize * counter / sum;
    System.out.printf(
        "File: %s;\n"
            + "File size: %d(bytes);\n"
            + "Block size: %d(bytes);\n"
            + "Total time: %d(ms);\n"
            + "Average time: %f(ms/blc);\n"
            + "Seek Speed: %f(bytes/ms)%n",
        file.getAbsolutePath(), fileLength, blockSize, sum, averageTime, speed);
  }

  public static void main(String[] args) throws IOException {
    if (args.length > 0) {
      String filePath = args[0];
      long length = Util.getLength(args[1]);
      int iterations = Integer.parseInt(args[2]);
      File file = new File(filePath);
      testSequentialSeek(file, length, iterations);

    }
  }
}
