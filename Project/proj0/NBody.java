public class NBody{

	public static double readRadius(String filename){
		In in = new In(filename);
		int N = in.readInt();
		double secondItemInFile = in.readDouble();
		return secondItemInFile;
	}	
	public static Planet[] readPlanets(String filename){
		In in = new In(filename);
        int N = in.readInt();
        double radius = in.readDouble();

        Planet[] planets = new Planet[N];
        int i = 0;      // easiest way to ensure we read only relevant lines

        while(i != N) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, img);
            i += 1;
        }

        return planets;
	}
	public static void main(String[] args) {

		/**Drawing the Initial Universe State (main)

		Collecting All Needed Input*/
		double T = Double.valueOf(args[0]);
		double dt =  Double.valueOf(args[1]);
		String filename = args[2];
		double readRadius = readRadius(filename);
 		Planet[] planets = readPlanets(filename);
		
		 /** Drawing the Background*/
	 	String imagePath = "./images/starfield.jpg";
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setScale(-readRadius, readRadius);
	    StdDraw.clear();
	    StdDraw.picture(0, 0, imagePath);

	    /** Drawing All of the Planets*/
	    for (Planet p : planets){
	      	p.draw();
	    }

        /**Creating an Animation*/
        for (double time = 0; time <= T; time += dt){
        	
        	double xForces [] = new double[planets.length];
        	double yForces [] = new double[planets.length];

        	for (int i = 0; i < planets.length; i ++){
        		xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
        	}
        
        	for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }
        	
        	StdDraw.picture(0, 0, imagePath);
            
            for (int i = 0; i < planets.length; i++) {
                planets[i].draw();
            }
            StdDraw.show();
            int pauseTime = 1;
            StdDraw.pause(pauseTime);
        }
        StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", readRadius);
		for (int i = 0; i < planets.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
		                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}
}