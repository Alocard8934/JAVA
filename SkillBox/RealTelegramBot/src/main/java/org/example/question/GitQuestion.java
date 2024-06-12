package org.example.question;

public class GitQuestion extends AbstractQuestion{
    public GitQuestion() {
        super("Вопрос 3: С помощью какой команды в системе контроля версий Git можно посмотреть авторов различных строк в одном файле?");
    }

    @Override
    public boolean checkAnswer(String answer) {

        return answer.toLowerCase().contains("blame");
    }
}
