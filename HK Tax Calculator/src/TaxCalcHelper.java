import java.util.Scanner;

public class TaxCalcHelper {

    /**
     * Tax calculator
     */

    private double taxCalc(double income) {
        double rate = 0;
        if (income >= 200000) {
            rate = 16000 + (income - 200000) * 0.17;
        } else if (income >= 150000) {
            rate = 9000 + (income - 150000) * 0.14;
        } else if (income >= 100000) {
            rate = 4000 + (income - 100000) * 0.1;
        } else if (income >= 50000) {
            rate = 1000 + (income - 50000) * 0.06;
        } else if (0 < income) {
            rate = income * 0.02;
        } else if (income <= 0) {
            rate = 0;
            System.out.println("No tax needed to be paid");
        } else {
            System.out.println("Input Error");
        }
        return rate;
    }

    /**
     * Deduction calculation
     *
     * @param exp  outgoings and expenses
     * @param edu  self education expenses
     * @param don  approved charitable donations
     * @param loan home loan interest
     * @param ins  qualifying health insurance
     * @param eld  elderly residential care expenses
     * @return total deductions
     */

    private double ded(double exp, double edu, double don, double loan, double ins, double eld) {
        return exp + edu + don + loan + ins + eld;
    }


    /**
     * Allowance calculation
     *
     * @param dis   personal disability allowance
     * @param child dependent children allowance
     * @param bro   dependent brother/sister allowance
     * @param par   dependent parents allowance
     * @param gad   dependent grandparents age 55-60 allowance
     * @param dep   dependent grandparents age 60+ allowance
     * @return total deductions
     */

    private double all(double dis, double child, double bro, double par, double gad, double dep) {
        return dis + child + bro + par + gad + dep;
    }

    /**
     * Display
     */

    public void calc() {
        FileHelper f = new FileHelper();
        Scanner input = new Scanner(System.in);
        System.out.println("--------------------------------");
        System.out.println("--Welcome to HK Tax Calculator--");
        System.out.println("--------------------------------");
        boolean isR = true;
        while (isR) {

            System.out.println("Please enter your name: ");
            String name = input.next();
            System.out.println("Please enter your age");
            int age = input.nextInt();
            System.out.println("Please enter your income");
            double income = input.nextDouble();

            if (income<0){
                isR = false;
                System.out.println("\n**ERROR, Please enter a non-negative income**");
            }else {


                System.out.println("--------Allowance Calculations---------");
                System.out.println("Please enter your personal disability allowance");
                double dis = input.nextDouble();
                System.out.println("Please enter your dependent children allowance");
                double child = input.nextDouble();
                System.out.println("Please enter your dependent brother/sister allowance");
                double bro = input.nextDouble();
                System.out.println("Please enter your dependent parents allowance");
                double par = input.nextDouble();
                System.out.println("Please enter your dependent grandparents aged 55-60");
                double gad = input.nextDouble();
                System.out.println("Please enter your dependent grandparents aged over 60");
                double dep = input.nextDouble();

                income -= all(dis, child, bro, par, gad, dep);

                System.out.println("--------Deductions Calculations---------");
                System.out.println("Please enter your outgoings and expenses");
                double exp = input.nextDouble();
                System.out.println("Please enter your self education expenses");
                double edu = input.nextDouble();
                System.out.println("Please enter your approved charitable donations");
                double don = input.nextDouble();
                System.out.println("Please enter your home loan interest");
                double loan = input.nextDouble();
                System.out.println("Please enter your qualifying health insurance");
                double ins = input.nextDouble();
                System.out.println("Please enter your elderly residential care expenses");
                double eld = input.nextDouble();

                income -= ded(exp, edu, don, loan, ins, eld);


                //Using taxCalc
                double taxRate = taxCalc(income);

                System.out.println("Hello! " + name + " your tax information is: ");
                System.out.println("Name: " + name);
                System.out.println("Age: " + age);
                System.out.println("Tax: $" + taxRate);

                //Payment
                System.out.println("How would you like to pay your tax: 1. Over two installments; 2. At once");
                int resR = input.nextInt();
                String str = null;
                if (resR == 1) {
                    str = "************HK TAX CALCULATOR RECEIPT************" +
                            "\nYou have chosen to pay in two installments a total tax of: $" + taxRate + " over a span of 5 months" +
                            "\nEach installment you have to pay: $" + taxRate / 2 +
                            "\nThank you for your cooperation!";
                    f.readTax(str, name);
                } else {
                    str = "************HK TAX CALCULATOR RECEIPT************" +
                            "\nYou have chosen to pay at once: $" + taxRate +
                            "\nThank you for your cooperation!";
                    f.readTax(str, name);
                }

                System.out.println("\nYOUR RECEIPT HAS BEEN SAVED");
            }
                //Ask if user wants to repeat it or exit
                System.out.println("-------------------------------------");
                System.out.println("Do you want to repeat? (Yes/No)");
                String res = input.next();
                if (res.equals("Yes")) {
                    isR = true;
                } else {
                    isR = false;
                }

                System.out.println("---------Thank you---------");




        }

    }

}