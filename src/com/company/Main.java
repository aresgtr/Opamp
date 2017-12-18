package com.company;

import java.math.*;

public class Main {

    public static void main(String[] args) {
        //modifiable
        double power = 0.001; //prompt for power
        double gain_db = 60;
        double av = 0 - Math.pow(10, gain_db/20);
        //double av = -3000;
        double swing = 1.4;
        double R = 1000;



        //global variables
        double vthn = 0.6;
        double vthp = 0.7;

        double lambdan = 0.08;
        double lambdap = 0.16;

        double uncox = 1.77 * Math.pow(10,-4);
        double upcox = 4.71 * Math.pow(10, -5);

        //specification
        double vdd = 3.3;
        double i12 = 25 * Math.pow(10, -6); //25uA

        double Cl = 0.5 * Math.pow(10, -12);
        double vin = 1.65; //nominal input common-mode, input DC level




        double l = 0.35 * Math.pow(10, -6);

        /*
         * Now begins the design calculation
         */
        double totalcurrent = power / vdd;
        double i11 = i12;
        double i5 = (totalcurrent - i11 - i12)/2;
        double i7 = i5; //assume i5=i7
        double i6 = i7;
        double i1 = i5/2;
        double i2 = i1;
        double i3 = i1;
        double i4 = i1;
        double i9 = i11;
        double i10 = i12;

        double veff6 = (vdd - swing)/2;
        double veff7 = veff6; //assume

        System.out.println("veff 6 and 7 is " + veff7);

        double w7 = calculateW(i7, upcox, veff7, l);
        double w5 = w7;
        double w6 = calculateW(i6, uncox, veff6, l);

        double vsg7 = veff7 + vthp;
        double vbias = vdd - vsg7;

        double w11 = calculateW(i11, upcox, vdd - vbias - vthp, l );
        double w12 = w11;

        double gm6 = 2 * i6/veff6;

        double veff3 = veff6;
        double veff4 = veff6;

        double w4 = calculateW(i4, uncox, veff4, l);
        double w3 = w4;


        double vg10 = R * i9 + vthn;
        double vg9 = R * i9 + vg10;

        double veff9 = vg9 - vthn;
        double veff10 = vg10 - vthn;

        double w9 = calculateW(i9, uncox, veff9, l);
        double w10 = calculateW(i10, uncox, veff10, l);

        double ro2 = 1/(lambdap * i2);
        double ro4 = 1/(lambdan * i4);
        double ro6 = 1/(lambdap * i6);
        double ro7 = 1/(lambdan * i7);

        double gm1 = -av/gm6/parallelResistance(ro2, ro4)/parallelResistance(ro6, ro7);
        double w1 = Math.pow(gm1, 2)/2/upcox/i1*l;
        double w2 = w1;

        double veff1 = Math.sqrt(i1/0.5/upcox/(w1/l));
        double SR = 10 * Math.pow (10, 6);
        double omegata = SR/veff1;
        double Cc = gm1 / omegata;
        System.out.println("Cc is " + Cc);

        /*
         * This is for printing results
         */
        double temp1 = w1;
        double temp2 = w2;
        double temp3 = w3;
        double temp4 = w4;
        double temp5 = w5;
        double temp6 = w6;
        double temp7 = w7;
        double temp9 = w9;
        double temp10 = w10;
        double temp11 = w11;
        double temp12 = w12;

        double templ = l;

        while (compareToBoundaryW(w1) == false) {
            w1 = w1 + temp1;
            l = l + templ;
        }
        System.out.println("M1  W:" + w1 * Math.pow(10, 6) + "uM, L: " + l * Math.pow(10, 6) +"uM   " + w1/l);
        l = 0.35 * Math.pow(10, -6);


        while (compareToBoundaryW(w2) == false) {
            w2 = w2 + temp2;
            l = l + templ;
        }
        System.out.println("M2  W:" + w2 * Math.pow(10, 6) + "uM, L: " + l * Math.pow(10, 6) +"uM   " + w2/l);
        l = 0.35 * Math.pow(10, -6);


        while (compareToBoundaryW(w3) == false) {
            w3 = w3 + temp3;
            l = l + templ;
        }
        System.out.println("M3  W:" + w3 * Math.pow(10, 6) + "uM, L: " + l * Math.pow(10, 6) +"uM   " + w3/l);
        l = 0.35 * Math.pow(10, -6);


        while (compareToBoundaryW(w4) == false) {
            w4 = w4 + temp4;
            l = l + templ;
        }
        System.out.println("M4  W:" + w4 * Math.pow(10, 6) + "uM, L: " + l * Math.pow(10, 6) +"uM   " + w4/l);
        l = 0.35 * Math.pow(10, -6);


        while (compareToBoundaryW(w5) == false) {
            w5 = w5 + temp5;
            l = l + templ;
        }
        System.out.println("M5  W:" + w5 * Math.pow(10, 6) + "uM, L: " + l * Math.pow(10, 6) +"uM   " + w5/l);
        l = 0.35 * Math.pow(10, -6);


        while (compareToBoundaryW(w6) == false) {
            w6 = w6 + temp6;
            l = l + templ;
        }
        System.out.println("M6  W:" + w6 * Math.pow(10, 6) + "uM, L: " + l * Math.pow(10, 6) +"uM   " + w6/l);
        l = 0.35 * Math.pow(10, -6);


        while (compareToBoundaryW(w7) == false) {
            w7 = w7 + temp7;
            l = l + templ;
        }
        System.out.println("M7  W:" + w7 * Math.pow(10, 6) + "uM, L: " + l * Math.pow(10, 6) +"uM   " + w7/l);
        l = 0.35 * Math.pow(10, -6);


        while (compareToBoundaryW(w9) == false) {
            w9 = w9 + temp9;
            l = l + templ;
        }
        System.out.println("M9  W:" + w9 * Math.pow(10, 6) + "uM, L: " + l * Math.pow(10, 6) +"uM   " + w9/l);
        l = 0.35 * Math.pow(10, -6);


        while (compareToBoundaryW(w10) == false) {
            w10 = w10 + temp10;
            l = l + templ;
        }
        System.out.println("M10 W:" + w10 * Math.pow(10, 6) + "uM, L: " + l * Math.pow(10, 6) +"uM  " + w10/l);
        l = 0.35 * Math.pow(10, -6);


        while (compareToBoundaryW(w11) == false) {
            w11 = w11 + temp11;
            l = l + templ;
        }
        System.out.println("M11 W:" + w11 * Math.pow(10, 6) + "uM, L: " + l * Math.pow(10, 6) +"uM  " + w11/l);
        l = 0.35 * Math.pow(10, -6);


        while (compareToBoundaryW(w12) == false) {
            w12 = w12 + temp12;
            l = l + templ;
        }
        System.out.println("M12 W:" + w12 * Math.pow(10, 6) + "uM, L: " + l * Math.pow(10, 6) +"uM  " + w12/l);
        l = 0.35 * Math.pow(10, -6);

//        System.out.println("gm1 " + gm1);
//        System.out.println("ro2 " + ro2);
//        System.out.println("ro4 " + ro4);
//        System.out.println("ro6 " + ro6);
//        System.out.println("ro7 " + ro7);
//        System.out.println("veff1 " + veff1);

    }

    public static double calculateW (double i, double uxcox, double veff, double l){
        return i/0.5/uxcox/Math.pow(veff, 2) * l;
    }

    public static double parallelResistance (double a, double b){
        double inva = 1/a;
        double invb = 1/b;
        return (1/(inva+invb));
    }

    public static boolean compareToBoundaryW (double number){
        //boundary conditions
        double wmax = 2.001 * Math.pow(10, -4);
        double wmin = 4 * Math.pow(10, -7);

        if (number <= wmax && number >= wmin)
            return true;
        else
            return false;
    }

    public static boolean compareToBoundaryL (double number){
        //boundary conditions
        double lmax = 2.01 * Math.pow(10, -5);
        double lmin = 3.48 * Math.pow(10, -7);

        if (number <= lmax && number >= lmin)
            return true;
        else
            return false;
    }
}
