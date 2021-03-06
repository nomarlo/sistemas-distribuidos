
package net.webservicex;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConvertTempResult" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "convertTempResult"
})
@XmlRootElement(name = "ConvertTempResponse")
public class ConvertTempResponse {

    @XmlElement(name = "ConvertTempResult")
    protected double convertTempResult;

    /**
     * Obtiene el valor de la propiedad convertTempResult.
     * 
     */
    public double getConvertTempResult() {
        return convertTempResult;
    }

    /**
     * Define el valor de la propiedad convertTempResult.
     * 
     */
    public void setConvertTempResult(double value) {
        this.convertTempResult = value;
    }

}
