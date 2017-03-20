//package learning;
//import java.lang.Math;
//
//class Point{
//	double x;
//	double y;
//	public Point(double x,double y){
//		this.x = x;
//		this.y = y;
//	}
//}
////Simulated Annealing模拟退火算法
////费马点问题求解（给n个点，找出一个点，使这个点到其他所有点的距离最小）
//public class SA_1 {
//	double tMin = 1;
//	
//	public double dist(Point a,Point b){
//		return Math.sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y));
//	}
//	
//	public double getSum(Point[] pl,Point p){
//		int len = pl.length;
//		double sumDist = 0;
//		for(int i=0;i<len;i++){
//			sumDist += dist(pl[i], p);
//		}
//		return sumDist;
//	}
//	
//	public Point search(Point[] pl,double t){
//		Point bestPoint = null;
//		int count = 0;
//		double minDist = 0xFFFF;
//		while(t > tMin){
//			for(int i=0;i<pl.length;i++){
//				double dist = getSum(pl,pl[i]);
//				double dt = dist - minDist;
//				if(dt < 0){
//					count = 0;
//					bestPoint = pl[i];
//					minDist = dist;
//				}else{
//					if(Math.exp(-dt/t) > Math.random()){
//						count = 0;
//						bestPoint = pl[i];
//						minDist = dist;
//					}else{
//						count++;
//					}
//				}
//			}
//			if(count > 3){
//				break;
//			}
//			t *= 0.9;
//		}
//		return bestPoint;
//	}
//	
//	public static void main(String[] args) {
//		Point[] pl = new Point[5];
//		pl[0] = new Point(1,1);
//		pl[1] = new Point(0,0);
//		pl[2] = new Point(1,0);
//		pl[3] = new Point(0,1);
//		pl[4] = new Point(0.5,0.5);
//		SA_1 sa = new SA_1();
//		Point result = sa.search(pl, 100);
//		System.out.println(result.x);
//		System.out.println(result.y);
//	}
//}
