package com.bowleu.exam.java;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCoding {

    public static String encode(String text) {
        Map<Character, String> codes = buildHuffmanTree(text);
        StringBuilder encoded = new StringBuilder();
        for (char c : text.toCharArray()) {
            encoded.append(codes.get(c));
        }

        return encoded.toString();
    }

    private static void buildCodes(HuffmanNode root, String code, Map<Character, String> codes) {
        if (root == null) return;

        if (root.getLeft() == null && root.getRight() == null) {
            codes.put(root.getChar(), code);
        }

        buildCodes(root.getLeft(), code + "0", codes);
        buildCodes(root.getRight(), code + "1", codes);
    }

    private static Map<Character, String> buildHuffmanTree(String text) {

        Map<Character, Integer> freqMap = new HashMap<>();

        for (char c : text.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(new HuffmanComparator());

        for (var entry : freqMap.entrySet()) {
            pq.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (pq.size() > 1) {

            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();

            int leftFreq = 0;
            if (left != null)
                leftFreq = left.getFreq();
            int rightFreq = 0;
            if (right != null)
                rightFreq = right.getFreq();

            HuffmanNode parent = new HuffmanNode('\0', leftFreq + rightFreq);
            parent.setLeft(left);
            parent.setRight(right);

            pq.add(parent);
        }

        HuffmanNode root = pq.poll();

        Map<Character, String> codes = new HashMap<>();
        buildCodes(root, "", codes);

        return codes;
    }

    private static class HuffmanComparator implements Comparator<HuffmanNode> {
        public int compare(HuffmanNode a, HuffmanNode b) {
            return a.getFreq() - b.getFreq();
        }
    }

    private static class HuffmanNode {
        private final char ch;
        private final int freq;
        private HuffmanNode left;
        private HuffmanNode right;

        HuffmanNode(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }

        public char getChar() {
            return ch;
        }

        public int getFreq() {
            return freq;
        }

        public HuffmanNode getLeft() {
            return left;
        }

        public void setLeft(HuffmanNode left) {
            this.left = left;
        }

        public HuffmanNode getRight() {
            return right;
        }

        public void setRight(HuffmanNode right) {
            this.right = right;
        }
    }
}