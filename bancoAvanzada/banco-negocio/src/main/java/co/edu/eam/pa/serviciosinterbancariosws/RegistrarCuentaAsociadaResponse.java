
package co.edu.eam.pa.serviciosinterbancariosws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for registrarCuentaAsociadaResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="registrarCuentaAsociadaResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuestaServicio" type="{http://www.eam.edu.co/interbancario}respuestaServicio" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registrarCuentaAsociadaResponse", propOrder = {
    "respuestaServicio"
})
public class RegistrarCuentaAsociadaResponse {

    protected RespuestaServicio respuestaServicio;

    /**
     * Gets the value of the respuestaServicio property.
     * 
     * @return
     *     possible object is
     *     {@link RespuestaServicio }
     *     
     */
    public RespuestaServicio getRespuestaServicio() {
        return respuestaServicio;
    }

    /**
     * Sets the value of the respuestaServicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link RespuestaServicio }
     *     
     */
    public void setRespuestaServicio(RespuestaServicio value) {
        this.respuestaServicio = value;
    }

}
