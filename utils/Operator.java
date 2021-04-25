package utils;

import java.util.ArrayList;
import java.util.Arrays;

import static utils.TokenType.*;

public class Operator {
    static final ArrayList<TokenType> binaryOperators = new ArrayList<>(Arrays.asList(ADD, SUB, DIV, MOD, MUL));

    public static boolean isBinaryOperator(TokenType nt) {
        return Operator.binaryOperators.contains(nt);
    }

    public static boolean isBinaryOperator(String s){
        return isBinaryOperator(getType(s));
    }

    /**
     * 
     * @param nt
     * @return priority of operator
     */
    public static int getPriority(TokenType nt) {
        if (nt == LB || nt == RB)
            return 0;
        if (nt == SUB || nt == ADD)
            return 1;
        else if (nt == MUL || nt == DIV)
            return 2;
        return 4;
    }
}