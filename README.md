# seek-and-read-HDD
This project provides tools for testing seek and scanning consecutive blocks time on HDD. This documents introduce how these tools are used. FileGenerator, TestScan and TestSeek should be transformed to executable jar file at the beginning. You can modify the pom.xml to specify a Main Class and then use `mvn package` command. 

## FileGenerator

This tool generates a file of a specified size. The file name is given by the user. The file will be filled with zeroes. It takes 2 parameters:

1. name of the output file, such as "test"
2. size of the output file, such as "256KB", "2MB", "16MB", etc.

Here's an example:
```
java -jar FileGenerator.jar test 256KB
```

## TestSeek
This tool tests average seek time and seek speed. It performs seek on a file from a position to another with the specified span. When it hit the end of the file or the position exceeds the length of the file, it actually returns to the begin of the file by modular arithmetic. However, in that case, the result will not be taken into account since the seek is not sequantial. The tool takes 3 parameters:

1. name of the input file, such as "test"
2. span size, such as "256K"
3. iteration number, such as "5000"

Here's an example:
```
java -jar TestSeek.jar test 256K 5000
```

## TestScan
Put the test_scan.sh and TestScan.jar to a same directory, and then run test_scan.sh. The reason to use shell script is to execute Linux command to clear the system cache. The number of parameters of test_scan.sh is not fixed, while each parameter specifies the size of span 
