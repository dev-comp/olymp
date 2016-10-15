import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Task01 {

  private static class RaceTime {
    long timeOnTrack;
    String name;
    long finishedAt;
  }

  public static void main(String[] args) throws IOException {
    //test data
    //    String input = "6\n" + "Valera 21:38.2\n" + "Anatoly 21:21.0\n" + "Mikhail 22:04.4\n" + "John 22:06.1\n" + "Boris 21:11.1\n" + "Oleg 22:05.8";
    //    ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
    //    System.setIn(bais);

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String line;

    //noinspection ResultOfMethodCallIgnored ignore first line. it contains redundant info
    Integer.parseInt(in.readLine());

    List<String> participants = new ArrayList<String>();
    while ((line = in.readLine()) != null) {
      participants.add(line);
    }

    HashMap<Long, RaceTime> arrivalTimeMap = new HashMap<Long, RaceTime>();
    List<Long> arrivalTimes = new ArrayList<Long>();
    int cnt = 0;

    for (String participant : participants) {
      cnt++;
      String[] inputString = participant.split(" ");

      RaceTime raceTime = new RaceTime();
      String name = inputString[0];
      String time = inputString[1];
      String[] split = time.split("[^\\w]");
      long min = Long.parseLong(split[0]);
      long sec = Long.parseLong(split[1]);
      long ms = Long.parseLong(split[2]);

      raceTime.timeOnTrack = min * 60 * 10 + sec * 10 + ms;
      raceTime.name = name;
      raceTime.finishedAt = (cnt - 1) * 30 * 10 + raceTime.timeOnTrack;
      arrivalTimeMap.put(raceTime.finishedAt, raceTime);
      arrivalTimes.add(raceTime.finishedAt);

    }
    Collections.sort(arrivalTimes);

    List<String> leadersList = new ArrayList<String>();
    Long leaderTime = arrivalTimeMap.get(arrivalTimes.get(0)).timeOnTrack;
    leadersList.add(arrivalTimeMap.get(arrivalTimes.get(0)).name);

    for (Long arrivalTime : arrivalTimes) {
      long timeOnTrack = arrivalTimeMap.get(arrivalTime).timeOnTrack;
      if (timeOnTrack < leaderTime) {
        leaderTime = timeOnTrack;
        leadersList.add(0, arrivalTimeMap.get(arrivalTime).name);
      }
    }

    Collections.sort(leadersList);
    StringBuilder sb = new StringBuilder();
    for (String s1 : leadersList) {
      sb.append(s1).append("\n");
    }

    PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    out.print(leadersList.size() + "\n" + sb.toString());
    out.flush();
  }
}
