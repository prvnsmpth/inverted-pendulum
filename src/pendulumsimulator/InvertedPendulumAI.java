/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pendulumsimulator;

import net.sourceforge.jFuzzyLogic.FIS;

/**
 *
 * @author praveen
 */
public class InvertedPendulumAI {

    
    
    private static String FCL_FILE = "pendulum.fcl";
    
    
    public InvertedPendulumAI(String profile)
    {
        FCL_FILE = "pendulum-" + profile + ".fcl";
    }
    
    
    public double getCurrent(double theta, double omega)
    {
        double current = 0.0;        
        
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
                
        current = fis.getVariable("current").getLatestDefuzzifiedValue();
        return current;
    }
    
    public void displayCharts()
    {
        FIS fis = FIS.load(FCL_FILE,true);
        
        // Error while loading
        if( fis == null ) { 
            System.err.println("Can't load file: '" 
                                   + FCL_FILE + "'");            
        }
        
        // set variables
        fis.setVariable("theta", 0);
        fis.setVariable("omega", 0);
        
        // chart the parameters
        fis.chart();
         
        // Show output variable's chart 
        fis.getVariable("current").chartDefuzzifier(true);

    }
    
}
