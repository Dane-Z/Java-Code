package proj_2_similarity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import edu.princeton.cs.algs4.Queue;

/*
 * When we have Java projects with multiple files, it makes sense to collect
 * constants that relate to them all in a central point. Our 'Defaults' class
 * serves this purpose.
 */

public class Defaults {

	/*
	 * Our principle form of organization in CS-2110 is to arrange our files (Java
	 * as well as data) into package folders. However, when a Java program located
	 * in one of these packages run, its default folder is set to that of the
	 * project (i.e. the parent of the package folder). We therefore define the
	 * following constant and concatenate it to the beginning of data file names.
	 */
	public static final String pkg = "proj_2_similarity";

}
