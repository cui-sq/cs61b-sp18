public class Planet{
	double xxPos; //Its current x position
	double yyPos; //Its current y position

	double xxVel; //Its current velocity in the x direction
	double yyVel; //Its current velocity in the y direction

	double mass;

	String imgFileName;

	static final double G = 6.67e-11;

	/** Constructor*/

	public Planet(double xP, double yP, double xV, 
		double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	/**Copy another body*/

	public Planet(Planet b){
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
	}

	/**take in a single Planet
	 * should return distance between the supplied planet 
	 * and the planet that is doing the calculation*/

	 public double calcDistance(Planet b){
	 	return Math.sqrt((b.xxPos - this.xxPos) * (b.xxPos - this.xxPos)
	 		+ (b.yyPos - this.yyPos) * (b.yyPos - this.yyPos));
	 }

	 /** Calculates a double describing the force exerted on this planet
	  * by the given planet b*/

	  public double calcForceExertedBy(Planet b){
	  	return this.mass * b.mass * G /
	  	(this.calcDistance(b) * this.calcDistance(b) );
	  }

	 /** Calculates a double describing the force exerted in the X direction */

	  public double calcForceExertedByX(Planet b){
	  	return this.calcForceExertedBy(b) * (b.xxPos - this.xxPos) /
	  	this.calcDistance(b);
	  }

	 /** Calculates a double describing the force exerted in the Y direction */

	  public double calcForceExertedByY(Planet b){
	  	return this.calcForceExertedBy(b) * (b.yyPos - this.yyPos) /
	  	this.calcDistance(b);
	  }


	  /**Takes in an array of Planets
	   * Calculates the nex X force exerted by all planets in that array 
	   * upon the current planet*/

	  public double calcNetForceExertedByX(Planet[] allPlanets){
	  	double NetForceExertedByX = 0;
	  	for(Planet b : allPlanets){
	  		if(this.equals(b)) ; //exclude itself
	  		else NetForceExertedByX += this.calcForceExertedByX(b);
	  	}
	  	return NetForceExertedByX;
	  }

	  /**Takes in an array of Planets
	   * Calculates the nex Y force exerted by all planets in that array 
	   * upon the current planet*/

	  public double calcNetForceExertedByY(Planet[] allPlanets){
	  	double NetForceExertedByY = 0;
	  	for(Planet b : allPlanets){
	  		if(this.equals(b)) ; //exclude itself
	  		else NetForceExertedByY += this.calcForceExertedByY(b);
	  	}
	  	return NetForceExertedByY;
	  }

	  /**Takes in a Planet
	   * compare if the Planet is equal to the current Planet. 
	   * just check the position*/

	   public boolean equals(Planet b){
	   	if( (this.xxPos == b.xxPos) && (this.yyPos == b.yyPos))
	   		return true;
	   	return false;
	   }

	   /** Takes in time, x-force and y-force applied on the current Planet
	    * Updates the planet's velocity and position instance variables*/

	   public void update(double dt, double fX, double fY){
	   	double aX = fX / this.mass;
	   	double aY = fY / this.mass;
	   	this.xxVel += dt * aX;
	   	this.yyVel += dt * aY;
	   	this.xxPos += dt * this.xxVel;
	   	this.yyPos += dt * this.yyVel;
	   }

	   /**Draws the Planet's image using the StdDraw API
	    * takes in no parameters and returns nothing. */
	    public void draw(){
	    	StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	    	StdDraw.show();
	    }






}