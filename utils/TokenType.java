package utils;

public enum TokenType {
    OPERAND, ADD, SUB, DIV, MOD, MUL, LB, RB;

    /**
     * 
     * @param s
     * @return TokenType element from string
     */
    public static TokenType getType(String s) {
        switch (s) {
            case "+":
                return ADD;
            case "-":
                return SUB;
            case "/":
                return DIV;
            case "x":
                return MUL;
            case "%":
                return MOD;
            case "(":
                return LB;
            case ")":
                return RB;
            default:
                return OPERAND;
        }
    }

    /**
     * 
     * @param nt
     * @return boolean value representing if the nt is an operator
     */
    public static boolean isOperator(TokenType nt) {
        return nt != OPERAND;
    }

    /**
     * 
     * @param s
     * @return boolean value representing if the s is an operator
     */
    public static boolean isOperator(String s) {
        return isOperator(getType(s));
    }

    /**
     * 
     * @param nt
     * @return boolean value representing if the nt is an operand
     */
    public static boolean isOperand(TokenType nt) {
        return nt == OPERAND;
    }

    /**
     * 
     * @param s
     * @return boolean value representing if s is and operand
     */
    public static boolean isOperand(String s) {
        return isOperand(getType(s));
    }

    public static boolean isDouble(String string) {
        try {
            Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
};
