import org.junit.Test;
import static org.junit.Assert.*;
public class TestArrayDequeGold {
    @Test
    public void testStudentArrayDeque() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < 500; i += 1) {
            //assertEquals(sad.isEmpty(), ads.isEmpty());
            if (sad.isEmpty() && ads.isEmpty()) {
                sad.addFirst(i);
                ads.addFirst(i);
                message.append("addFirst(").append(i).append(")\n");
            }
            /* @source StudentArrayDequeLauncher.java*/
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.25) {
                sad.addFirst(i);
                ads.addFirst(i);
                message.append("addFirst(").append(i).append(")\n");
                message.append("get(0)\n");
                assertEquals(message.toString(), ads.get(0), sad.get(0));
            } else if (numberBetweenZeroAndOne < 0.5) {
                sad.addLast(i);
                ads.addLast(i);

                int posAds = ads.size() - 1;
                message.append("addLast(").append(i).append(")\n");
                message.append("size()\n");
                message.append("get(").append(posAds).append(")\n");
                assertEquals(message.toString(), ads.get(posAds), sad.get(posAds));
            } else if (numberBetweenZeroAndOne < 0.75) {
                message.append("removeFirst()\n");
                assertEquals(message.toString(), ads.removeFirst(), sad.removeFirst());
            } else {
                message.append("removeLast()\n");
                assertEquals(message.toString(), ads.removeLast(), sad.removeLast());
            }
        }
    }

}
