package Trie;

import java.util.List;

/**
 * Example 1:
 *
 * Input: dict = ["cat", "bat", "rat"]
 * sentence = "the cattle was rattled by the battery"
 * Output: "the cat was rat by the bat"
 *
 */
public class LC648ReplaceWords {
    class TrieNode {
        String word;
        TrieNode[] children;
        public TrieNode() {
            children = new TrieNode[26];
        }
    }
    public String replaceWords(List<String> dict, String sentence) {
        TrieNode trie = new TrieNode();
        for (String word : dict) {
            TrieNode p = trie;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (p.children[index] == null) {
                    p.children[index] = new TrieNode();
                }
                p = p.children[index];
            }
            p.word = word;
        }

        StringBuilder res = new StringBuilder();
        for (String word : sentence.split("\\s+")) {
            if (res.length() > 0) {
                res.append(" ");
            }

            TrieNode p = trie;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (p.children[index] == null || p.word != null) {
                    break;
                }
                p = p.children[index];
            }
            res.append(p.word != null ? p.word : word);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String word = "asadaf";
        System.out.println(word.startsWith("ss"));
        System.out.println(word.endsWith("ss"));
    }
}
