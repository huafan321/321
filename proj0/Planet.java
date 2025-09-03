public class Planet {
    // 实例变量
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    // 引力常数
    public static final double G = 6.67e-11;

    /**
     * 主构造函数
     */
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /**
     * 拷贝构造函数
     */
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    /**
     * 计算与另一个行星的距离
     */
    public double calcDistance(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * 计算另一个行星施加的引力大小
     */
    public double calcForceExertedBy(Planet p) {
        double r = this.calcDistance(p);
        return G * this.mass * p.mass / (r * r);
    }

    /**
     * 计算另一个行星施加的X方向分力
     */
    public double calcForceExertedByX(Planet p) {
        double totalForce = this.calcForceExertedBy(p);
        double dx = p.xxPos - this.xxPos;
        double r = this.calcDistance(p);
        return totalForce * dx / r;
    }

    /**
     * 计算另一个行星施加的Y方向分力
     */
    public double calcForceExertedByY(Planet p) {
        double totalForce = this.calcForceExertedBy(p);
        double dy = p.yyPos - this.yyPos;
        double r = this.calcDistance(p);
        return totalForce * dy / r;
    }

    /**
     * 计算所有行星施加的X方向净力（排除自身）
     */
    public double calcNetForceExertedByX(Planet[] planets) {
        double netForceX = 0.0;
        for (Planet p : planets) {
            if (!this.equals(p)) {
                netForceX += this.calcForceExertedByX(p);
            }
        }
        return netForceX;
    }

    /**
     * 计算所有行星施加的Y方向净力（排除自身）
     */
    public double calcNetForceExertedByY(Planet[] planets) {
        double netForceY = 0.0;
        for (Planet p : planets) {
            if (!this.equals(p)) {
                netForceY += this.calcForceExertedByY(p);
            }
        }
        return netForceY;
    }

    /**
     * 根据受力更新行星的位置和速度
     */
    public void update(double dt, double fX, double fY) {
        // 计算加速度
        double aX = fX / this.mass;
        double aY = fY / this.mass;

        // 更新速度
        this.xxVel += aX * dt;
        this.yyVel += aY * dt;

        // 更新位置
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    public void Draw ()
    {
	StdDraw.picture(xxPos,yyPos,"images/" + imgFileName);
    }
}
