package inAndOut;
//Dane Zeeb, dzeeb1@jhu.edu

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Programming Assignment 1
//Input point and output distances between the m closest pairs of points, including x/y coordinates

public class closestPair {
	
	// A class to read a .txt file and extract x and y coordinates
	public static void readCoordinates(String path, List<Double> xCoo, List<Double> yCoo) throws Exception {
		File file = new File(path);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		while (line != null) {
			String[] coordinates = line.split(" ");
			assert coordinates.length == 2;
			xCoo.add(Double.parseDouble(coordinates[0]));
			yCoo.add(Double.parseDouble(coordinates[1]));
			line = br.readLine();
		}
	}
	// A class to convert coordinate pairs to point objects
	public static List<Point> inputToPoints(List<Double> dx, List<Double> dy) {
		List<Point> points = new LinkedList<>();
		for (int i = 0; i < dx.size(); i++) {
			double pairedx = dx.get(i);
			double pairedy = dy.get(i);
			Point p = new Point(pairedx, pairedy);
			points.add(p);
		}
		return points;
	}
	
	// Comparable class which will be used to sort points in P in O(n log n) time
	static class comparable implements Comparator<Point> {
		@Override
		public int compare(Point o1, Point o2) {
			return Double.compare(o1.x(), o2.x());
		}
	}
	
	// Euclidean distance calculation between point objects
	public static double distance(Point i, Point j) {
		return Math.sqrt(((i.x() - j.x()) * (i.x() - j.x()) + (i.y() - j.y() * (i.y() - j.y()))));
	}
	
	// A function to find the smallest distance between points in an list of points P, with size m
	public static void closestSorted(List<Point> P, int m) {
		double lowest = 100000000;
		Deque<Point> stack = new ArrayDeque<Point>();
		
		for(int i = 0; i < m; i++) {
			for (int j = i + 1; j < m-1; j++) {
				if(distance(P.get(i), P.get(j)) < lowest); {
					stack.push(P.get(i));
					stack.push(P.get(j));
					lowest = distance(P.get(i), P.get(j));
				}
			}
		}
		System.out.println( "Lowest Distance: " + lowest + " p1: " + //
		stack.pop() + " p2: " + stack.pop());
	}

	public static void main(String[] args) throws Exception {
		List<Double> xCoo = new LinkedList<Double>();
		List<Double> yCoo = new LinkedList<Double>();
		List<Point> points = new LinkedList<Point>();
		String filePath = "input.txt";
		readCoordinates(filePath, xCoo, yCoo);
		System.out.println(xCoo);
		System.out.println(yCoo);
		points = inputToPoints(xCoo, yCoo);
		System.out.println(points);
		Collections.sort(points, new comparable());
		System.out.println(points);
		closestSorted(points, points.size());
	}
}
