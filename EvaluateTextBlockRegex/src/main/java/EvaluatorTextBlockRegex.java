import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

 public class EvaluatorTextBlockRegex {
    private final String[] ARRAY_OF_REGEX = {"<nome>([a-zA-Z]+\\s*)*</nome>",
                                        "<email>.*</email>",
                                        "<descricao>.*</descricao>"};    

    public static void main(String[] args) {
         EvaluatorTextBlockRegex evaluatorTextBlockRegex = new EvaluatorTextBlockRegex();
         BufferedReader bf = evaluatorTextBlockRegex.readFile();
         evaluatorTextBlockRegex.evaluateTextBlockRegex(bf);
     }

    private BufferedReader readFile(){
        try{            
            return new BufferedReader(new FileReader(new File("../resources/precisoestudarsempre.xml")));
        } catch (java.io.FileNotFoundException e){
            System.out.println("Arquivo não encontrado !");
            e.printStackTrace();
        }
        return null;
    }

    private void evaluateTextBlockRegex(BufferedReader bf){
        try{
            int regexCounter;
            int lineNumber;
            regexCounter = lineNumber = 0;
            boolean isMatchedRegex  = true;
            Pattern pattern = null;
                        
            while (bf.ready()) {
                String line = bf.readLine();
                lineNumber++;                

                if(regexCounter == ARRAY_OF_REGEX.length){
                    //só para pular linha
                    System.out.println();
                    regexCounter = 0;                    
                }                               
                while (isMatchedRegex) {
                    String regex = ARRAY_OF_REGEX[regexCounter];
                    
                    try {
                        pattern = Pattern.compile(regex);
                        Matcher matcher = pattern.matcher(line);
                        isMatchedRegex = matcher.find();                                                                                
                        if(isMatchedRegex){
                            //exibo a linha capturada
                            System.out.println("Linha " + lineNumber + " capturada: " + line);                            
                            regexCounter++;
                            break;
                        }
                    } catch (java.util.regex.PatternSyntaxException pse) {
                        //talvez que a regex não seja compilada
                        isMatchedRegex = false;
                    }
                }               
                if(!isMatchedRegex){                    
                    isMatchedRegex = true;
                    regexCounter = 0;
                }                
            }
        } catch(java.io.IOException e){
            System.out.println("An I/O error occurs!");
        }
    }
 }