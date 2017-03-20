package learning;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

//��ģ���˻��㷨���TSP���⣨���������⣩
//������һ����������Ҫ�ݷ�n�����У�������ѡ����Ҫ�ߵ�·����·����������ÿ������ֻ�ܰݷ�һ�Σ�
//�������Ҫ�ص�ԭ�������ĳ��С�·����ѡ��Ŀ����Ҫ��õ�·��·��Ϊ����·��֮�е���Сֵ��
class City {
	double x;
	double y;

	public City(double x, double y) {
		this.x = x;
		this.y = y;
	}
}

public class SA_2 {
	public int num;
	public City[] cities;
	public Random r = new Random(System.currentTimeMillis());
	public double w[][];
	public int[] temPath;

	public int[] loadData() {
		int path[] = null;
		try {
			File f = new File("resources/tsp.dat");//�޸�·��
			InputStreamReader read = null;
			read = new InputStreamReader(new FileInputStream(f), "utf-8");
			BufferedReader bufferedReader = new BufferedReader(read);
			String line = bufferedReader.readLine();
			num = Integer.parseInt(line);
			cities = new City[num];
			int i = 0;
			path = new int[num];
			while ((line = bufferedReader.readLine()) != null) {
				String[] splited = line.split("\\s+");
				path[i] = i;
				cities[i++] = new City(Integer.parseInt(splited[0]), Integer.parseInt(splited[1]));
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	public double dist(City a, City b) {
		return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
	}

	public void copyPath(int[] pathB, int[] pathA) {
		for (int i = 0; i < num; i++) {
			pathA[i] = pathB[i];
		}
	}

	public double search(double t) {
		int[] path = loadData();
		temPath = new int[num];
		w = new double[num][num];
		for (int i = 0; i < num; i++) {
			for (int j = i + 1; j < num; j++) {
				w[i][j] = dist(cities[i], cities[j]);
				w[j][i] = w[i][j];
			}
		}
		double minT = 1;
		double minDist = sumDist(path);
		System.out.println("the first min distance: " + minDist);

		int inLoop = 2000;// ��ѭ������
		int[] bestPath = new int[num];
		int count;
		while (t > minT) {
			count = 0;
			for (int i = 0; i < inLoop; i++) {
				getPath(path, temPath);
				double iDist = sumDist(temPath);
				double dt = iDist - minDist;
				if (dt < 0 || (dt > 0 && Math.exp(-dt / t) > Math.random())) {
					count = 0;
					minDist = iDist;
					copyPath(temPath, path);
					copyPath(path, bestPath);
				} else {
					count++;
				}
			}
			if (count >= 1000)// ѭ����ֹ����
				break;
			t *= 0.98;
		}
		for (int i = 0; i < num; i++) {
			System.out.print(bestPath[i] + ",");
		}
		System.out.println();
		return minDist;
	}

	public double sumDist(int[] path) {
		double sumD = 0;
		for (int i = 0; i < num - 1; i++) {
			sumD += w[path[i]][path[i + 1]];
		}
		sumD += w[path[num - 1]][path[0]];
		return sumD;
	}

	public void getPath(int[] path, int[] tempPath) {
		int x = r.nextInt(num);
		int y = r.nextInt(num);
		while (x == y) {
			y = r.nextInt(num);
		}
		for (int i = 0; i < num; i++) {
			tempPath[i] = path[i];
		}
		int temp = tempPath[x];
		tempPath[x] = tempPath[y];
		tempPath[y] = temp;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SA_2 sa2 = new SA_2();
		double result = sa2.search(250);
		System.out.println("the best min distances: " + result);
	}
}
