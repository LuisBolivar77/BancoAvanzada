
package co.edu.eam.pa.clientews;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for enviarSMSResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="enviarSMSResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RespuestaNotificacion" type="{http://www.eam.edu.co/notificaciones}respuestaNotificacion" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "enviarSMSResponse", propOrder = {
    "respuestaNotificacion"
})
public class EnviarSMSResponse {

    @XmlElement(name = "RespuestaNotificacion")
    protected RespuestaNotificacion respuestaNotificacion;

    /**
     * Gets the value of the respuestaNotificacion property.
     * 
     * @return
     *     possible object is
     *     {@link RespuestaNotificacion }
     *     
     */
    public RespuestaNotificacion getRespuestaNotificacion() {
        return respuestaNotificacion;
    }

    /**
     * Sets the value of the respuestaNotificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link RespuestaNotificacion }
     *     
     */
    public void setRespuestaNotificacion(RespuestaNotificacion value) {
        this.respuestaNotificacion = value;
    }

}
