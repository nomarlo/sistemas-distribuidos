
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
 *         &lt;element name="Temperature" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="FromUnit" type="{http://www.webserviceX.NET/}TemperatureUnit"/>
 *         &lt;element name="ToUnit" type="{http://www.webserviceX.NET/}TemperatureUnit"/>
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
    "temperature",
    "fromUnit",
    "toUnit"
})
@XmlRootElement(name = "ConvertTemp")
public class ConvertTemp {

    @XmlElement(name = "Temperature")
    protected double temperature;
    @XmlElement(name = "FromUnit", required = true)
    protected TemperatureUnit fromUnit;
    @XmlElement(name = "ToUnit", required = true)
    protected TemperatureUnit toUnit;

    /**
     * Obtiene el valor de la propiedad temperature.
     * 
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * Define el valor de la propiedad temperature.
     * 
     */
    public void setTemperature(double value) {
        this.temperature = value;
    }

    /**
     * Obtiene el valor de la propiedad fromUnit.
     * 
     * @return
     *     possible object is
     *     {@link TemperatureUnit }
     *     
     */
    public TemperatureUnit getFromUnit() {
        return fromUnit;
    }

    /**
     * Define el valor de la propiedad fromUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link TemperatureUnit }
     *     
     */
    public void setFromUnit(TemperatureUnit value) {
        this.fromUnit = value;
    }

    /**
     * Obtiene el valor de la propiedad toUnit.
     * 
     * @return
     *     possible object is
     *     {@link TemperatureUnit }
     *     
     */
    public TemperatureUnit getToUnit() {
        return toUnit;
    }

    /**
     * Define el valor de la propiedad toUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link TemperatureUnit }
     *     
     */
    public void setToUnit(TemperatureUnit value) {
        this.toUnit = value;
    }

}
