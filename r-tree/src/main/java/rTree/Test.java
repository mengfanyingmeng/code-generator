package rTree;

import com.vividsolutions.jts.geom.*;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;
import com.vividsolutions.jts.index.strtree.STRtree;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.math.RoundingMode.HALF_UP;

public class Test<type> {

    /**
     * R树查询
     * @param tree
     * @param searchGeo
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static List<Point> query(STRtree tree, Geometry searchGeo){
        List<Point> result=new ArrayList<Point>();
        List list=tree.query(searchGeo.getEnvelopeInternal());
        for (Object object : list) {
            Point point = (Point)object;
            result.add(point);
        }
        return result;
    }

    public static Geometry generateSearchGeo(double left_top_x,double left_top_y,double right_bottom_x,double right_bottom_y){
        Coordinate[] coors=new Coordinate[5];
        coors[0]=new Coordinate(left_top_x, left_top_y);
        coors[1]=new Coordinate(right_bottom_x, left_top_y);
        coors[2]=new Coordinate(left_top_x, right_bottom_y);
        coors[3]=new Coordinate(right_bottom_x, right_bottom_y);
        coors[4]=new Coordinate(left_top_x, left_top_y);
        LinearRing ring = new LinearRing(new CoordinateArraySequence(coors), new GeometryFactory());
        return ring;
    }


    public static void main(String[] args) {
        STRtree tree=new STRtree();
        GeometryFactory factory=new GeometryFactory();

        com.vividsolutions.jts.geom.Point point=factory.createPoint(new Coordinate(112.971585, 28.265007));//长沙
        tree.insert(point.getEnvelopeInternal(), point);

        point=factory.createPoint(new Coordinate(113.040862, 28.192062));//芙蓉区
        tree.insert(point.getEnvelopeInternal(), point);

        point=factory.createPoint(new Coordinate(112.902882, 28.223639));//岳麓区
        tree.insert(point.getEnvelopeInternal(), point);

        point=factory.createPoint(new Coordinate(113.040862, 28.142129));//雨花区
        tree.insert(point.getEnvelopeInternal(), point);

        point=factory.createPoint(new Coordinate(111.491465, 27.269792));//邵阳市
        tree.insert(point.getEnvelopeInternal(), point);
        point=factory.createPoint(new Coordinate(109.982886, 27.630889));//怀化市
        tree.insert(point.getEnvelopeInternal(), point);

        tree.build();
        System.out.println("R树索引创建成功！");
        Geometry searchGeo = Test.generateSearchGeo(112.764903, 28.18409, 113.484697,28.264261);
        List<Point> list = Test.query(tree, searchGeo);

        for (Point p : list) {
            System.out.println("p==>" + p);
            System.out.println(p.getX() + "\t" + p.getY());
        }
        System.out.println("找到数据条数为==> " + list.size());

        int size1 = 2;
        int size2 =12;

        BigDecimal todayOnYest = BigDecimal.valueOf(size1 - size2).divide(BigDecimal.valueOf(size2), 4, HALF_UP);
        System.out.println(todayOnYest);
        todayOnYest = todayOnYest.multiply(BigDecimal.valueOf(100));
        System.out.println(todayOnYest);
    }



}

