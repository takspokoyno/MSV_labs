package msv.lab1;

public class Main {
    public static double func(double x,double y,double z,double h)
    {
        return z+h*((0.5/(x+0.4))*z+Math.sqrt(x+0.4)*y-(2/3.)*Math.pow((x+0.4),2));
    }

    public static double yBAlfa(double tan,double h)
    {
        double[] x=new double[11];
        double[] z=new double[11];
        double[] y=new double[11];
        x[0]=0;
        y[0]=Math.sqrt(1.4);
        z[0]=tan;
        System.out.println("x= "+String.format("%.4f", x[0])+"  y= "+String.format("%.4f",y[0])+"  z= "+String.format("%.4f",z[0]));
        for(int i=1;i<=10;i++)
        {
            x[i]=i*h;
            y[i]=y[i-1]+h*z[i-1];
            z[i]=z[i-1]+h*func(x[i-1],y[i-1],z[i-1],h);
            System.out.println("x= "+String.format("%.4f",x[i])+"  y= "+String.format("%.4f",y[i])+"  z= "+String.format("%.4f",z[i]));
        }
        System.out.println("_______________________________");
        return y[10];
    }
    public static double comparison(double tan,double yBalfa,double eps,double y1)
    {
        if(Math.abs(yBalfa-y1)<=eps )
        {System.out.println("        It is Result");
            return 0;}

        if(Math.abs(yBalfa-y1)>eps && yBalfa>y1)
        {
            System.out.println("Alfa next < this alfa");
            return -1;
        }
        else if(Math.abs(yBalfa-y1)>eps && yBalfa<y1)
        {
            System.out.println("Alfa next > this alfa");
            return 1;
        }
        else return 1000;
    }
    public static double nextAlfa(double comp1,double tan1,double comp2,double tan2)
    {
        double alfa=1000;
        if(comp1+comp2==2)
        {
            if(180*Math.atan(tan1)/Math.PI>180*Math.atan(tan2)/Math.PI)
            {
                System.out.println("Next alfa >");
                alfa=Math.atan(tan1);}
            if(180*Math.atan(tan2)/Math.PI>=180*Math.atan(tan1)/Math.PI)
            {
                System.out.println("Next alfa >");
                alfa=Math.atan(tan2);
            }
        }
        if(comp1+comp2==-2)
        {
            if(180*Math.atan(tan1)/Math.PI<180*Math.atan(tan2)/Math.PI)
            {
                alfa=Math.atan(tan1);
                System.out.println("Next alfa >"+String.format("%.4f",alfa));
            }
            if(180*Math.atan(tan2)/Math.PI<=180*Math.atan(tan1)/Math.PI)
            {
                alfa=Math.atan(tan2);
                System.out.println("Next alfa >"+String.format("%.4f",alfa));

            }
        }
        if(comp1+comp2==0)
        {
            alfa=((180*Math.atan(tan1)/Math.PI+180*Math.atan(tan2)/Math.PI)/2)*Math.PI/180;
            if(comp1==1)
            {
                System.out.println("Next alfa > "+String.format("%.4f",180*Math.atan(tan1)/Math.PI)+ " and next alfa < "+String.format("%.4f",180*Math.atan(tan2)/Math.PI));
            }
            if(comp2==1)
            {
                System.out.println("Next alfa > "+String.format("%.4f",(180*Math.atan(tan2)/Math.PI))+ " and next alfa < "+String.format("%.4f",180*Math.atan(tan1)/Math.PI));
            }
        }
        System.out.println("Next alfa = "+String.format("%.4f",alfa*180/Math.PI));
        System.out.println("_______________________________");
        return alfa;
    }
    public static double tan (double alfa)
    {
        return Math.tan(alfa);
    }
    public static double calculation (double tanalfa,double h,double eps,double y1)
    {
        if (h <= 0 || eps <= 0) {
            throw new IllegalArgumentException("h and eps must be positive");
        }
        double yalfa=yBAlfa(tanalfa,h);
        double comp=comparison(tanalfa,yalfa,eps,y1);
        return comp;
    }

    public static void main()
    {
        double eps=0.05;
        double y1=2.;
        System.out.println("y(1)= "+y1);
        double tanAlfa=(2-Math.sqrt(1.4));
        System.out.println("Tangent: "+tanAlfa);
        double alfa0=180*Math.atan(tanAlfa)/Math.PI;
        System.out.println("Arcangent: "+alfa0);
        double h=0.1;
        double yalfa=yBAlfa(tanAlfa,h);
        double comp1= comparison(tanAlfa,yalfa,eps,y1);
        if(Math.abs(yalfa-y1)>eps && yalfa>y1)
        {
            double tanAlfa2=0;
            double comp2=calculation(tanAlfa2,h,eps,y1);
            double alfa3=nextAlfa(comp1,tanAlfa,comp2,tanAlfa2);
            double tanAlfa3=tan(alfa3);
            double comp3=calculation(tanAlfa3,h,eps,y1);
            double alfa4=nextAlfa(comp1,tanAlfa,comp3,tanAlfa3);
            double tanAlfa4=Math.tan(alfa4);
            double comp4=calculation(tanAlfa4,h,eps,y1);
            double alfa5=nextAlfa(comp3,tanAlfa3,comp4,tanAlfa4);
            double tanAlfa5=Math.tan(alfa5);
            double comp5=calculation(tanAlfa5,h,eps,y1);
        }
    }
}