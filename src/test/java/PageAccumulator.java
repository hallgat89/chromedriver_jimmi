import pages.Article;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class PageAccumulator {

    private final String targetPath;
    private final String targetFile;
    private final String targetType = ".html";

    private final String openDoc = "<html>\n<head>%d</head>\n<body>";
    private final String endDoc = "\n</body>\n</html>";
    private Map<Integer, List<Article>> articles = new HashMap<>();

    public PageAccumulator(String targetPath, String targetFile) {
        this.targetFile = targetFile;
        this.targetPath = targetPath;
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
            articles.remove(oldestYear);
        }
    }

    private void writeArticles(Integer year, List<Article> outArticles) {
        String fileName = year.toString() + targetType;

        File targetFolder = new File(targetPath);
        targetFolder.mkdirs();

        File file = new File(targetFolder, fileName);
        try (
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
        ) {
            file.createNewFile();
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
        Collections.sort(outArticles);
        outArticles.forEach(e -> {
            try {
                bw.write("\n<div class=\"article\">\n");
                bw.write(e.toString());
                bw.write("\n</div>\n");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

}
