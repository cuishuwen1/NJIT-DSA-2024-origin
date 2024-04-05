package oy.tol.tra;

public class ParenthesisChecker {

    private ParenthesisChecker() {
    }

    public static int checkParentheses(StackInterface<Character> parenthesisStack, String inputString) throws ParenthesesException {
        int count = 0;

        for (int index = 0; index < inputString.length(); index++) {
            char currentChar = inputString.charAt(index);

            if (currentChar == '(' || currentChar == '[' || currentChar == '{') {
                try {
                    parenthesisStack.push(currentChar);
                    count++;
                } catch (Exception e) {
                    throw new ParenthesesException("Stack is full. Cannot push more characters.",
                            ParenthesesException.STACK_FAILURE);
                }
            } else if (currentChar == ')' || currentChar == ']' || currentChar == '}') {
                if (parenthesisStack.isEmpty()) {
                    throw new ParenthesesException("There are too many closing parentheses.",
                            ParenthesesException.TOO_MANY_CLOSING_PARENTHESES);
                }
                Character popped = parenthesisStack.pop();
                count++;

                if ((currentChar == ')' && popped != '(') || (currentChar == ']' && popped != '[') || (currentChar == '}' && popped != '{')) {
                    throw new ParenthesesException("Wrong kind of parenthesis were in the text.",
                            ParenthesesException.PARENTHESES_IN_WRONG_ORDER);
                }
            }
        }

        if (!parenthesisStack.isEmpty()) {
            throw new ParenthesesException("There are more opening than closing parentheses.",
                    ParenthesesException.TOO_FEW_CLOSING_PARENTHESES);
        }

        return count;
    }
}
