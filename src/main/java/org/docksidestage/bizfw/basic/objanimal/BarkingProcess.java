package org.docksidestage.bizfw.basic.objanimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BarkingProcess  {

    private static final Logger logger = LoggerFactory.getLogger(Animal.class);
    protected int hitPoint = 10;


    public BarkedSound bark() {
        breatheIn();
        prepareAbdominalMuscle();
        String barkWord = getBarkWord();
        BarkedSound barkedSound = doBark(barkWord);
        return barkedSound;
    }

    protected void prepareAbdominalMuscle() {
        logger.debug("...Using my abdominal muscle"); // dummy implementation
        downHitPoint();
    }

    protected void breatheIn() {
        logger.debug("...Breathing in"); // dummy implementation
        downHitPoint();
    }

    protected abstract String getBarkWord();

    protected BarkedSound doBark(String barkWord) {
        downHitPoint();
        return new BarkedSound(barkWord);
    }
    // ===================================================================================
    //                                                                           Hit Point
    //
    protected void downHitPoint() {
        --hitPoint;
        if (hitPoint == 0) {
            throw new IllegalStateException("I'm very tired, so I want to sleep" + getBarkWord());
        }
    }

    // ===================================================================================
    //                                                                               Loud
    //                                                                              ======
    public String soundLoudly() {
        return bark().getBarkWord();
    }

}
