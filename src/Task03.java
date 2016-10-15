import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Task03 {

  public static void main(String[] args) throws IOException {
    //test data
//            String input = "Ana";
//            ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
//            System.setIn(bais);

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    String word = in.readLine();
    StringBuilder sb = new StringBuilder(word);

    int endIndex = sb.length();
    int currentIndex = 0;
    sb.insert(endIndex, sb.charAt(currentIndex++));//the second word is not empty, so let's add at least one letter
    while (!isPalyndrome(sb)) {
      sb.insert(endIndex, sb.charAt(currentIndex));
      currentIndex++;
    }

    PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    out.print(sb.toString());
    out.flush();
  }

  private static boolean isPalyndrome(StringBuilder sb) {
    int length = sb.length() - 1;
    for (int i = 0, j = length; i <= j; i++, j--) {
//      char charAtI = toLowerCase(sb.charAt(i)); //just in case 'coz the problem is buggy!!!
//      char charAtJ = toLowerCase(sb.charAt(j));
      if (sb.charAt(j) != sb.charAt(i)) {
        return false;
      }
    }
    return true;
  }

  @SuppressWarnings("unused")
  private static char toLowerCase(char c) {
    if (97 <= c && c <=122) {
      return c;
    }
    return (char) (c + 32);
  }
}
