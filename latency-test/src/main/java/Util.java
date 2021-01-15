public class Util {

  private enum UNIT {
    B, BYTES, BYTE, K, KB, M, MB, G, GB, T, TB
  }

  /**
   * check if the given character is a number or not
   *
   * @param c, is given character
   * @return true, if the give character is a number, false otherwise
   */
  private static boolean isNum(char c) {
    return c >= '0' && c <= '9';
  }

  /**
   * transform a unit to length of bytes
   *
   * @param unitString, given unit string, e.g., "KB", "MB"
   * @return length of bytes. e.g., 1 KB = 1024 bytes, so the result will be 1024
   */
  private static long getUnit(String unitString) {
    long unit = 1;
    switch (UNIT.valueOf(unitString.toUpperCase())) {
      case B:
      case BYTES:
      case BYTE:
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

  /**
   * Calculate the number of bytes the input size string means.
   *
   * @param sizeString, size string, e.g., "2KB"
   * @return number of bytes, e.g., 2 KB = 2048 bytes, so the result should be 2048
   */
  public static long getLength(String sizeString) {
    boolean recognized = false;
    String unitString = "";
    long size = 0;
    for (int i = 0; i < sizeString.length(); i++) {
      if (!isNum(sizeString.charAt(i))) {
        size = Integer.parseInt(sizeString.substring(0, i));
        unitString = sizeString.substring(i);
        recognized = true;
        break;
      }
    }
    if (!recognized) {
      throw new IllegalArgumentException("Unrecognized size string \"" + sizeString + "\"");
    }
    return size * getUnit(unitString);
  }
}
