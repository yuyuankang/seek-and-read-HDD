import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileGenerator {

  /**
   * Generate a file given length. The file will be filled with zeroes.
   *
   * @param file,   output file
   * @param length, length of the file
   */
  private static void generateFile(File file, long length) throws IOException {
    long start = System.currentTimeMillis();
    FileOutputStream fos = null;
    FileChannel output = null;
    try {
      fos = new FileOutputStream(file);
      output = fos.getChannel();
      output.write(ByteBuffer.allocate(1), length - 1);
    } finally {
      try {
        if (output != null) {
          output.close();
        }
        if (fos != null) {
          fos.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    long end = System.currentTimeMillis();
    System.out.println(
        "A file is created: " + file.getAbsolutePath() + ", takes " + (end - start) + " ms");
  }


  public static void main(String[] args) throws IOException {
    if (args.length > 0) {
      String fileName = args[0];
      String sizeString = args[1];
      long length = Util.getLength(sizeString);
      File file = new File(fileName);
      if (file.exists()) {
        file.delete();
      }
      file.createNewFile();
      generateFile(file, length);
    }
  }

}
