/**
 * Created by dingzhang on 9/10/16.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;

public class getTop {

    public static void main(String[] args) {

        BufferedReader br = null;
        File file = new File("3000.xml");


        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader("dblp.xml"));
            int countline =0;
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            while ((sCurrentLine = br.readLine()) != null && countline<3001) {
                if(sCurrentLine.indexOf("<article")>=0) {
                    countline++;
                    //System.out.println(sCurrentLine);
                }
                if(countline !=3001)
                    bw.write(sCurrentLine + "\n");
            }
            bw.write("</dblp>");
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}
