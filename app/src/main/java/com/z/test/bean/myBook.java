package com.z.test.bean;

public class myBook {
    private String bookSourceName;
    private int bookSourceType;
    private boolean enabled;
    private ruleSearch ruleSearch;

    public myBook(String bookSourceName, int bookSourceType, boolean enabled) {
        this.bookSourceName = bookSourceName;
        this.bookSourceType = bookSourceType;
        this.enabled = enabled;
    }

    public String getBookSourceName() {
        return bookSourceName;
    }

    public void setBookSourceName(String bookSourceName) {
        this.bookSourceName = bookSourceName;
    }

    public int getBookSourceType() {
        return bookSourceType;
    }

    public void setBookSourceType(int bookSourceType) {
        this.bookSourceType = bookSourceType;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public com.z.test.bean.ruleSearch getRuleSearch() {
        return ruleSearch;
    }
}
class ruleSearch{
    private String author;
    private String bookList;

    public String getAuthor() {
        return author;
    }

    public String getBookList() {
        return bookList;
    }
}
