class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        int n = s.length();
        int m = words.length;
        int len = words[0].length();
        int total = m * len;

        if (n < total) return ans;

        Map<String, Integer> target = new HashMap<>();
        for (String w : words) {
            target.put(w, target.getOrDefault(w, 0) + 1);
        }

        for (int i = 0; i < len; i++) {
            int left = i, count = 0;
            Map<String, Integer> window = new HashMap<>();

            for (int right = i; right + len <= n; right += len) {
                String word = s.substring(right, right + len);

                if (target.containsKey(word)) {
                    window.put(word, window.getOrDefault(word, 0) + 1);
                    count++;

                    while (window.get(word) > target.get(word)) {
                        String leftWord = s.substring(left, left + len);
                        window.put(leftWord, window.get(leftWord) - 1);
                        left += len;
                        count--;
                    }

                    if (count == m) {
                        ans.add(left);
                        String leftWord = s.substring(left, left + len);
                        window.put(leftWord, window.get(leftWord) - 1);
                        left += len;
                        count--;
                    }
                } else {
                    window.clear();
                    count = 0;
                    left = right + len;
                }
            }
        }

        return ans;
    }
}