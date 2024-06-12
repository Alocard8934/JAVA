package org.example.question;

public class JavaQuestion extends AbstractQuestion{
    public JavaQuestion() {
        super("Вопрос 1: Сколько в языке програмирования Java есть примитивов?");
    }

    @Override
    public boolean checkAnswer(String answer) {
        return answer.equals("8");
    }
}
