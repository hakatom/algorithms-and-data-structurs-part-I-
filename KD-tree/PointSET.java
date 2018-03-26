import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;


public class PointSET {
    private TreeSet<Point2D> points;
    /**
     * construct an empty set of points
     */
   public         PointSET() {
       points = new TreeSet<>();

   }
   /**
    * is the set empty?
    */
   public           boolean isEmpty() {
   return points.isEmpty();    
}
    public int size() {
        return points.size();
    }
   /**
    * add the point to the set(if not already in the set)
    */
 
   public              void insert(Point2D p) {
       checkNull(p);
       if  (!points.contains(p)) {
           points.add(p);
       }
   }
   /**
    * does the set contain point p?
    */
   public           boolean contains(Point2D p) {
       checkNull(p);
       return points.contains(p);
   }
   /**
    * draw all pooints to standart draw
    */
   public              void draw() {

   }

   /**
    * all points that are inside the rectangle
    */
   public Iterable<Point2D> range(RectHV rect) {
       checkNull(rect);
       Point2D minPoint = new Point2D(rect.xmin(), rect.ymin());
       Point2D maxPoint = new Point2D(rect.xmax(), rect.ymax());
       List<Point2D> PointInRect = new LinkedList<>();

       for (Point2D p : points.subSet(minPoint, true, maxPoint,true)) {
           if (p.x() >= rect.xmin() && p.x() <= rect.xmax() ) {
               PointInRect.add(p);
           }
       }
       return PointInRect;
   }            // all points that are inside the rectangle 
   /**
    * a nearest neighbor in theset to point p; ull of the set is empty
    */
   public           Point2D nearest(Point2D p) {
       checkNull(p);
       if (isEmpty()) {
           return null;
       }

       // find a neighbor 
       Point2D next =  points.ceiling(p);
       Point2D prev = points.floor(p);
       if (next == null && prev == null) {
           return null;
       }
       double distNext = next == null ? Double.MAX_VALUE : p.distanceTo(next);
       double distprev = prev == null ? Double.MIN_VALUE : p.distanceTo(prev);
       double d = Math.min(distNext, distprev);

       Point2D minPoint = new Point2D(p.x(), p.y() - d);
       Point2D maxPoint = new Point2D(p.x(), p.y() + d);
       Point2D nearest = next == null ? prev : next;

      /* for (Point2D candidate : points.subSet(minPoint, true, maxPoint, true)); {
           if (p.distanceTo(candidate) < p.distanceTo(nearest)) {
               nearest = candidate;
           }
       }
       */
      for (Point2D candidate: points.subSet(minPoint, true, maxPoint, true)) {
            if (p.distanceTo(candidate) < p.distanceTo(nearest)) {
                nearest = candidate;
            }
}
       return nearest;

   }           
   private void checkNull(Object obj) {
       if (obj == null) {
           throw new NullPointerException();
       }
   }

   public static void main(String[] args) {}                 // unit testing of the methods (optional) 
}