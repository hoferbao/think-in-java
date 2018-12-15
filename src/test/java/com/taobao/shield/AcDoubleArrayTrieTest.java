package com.taobao.shield;

import com.taobao.shield.ac.v1.AcDoubleArrayTrie;
import junit.framework.TestCase;

import java.io.*;
import java.util.*;

/**
 * @author hofer.bhf
 * created on 2018/12/15 2:30 PM
 */
public class AcDoubleArrayTrieTest extends TestCase {
    public void testSimple() {
        AcDoubleArrayTrie<String> acdat = buildASimpleACDoubleArrayTrie();
        validateASimpleACDoubleArrayTrie(acdat);
    }

    private AcDoubleArrayTrie<String> buildASimpleACDoubleArrayTrie() {
        // Collect test data set
        TreeMap<String, String> map = new TreeMap<>();
        String[] keyArray = new String[]
                {
                        "hers",
                        "his",
                        "she",
                        "he"
                };
        for (String key : keyArray) {
            map.put(key, key);
        }
        // Build an AcDoubleArrayTrie
        AcDoubleArrayTrie<String> acdat = new AcDoubleArrayTrie<>();
        acdat.build(map);
        return acdat;
    }

    private void validateASimpleACDoubleArrayTrie(AcDoubleArrayTrie<String> acdat) {
        // Test it
        final String text = "uhers";
        acdat.parseText(text, (begin, end, value) -> {
            System.out.printf("[%d:%d]=%s\n", begin, end, value);
            assertEquals(text.substring(begin, end), value);
        });
        // Or simply use
        List<AcDoubleArrayTrie.Hit<String>> wordList = acdat.parseText(text);
        System.out.println(wordList);
    }


    public void testBuildAndParseWithBigFile() throws Exception {
        // Load test data from disk
        Set<String> dictionary = loadDictionary("cn/dictionary.txt");
        final String text = loadText("cn/text.txt");
        // You can use any type of Map to hold data
        Map<String, String> map = new TreeMap<String, String>();
//        Map<String, String> map = new HashMap<String, String>();
//        Map<String, String> map = new LinkedHashMap<String, String>();
        for (String key : dictionary) {
            map.put(key, key);
        }
        // Build an AcDoubleArrayTrie
        AcDoubleArrayTrie<String> acdat = new AcDoubleArrayTrie<String>();
        acdat.build(map);
        // Test it
        acdat.parseText(text, new AcDoubleArrayTrie.IHit<String>() {
            @Override
            public void hit(int begin, int end, String value) {
                assertEquals(text.substring(begin, end), value);
            }
        });
    }

    private static class CountHits implements AcDoubleArrayTrie.IHitCancellable<String> {
        private int count;
        private boolean countAll;

        CountHits(boolean countAll) {
            this.count = 0;
            this.countAll = countAll;
        }

        public int getCount() {
            return count;
        }

        @Override
        public boolean hit(int begin, int end, String value) {
            count += 1;
            return countAll;
        }
    }

    public void testMatches() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("space", 1);
        map.put("keyword", 2);
        map.put("ch", 3);
        AcDoubleArrayTrie<Integer> trie = new AcDoubleArrayTrie<Integer>();
        trie.build(map);

        assertTrue(trie.matches("space"));
        assertTrue(trie.matches("keyword"));
        assertTrue(trie.matches("ch"));
        assertTrue(trie.matches("  ch"));
        assertTrue(trie.matches("chkeyword"));
        assertTrue(trie.matches("oooospace2"));
        assertFalse(trie.matches("c"));
        assertFalse(trie.matches(""));
        assertFalse(trie.matches("spac"));
        assertFalse(trie.matches("nothing"));
    }

    public void testFirstMatch() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("space", 1);
        map.put("keyword", 2);
        map.put("ch", 3);
        AcDoubleArrayTrie<Integer> trie = new AcDoubleArrayTrie<Integer>();
        trie.build(map);

        AcDoubleArrayTrie.Hit<Integer> hit = trie.findFirst("space");
        assertEquals(0, hit.begin);
        assertEquals(5, hit.end);
        assertEquals(1, hit.value.intValue());

        hit = trie.findFirst("a lot of garbage in the space ch");
        assertEquals(24, hit.begin);
        assertEquals(29, hit.end);
        assertEquals(1, hit.value.intValue());

        assertNull(trie.findFirst(""));
        assertNull(trie.findFirst("value"));
        assertNull(trie.findFirst("keywork"));
        assertNull(trie.findFirst(" no pace"));
    }

    public void testCancellation() throws Exception {
        // Collect test data set
        TreeMap<String, String> map = new TreeMap<String, String>();
        String[] keyArray = new String[]
                {
                        "foo",
                        "bar"
                };
        for (String key : keyArray) {
            map.put(key, key);
        }
        // Build an AcDoubleArrayTrie
        AcDoubleArrayTrie<String> acdat = new AcDoubleArrayTrie<String>();
        acdat.build(map);
        // count matches
        String haystack = "sfwtfoowercwbarqwrcq";
        CountHits cancellingMatcher = new CountHits(false);
        CountHits countingMatcher = new CountHits(true);
        System.out.println("Testing cancellation");
        acdat.parseText(haystack, cancellingMatcher);
        acdat.parseText(haystack, countingMatcher);
        assertEquals(cancellingMatcher.count, 1);
        assertEquals(countingMatcher.count, 2);
    }

    private String loadText(String path) throws IOException {
        StringBuilder sbText = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(path), "UTF-8"));
        String line;
        while ((line = br.readLine()) != null) {
            sbText.append(line).append("\n");
        }
        br.close();

        return sbText.toString();
    }

    private Set<String> loadDictionary(String path) throws IOException {
        Set<String> dictionary = new TreeSet<String>();
        BufferedReader br = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(path), "UTF-8"));
        String line;
        while ((line = br.readLine()) != null) {
            dictionary.add(line);
        }
        br.close();

        return dictionary;
    }

    private void runTest(String dictionaryPath, String textPath) throws IOException {
        Set<String> dictionary = loadDictionary(dictionaryPath);
        String text = loadText(textPath);
        // Build a ahoCorasickNaive implemented by robert-bor

        // Build a AcDoubleArrayTrie implemented by hankcs
        AcDoubleArrayTrie<String> AcDoubleArrayTrie = new AcDoubleArrayTrie<String>();
        TreeMap<String, String> dictionaryMap = new TreeMap<String, String>();
        for (String word : dictionary) {
            dictionaryMap.put(word, word);  // we use the same text as the property of a word
        }
        AcDoubleArrayTrie.build(dictionaryMap);
        // Let's test the speed of the two Aho-Corasick automata
        System.out.printf("Parsing document which contains %d characters, with a dictionary of %d words.\n", text.length(), dictionary.size());
        long start = System.currentTimeMillis();
        long costTimeNaive = System.currentTimeMillis() - start;
        start = System.currentTimeMillis();
        AcDoubleArrayTrie.parseText(text, new AcDoubleArrayTrie.IHit<String>() {
            @Override
            public void hit(int begin, int end, String value) {

            }
        });
        long costTimeACDAT = System.currentTimeMillis() - start;
        System.out.printf("%-15s\t%-15s\t%-15s\n", "", "Naive", "ACDAT");
        System.out.printf("%-15s\t%-15d\t%-15d\n", "time", costTimeNaive, costTimeACDAT);
        System.out.printf("%-15s\t%-15.2f\t%-15.2f\n", "char/s", (text.length() / (costTimeNaive / 1000.0)), (text.length() / (costTimeACDAT / 1000.0)));
        System.out.printf("%-15s\t%-15.2f\t%-15.2f\n", "rate", 1.0, costTimeNaive / (double) costTimeACDAT);
        System.out.println("===========================================================================");
    }

    /**
     * Compare my AcDoubleArrayTrie with robert-bor's aho-corasick, notice that robert-bor's aho-corasick is
     * compiled under jdk1.8, so you will need jdk1.8 to run this test<br>
     * To avoid JVM wasting time on allocating memory, please use -Xms512m -Xmx512m -Xmn256m .
     *
     * @throws Exception
     */
    public void testBenchmark() throws Exception {
        runTest("en/dictionary.txt", "en/text.txt");
        runTest("cn/dictionary.txt", "cn/text.txt");
    }

    public void testSaveAndLoad() throws Exception {
        AcDoubleArrayTrie<String> acdat = buildASimpleACDoubleArrayTrie();
        final String tmpPath = System.getProperty("java.io.tmpdir").replace("\\\\", "/") + "/acdat.tmp";
        System.out.println("Saving acdat to: " + tmpPath);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(tmpPath));
        out.writeObject(acdat);
        out.close();
        System.out.println("Loading acdat from: " + tmpPath);
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(tmpPath));
        acdat = (AcDoubleArrayTrie<String>) in.readObject();
        validateASimpleACDoubleArrayTrie(acdat);
    }
}
