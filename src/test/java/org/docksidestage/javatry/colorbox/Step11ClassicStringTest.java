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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.color.BoxColor;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of String with color-box, not using Stream API. <br>
 * Show answer by log() for question of javadoc. <br>
 * <pre>
 * addition:
 * o e.g. "string in color-boxes" means String-type content in space of color-box
 * o don't fix the YourPrivateRoom class and color-box classes
 * </pre>
 * @author jflute
 * @author Ajinkya Takawale
 */
public class Step11ClassicStringTest extends PlainTestCase {

    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * How many lengths does color name of first color-boxes have? <br>
     * (最初のカラーボックスの色の名前の文字数は？)
     */
    public void test_length_basic() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            ColorBox colorBox = colorBoxList.get(0);
            BoxColor boxColor = colorBox.getColor();
            String colorName = boxColor.getColorName();
            int answer = colorName.length();
            log(answer + " (" + colorName + ")"); // also show name for visual check
        } else {
            log("*not found");
        }
    }

    /**
     * Which string has max length in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長い文字列は？)
     */
    public void test_length_findMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String maxString = "";
        int length = 0;
        for (ColorBox colorBox : colorBoxList) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object content = boxSpace.getContent();
                if (content instanceof String) {
                    if (((String) content).length() > length) {
                        maxString = content.toString();
                        length = maxString.length();
                    }
                }
            }
        }

        log(length + " (" + maxString + ")");

    }

    /**
     * How many characters are difference between max and min length of string in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長いものと短いものの差は何文字？)
     */
    public void test_length_findMaxMinDiff() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String maxString = "";
        String minString = "initialLongStringToStart";
        int maxLength = 0;
        int minLength = minString.length();
        for (ColorBox colorBox : colorBoxList) {
            for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                Object content = boxSpace.getContent();
                if (content instanceof String) {
                    // TODO (((String) content).length() > maxLength) looks complicated,
                    //  create String contentString = content.toString();
                    //  and you can use it in if-max, if-min, maxString and minString by zaya 2019/10/18
                    if (((String) content).length() > maxLength) {
                        maxString = content.toString();
                        maxLength = maxString.length();
                    }
                    if (((String) content).length() < minLength) {  //DONE TODO ajinkya It's good, but if 'if sentence' move into previous
                        minString = content.toString();             // for sentence, you can reduce for loop steps. by ちーかま
                        minLength = minString.length();
                    }
                }
            }
        }

        log("difference is: " + (maxString.length() - minString.length()) + " (" + maxString, minString + ")");
    }

    /**
     * Which value (toString() if non-string) has second-max length in color-boxes? (without sort) <br>
     * (カラーボックスに入ってる値 (文字列以外はtoString()) の中で、二番目に長い文字列は？ (ソートなしで))
     */
    public void test_length_findSecondMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            String maxString = "";
            int length = 0;
            int secondMax = 0;
            String secondMaxString = "";
            for (ColorBox colorBox : colorBoxList) {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    // TODO fix to if (boxSpace.getContent() != null) by zaya 2019/10/18
                    if (!(boxSpace.getContent() == null)) {
                        String content = boxSpace.getContent().toString();
                        if (content.length() > 0) {
                            // TODO ajinkya please confirm,　if content.length() is between length and secondMax by ちーかま
                            // DONE : content.length() is > length and secondMax is always <= length
                            if (content.length() > length) {
                                secondMaxString = maxString;
                                secondMax = length;
                                maxString = content;
                                length = maxString.length();
                            }
                        }
                    }
                }
            }
            log(secondMax + "  ,Second max string is: " + " (" + secondMaxString + ")");
        }
    }

    /**
     * How many total lengths of strings in color-boxes? <br>
     * (カラーボックスに入ってる文字列の長さの合計は？)
     */
    public void test_length_calculateLengthSum() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int totalLength = 0;
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    // DONE TODO ajinkya please reformat !(boxSpace.getContent() == null) -> boxSpace.getContent() != null by ちーかま
                    if ((boxSpace.getContent() != null) && (boxSpace.getContent() instanceof String)) {
                        String content = boxSpace.getContent().toString();
                        if (content.length() > 0) {
                            totalLength = totalLength + content.length();
                        }
                    }
                }
            }
        }
        log("Total length is: " + totalLength);
    }
    /**
     * Which color name has max length in color-boxes? <br>
     * (カラーボックスの中で、色の名前が一番長いものは？)
     */
    public void test_length_findMaxColorSize() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int maxLength = 0;
        String maxName = "";
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                if (colorBox.getColor().getColorName().length() > maxLength) {
                    maxName = colorBox.getColor().getColorName();
                    maxLength = maxName.length();
                }
            }
        }
        log("Max Length is: " + maxLength, "Color is: " + maxName);
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
        String color = "";
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    if (!(boxSpace.getContent() == null) && (boxSpace.getContent() instanceof String)) {
                        String content = boxSpace.getContent().toString();
                        if (content.length() > 0) {
                            if (content.startsWith("Water")) {
                                color = colorBox.getColor().getColorName();
                            }
                        }
                    }
                }
            }
        }
        // TODO what happens if there are no string containing "Water" in there? by zaya 2019/10/18
        log("Color is: " + color);
    }

    /**
     * What is color in the color-box that has string ending with "front"? <br>
     * ("front" で終わる文字列をしまっているカラーボックスの色は？)
     */
    public void test_endsWith_findLastWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String color = "";
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    if (!(boxSpace.getContent() == null) && (boxSpace.getContent() instanceof String)) {
                        String content = boxSpace.getContent().toString();
                        if (content.length() > 0) {
                            if (content.endsWith("front")) {
                                color = colorBox.getColor().getColorName();
                            }
                        }
                    }
                }
            }
        }
        log("Color is: " + color);
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
        int index = 0;
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    if (!(boxSpace.getContent() == null) && (boxSpace.getContent() instanceof String)) {
                        String content = boxSpace.getContent().toString();
                        if (content.length() > 0) {
                            if (content.endsWith("front")) {
                                index = content.indexOf("front");
                            }
                        }
                    }
                }
            }
        }
        log("Index is: " + index);
    }

    /**
     * What number character is starting with the late "ど" of string containing plural "ど"s in color-boxes? (e.g. "どんどん" => 3) <br>
     * (カラーボックスに入ってる「ど」を二つ以上含む文字列で、最後の「ど」は何文字目から始まる？ (e.g. "どんどん" => 3))
     */
    public void test_lastIndexOf_findIndex() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int index = 0;
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    if (!(boxSpace.getContent() == null) && (boxSpace.getContent() instanceof String)) {
                        String content = boxSpace.getContent().toString();
                        if (content.length() > 0) {
                            if (content.contains("ど")) {
                                index = content.lastIndexOf("ど");
                            }
                        }
                    }
                }
            }
        }
        log("Last Index is: " + index);
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
        String character = "";
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    if (!(boxSpace.getContent() == null) && (boxSpace.getContent() instanceof String)) {
                        String content = boxSpace.getContent().toString();
                        if (content.length() > 0) {
                            if (content.endsWith("front")) {
                                character = content.substring(0, 1);
                            }
                        }
                    }
                }
            }
        }
        log("First character is: " + character);
    }

    /**
     * What character is last of string starting with "Water" in color-boxes? <br>
     * (カラーボックスに入ってる "Water" で始まる文字列の最後の一文字は？)
     */
    public void test_substring_findLastChar() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String character = "";
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    if (!(boxSpace.getContent() == null) && (boxSpace.getContent() instanceof String)) {
                        String content = boxSpace.getContent().toString();
                        if (content.length() > 0) {
                            if (content.startsWith("Water")) {
                                character = content.substring(content.length() - 1);
                            }
                        }
                    }
                }
            }
        }
        log("Last character is: " + character);
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
        int remainingCount = 0;
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    if (!(boxSpace.getContent() == null) && (boxSpace.getContent() instanceof String)) {
                        String content = boxSpace.getContent().toString();
                        if (content.length() > 0) {
                            if (content.contains("o")) {
                                remainingCount = content.replace("o", "").length();
                            }
                        }
                    }
                }
            }
        }
        log("Remaining characters: " + remainingCount);
    }

    /**
     * What string is path string of java.io.File in color-boxes, which is replaced with "/" to Windows file separator? <br>
     * カラーボックスに入ってる java.io.File のパス文字列のファイルセパレーターの "/" を、Windowsのファイルセパレーターに置き換えた文字列は？
     */
    public void test_replace_fileseparator() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String pathName = "";
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    if (!(boxSpace.getContent() == null) && (boxSpace.getContent() instanceof File)) {
                        File content = (File) boxSpace.getContent();
                        pathName = content.getPath();
                    }
                }
            }
        }
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
        int length = 0;
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    if (!(boxSpace.getContent() == null) && (boxSpace.getContent() instanceof YourPrivateRoom.DevilBox)) {
                        YourPrivateRoom.DevilBox devil = (YourPrivateRoom.DevilBox) boxSpace.getContent();
                        devil.wakeUp();
                        devil.allowMe();
                        devil.open();
                        String text = "";
                        try {
                            text = devil.getText();
                        } catch (YourPrivateRoom.DevilBoxTextNotFoundException e) {
                            log("Message: ", e);
                        }
                        length = length + text.length();
                    }
                }
            }
        }
        log("Total Length is: " + length);
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
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    if (!(boxSpace.getContent() == null) && (boxSpace.getContent() instanceof java.util.Map)) {
                        String result = "map:{ ";
                        // TODO how do we know the key in map is String? by zaya 2019/10/18
                        // TODO [question] why the map name is gotMap? (?o?) by zaya 2019/10/18
                        Map<String, Object> gotMap = (Map<String, Object>) boxSpace.getContent();
                        for (Map.Entry<String, Object> entry : gotMap.entrySet()) {
                            String key = entry.getKey();
                            Object value = entry.getValue();
                            result = result + key + " = " + value + " ; ";
                        }
                        result = result.substring(0, result.length() - 3) + " }";
                        log("Found map: ", result);
                    }
                }
            }
        }
    }

    /**
     * What string is converted to style "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" という形式で表示すると？)
     */
    public void test_showMap_nested() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
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
                    }
                }
            }
        }
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
    /**
     * What string of toString() is converted from text of SecretBox class in upper space on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのupperスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */

    public void test_parseMap_flat() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        ColorBox colorBox = colorBoxList.get(4); //whiteClass
        List<BoxSpace> spacelist = colorBox.getSpaceList();
        BoxSpace upperSpace = spacelist.get(0);
        YourPrivateRoom.SecretBox content = (YourPrivateRoom.SecretBox) upperSpace.getContent();
        String value = content.getText();
        value = value.substring(5, value.length() - 1);           //remove curly brackets
        String[] keyValuePairs = value.split(";");              //split the string to creat key-value pairs
        Map<String, String> map = new HashMap<>();

        for (String pair : keyValuePairs)                        //iterate over the pairs
        {
            String[] entry = pair.split("=");                   //split the pairs to get key and value
            map.put(entry[0].trim(), entry[1].trim());          //add them to the hashmap and trim whitespaces
        }
        log(map);
    }

    /**
     * What string of toString() is converted from text of SecretBox class in both middle and lower spaces on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのmiddleおよびlowerスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_nested() {

    }
}
