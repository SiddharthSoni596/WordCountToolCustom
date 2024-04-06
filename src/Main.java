import java.io.*;
import java.util.Scanner;

public class Main {
    public static long getSize(File pathFile){
        return pathFile.length();
    }

    public static int getLineCount(File pathFile){
        int cnt = 0;
        try(FileReader fr = new FileReader(pathFile);
            BufferedReader br = new BufferedReader(fr)) {
            while(br.readLine() != null)
                cnt++;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cnt;
    }

    public static int getWordCount(File pathFile){
        int cnt = 0;
        String line = null;
        try(FileReader fr = new FileReader(pathFile);
            BufferedReader br = new BufferedReader(fr)) {
            line = br.readLine();
            while(line != null) {
                if(!line.isBlank())
                    cnt = cnt + line.strip().split("\\s+").length;
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cnt;
    }

    public static long getCharacterCount(File pathFile){
        int cnt = 0;
        int line = 0;
        try(FileReader fr = new FileReader(pathFile)) {
            char[] charArray = new char[8192];
            line = fr.read(charArray);
            while(line != -1) {
                cnt = cnt + line;
                line = fr.read(charArray);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cnt;
    }
    public static void logic(String option, File pathFile) throws FileNotFoundException {

        if(pathFile.exists()){
            switch (option){
                case "-c":
                    System.out.println(getSize(pathFile)+" "+pathFile.getName());
                    break;
                case "-l":
                    System.out.println(getLineCount(pathFile)+" "+pathFile.getName());
                    break;
                case "-w":
                    System.out.println(getWordCount(pathFile)+" "+pathFile.getName());
                    break;
                case "-m":
                    System.out.println(getCharacterCount(pathFile)+" "+pathFile.getName());
                    break;
                case " ":
                    System.out.println(getLineCount(pathFile)+" "+getWordCount(pathFile)+" "+getSize(pathFile)+" "+pathFile.getName());
                    break;
                default:
                    System.out.println("Invalid Flag used, hint: [-c,-w,-l,-m]");
            }
        } else {
            System.out.println("File not found at "+pathFile.getAbsolutePath());
        }
    }
    public static void logic( File pathFile) throws FileNotFoundException {
        logic(" ",pathFile);
    }

    public static void main(String[] args) throws FileNotFoundException {
        String basePath = new File("").getAbsolutePath();
        try {
            if(System.in.available() > 0){
                FileWriter fr = new FileWriter("sample.txt");
                int ch = System.in.read();
                fr.write(ch);
                while(ch != -1){
                    fr.write(ch);
                    ch = System.in.read();
                }
                fr.close();
                logic(args[0],new File(basePath,"sample.txt"));
            }
            else if(args.length > 1) {
                File path = new File(basePath,args[1]);
                logic(args[0], path);
            }
            else {
                File path = new File(basePath,args[0]);
                logic(path);
            }
        }
        catch (FileNotFoundException e){
            throw new FileNotFoundException("File not found");
        } catch (IOException e) {
            throw new RuntimeException("Error in Streaming Input");
        }

    }
}