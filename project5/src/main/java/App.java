import edu.princeton.cs.algs4.Point2D;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by nm on 17.6.17.
 */
public class App {
    public static void main(String[] args) {
        try {
            KdTree kdTree = new KdTree();
            Class<?> clazz = Class.forName("KdTree$PointHolder");
            Constructor<?> constructor = clazz.getDeclaredConstructor(Point2D.class);
            constructor.setAccessible(true);
            Object o = constructor.newInstance(new Point2D(0,0));
            System.out.println(o.getClass().getName());
            Method getDivAxis = o.getClass().getMethod("getDivAxis", null);
            getDivAxis.setAccessible(true);
            Boolean x = (Boolean) getDivAxis.invoke(o);
            System.out.println(x);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
