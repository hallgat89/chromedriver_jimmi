import pages.Article;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class PageAccumulator {

    private final String targetFile;
    private final String targetType = ".html";

    private final String openDoc = "<html><head>%d</head><body>";
    private final String endDoc = "</body></html>";
    private Map<Integer, List<Article>> articles = new HashMap<>();

    public PageAccumulator(String targetFile) {
        this.targetFile = targetFile;
    }

    public void addArticle(Article article) {
        Integer year = article.getYear();
        if (!articles.containsKey(year)) {
            ArrayList<Article> newList = new ArrayList<Article>();
            newList.add(article);
            articles.put(year, newList);
        } else {
            articles.get(year).add(article);
        }
    }

    // flushes the articles with older keys if there is any
    public void flushOld() {
        while (articles.keySet().size() > 1) {
            Integer oldestYear = articles.keySet().stream().min(Integer::compareTo).get();
            List<Article> currentList = articles.get(oldestYear);
            writeArticles(oldestYear, currentList);
            articles.remove(oldestYear);
        }
    }

    public void flushAll() {
        while (articles.keySet().size() > 0) {
            Integer oldestYear = articles.keySet().stream().min(Integer::compareTo).get();
            List<Article> currentList = articles.get(oldestYear);
        }
    }

    private void writeArticles(Integer year, List<Article> outArticles) {
        String fileName=year.toString() + targetType;
        File isActuallyADir=new File("dickbutt");
        isActuallyADir.mkdirs();
        File file = new File(isActuallyADir,fileName);
        try (
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
        ) {
            writeDocument(year, outArticles, bw);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void writeDocument(Integer year, List<Article> outArticles, BufferedWriter bw) throws IOException {
        bw.write(String.format(openDoc, year));
        includeArticles(outArticles, bw);
        bw.write(String.format(endDoc, year));
    }

    private void includeArticles(List<Article> outArticles, BufferedWriter bw) {
        outArticles.forEach(e -> {
            try {
                bw.write(e.toString());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

}
