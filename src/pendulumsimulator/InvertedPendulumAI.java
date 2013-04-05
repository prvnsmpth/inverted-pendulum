/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pendulumsimulator;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.jFuzzyLogic.FIS;
import org.apache.commons.exec.*;

/**
 *
 * @author praveen
 */
public class InvertedPendulumAI {

    private static final String AI_PATH = "/home/praveen/Desktop/sem6/cs386/assgn1/";
    
    
    private static String FCL_FILE = "pendulum.fcl";
    
    
    public InvertedPendulumAI(String profile)
    {
        FCL_FILE = "pendulum-" + profile + ".fcl";
    }
    
    
    public double getCurrent(double theta, double omega)
    {
        double current = 0.0;
        
        
        /*try {           
            Executor exec = new DefaultExecutor();
            exec.setWorkingDirectory(new File(AI_PATH));
            CommandLine cl = new CommandLine("./inv_pendulum");
            cl.addArgument(String.valueOf(theta));
            cl.addArgument(String.valueOf(omega));
            PipedOutputStream pout = new PipedOutputStream();
            PipedInputStream in = new PipedInputStream(pout); 
            PumpStreamHandler ph = new PumpStreamHandler(pout);
            exec.setStreamHandler(ph);
            int exitvalue = exec.execute(cl);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line = br.readLine();
            System.out.println("Script says: " + line);
            current = Double.valueOf(line.split(" = ")[1]);            
        } catch (ExecuteException ex) {
            Logger.getLogger(InvertedPendulumAI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InvertedPendulumAI.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        
        // Load from 'FCL' file
        FIS fis = FIS.load(FCL_FILE,true);
        // Error while loading?
        if( fis == null ) { 
            System.err.println("Can't load file: '" 
                                   + FCL_FILE + "'");
            return current;
        }

        // Set inputs
        fis.setVariable("theta", theta);
        fis.setVariable("omega", omega);

        // Evaluate
        fis.evaluate();
        
        //fis.chart();

        // Show output variable's chart 
        //fis.getVariable("current").chartDefuzzifier(true);                
                
        current = fis.getVariable("current").getLatestDefuzzifiedValue();
        return current;
    }
    
}
