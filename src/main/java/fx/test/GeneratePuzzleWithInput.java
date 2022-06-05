package fx.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneratePuzzleWithInput {

    private final String[] alphabet ="abcçdefgğhıijklmnoöprsştuüvyz".split("");
    private final File file;
    private List<String> words;
    private List<String> words2;

    private String harfler;


    public List<String> getWords2() {
        return words2;
    }

    public List<String> getWords() {
        return words;
    }




    public GeneratePuzzleWithInput(File file,String harfler) throws FileNotFoundException {
        this.file=file;
        this.words = this.readWords();
        setHarfler(harfler);
        this.words2 = abcd();

    }

    public void setHarfler(String harfler) {
        if(!checkText(harfler)){
            this.harfler = harfler;
        }
        else{
            throw new RuntimeException("something went wrong");
        }
    }

    public String getHarfler() {
        return harfler;
    }

    private List<String> readWords() throws FileNotFoundException {

        List<String> words = new ArrayList<>();
        Scanner scan = new Scanner(file);
        while(scan.hasNextLine()){
            words.add(scan.nextLine());
        }
        return words;
    }





    //elimizdeki harflerle bulunabilen kelimeler.
    public List<String> abcd(){
        String letters = harfler;
        Pattern pivot = Pattern.compile("["+letters.substring(0,1) +"]" );
        Pattern pattern = Pattern.compile("[^"+letters+"]");
        List<String> words = getWords();
        List <String> wfd = new ArrayList<>();
        for (int i = 0; i < words.size();i++){
            Matcher matcher = pattern.matcher(words.get(i));
            Matcher pivotMatcher = pivot.matcher(words.get(i));
            if(!matcher.find() && words.get(i).length() >= 4 && pivotMatcher.find()){
                if(wfd.size() <= 80){
                    wfd.add(words.get(i));
                }
            }
        }

        return wfd;
    }


  /*  public boolean checkList(List<String> list){
        boolean somebool = false;
        for(int i = 0; i < list.size(); i++){
            boolean isPangramexist = true;

            for(int k = 0; k < harfler.length(); k++){
                List<String> x  = Arrays.stream(list.get(i).split("")).toList();
                if(list.get(i).length() < harfler.length()){
                    isPangramexist = false;
                    break;}
                isPangramexist = isPangramexist && x.contains(harfler.substring(k,k+1));
            }
            if(isPangramexist){
                System.out.println(list.get(i));
                somebool = true;
                break;
            }

        }
        if(list.size() < 20 | somebool == false){
            return false;
        }
        return true;
    }*/


    public boolean checkText(String text) {
        int inputLength = text.length();
        Pattern pattern = Pattern.compile("[^a-z]");
        Matcher matcher = pattern.matcher(text);
        if(inputLength != 7)
            throw new RuntimeException("7 harf giriniz");
        if(matcher.find())
            throw new RuntimeException("ozel karakter kullanmayin");
        if(appears(text))
            throw new RuntimeException("ayni harfi iki kere kullanmayiniz");
        return inputLength != 7 || matcher.find() || appears(text);
    }


    private boolean appears(String text){
        char[] chars = text.toCharArray();


        for(int i = 0; i < text.length();i++){

            for( int k =i+1 ; k < text.length();k++){
                if(chars[i] == chars[k]){
                    return true;
                }

            }
        }
        return false;
    }

}
