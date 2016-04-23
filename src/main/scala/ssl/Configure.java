package ssl;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by rahul on 13/4/16.
 */
public class Configure {

    public void enable_TLS(String args1, String args2) {

        SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket sslsocket =
                null;
        try {
            sslsocket = (SSLSocket) sslsocketfactory.createSocket(args1, Integer.parseInt(args2));
            Set<String> enabledTLSSet = new HashSet(Arrays.asList(sslsocket.getEnabledProtocols()));
            enabledTLSSet.add("TLSv1.2");
            sslsocket.setEnabledProtocols(enabledTLSSet.toArray(new String[enabledTLSSet.size()]));

            Set<String> enabledCipherSuitesSet = new HashSet<String>(Arrays.asList(sslsocket.getEnabledCipherSuites()));
            enabledCipherSuitesSet.add("TLS_RSA_WITH_AES_256_CBC_SHA256");
            sslsocket.setEnabledCipherSuites(enabledCipherSuitesSet.toArray(new String[enabledCipherSuitesSet.size()]));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(sslsocket != null)
                try {
                    sslsocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }


    }
}
