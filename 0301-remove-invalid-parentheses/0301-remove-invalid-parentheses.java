class Solution {

    Set<String> result = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {

        int left = 0;
        int right = 0;

        // Find minimum number of removals
        for (char c : s.toCharArray()) {

            if (c == '(') {
                left++;
            } 
            else if (c == ')') {

                if (left > 0) {
                    left--;
                } 
                else {
                    right++;
                }
            }
        }

        backtrack(s, 0, left, right, 0, "");

        return new ArrayList<>(result);
    }

    private void backtrack(String s, int index,
                           int leftRem, int rightRem,
                           int balance, String current) {

        // End of string
        if (index == s.length()) {

            if (leftRem == 0 &&
                rightRem == 0 &&
                balance == 0) {

                result.add(current);
            }

            return;
        }

        char c = s.charAt(index);

        // REMOVE
        if (c == '(' && leftRem > 0) {
            backtrack(s, index + 1,
                      leftRem - 1, rightRem,
                      balance, current);
        }

        if (c == ')' && rightRem > 0) {
            backtrack(s, index + 1,
                      leftRem, rightRem - 1,
                      balance, current);
        }

        // KEEP
        if (c != '(' && c != ')') {

            backtrack(s, index + 1,
                      leftRem, rightRem,
                      balance, current + c);

        } 
        else if (c == '(') {

            backtrack(s, index + 1,
                      leftRem, rightRem,
                      balance + 1,
                      current + c);

        } 
        else if (c == ')' && balance > 0) {

            backtrack(s, index + 1,
                      leftRem, rightRem,
                      balance - 1,
                      current + c);
        }
    }
}