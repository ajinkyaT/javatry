package org.docksidestage.bizfw.basic.objanimal;

public class AnimalConcrete implements Eating{


    public String getBarkWord() {
        return "Concrete Animal";
    }

    @Override
    public String getEatSound() {
        return "Chop Chop";
    }
}
