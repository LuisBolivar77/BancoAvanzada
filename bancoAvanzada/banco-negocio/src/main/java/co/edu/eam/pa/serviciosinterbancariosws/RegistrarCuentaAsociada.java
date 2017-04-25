
package co.edu.eam.pa.serviciosinterbancariosws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for registrarCuentaAsociada complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="registrarCuentaAsociada">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idbanco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipodoc" type="{http://www.eam.edu.co/interbancario}tipoDocumentoEnum" minOccurs="0"/>
 *         &lt;element name="numerodoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numerocuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registrarCuentaAsociada", propOrder = {
    "idbanco",
    "tipodoc",
    "numerodoc",
    "nombre",
    "numerocuenta"
})
public class RegistrarCuentaAsociada {

    protected String idbanco;
    @XmlSchemaType(name = "string")
    protected TipoDocumentoEnum tipodoc;
    protected String numerodoc;
    protected String nombre;
    protected String numerocuenta;

    /**
     * Gets the value of the idbanco property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdbanco() {
        return idbanco;
    }

    /**
     * Sets the value of the idbanco property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdbanco(String value) {
        this.idbanco = value;
    }

    /**
     * Gets the value of the tipodoc property.
     * 
     * @return
     *     possible object is
     *     {@link TipoDocumentoEnum }
     *     
     */
    public TipoDocumentoEnum getTipodoc() {
        return tipodoc;
    }

    /**
     * Sets the value of the tipodoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoDocumentoEnum }
     *     
     */
    public void setTipodoc(TipoDocumentoEnum value) {
        this.tipodoc = value;
    }

    /**
     * Gets the value of the numerodoc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumerodoc() {
        return numerodoc;
    }

    /**
     * Sets the value of the numerodoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumerodoc(String value) {
        this.numerodoc = value;
    }

    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the numerocuenta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumerocuenta() {
        return numerocuenta;
    }

    /**
     * Sets the value of the numerocuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumerocuenta(String value) {
        this.numerocuenta = value;
    }

}
