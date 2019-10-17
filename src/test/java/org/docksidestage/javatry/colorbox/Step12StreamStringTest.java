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

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of String with color-box, using Stream API you can. <br>
 * Show answer by log() for question of javadoc.
 * @author jflute
 * @author Ajinkya Takawale
 */
public class Step12StreamStringTest extends PlainTestCase {

    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * What is color name length of first color-box? <br>
     * (最初のカラーボックスの色の名前の文字数は？)
     */
    public void test_length_basic() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String answer = colorBoxList.stream()
                .findFirst()
                .map(colorBox -> colorBox.getColor().getColorName())
                .map(colorName -> colorName.length() + " (" + colorName + ")")
                .orElse("*not found");
        log(answer);
    }

    /**
     * Which string has max length in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長い文字列は？)
     */
    public void test_length_findMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> collection = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof String)
                .map(content -> content.toString())
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
        log(collection.get(collection.size() - 1));
    }

    /**
     * How many characters are difference between max and min length of string in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長いものと短いものの差は何文字？)
     */
    public void test_length_findMaxMinDiff() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> collection = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof String)
                .map(content -> content.toString())
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
        log("Max diff: ", (collection.get(collection.size() - 1).length() - collection.get(0).length()));
    }

    // has small #adjustmemts from ClassicStringTest
    //  o sort allowed in Stream
    /**
     * Which value (toString() if non-string) has second-max length in color-boxes? (sort allowed in Stream)<br>
     * (カラーボックスに入ってる値 (文字列以外はtoString()) の中で、二番目に長い文字列は？ (Streamでのソートありで))
     */
    public void test_length_findSecondMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> collection = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof String)
                .map(content -> content.toString())
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());

        log("Second Max ", (collection.get(collection.size() - 2)));

    }

    /**
     * How many total lengths of strings in color-boxes? <br>
     * (カラーボックスに入ってる文字列の長さの合計は？)
     */
    public void test_length_calculateLengthSum() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> collection = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof String)
                .map(content -> content.toString())
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());

        log("Total length of strings: ", collection.stream().map(text -> text.length()).reduce(0, Integer::sum));

    }

    /**
     * Which color name has max length in color-boxes? <br>
     * (カラーボックスの中で、色の名前が一番長いものは？)
     */
    public void test_length_findMaxColorSize() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> collection = colorBoxList.stream()
                .map(colorBox -> colorBox.getColor().getColorName())
                .filter(content -> content instanceof String)
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());

        log("Max color name: ", collection.get(collection.size() - 2));

    }

    // ===================================================================================
    //                                                            startsWith(), endsWith()
    //                                                            ========================
    /**
     * What is color in the color-box that has string starting with "Water"? <br>
     * ("Water" で始まる文字列をしまっているカラーボックスの色は？)
     */
    public void test_startsWith_findFirstWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<ColorBox> colorBoxes = colorBoxList.stream()
                .filter(colorBox -> colorBox.getSpaceList()
                        .stream()
                        .anyMatch(boxSpace -> boxSpace.getContent() instanceof String && boxSpace.getContent()
                                .toString()
                                .startsWith("Water")))
                .collect(Collectors.toList());
        String colorName = colorBoxes.get(0).getColor().getColorName();
        log(colorName);
    }

    /**
     * What is color in the color-box that has string ending with "front"? <br>
     * ("front" で終わる文字列をしまっているカラーボックスの色は？)
     */
    public void test_endsWith_findLastWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<ColorBox> colorBoxes = colorBoxList.stream()
                .filter(colorBox -> colorBox.getSpaceList()
                        .stream()
                        .anyMatch(
                                boxSpace -> boxSpace.getContent() instanceof String && boxSpace.getContent().toString().endsWith("front")))
                .collect(Collectors.toList());
        String colorName = colorBoxes.get(0).getColor().getColorName();
        log(colorName);
    }

    // ===================================================================================
    //                                                            indexOf(), lastIndexOf()
    //                                                            ========================
    /**
     * What number character is starting with first "front" of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列で、最初の "front" は何文字目から始まる？)
     */
    public void test_indexOf_findIndex() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String contentString = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(boxSpace -> boxSpace.getContent() instanceof String && boxSpace.getContent().toString().endsWith("front"))
                .map(boxSpace -> boxSpace.getContent().toString())
                .findFirst()
                .orElse("*not found");

        int index = contentString.indexOf("front");

        log("Character index is : " + index);

    }

    /**
     * What number character is starting with the late "ど" of string containing plural "ど"s in color-boxes? (e.g. "どんどん" => 3) <br>
     * (カラーボックスに入ってる「ど」を二つ以上含む文字列で、最後の「ど」は何文字目から始まる？ (e.g. "どんどん" => 3))
     */
    public void test_lastIndexOf_findIndex() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String contentString = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(boxSpace -> boxSpace.getContent() instanceof String && boxSpace.getContent().toString().contains("ど"))
                .map(boxSpace -> boxSpace.getContent().toString())
                .findFirst()
                .orElse("*not found");

        int index = contentString.lastIndexOf("ど");

        log("Character index is : " + index);

    }

    // ===================================================================================
    //                                                                         substring()
    //                                                                         ===========
    /**
     * What character is first of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列の最初の一文字は？)
     */
    public void test_substring_findFirstChar() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String contentString = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(boxSpace -> boxSpace.getContent() instanceof String && boxSpace.getContent().toString().endsWith("front"))
                .map(boxSpace -> boxSpace.getContent().toString())
                .findFirst()
                .orElse("*not found");

        log("Character is : " + contentString.substring(0, 1));

    }

    /**
     * What character is last of string starting with "Water" in color-boxes? <br>
     * (カラーボックスに入ってる "Water" で始まる文字列の最後の一文字は？)
     */
    public void test_substring_findLastChar() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String contentString = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(boxSpace -> boxSpace.getContent() instanceof String && boxSpace.getContent().toString().startsWith("Water"))
                .map(boxSpace -> boxSpace.getContent().toString())
                .findFirst()
                .orElse("*not found");

        log("Character is : " + contentString.substring(contentString.length() - 1));

    }

    // ===================================================================================
    //                                                                           replace()
    //                                                                           =========
    /**
     * How many characters does string that contains "o" in color-boxes and removing "o" have? <br>
     * (カラーボックスに入ってる "o" (おー) を含んだ文字列から "o" を全て除去したら何文字？)
     */
    public void test_replace_remove_o() {

        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String contentString = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(boxSpace -> boxSpace.getContent() instanceof String && boxSpace.getContent().toString().contains("o"))
                .map(boxSpace -> boxSpace.getContent().toString())
                .findFirst()
                .orElse("*not found");

        log("Number of remaining characters is : " + contentString.replace("o", "").length());

    }

    /**
     * What string is path string of java.io.File in color-boxes, which is replaced with "/" to Windows file separator? <br>
     * カラーボックスに入ってる java.io.File のパス文字列のファイルセパレーターの "/" を、Windowsのファイルセパレーターに置き換えた文字列は？
     */
    public void test_replace_fileseparator() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String pathName = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(boxSpace -> boxSpace.getContent() instanceof File)
                .map(boxSpace -> ((File) boxSpace.getContent()).getPath())
                .findFirst()
                .orElse("*not found");

        log("Path String: " + pathName);
    }

    // ===================================================================================
    //                                                                    Welcome to Devil
    //                                                                    ================
    /**
     * What is total length of text of DevilBox class in color-boxes? <br>
     * (カラーボックスの中に入っているDevilBoxクラスのtextの長さの合計は？)
     */
    public void test_welcomeToDevil() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> text = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(boxSpace -> boxSpace.getContent() != null && boxSpace.getContent() instanceof YourPrivateRoom.DevilBox)
                .map(boxSpace -> {
                    YourPrivateRoom.DevilBox devil = (YourPrivateRoom.DevilBox) boxSpace.getContent();
                    devil.wakeUp();
                    devil.allowMe();
                    devil.open();
                    String boxText = "";
                    try {
                        boxText = devil.getText();
                    } catch (YourPrivateRoom.DevilBoxTextNotFoundException e) {
                        log("Message: ", e);
                    }
                    return boxText;
                })
                .collect(Collectors.toList());

        log("Total Length is: " + text.stream().mapToInt(tex -> tex.length()).sum());
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What string is converted to style "map:{ key = value ; key = value ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = value ; ... }" という形式で表示すると？)
     */
    public void test_showMap_flat() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(boxSpace -> boxSpace.getContent() != null && boxSpace.getContent() instanceof Map)
                .map(boxSpace -> {
                    if (!(boxSpace.getContent() == null) && (boxSpace.getContent() instanceof Map)) {
                        String result = "map:{ ";
                        Map<String, Object> gotMap = (Map<String, Object>) boxSpace.getContent();
                        for (Map.Entry<String, Object> entry : gotMap.entrySet()) {
                            String key = entry.getKey();
                            Object value = entry.getValue();
                            result = result + key + " = " + value + " ; ";
                        }
                        result = result.substring(0, result.length() - 3) + " }";
                        log("Found map: ", result);
                    }
                    return null;
                })
                .collect(Collectors.toList());
    }



    /**
     * What string is converted to style "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" という形式で表示すると？)
     */
    public void test_showMap_nested() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(boxSpace -> boxSpace.getContent() != null && boxSpace.getContent() instanceof Map)
                .map(boxSpace -> {
                    if (!(boxSpace.getContent() == null) && (boxSpace.getContent() instanceof java.util.Map)) {
                    String result = "map:{ ";
                    Map<String, Object> gotMap = (Map<String, Object>) boxSpace.getContent();
                    for (Map.Entry<String, Object> entry : gotMap.entrySet()) {
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        if (value instanceof Map) {
                            result = result + helperMapParser(key, (Map) value) + " ; ";
                        } else {
                            result = result + key + " = " + value + " ; ";
                        }
                    }
                    result = result.substring(0, result.length() - 3) + " }";
                    log("Found map: ", result);
                    return  result;
                }
                    return null;
                })
                .collect(Collectors.toList());
    }

    public String helperMapParser(String key, Map digMap) {
        String result = "";
        result = result + key + " = map:{ ";
        for (Map.Entry<String, Object> nestEntry : ((Map<String, Object>) digMap).entrySet()) {
            String nestKey = nestEntry.getKey();
            Object nestValue = nestEntry.getValue();
            if (nestValue instanceof Map) {
                result = result + helperMapParser(nestKey, (Map) nestValue) + " ; ";
            } else
                result = result + nestKey + " = " + nestValue + " ; ";
        }
        result = result.substring(0, result.length() - 2) + " }";
        return result;
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    // has small #adjustmemts from ClassicStringTest
    //  o comment out because of too difficult to be stream?
    ///**
    // * What string of toString() is converted from text of SecretBox class in upper space on the "white" color-box to java.util.Map? <br>
    // * (whiteのカラーボックスのupperスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
    // */
    //public void test_parseMap_flat() {
    //}
    //
    ///**
    // * What string of toString() is converted from text of SecretBox class in both middle and lower spaces on the "white" color-box to java.util.Map? <br>
    // * (whiteのカラーボックスのmiddleおよびlowerスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
    // */
    //public void test_parseMap_nested() {
    //}
}
