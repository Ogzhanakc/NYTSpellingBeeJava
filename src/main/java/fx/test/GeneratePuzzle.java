package fx.test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneratePuzzle {
    private String[] alphabet ="abcçdefgğhıijklmnoöprsştuüvyz".split("");
    private File file;
    private List<String> words;
    private List<String> words2;

    private String harfler;


    public List<String> getWords2() {
        return words2;
    }

    public List<String> getWords() {
        return words;
    }




    public GeneratePuzzle(File file) throws FileNotFoundException {
        this.file=file;
        this.words = this.readWords();
        this.harfler = getLetters();
        this.words2 = abcd();


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

    public String getLetters(){
        int a = 0;
        String letters = new String();
        while(words.get(a).length() != 7){
            a = (int) (Math.random() * words.size());
            if(words.get(a).length() == 7){
                String word = words.get(a);
                 letters = words.get(a);
            }

        }
        String[] word = letters.split("");
        List<String> word1 = Arrays.stream(word).toList();
        for(int i = 0; i < word.length - 1; i++){

           for(int k = i+1; k < word.length; k++){
               if(word[i].equalsIgnoreCase(word[k])){
                   int x = (int) (Math.random() * 29);
                   while(word1.contains(alphabet[x])){
                       x =(int)(Math.random() * 29);
                   }
                   word[k] = alphabet[x];
               }
           }

        }
        word1 = Arrays.stream(word).toList();
        String result = String.join("", word1);


        return result;
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
        if(wfd.size() < 20)
            abcd();
        return wfd;
    }






}
