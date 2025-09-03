public class NBody{
    public static double readRadius(String filename) {
        In in = new In(filename);
        int numPlanets = in.readInt();  // 读取行星数量（但忽略）
        double radius = in.readDouble(); // 读取宇宙半径
        in.close();
        return radius;
    }

    /**
     * 从文件读取所有行星数据
     */
    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int numPlanets = in.readInt();   // 读取行星数量
        double radius = in.readDouble(); // 读取宇宙半径（但这里不返回）

        Planet[] planets = new Planet[numPlanets];
        
        for (int i = 0; i < numPlanets; i++) {
            // 按顺序读取行星数据
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble(); 
	    double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        
        in.close();
        return planets;
    }
   
	public static void main(String[] args)
	{
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Planet [] p = readPlanets(filename);
		double r = readRadius(filename);

		StdDraw.enableDoubleBuffering();
		StdDraw.setCanvasSize();
		StdDraw.setXscale(-r,r);
		StdDraw.setYscale(-r,r);
		StdDraw.clear();
		StdDraw.picture(0,0,"images/starfield.jpg");


		for (Planet pl : p)
		{
			pl.Draw();
		}


		double time = 0;
		for (time = 0; time < T; time += dt)
		{
			double[] xArray = new double[p.length];
			double[] yArray = new double[p.length];
			for(int i = 0; i < p.length; i++)
			{
				xArray[i] = p[i].calcNetForceExertedByX(p);
				yArray[i] = p[i].calcNetForceExertedByY(p);		
			}

			StdDraw.picture(0,0,"images/starfield.jpg");
			
			for(int i = 0; i < p.length; i++)
			{
				p[i].update(dt,xArray[i],yArray[i]);
				p[i].Draw();
			}

			StdDraw.show();
			StdDraw.pause(10);
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
   			 StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  	planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  	planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
		}
	}
}
