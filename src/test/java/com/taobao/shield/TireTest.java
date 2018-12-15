package com.taobao.shield;

import com.taobao.shield.ac.v0.trie.Emit;
import com.taobao.shield.ac.v0.trie.Trie;
import junit.framework.TestCase;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.LinkedList;

/**
 * @author hofer.bhf
 * created on 2018/12/15 5:52 PM
 */
public class TireTest extends TestCase {
    public void testBasic() {
        Trie trie = new Trie();
        trie.addKeyword("hers");
        trie.addKeyword("his");
        trie.addKeyword("she");
        trie.addKeyword("he");
        Collection<Emit> emits = trie.parseText("ushers");
        System.out.println(emits);
    }

    public void testMore() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("dictionary.txt"), "UTF-8"));
        String line;
        Trie asciiTrie = new Trie(true);
        Trie unicodeTrie = new Trie(false);
        LinkedList<String> dictionary = new LinkedList<String>();
        while ((line = br.readLine()) != null) {
            dictionary.add(line);
        }
        br.close();
        long start = System.currentTimeMillis();
        for (String word : dictionary) {
            asciiTrie.addKeyword(word);
        }
        System.out.printf("asciiTrie adding time:%dms%n", System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        for (String word : dictionary) {
            unicodeTrie.addKeyword(word);
        }
        System.out.printf("unicodeTrie adding time:%dms%n", System.currentTimeMillis() - start);

        int pressure = 100000;
        String text = "The quick brown fox jumps over the lazy dog";

        start = System.currentTimeMillis();
        System.out.println(asciiTrie.parseText(text));
        System.out.printf("asciiTrie building time:%dms%n", System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        System.out.println(unicodeTrie.parseText(text));
        System.out.printf("unicodeTrie building time:%dms%n", System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        for (int i = 0; i < pressure; ++i) {
            asciiTrie.parseText(text);
        }
        System.out.printf("asciiTrie used time:%dms%n", System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        for (int i = 0; i < pressure; ++i) {
            unicodeTrie.parseText(text);
        }
        System.out.printf("unicodeTrie used time:%dms%n", System.currentTimeMillis() - start);
    }
}
