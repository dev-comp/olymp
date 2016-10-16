import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class Task08 {


  public static void main(String[] args) throws IOException {

    //test data
    //    String input = "oycrptagrhpy";
    //    ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
    //    System.setIn(bais);

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    String word = in.readLine();
    String decrypt = decrypt(word);

    PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    out.print(decrypt);
    out.flush();
  }

  /**
   * Encrypts data according to the algirithm described in the task
   * @param input
   * @return
   */
  public static String encrypt(final String input) {
    int initialCapacity = input.length();
    List<Integer> encryptPositions = calcPositions(initialCapacity);

    char[] chars = new char[initialCapacity];
    int cnt = 0;
    for (Integer decryptPosition : encryptPositions) {
      chars[cnt] = input.charAt(decryptPosition);
      cnt++;
    }
    return new String(chars);
  }

  public static void calcPositions(Stack<List<Integer>> listStack, List<Integer> decryptPositions) {
    List<Integer> positions = listStack.pop();
    int len = positions.size();

    if (len <= 2) {
      decryptPositions.addAll(positions);
    } else {

      int middleCharAdjuster = len % 2 == 0 ? -1 : 0;
      decryptPositions.add(positions.get(len / 2 + middleCharAdjuster));

      listStack.push(positions.subList(len / 2 + middleCharAdjuster + 1, len));
      listStack.push(positions.subList(0, len / 2 + middleCharAdjuster));

      while (!listStack.isEmpty()) {
        calcPositions(listStack, decryptPositions);
      }
    }
  }

  public static String decrypt(String input) {
    int initialCapacity = input.length();
    List<Integer> encryptPositions = calcPositions(initialCapacity);

    char[] sb = new char[initialCapacity];
    StringBuilder encr = new StringBuilder(input);

    int cnt = 0;
    for (Integer encryptPosition : encryptPositions) {
      sb[encryptPosition] = encr.charAt(cnt);
      cnt++;
    }

    return new String(sb);
  }

  private static List<Integer> calcPositions(int initialCapacity) {
    Stack<List<Integer>> stack = new Stack<>();
    ArrayList<Integer> item = new ArrayList<>(initialCapacity);
    for (int i = 0; i < initialCapacity; i++) {
      item.add(i);
    }
    stack.push(item);
    List<Integer> encryptPositions = new ArrayList<>(initialCapacity);
    calcPositions(stack, encryptPositions);
    return encryptPositions;
  }
}
