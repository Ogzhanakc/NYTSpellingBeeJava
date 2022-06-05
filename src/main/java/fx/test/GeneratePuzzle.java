package fx.test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneratePuzzle implements PuzzleInterface{
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
        this.words2 = abcd();
        while(checkList(words2) == false){
            words2.clear();
            this.words2 = abcd();
        }
    }

    public void setHarfler(String harfler) {
        this.harfler = harfler;
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
        this.harfler = getLetters();
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


    public boolean checkList(List<String> list){
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
    }





}
