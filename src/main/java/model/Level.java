package model;

import com.google.gson.annotations.SerializedName;
import hint.HintText;

import java.util.List;

public class Level {
    @SerializedName("name")
    private final String name;

    @SerializedName("hints")
    private final List<HintText> hints;

    @SerializedName("correctAnswer")
    private final String correctAnswer;

    public Level(String name, List<HintText> hints, String correctAnswer) {
        this.name = name;
        this.hints = hints;
        this.correctAnswer = correctAnswer;
    }
}