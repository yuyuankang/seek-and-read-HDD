import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AnalyzeSeek {

  static File testFile;
  static long fileSize;

  public static void analyze(File file) throws IOException {
    long sum = 0;
    long counter = 0;
    String sizeString = file.getName().split("_")[1];
    long blockSize = Util.getLength(sizeString);
    BufferedReader reader = new BufferedReader(new FileReader(file));
    String line;
    while ((line = reader.readLine()) != null) {
      sum += Integer.parseInt(line);
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
        testFile.getAbsolutePath(), fileSize, blockSize, sum, averageTime, speed);
  }

  public static void analyze(ArrayList<File> files) throws IOException {
    for (File file : files) {
      analyze(file);
      file.delete();
    }
  }

  public static void main(String[] args) throws IOException {
    String testFilePath = args[0];
    String currentPath = args[1];
    File[] files = new File(currentPath).listFiles();
    ArrayList<File> tempFiles = new ArrayList<>();
    for (File file : files) {
      if (file.getName().startsWith("temp")) {
        tempFiles.add(file);
      }
    }
    testFile = new File(testFilePath);
    fileSize = testFile.length();
    analyze(tempFiles);
  }
}
