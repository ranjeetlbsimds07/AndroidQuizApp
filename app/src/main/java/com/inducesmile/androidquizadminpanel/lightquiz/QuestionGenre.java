package com.inducesmile.androidquizadminpanel.lightquiz;

/**
 * Created by demiurgosoft - 5/12/15
 */
public enum QuestionGenre {
    //GEOGRAFIA, CIENCIAS, ARTE, CINE,GeneralKnowledge, Entertainment,History,
    // Sports,Business,Computer,ArtAndDesign,Computer,Movies;
    GeneralKnowledge, Entertainment,History,Sports,Business,Computer;


    public static String[] names() {
        QuestionGenre[] questions = QuestionGenre.values();
        String[] result = new String[questions.length];

        for (int i = 0; i < questions.length; i++) {
            result[i] = questions[i].toString();
        }
        return result;
    }

}
