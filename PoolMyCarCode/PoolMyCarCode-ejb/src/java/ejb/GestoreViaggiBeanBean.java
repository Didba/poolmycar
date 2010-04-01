/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import viaggi.Tappa;

/**
 *
 * @author berto
 */
@Stateless
public class GestoreViaggiBeanBean implements GestoreViaggiBeanLocal {
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")
    public void geocoding(List<String> indirizzi){
        List<Tappa> tappe=new LinkedList<Tappa>();
        for(String s: indirizzi){
            s.replace(' ', '+');
            URL url=null;
            try {
                url = new URL("http://maps.google.com/maps/geo?q=" + s + "&output=csv&sensor=false&key=ABQIAAAAuAzM4aqr6vo3bsSj_YOfIBRi_j0U6kJrkFvY4-OX2XYmEAa76BRFIJ78nqu_sSWAWUJTZFaxBpaeTA");
                
            } catch (MalformedURLException ex) {
                Logger.getLogger(GestoreViaggiBeanBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            BufferedReader in;
            try {
                in = new BufferedReader(new InputStreamReader(url.openStream()));
                
                String inputLine;

                while ((inputLine = in.readLine()) != null){
                    String[] coordinate=inputLine.split(",");
                    if(coordinate.length==4){
                        Tappa t=new Tappa();
                        t.setLatitudine(new Double(coordinate[2]));
                        t.setLongitudine(new Double(coordinate[3]));
                        tappe.add(t);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(GestoreViaggiBeanBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
 
}
