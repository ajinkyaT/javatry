/*
 * Copyright 2019-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.javatry.colorbox;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.impl.DoorColorBox;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of Number with color-box. <br>
 * Show answer by log() for question of javadoc.
 * @author jflute
 * @author Ajinkya Takawale
 */
public class Step13NumberTest extends PlainTestCase {

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    /**
     * How many integer-type values in color-boxes are between 0 and 54? <br>
     * (カラーボックの中に入っているInteger型で、0から54までの値は何個ある？)
     */
    public void test_countZeroToFiftyFour_IntegerOnly() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String maxString = "";
        int count = 0;
        for (ColorBox colorBox : colorBoxList) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object content = boxSpace.getContent();
                if (content instanceof Integer) {
                    if (((Integer) content) > 0 && ((Integer) content) < 54) {
                        count++;
                    }
                }
            }
        }

        log("Total count: " + count);

    }

    /**
     * How many number values in color-boxes are between 0 and 54? <br>
     * (カラーボックの中に入っている数値で、0から54までの値は何個ある？)
     */
    public void test_countZeroToFiftyFour_Number() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int count = 0;
        for (ColorBox colorBox : colorBoxList) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object content = boxSpace.getContent();
                if (content instanceof Number) {
                    if ((((Number) content).intValue()) > 0 && (((Number) content).intValue()) < 54) {
                        count++;
                    }
                }
            }
        }

        log("Total count: " + count);

    }

    /**
     * What color name is used by color-box that has integer-type content and the biggest width in them? <br>
     * (カラーボックスの中で、Integer型の Content を持っていてBoxSizeの幅が一番大きいカラーボックスの色は？)
     */
    public void test_findColorBigWidthHasInteger() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String color = "";
        int width = 0;

        for (ColorBox colorBox : colorBoxList) {

            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object content;
                try {
                    content = boxSpace.getContent();
                } catch (RuntimeException e) {
                    continue;
                }
                if (content != null && content instanceof Integer && (colorBox.getSize().getWidth() > width)) {
                    color = colorBox.getColor().getColorName();
                    width = colorBox.getSize().getWidth();
                }
            }
        }

        log(color);
    }

    /**
     * What is total of BigDecimal values in List in color-boxes? <br>
     * (カラーボックスの中に入ってる List の中の BigDecimal を全て足し合わせると？)
     */
    public void test_sumBigDecimalInList() {

        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List numList = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(boxSpace -> boxSpace.getContent() instanceof ArrayList)
                .map(boxSpace -> {
                    return (List) boxSpace.getContent();
                })
                .findFirst()
                .orElse(new ArrayList());

        BigDecimal sum = new BigDecimal(0);
        for (int i = 0; i < numList.size(); i++) {

            if (numList.get(i) instanceof BigDecimal) {
                String bigValue = numList.get(i).toString();
                BigDecimal basisValue = new BigDecimal(bigValue);
                sum = sum.add(basisValue);
            }
        }
        log(sum.toString());
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What key is related to value that is max number in Map that has only number in color-boxes? <br>
     * (カラーボックスに入ってる、valueが数値のみの Map の中で一番大きいvalueのkeyは？)
     */
    public void test_findMaxMapNumberValue() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        Map<String, Integer> map = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(boxSpace -> boxSpace.getContent() instanceof Map<?, ?>)
                .map(boxSpace -> {
                    return (Map) boxSpace.getContent();
                })
                .findFirst()
                .orElse(new HashMap());

        log(map.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey());
    }

    /**
     * What is total of number or number-character values in Map in purple color-box? <br> 
     * (purpleのカラーボックスに入ってる Map の中のvalueの数値・数字の合計は？)
     */
    public void test_sumMapNumberValue() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        Map<String, ?> map = colorBoxList.stream()
                .filter(colorBox -> colorBox.getColor().getColorName() == "purple")
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(boxSpace -> (boxSpace.getContent() instanceof Map<?, ?>) && ((Map) boxSpace.getContent()).values()
                        .toArray()[0] instanceof Number)
                .map(boxSpace -> {
                    return (Map) boxSpace.getContent();
                })
                .findFirst()
                .orElse(new HashMap());

        int sum = 0;
        List<Object> nums = new ArrayList<Object>(map.values());

        for (int i = 0; i < nums.size(); i++) {
                String value = nums.get(i).toString().replace('O','0');
                int intValue = Integer.parseInt(value);
                sum = sum + intValue;
            }
        log("Total is: ",sum);
        
    }
}

