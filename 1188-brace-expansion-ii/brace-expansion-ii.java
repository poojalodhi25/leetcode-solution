import java.util.*;

class Solution {

    public List<String> braceExpansionII(String expression) {

        Set<String> result = solve(expression);

        List<String> ans = new ArrayList<>(result);

        Collections.sort(ans);

        return ans;
    }

    private Set<String> solve(String s) {

        Set<String> result = new HashSet<>();

        int i = 0;

        while (i < s.length()) {

            Set<String> current = new HashSet<>();

            if (s.charAt(i) == '{') {

                int start = i;
                int count = 0;

                while (i < s.length()) {

                    if (s.charAt(i) == '{') {
                        count++;
                    }

                    if (s.charAt(i) == '}') {
                        count--;
                    }

                    if (count == 0) {
                        break;
                    }

                    i++;
                }

                String inside = s.substring(start + 1, i);

                current = parseBraces(inside);

            } else {

                current.add(String.valueOf(s.charAt(i)));
            }

            if (result.isEmpty()) {

                result.addAll(current);

            } else {

                Set<String> temp = new HashSet<>();

                for (String a : result) {

                    for (String b : current) {

                        temp.add(a + b);
                    }
                }

                result = temp;
            }

            i++;
        }

        return result;
    }

    private Set<String> parseBraces(String s) {

        Set<String> result = new HashSet<>();

        int level = 0;
        int start = 0;

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (ch == '{') {
                level++;
            }

            else if (ch == '}') {
                level--;
            }

            else if (ch == ',' && level == 0) {

                result.addAll(solve(s.substring(start, i)));

                start = i + 1;
            }
        }

        result.addAll(solve(s.substring(start)));

        return result;
    }
}