package fx.test;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
public class PlayPuzzle {



    private GeneratePuzzle generatePuzzle;
    private int score;
    private List<String> words2;
    private List<String> words;
    private List<String> wordsFound;

    public PlayPuzzle(GeneratePuzzle generatePuzzle) {
        this.generatePuzzle = generatePuzzle;

        this.words2 = generatePuzzle.getWords2();
        this.wordsFound = new ArrayList<>();
    }



    //kullanicinin girdigi harfler bizim 7 harfimizle eslesiyor mu return  value su stringe donebilir.
    public boolean contains(String input){
        String letters = generatePuzzle.getHarfler();
        Pattern pattern = Pattern.compile("[^"+letters+"]");
        Matcher matcher = pattern.matcher(input);
        return !matcher.find();
    }

    //pivot kullanildi mi return valuesu stringe cevirilebilir.
    public boolean isPivotUsed(String input){
        String pivot = generatePuzzle.getHarfler().substring(0,1);
        Pattern pattern = Pattern.compile("["+pivot+"]");
        Matcher matcher = pattern.matcher(input);

        return matcher.find();
    }

    //puanlamayi olusturuyor scorela birlikte playpuzzle a al.
    public void setScore(String word){
        if(isFound(word) && !isExist(word)){
            if(word.length() == 7){
                score += 20;
            }
            else{
                score += word.length() * 2;
            }
        }
    }
    //girilen kelime listede var mi icerideki words listesi abcd() fonksiyonuyla degistir
    public boolean isExist(String word){
        if(words2.contains(word)){
            wordsFound.add(words2.get(words2.indexOf(word)));

        }
        return words2.contains(word);
    }
    //girilen kelime daha once bulundu mu
    public  boolean isFound(String word){
        return wordsFound.contains(word);
    }
}
