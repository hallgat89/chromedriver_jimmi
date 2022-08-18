import pages.Article;

import java.util.*;

public class PageWriter {

    private String targetFolder;
    Map<Integer, List<Article>> articles=new HashMap<>();

    public PageWriter(String targetFolder) {
        this.targetFolder = targetFolder;
    }

    public void addArticle(Article article)
    {
        Integer year = article.getYear();
        if(!articles.containsKey(year))
        {
            ArrayList<Article> newList = new ArrayList<Article>();
            newList.add(article);
            articles.put(year,newList);
        }else{
            articles.get(year).add(article);
        }
    }

}
