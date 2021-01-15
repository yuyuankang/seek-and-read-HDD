import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileGenerator {

  private static void createFixLengthFile(File file, long length) throws IOException {
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

  private static boolean isNum(char c) {
    return c >= '0' && c <= '9';
  }

  private enum UNIT {
    B, BYTES, BYTE, K, KB, M, MB, G, GB, T, TB
  }

  private static long getUnit(String unitString) {
    long unit = 1;
    switch (UNIT.valueOf(unitString.toUpperCase())) {
      case B:
      case BYTES:
      case BYTE:
        unit = 1;
        break;
      case K:
      case KB:
        unit = 1024;
        break;
      case M:
      case MB:
        unit = (long) Math.pow(1024, 2);
        break;
      case G:
      case GB:
        unit = (long) Math.pow(1024, 3);
        break;
      case T:
      case TB:
        unit = (long) Math.pow(1024, 4);
        break;
      default:
        throw new IllegalArgumentException("Unrecognized unit string: \"" + unitString + "\"");
    }
    return unit;
  }

  public static void main(String[] args) throws IOException {
    if (args.length > 0) {
      String fileName = args[0];
      String sizeString = args[1];
      int size = 0;
      String unitString = "";
      for (int i = 0; i < sizeString.length(); i++) {
        if (!isNum(sizeString.charAt(i))) {
          size = Integer.parseInt(sizeString.substring(0, i));
          unitString = sizeString.substring(i);
          break;
        }
      }
      long unit = getUnit(unitString);
      File file = new File(fileName);
      if (file.exists()) {
        file.delete();
      }
      file.createNewFile();
      createFixLengthFile(file, size * unit);
    }
  }

}
