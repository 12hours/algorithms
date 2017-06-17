import edu.princeton.cs.algs4.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KdTree {
    private class PointHolder {
        public static final boolean X = true;
        public static final boolean Y = false;

        private boolean coordinate;
        private Point2D point;
        private PointHolder left;
        private PointHolder right;

        public RectHV getRectangle() {
            return rectangle;
        }

        public void setRectangle(RectHV rectangle) {
            this.rectangle = rectangle;
        }

        private RectHV rectangle;

        public boolean getDivAxis() {
            return coordinate;
        }

        public void setDivAxis(boolean coordinate) {
            this.coordinate = coordinate;
        }

        public PointHolder(Point2D point) {
            this.point = point;
        }

        public Point2D getPoint() {
            return point;
        }

        public PointHolder getLeft() {
            return left;
        }

        public void setLeft(PointHolder left) {
            this.left = left;
        }

        public PointHolder getRight() {
            return right;
        }

        public void setRight(PointHolder right) {
            this.right = right;
        }

    }

    private class TwoDTree {
        // Division coordinate of the phantom point before root; root will be divided on inverted coordinate;
        private static final boolean BEFORE_ROOT_COORDINATE = PointHolder.Y;
        private final double[] BOARD = new double[]{0.0, 0.0, 1.0, 1.0};
        private PointHolder root;
        private int size;

        public TwoDTree() {
            this.root = null;
            this.size = 0;
        }

        public void insert(Point2D point) {
            PointHolder newPointHolder = new PointHolder(point);
            root = insert(root, newPointHolder, BEFORE_ROOT_COORDINATE, BOARD[0], BOARD[1], BOARD[2], BOARD[3]);
        }

        private PointHolder insert(PointHolder currPoint, PointHolder newPoint, boolean prevDivAxis,
                                   double minX, double minY, double maxX, double maxY) {
            // we can insert if we are at empty leaf of the tree
            if (currPoint == null) {
                newPoint.setDivAxis(!prevDivAxis);
                newPoint.setRectangle(new RectHV(minX, minY, maxX, maxY));
                this.size++;
                return newPoint;
            }

            // check if current point is the same as goal point, so no inserting needed
            if (currPoint.getPoint().compareTo(newPoint.getPoint()) == 0) return currPoint;

            // get the comparator according to what is current node division axis
            boolean currDivAxis = currPoint.getDivAxis();
            Comparator<Point2D> comparator;
            if (currDivAxis == PointHolder.X) {
                comparator = Point2D.X_ORDER;
            } else {
                comparator = Point2D.Y_ORDER;
            }

            int cmp = comparator.compare(newPoint.getPoint(), currPoint.getPoint());
            if (cmp < 0) {
                // we have to find borders of new rectangle. New rectangle is divided in two halfs
                // according to current divAxis by current node's point
                if (currDivAxis == PointHolder.X) maxX = currPoint.getPoint().x();
                else maxY = currPoint.getPoint().y();
                currPoint.setLeft(insert(currPoint.getLeft(), newPoint, currDivAxis,
                        minX, minY, maxX, maxY));
            } else if (cmp >= 0) {
                if (currDivAxis == PointHolder.X) minX = currPoint.getPoint().x();
                else minY = currPoint.getPoint().y();
                currPoint.setRight(insert(currPoint.getRight(), newPoint, currDivAxis,
                        minX, minY, maxX, maxY));
            }

            return currPoint;
        }

        public Point2D find(Point2D point) {
            return find(root, point, BEFORE_ROOT_COORDINATE);
        }

        private Point2D find(PointHolder currPoint, Point2D goalPoint, boolean prevCoordinate) {
            if (currPoint == null) return null;
            if (currPoint.getPoint().compareTo(goalPoint) == 0) return currPoint.getPoint();

            boolean currCoordinate = currPoint.getDivAxis();
            Comparator<Point2D> comparator;
            if (currCoordinate == PointHolder.X) {
                comparator = Point2D.X_ORDER;
            } else {
                comparator = Point2D.Y_ORDER;
            }

            int cmp = comparator.compare(goalPoint, currPoint.getPoint());
            if (cmp < 0) return find(currPoint.getLeft(), goalPoint, currCoordinate);
            else if (cmp >= 0) return find(currPoint.getRight(), goalPoint, currCoordinate);

            return null;
        }

        public List<Point2D> findRange(RectHV rect) {
            List<Point2D> pointSet = new ArrayList<Point2D>();
            findRange(root, rect, pointSet);
            return pointSet;
        }

        private void findRange(PointHolder currPoint, RectHV rect, List<Point2D> pointSet) {
//            if (!rect.intersects(currPoint.getRectangle())) return;
            if (rect.contains(currPoint.getPoint())) pointSet.add(currPoint.getPoint());

            if (currPoint.getLeft() != null &&
                    rect.intersects(currPoint.getLeft().getRectangle())) findRange(currPoint.getLeft(), rect, pointSet);
            if (currPoint.getRight() != null &&
                    rect.intersects(currPoint.getRight().getRectangle())) findRange(currPoint.getRight(), rect, pointSet);
        }

        public Point2D findNearest(Point2D goalPoint) {
            if (root == null) return null;
            // we assume that root is nearest point if it's only point in the set
            return find(root, goalPoint, root.getPoint());
        }

        private Point2D find(PointHolder currentPoint, Point2D goalPoint, Point2D currentNearest) {
            if (currentPoint.getPoint().distanceTo(goalPoint) < currentNearest.distanceTo(goalPoint))
                currentNearest = currentPoint.getPoint();

            PointHolder leftPoint = currentPoint.getLeft();
            PointHolder rightPoint = currentPoint.getRight();

            Point2D newNearest = currentNearest;

            if (leftPoint != null) {
                if (leftPoint.getRectangle().distanceTo(goalPoint) < currentNearest.distanceTo(goalPoint)) {
                    newNearest = find(leftPoint, goalPoint, currentNearest);
                    if (newNearest.distanceTo(goalPoint) < currentNearest.distanceTo(goalPoint)) {
                        currentNearest = newNearest;
                    }
                }
            }
            if (rightPoint != null) {
                if (rightPoint.getRectangle().distanceTo(goalPoint) < currentNearest.distanceTo(goalPoint)) {
                    newNearest = find(rightPoint, goalPoint, currentNearest);
                    if (newNearest.distanceTo(goalPoint) < currentNearest.distanceTo(goalPoint)) {
                        currentNearest = newNearest;
                    }
                }
            }

            return currentNearest;
        }

        public Iterable<PointHolder> allPointHolders(){
            List<PointHolder> lst = new ArrayList<>();
            if (root != null){
                allPointHolders(root, lst);
            }
            return lst;
        }

        private void allPointHolders(PointHolder point, List<PointHolder> lst) {
            if (point == null)  return;
            lst.add(point);
            allPointHolders(point.getLeft(), lst);
            allPointHolders(point.getRight(), lst);
        }

        public boolean isEmpty() {
            return root == null;
        }

        public int getSize() {
            return size;
        }
    }

    // ============================================================= //

    private TwoDTree twoDTree;

    /**
     * construct an empty set of points
     */
    public KdTree() {
        this.twoDTree = new TwoDTree();
    }

    /**
     * @return is the set empty?
     */
    public boolean isEmpty() {
        return twoDTree.isEmpty();
    }

    /**
     * @return number of points in the set
     */
    public int size() {
        return twoDTree.getSize();
    }

    /**
     * add the point to the set (if it is not already in the set)
     *
     * @param p point
     */
    public void insert(Point2D p) {
        twoDTree.insert(p);
    }

    /**
     * @param p target point
     * @return does the set contain point p?
     */
    public boolean contains(Point2D p) {
        return twoDTree.find(p) != null;
    }

    /**
     * draw all points to standard draw
     */
    public void draw() {
        RectHV fullRect = new RectHV(0, 0, 1, 1);
        for (PointHolder point : twoDTree.allPointHolders()) {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.01);
            StdDraw.point(point.getPoint().x(), point.getPoint().y());

            StdDraw.setPenRadius(0.001);
            if(point.getDivAxis() == PointHolder.X){
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.line(point.getPoint().x(), point.getRectangle().ymin(), point.getPoint().x(), point.getRectangle().ymax());
            } else {
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.line(point.getRectangle().xmin(), point.getPoint().y(),point.getRectangle().xmax(), point.getPoint().y());
            };
        }
        StdDraw.show();
    }

    /**
     * @param rect target rectangle
     * @return all points that are inside the rectangle
     */
    public Iterable<Point2D> range(RectHV rect) {
        return twoDTree.findRange(rect);
    }

    /**
     * @param p target point
     * @return a nearest neighbor in the set to point p; null if the set is empty
     */
    public Point2D nearest(Point2D p) {
        return twoDTree.findNearest(p);
    }

    public static void main(String[] args) {
        KdTree kdTree = new KdTree();
        for (int i = 0; i < 20; i++) {
            double x = StdRandom.uniform(0.0, 1.0);
            double y = StdRandom.uniform(0.0, 1.0);
            kdTree.insert(new Point2D(x, y));
            StdOut.printf("%8.6f %8.6f\n", x, y);
        }

        kdTree.draw();
    }
}