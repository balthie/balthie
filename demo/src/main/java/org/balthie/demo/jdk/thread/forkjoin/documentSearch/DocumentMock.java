package org.balthie.demo.jdk.thread.forkjoin.documentSearch;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Random;

public class DocumentMock
{
    private String[] words = {"the","hello","world","good","bye","to","you","pack","java","thread","pool","charm"};
    
    String[][] generateDocument(int numLines, int numWords, String word)
    {
        int counter = 0;
        String document[][] = new String[numLines][numWords];
        Random r = new Random();
        for(int i=0;i<numLines;i++)
        {
            for(int j=0;j<numWords;j++)
            {
                int index = r.nextInt(words.length);
                document[i][j] = words[index];
                if(document[i][j].equals(word))
                {
                    counter++;
                }
            }
        }
        
        System.out.println(MessageFormat.format("【{0}】generate document at【{1, time, HH:mm:ss:ms}】 word【{2}】appears【{3}】times",
                Thread.currentThread().getName(),new Date(), word, counter));
        return document;
    }
}
