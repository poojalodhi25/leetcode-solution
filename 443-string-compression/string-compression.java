class Solution {
    public int compress(char[] chars) {

        int read = 0;
        int write = 0;

        while (read < chars.length) {

            char current = chars[read];

            int start = read;

            // Find complete group
            while (read < chars.length
                    && chars[read] == current) {

                read++;
            }

            // Write character
            chars[write] = current;
            write++;

            // Number of repetitions
            int count = read - start;

            // Write count only if > 1
            if (count > 1) {

                String number = String.valueOf(count);

                for (char digit : number.toCharArray()) {

                    chars[write] = digit;
                    write++;
                }
            }
        }

        return write;
    }
}