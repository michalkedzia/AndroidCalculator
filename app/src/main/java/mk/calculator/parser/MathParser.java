package mk.calculator.parser;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;
import mk.calculator.exceptions.IncorrectInputException;

public class MathParser {

    public static void calculateMathFunctions(ListIterator<String> iterator) throws IncorrectInputException {
        double sign;
        int offset;
        while (iterator.hasNext()) {
            sign = 1.0;
            offset = 0;
            String s = iterator.next();
            if (s.contains("-")) {
                sign = -1.0;
                offset = 1;
            }

            if (s.contains("sin")) {
                if (isValidNumber(s.substring(4 + offset, s.length() - 1)))
                    throw new IncorrectInputException();
                iterator.set(String.valueOf(sign * Math.sin(Double.parseDouble(s.substring(4 + offset, s.length() - 1)))));
            } else if (s.contains("cos")) {
                if (isValidNumber(s.substring(4 + offset, s.length() - 1)))
                    throw new IncorrectInputException();
                iterator.set(String.valueOf(sign * Math.cos(Double.parseDouble(s.substring(4 + offset, s.length() - 1)))));
            } else if (s.contains("tan")) {
                if (isValidNumber(s.substring(4 + offset, s.length() - 1)))
                    throw new IncorrectInputException();
                iterator.set(String.valueOf(sign * Math.tan(Double.parseDouble(s.substring(4 + offset, s.length() - 1)))));
            } else if (s.contains("ln")) {
                if (isValidNumber(s.substring(3 + offset, s.length() - 1)))
                    throw new IncorrectInputException();
                iterator.set(String.valueOf(sign * Math.log10(Double.parseDouble(s.substring(3 + offset, s.length() - 1)))));
            } else if (s.contains("log")) {
                if (isValidNumber(s.substring(4 + offset, s.length() - 1)))
                    throw new IncorrectInputException();
                iterator.set(String.valueOf(sign * Math.log(Double.parseDouble(s.substring(4 + offset, s.length() - 1)))));
            } else if (s.contains("√")) {
                if (isValidNumber(s.substring(2 + offset, s.length() - 1)))
                    throw new IncorrectInputException();
                iterator.set(String.valueOf(sign * Math.log(Double.parseDouble(s.substring(2 + offset, s.length() - 1)))));
            }
        }
    }

    public static boolean isValidNumber(String string) {
        try {
            Double.valueOf(string);
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    public static LinkedList<String> parse(String s) {
        StringTokenizer st = new StringTokenizer(s, " ^", true);
        LinkedList<String> list = new LinkedList<>();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (!token.equals(" ") && !token.equals("=")) {
                list.add(token);
            }
        }
        return list;
    }

    public static void calculatePercents(ListIterator<String> iterator) throws IncorrectInputException {
        while (iterator.hasNext()) {
            String s = iterator.next();
            if (s.contains("%")) {
                String percent = s.substring(0, s.length() - 1);
                if (iterator.previousIndex() == 0) {
                    throw new IncorrectInputException();
                }
                iterator.previous();
                String sign = iterator.previous();
                String a = iterator.previous();
                iterator.next();
                iterator.next();
                iterator.next();
                if (sign.equals("*") || sign.equals("/")) {
                    iterator.set(String.valueOf(Double.parseDouble(percent) / 100.0));
                } else {
                    iterator.set(String.valueOf(Double.parseDouble(a) * (Double.parseDouble(percent) / 100.0)));
                }
            }
        }
    }

//    public static void reduceMathOperators(ListIterator<String> iterator) {
//        if (iterator.hasNext()) {
//            if (iterator.next().equals("-")) {
//                iterator.remove();
//                String s = iterator.next();
//                iterator.set("-" + s);
//
//            }
//        }
//    }

    public static void calculatePow(ListIterator<String> iterator) {
        while (iterator.hasNext()) {
            String s = iterator.next();
            if (s.equals("^")) {
                iterator.previous();
                String a = iterator.previous();
                iterator.remove();
                iterator.next();
                iterator.remove();
                String b = iterator.next();
                iterator.set(String.valueOf(Math.pow(Double.parseDouble(a), Double.parseDouble(b))));
            }
        }
    }

    public static void calculateMultiplicationDivison(ListIterator<String> iterator) throws IncorrectInputException {
        while (iterator.hasNext()) {
            String op = iterator.next();
            if (op.equals("*")) {
                iterator.remove();
                Double a = Double.parseDouble(iterator.previous());
                iterator.remove();
                Double b = Double.parseDouble(iterator.next());
                iterator.set(String.valueOf(a * b));
            } else if (op.equals("/")) {
                iterator.remove();
                Double a = Double.parseDouble(iterator.previous());
                iterator.remove();
                Double b = Double.parseDouble(iterator.next());
                String check = String.valueOf(a / b);
                if (check.equals("Infinity")) throw new IncorrectInputException();
                iterator.set(String.valueOf(a / b));
            }
        }
    }

    public static void calculateAddingSubstraction(ListIterator<String> iterator) {
        while (iterator.hasNext()) {
            String op = iterator.next();
            if (op.equals("+")) {
                iterator.remove();
                Double a = Double.parseDouble(iterator.previous());
                iterator.remove();
                Double b = Double.parseDouble(iterator.next());
                iterator.set(String.valueOf(a + b));
            } else if (op.equals("-")) {
                iterator.remove();
                Double a = Double.parseDouble(iterator.previous());
                iterator.remove();
                Double b = Double.parseDouble(iterator.next());
                iterator.set(String.valueOf(a - b));
            }
        }
    }

    public static void validateExpression(ListIterator<String> iterator) throws IncorrectInputException {
        while (iterator.hasNext()) {
            String s = iterator.next();

            if (s.contains("%")) {
                if (s.charAt(s.length() - 1) != '%') {
                    throw new IncorrectInputException();
                }
            }

            if (s.equals("^")) {
                if (!iterator.hasNext()) {
                    throw new IncorrectInputException();
                } else {
                    try {
                        Double.valueOf(iterator.next());
                    } catch (Exception e) {
                        throw new IncorrectInputException();
                    }
                    iterator.previous();
                }
            }
        }
    }
}
