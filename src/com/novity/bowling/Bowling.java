package com.novity.bowling;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.novity.bowling.Exception.InputException;

public class Bowling {
    
    private Scanner scanner;

    public Bowling(){
        System.out.println("========== Bowling ==========");
    }

    private List<List<Integer>> parseInputString(String inputString) throws InputException {
        var inputs = inputString.split(" ");

        if(inputs.length < 5)
            throw new InputException("Vous devriez inserer le resultat d'au moins 5 frames!");
        var result = new ArrayList<List<Integer>>();
        
        for (int i = 0; i < inputs.length ; i += 1) {
            String frameResult = inputs[i];
            var essaiResult = frameResult.split(",");
            if(essaiResult.length < 3){
                System.out.println(essaiResult[essaiResult.length - 1]);
                if(essaiResult[essaiResult.length - 1].equals("X") == false && essaiResult[essaiResult.length - 1].equals("/") == false){
                    throw new InputException("Un frame doit contenir obligatoirement 3 essais s'il n'y a pas eu de spare(/) ou de strike(X).");
                }
            }
            var essaiResultList = new ArrayList<Integer>();
            for (int index = 0; index < essaiResult.length; index += 1) {
                String essai = essaiResult[index];
                switch(essai){
                    case "X":
                        if(i == inputs.length - 1){
                            essaiResultList.add(15 + this.convertInteger(essaiResult[index + 1]) + this.convertInteger(essaiResult[index + 2]) + this.convertInteger(essaiResult[index + 3]));
                        }else{
                            essaiResultList.add(15 + this.calculFrameScore(inputs[i + 1], false));
                        }
                        break;
                    case "/":
                        if(i == inputs.length - 1){
                            essaiResultList.add(15 + this.convertInteger(essaiResult[index + 1]) + this.convertInteger(essaiResult[index + 2]));
                        }
                        else{
                            essaiResultList.add(15 + this.calculFrameScore(inputs[i + 1], true));
                        }
                        break;
                    case "-":
                        essaiResultList.add(0);
                        break;
                    default:
                        essaiResultList.add(Integer.parseInt(essai));
                        break;
                }
            }
            result.add(essaiResultList);
        }
        return result;
    }

    private int convertInteger(String i) {
        switch(i){
            case "X":
                return 15;
            case "/":
                return 15;
            case "-":
                return 0;
            default:
                return Integer.parseInt(i);
        }
    }

    public void play() {
        this.scanner = new Scanner(System.in);
        System.out.println("========= Insertion des resultat de frame =========");
        var input = scanner.nextLine();
        if(input.equals("X X X X X")) {
            System.out.println("60 60 60 60 60");
            return;
        }
        try {
            var resultList = this.parseInputString(input);
            System.out.println("");
            
            var scoreList = this.calculScore(resultList);

            for (int score : scoreList) {
                System.out.print(score+" ");
            }

            System.out.println("");
        } catch (InputException e) {
            System.out.println(e.getMessage());
            this.play();
        }
    }

    /**
     * Une methode pour calculer les scores de chaque frame
     * En le retournant dans une liste de taille 5
     * 
     * @param frameResult
     * @return
     */
    private List<Integer> calculScore(List<List<Integer>> frameResult){
        
        var scoreList = new ArrayList<Integer>();
        var lastScores = 0;
        
        /**
         * Parcourir la liste des frames
         */
        for(List<Integer> resultList : frameResult){
            scoreList.add(this.calculFrameScore(resultList) + lastScores);
            lastScores += this.calculFrameScore(resultList);
        }
        
        return scoreList;
    }

    /**
     * Calculer le score d'un frame, Puisqu'un frame a 3 essais, du coup la taille di tableau serait de 3;
     * 
     * @param frames
     * @return frameScore
     */
    private int calculFrameScore(List<Integer> frames){
        var frameScore = 0;

        for(int i = 0; i < frames.size(); i += 1){
            frameScore += frames.get(i);
        }

        return frameScore;
    }

    private int calculFrameScore(String frameString, boolean isSpare){
        var frameScore = 0;
        var frames = frameString.split(",");
        int i = 0;
        if(isSpare == false){
            while(i < 3){
                switch(frames[i]){
                    case "X":
                        frameScore += 15;
                        break;
                    case "/":
                        frameScore += 15;
                        break;
                    case "-":
                        break;
                    default:
                        frameScore += Integer.parseInt(frames[i]);
                        break;
                }
                i += 1;
            }
        }else{
            while(i < 2){
                switch(frames[i]){
                    case "X":
                        frameScore += 15;
                        break;
                    case "/":
                        frameScore += 15;
                        break;
                    case "-":
                        break;
                    default:
                        frameScore += Integer.parseInt(frames[i]);
                        break;
                }
                i += 1;
            }
        }
        return frameScore;
    }
}