public class NBody{
	/** Takes in a filename
	 * return a double corresponding to the radius of the universe in the file. */
	public static double readRadius(String FileName){
		In in = new In(FileName);
		in.readInt();
		return in.readDouble();
	}

	/**Takes in a filename
	 * return to an array of N planets. */
	 public static Planet[] readPlanets(String FileName){
	 	In in = new In(FileName);
		int N = in.readInt(); //number of planets a.k.a. length of Planet array
		Planet[] planets = new Planet[N];
		in.readDouble();
		for(int i = 0; i < N; i++ ){
			planets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
		}
		return planets;
	 }


	 public static void main(String[] args){
	 	/** */
	 	double T = Double.parseDouble(args[0]);
	 	double dt = Double.parseDouble(args[1]);
	 	String filename = args[2];

		//In in = new In(filename);
		//int N = in.readInt();

	 	double radius = readRadius(filename);
	 	Planet[] planets = readPlanets(filename);
	 	int N = planets.length;

	 	/**Draw the background*/
	 	StdDraw.setScale(-1 * radius, radius);
	 	StdDraw.clear();
	 	StdDraw.picture(0, 0, "images/starfield.jpg");

	 	/**Draw each planet in the Planet[] planets*/
	 	for(Planet planet : planets){
	 		planet.draw();
	 	}

	 	StdDraw.enableDoubleBuffering();

	 	//double t = 0; //Time variable
	 	for(double t = 0; t < T; t += dt){
	 		double[] xForces = new double[N];
	 		double[] yForces = new double[N];
	 		for(int i = 0; i < N; i++){
	 			xForces[i] = planets[i].calcNetForceExertedByX(planets);
	 			yForces[i] = planets[i].calcNetForceExertedByY(planets);
	 		}
	 		for(int i = 0; i < N; i++){
	 			planets[i].update(dt, xForces[i], yForces[i]);
	 		}
	 		StdDraw.picture(0, 0, "images/starfield.jpg");
	 		for(Planet planet : planets){
	 			planet.draw();
	 		}
	 		StdDraw.show();
	 		StdDraw.pause(25);
	 	}

	 	/**Print out the universe*/
	 	StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", 
    			planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
    	}


	 }
}