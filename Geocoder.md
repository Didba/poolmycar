Questo bellissimo programma java vi restituisce le coordinate della nostra amata università

import java.net.**;
import java.io.**;

public class URLReader {
> public static void main(String[.md](.md) args) throws Exception {
> URL yahoo = new URL("http://maps.google.com/maps/geo?q=Torino+corso+svizzera+185&output=csv&sensor=false&key=ABQIAAAAuAzM4aqr6vo3bsSj_YOfIBRi_j0U6kJrkFvY4-OX2XYmEAa76BRFIJ78nqu_sSWAWUJTZFaxBpaeTA");
> BufferedReader in = new BufferedReader(new InputStreamReader(yahoo.openStream()));

> String inputLine;

> while ((inputLine = in.readLine()) != null)
> > System.out.println(inputLine);


> in.close();
> > }
}


Ciao!