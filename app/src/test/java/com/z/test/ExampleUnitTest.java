package com.z.test;

import com.z.test.Utils.JsonUtil;
import com.z.test.bean.myBook;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        myBook book= JsonUtil.getBookBean("{\n" +
                "    \"bookSourceComment\": \"\",\n" +
                "    \"bookSourceGroup\": \"\\u666e\\u901a\",\n" +
                "    \"bookSourceName\": \"360\\u5c0f\\u8bf4\\u7f51tushu360\",\n" +
                "    \"bookSourceType\": 0,\n" +
                "    \"bookSourceUrl\": \"http:\\/\\/www.tushu360.com\",\n" +
                "    \"customOrder\": 0,\n" +
                "    \"enabled\": true,\n" +
                "    \"enabledExplore\": true,\"ruleSearch\": {\n" +
                "      \"author\": \"class.author@text\",\n" +
                "      \"bookList\": \"class.library@li\",\n" +
                "      \"bookUrl\": \"tag.a.0@href||tag.a.1@href\",\n" +
                "      \"coverUrl\": \"class.bookimg@img@src\",\n" +
                "      \"intro\": \"class.intro@text\",\n" +
                "      \"kind\": \"class.layui-badge layui-bg-blue@text\",\n" +
                "      \"lastChapter\": \"class.chapter@text\",\n" +
                "      \"name\": \"class.bookname@text\",\n" +
                "      \"wordCount\": \"tag.p.0@tag.a.1@text\"\n" +
                "    }}");
        System.out.println("book = " + book);
        //assertEquals(4, 2 + 2);
    }
}