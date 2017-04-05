
package co.edu.eam.pa.clientews;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "notificacionesService", targetNamespace = "http://www.eam.edu.co/notificaciones", wsdlLocation = "http://104.197.238.134:8080/notificaciones/notificacionesService?wsdl")
public class NotificacionesService
    extends Service
{

    private final static URL NOTIFICACIONESSERVICE_WSDL_LOCATION;
    private final static WebServiceException NOTIFICACIONESSERVICE_EXCEPTION;
    private final static QName NOTIFICACIONESSERVICE_QNAME = new QName("http://www.eam.edu.co/notificaciones", "notificacionesService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://104.197.238.134:8080/notificaciones/notificacionesService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        NOTIFICACIONESSERVICE_WSDL_LOCATION = url;
        NOTIFICACIONESSERVICE_EXCEPTION = e;
    }

    public NotificacionesService() {
        super(__getWsdlLocation(), NOTIFICACIONESSERVICE_QNAME);
    }

    public NotificacionesService(WebServiceFeature... features) {
        super(__getWsdlLocation(), NOTIFICACIONESSERVICE_QNAME, features);
    }

    public NotificacionesService(URL wsdlLocation) {
        super(wsdlLocation, NOTIFICACIONESSERVICE_QNAME);
    }

    public NotificacionesService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, NOTIFICACIONESSERVICE_QNAME, features);
    }

    public NotificacionesService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public NotificacionesService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Notificaciones
     */
    @WebEndpoint(name = "notificacionesPort")
    public Notificaciones getNotificacionesPort() {
        return super.getPort(new QName("http://www.eam.edu.co/notificaciones", "notificacionesPort"), Notificaciones.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Notificaciones
     */
    @WebEndpoint(name = "notificacionesPort")
    public Notificaciones getNotificacionesPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.eam.edu.co/notificaciones", "notificacionesPort"), Notificaciones.class, features);
    }

    private static URL __getWsdlLocation() {
        if (NOTIFICACIONESSERVICE_EXCEPTION!= null) {
            throw NOTIFICACIONESSERVICE_EXCEPTION;
        }
        return NOTIFICACIONESSERVICE_WSDL_LOCATION;
    }

}
