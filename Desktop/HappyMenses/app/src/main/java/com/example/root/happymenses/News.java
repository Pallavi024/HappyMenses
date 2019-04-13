package com.example.root.happymenses;

/**
 * Created by root on 13/4/19.
 */

public class News {

    private String mwebPublicationDate;
    private String mwebTitle;
    private String mSectionName;
    private String mwebUrl;
    private String mauthor;


    public News (String SectionName ,String webPublicationDate,String webTitle,String webUrl,String author){
        mSectionName=SectionName;
        mwebTitle=webTitle;
        mwebPublicationDate=webPublicationDate;
        mwebUrl=webUrl;
        mauthor=author;
    }

    public String  getwebPublicationDate(){
        return mwebPublicationDate;
    }
    public String getwebTitle(){return mwebTitle;}
    public String getSectionName(){
        return mSectionName;
    }
    public String getwebUrl(){return mwebUrl;}
    public String getauthor(){return mauthor;}
}
