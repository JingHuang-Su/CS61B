public class Planet {

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
      	this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public double calcDistance(Planet p){
    	double distance = (Math.pow(this.xxPos - p.xxPos, 2) + Math.pow(this.yyPos - p.yyPos, 2));
    	return Math.sqrt(distance);
    }

    public double calcForceExertedBy(Planet p){
    	double g = 6.67e-11 ;
    	double distance = Math.pow(this.calcDistance(p), 2);
    	double f = ((g * this.mass * p.mass) / distance);
    	return f;
    }
    /** I don't know how to implement this bug, so I need to check there after I done this project
    The number of distance will not be negative number, now I just plus minus in there return*/
    public double calcForceExertedByX(Planet p){
    	double distance_X = this.xxPos - p.xxPos;
    	double f_x = -(this.calcForceExertedBy(p) * distance_X / this.calcDistance(p));
    	return f_x;
    }

    public double calcForceExertedByY(Planet p){
    	double distance_Y = this.yyPos - p.yyPos;
    	double f_y = -(this.calcForceExertedBy(p) * distance_Y / this.calcDistance(p));
    	return f_y;
    }

    public double calcNetForceExertedByX(Planet[] planetArray) {
        double netForceX = 0;

        for (Planet p : planetArray) {
            if (!this.equals(p)) {
                netForceX += this.calcForceExertedByX(p);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] planetArray) {
        double netForceY = 0;

        for (Planet p : planetArray) {
            if (!this.equals(p)) {
                netForceY += this.calcForceExertedByY(p);
            }
        }
        return netForceY;
    }
    public void update(double dt, double fX, double fY){
    	double acc_x = fX / this.mass;
    	double acc_y = fY / this.mass;
    	this.xxVel = this.xxVel + dt * acc_x;
    	this.yyVel = this.yyVel + dt * acc_y;
		this.xxPos = this.xxPos + dt * this.xxVel;
		this.yyPos = this.yyPos + dt * this.yyVel;
	}

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "./images/" + this.imgFileName);
        StdDraw.show();
    }
}