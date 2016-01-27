import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * G1Main class
 */
public class G1Main
{
  public static void main(String[] args) {
    Random random = new Random(  );
    while(true) {
      int[] list = new int[random.nextInt(2000000)];
      for (int i = 0; i < list.length; i++)
        list[i] = i;
    }
  }

}
