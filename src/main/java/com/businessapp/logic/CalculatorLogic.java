package com.businessapp.logic;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * ********************************************************************************
 * Local implementation class (unfinished) of calculator logic.
 * <p>
 * Instance is invoked with public interface method nextToken( Token tok )
 * passing an input token that is created at the UI from a key event. Input
 * tokens are defined in CalculatorIntf and comprised of digits [0-9,.], numeric
 * operators [+,-,*,/,VAT] and control tokens [\backspace,=,C,CE,K_1000].
 * <p>
 * Outputs are are passed through display properties:
 * <p>
 * - CalculatorIntf.DISPLAY for numbers and<p>
 * - CalculatorIntf.SIDEAREA for VAT calculations.
 * <p>
 * Method(s): - public void nextToken( Token tok );	;process next token from UI
 * controller
 *
 */
class CalculatorLogic implements CalculatorLogicIntf {

    private StringBuffer dsb = new StringBuffer();

    ArrayList<Character> operator = new ArrayList<>();
    ArrayList<Double> operand = new ArrayList<>();
    int pointer = 0;
    Character sign;
    double op1;
    double op2;
    double erg;
    String ergebnis;

    /**
     * Local constructor.
     */
    CalculatorLogic() {
        nextToken(Token.K_C);		// reset buffers
    }

    /**
     * Process next token from UI controller. Tokens are defined in
     * CalculatorIntf.java
     * <p>
     * Outputs are are passed through display properties: -
     * CalculatorIntf.DISPLAY for numbers and - CalculatorIntf.SIDEAREA for VAT
     * calculations.
     * <p>
     * @param tok the next Token passed from the UI, CalculatorViewController.
     */
    public void nextToken(Token tok) {
        String d = tok == Token.K_DOT ? "." : CalculatorLogicIntf.KeyLabels[tok.ordinal()];
        try {
            switch (tok) {
                case K_0:
                case K_1:
                case K_2:
                case K_3:
                case K_4:
                case K_5:
                case K_6:
                case K_7:
                case K_8:
                case K_9:
                    appendBuffer(d);
                    break;

                case K_1000:
                    nextToken(Token.K_0);
                    nextToken(Token.K_0);
                    nextToken(Token.K_0);
                    break;

                case K_DIV:
                    // throw new ArithmeticException("ERR: div by zero");
                    appendBuffer(d);
                    break;
                case K_MUL:
                    appendBuffer(d);
                    break;
                case K_PLUS:
                    appendBuffer(d);
                    break;
                case K_MIN:
                    appendBuffer(d);
                    break;
                case K_EQ:
                    verarbeiten();
                    break;

                case K_VAT:
                    String wert = dsb.toString();
                    double netto = Double.valueOf(wert) / 1.19;
                    String nettoBetrag = String.format("%.2f", netto);
                    CalculatorLogicIntf.SIDEAREA.set(
                            "Brutto:  " + dsb + "\n"
                            + VAT_RATE + "% MwSt: " + String.format("%.2f", (Double.valueOf(wert) - netto)) + " \n"
                            + "Netto:  " + nettoBetrag
                    );
                    break;

                case K_DOT:
                    appendBuffer(d);
                    break;

                case K_BACK:
                    dsb.setLength(Math.max(0, dsb.length() - 1));
                    break;

                case K_C:
                    CalculatorLogicIntf.SIDEAREA.set("");
                case K_CE:
                    dsb.delete(0, dsb.length());
                    break;

                default:
            }
            String display = dsb.length() == 0 ? "0" : dsb.toString();
            CalculatorLogicIntf.DISPLAY.set(display);

        } catch (ArithmeticException e) {
            CalculatorLogicIntf.DISPLAY.set(e.getMessage());
        }
    }


    /*
	 * Private method(s).
     */
    private void appendBuffer(String d) {
        if (dsb.length() <= DISPLAY_MAXDIGITS) {
        
                dsb.append(d);
//                if(dsb.length()>=2){
//                if(dsb.substring(dsb.length()-1).equals(dsb.substring(dsb.length()-2)))
//                    dsb.replace(dsb.length()-2,dsb.length()-1, d);
//          
           }    
    }

    void calcAddSub(int zeiger) {
        //---rekursive logik
        if (operator.size() <= zeiger) {
            //    calcAddSub(0);
            ergebnis = String.format("%.2f", operand.get(0));
            dsb.delete(0, dsb.length());
            appendBuffer(ergebnis);
            System.out.println("OP " + operand.get(0));
            System.out.println("erg " + ergebnis);
            System.out.println("dsb " + dsb);
            return;
        }

        if (operator.get(zeiger) != null) {
            sign = operator.get(zeiger);
            op1 = operand.get(zeiger);
            op2 = operand.get(zeiger + 1);

            if (sign == '+' || sign == '-') {
                if (sign == '+') {
                    op1 = op1 + op2;
                    operand.set(zeiger, op1);
                    operand.remove(zeiger + 1);
                    operator.remove(zeiger);

                    //----> Manuell alle zellen überschreiben...
                    System.out.println("Zeiger:" + zeiger + " Operator: " + operator.toString() + "Sign: " + sign);
                    calcAddSub(zeiger);
                }

                if (sign == '-') {
                    op1 = op1 - op2;
                    operand.set(zeiger, op1);
                    operand.remove(zeiger + 1);
                    operator.remove(zeiger);

                    System.out.println("Zeiger:" + zeiger + " Operator: " + operator.toString() + "Sign: " + sign);
                    calcAddSub(zeiger);
                }
            }
        }
    }

    void calcMultDiv(int zeiger) {
        //---rekursive logik
        //--------------TODO--------->>> Auf Länge des Arrays prüfen. (if(Array.zice()==0){...}) und ARRAY am ENDE der Berechnung LEEREN!

        if (operator.size() <= zeiger) {
            calcAddSub(0);
////            ergebnis = operand.get(0).toString();
            //        dsb.delete(0, dsb.length());
            //       appendBuffer(ergebnis);
            //         System.out.println(dsb);
            return;
        }

        if (operator.get(zeiger) != null) {
            sign = operator.get(zeiger);
            op1 = operand.get(zeiger);
            op2 = operand.get(zeiger + 1);

            if (sign == '+' || sign == '-') {
                calcMultDiv(zeiger + 1);
            }
            if (sign == '*' || sign == '/') {
                if (sign == '*') {
                    op1 = op1 * op2;
                    operand.set(zeiger, op1);
                    operand.remove(zeiger + 1);
                    operator.remove(zeiger);

                    //----> Manuell alle zellen überschreiben...
                    System.out.println("Zeiger:" + zeiger + " Operator: " + operator.toString() + "Sign: " + sign);
                    calcMultDiv(zeiger);
                }

                if (sign == '/') {
                    op1 = op1 / op2;
                    operand.set(zeiger, op1);
                    operand.remove(zeiger + 1);
                    operator.remove(zeiger);

                    System.out.println("Zeiger:" + zeiger + " Operator: " + operator.toString() + "Sign: " + sign);
                    calcMultDiv(zeiger);
                }
            }
        }
    }

    private void verarbeiten() {

        operator.clear();
        operand.clear();

        String wert = dsb.toString();

        String[] wertSp = wert.split("[^\\d\\.]");
        char[] tmp = wert.toCharArray();

        //-------------Listen Füllen
          boolean doppel = false;
        for (char c : tmp) {
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                operator.add(c);
                doppel = true;
                continue;
         }
//                        if ((c == '+' || c == '-' || c == '*' || c == '/') && doppel==true) {
//                doppel = false;
//                continue;
//           
//            }
            
        }
        for (String werte : wertSp) {
            double tf = Double.valueOf(werte);
            operand.add(tf);
        }

        //-------------bererchnung
        int index = 0;
        for (char f : operator) {
            System.out.println("ZArray [ " + index + " ] : " + f);
            index++;
        }
        index = 0;
        for (double f : operand) {
            System.out.println("WArray [ " + index + " ] : " + f);
            index++;
        }
        calcMultDiv(0);
    }
}
