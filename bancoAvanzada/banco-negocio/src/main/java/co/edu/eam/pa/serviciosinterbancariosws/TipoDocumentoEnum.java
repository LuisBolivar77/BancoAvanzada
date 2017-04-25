
package co.edu.eam.pa.serviciosinterbancariosws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tipoDocumentoEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="tipoDocumentoEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CC"/>
 *     &lt;enumeration value="TI"/>
 *     &lt;enumeration value="PAS"/>
 *     &lt;enumeration value="CE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "tipoDocumentoEnum")
@XmlEnum
public enum TipoDocumentoEnum {

    CC,
    TI,
    PAS,
    CE;

    public String value() {
        return name();
    }

    public static TipoDocumentoEnum fromValue(String v) {
        return valueOf(v);
    }

}
