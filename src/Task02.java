import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Task02 {

  public static void main(String[] args) throws IOException {
    //test data
    //    String input = "99";
    //    ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
    //    System.setIn(bais);

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    out.print(numSquares(Integer.parseInt(in.readLine())));
    out.flush();
  }

  /**
   * see https://en.wikipedia.org/wiki/Lagrange%27s_four-square_theorem
   */
  private static int numSquares(int n) {
    while (n % 4 == 0) n /= 4;
    if (n % 8 == 7) return 4;
    for (int a = 0; a * a <= n; ++a) {
      int b = (int) Math.sqrt(n - a * a);
      if (a * a + b * b == n) {
        return (a != 0 && b != 0) ? 2 : 1;
      }
    }
    return 3;
  }
}
