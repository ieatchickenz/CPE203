final class Point {
    private double x;
    private double y;
    private int z;

    public Point(double x, double y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX(){return this.x;}

    public double getY(){return this.y;}

    public int getZ(){return this.z;}

    public Point scalePoint(){
        x = this.x/2;
        y = this.y/2;
        return new Point(x,y,this.z);
    }

    public Point translate(){
        x = this.x - 150;
        y = this.y - 37;
        return new Point(x,y,this.z);

    }

    public void setX(double x){this.x = x;}

    public void setY(double y){this.y = y;}

    public void setZ(int z){this.z = z;}
}
