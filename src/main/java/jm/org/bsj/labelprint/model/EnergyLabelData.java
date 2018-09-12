/*
 * EnergyLabelData.java
 *
 * Created on July 6, 2007, 6:15 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package jm.org.bsj.labelprint.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashMap;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.persistence.Table;

/**
 * Entity class EnergyLabelData
 *
 * @author dbennett
 */
@Entity
@Table(name = "refrigerator_energy_label_data")
@NamedQueries({
    @NamedQuery(name = "EnergyLabelData.findByEnergyLabelDataId", query = "SELECT r FROM EnergyLabelData r WHERE r.refrigeratorEnergyLabelDataId = :refrigeratorEnergyLabelDataId")
    ,
        @NamedQuery(name = "EnergyLabelData.findByAnnualConsumption", query = "SELECT r FROM EnergyLabelData r WHERE r.annualConsumption = :annualConsumption")
    ,
        @NamedQuery(name = "EnergyLabelData.findByBrand", query = "SELECT r FROM EnergyLabelData r WHERE r.brand = :brand")
    ,
        @NamedQuery(name = "EnergyLabelData.findByCapacity", query = "SELECT r FROM EnergyLabelData r WHERE r.capacity = :capacity")
    ,
        @NamedQuery(name = "EnergyLabelData.findByCostPerKwh", query = "SELECT r FROM EnergyLabelData r WHERE r.costPerKwh = :costPerKwh")
    ,
        @NamedQuery(name = "EnergyLabelData.findByCountry", query = "SELECT r FROM EnergyLabelData r WHERE r.country = :country")
    ,
        @NamedQuery(name = "EnergyLabelData.findByDefrost", query = "SELECT r FROM EnergyLabelData r WHERE r.defrost = :defrost")
    ,
        @NamedQuery(name = "EnergyLabelData.findByDistributor", query = "SELECT r FROM EnergyLabelData r WHERE r.distributor = :distributor")
    ,
        @NamedQuery(name = "EnergyLabelData.findById", query = "SELECT r FROM EnergyLabelData r WHERE r.id = :id")
    ,
        @NamedQuery(name = "EnergyLabelData.findByJobNumber", query = "SELECT r FROM EnergyLabelData r WHERE r.jobNumber = :jobNumber")
    ,
        @NamedQuery(name = "EnergyLabelData.findByLabelName", query = "SELECT r FROM EnergyLabelData r WHERE r.labelName = :labelName")
    ,
        @NamedQuery(name = "EnergyLabelData.findByManufacturer", query = "SELECT r FROM EnergyLabelData r WHERE r.manufacturer = :manufacturer")
    ,
        @NamedQuery(name = "EnergyLabelData.findByModel", query = "SELECT r FROM EnergyLabelData r WHERE r.model = :model")
    ,
        @NamedQuery(name = "EnergyLabelData.findByOperatingCost", query = "SELECT r FROM EnergyLabelData r WHERE r.operatingCost = :operatingCost")
    ,
        @NamedQuery(name = "EnergyLabelData.findByStandard", query = "SELECT r FROM EnergyLabelData r WHERE r.standard = :standard")
    ,
        @NamedQuery(name = "EnergyLabelData.findByType", query = "SELECT r FROM EnergyLabelData r WHERE r.type = :type")
    ,
        @NamedQuery(name = "EnergyLabelData.findByValidity", query = "SELECT r FROM EnergyLabelData r WHERE r.validity = :validity")
})
public class EnergyLabelData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "REFRIGERATOR_ENERGY_LABEL_DATA_ID", nullable = false)
    private BigInteger refrigeratorEnergyLabelDataId;

    @Column(name = "ANNUAL_CONSUMPTION")
    private String annualConsumption = "";

    @Column(name = "BRAND")
    private String brand = "";

    @Column(name = "CAPACITY")
    private String capacity = "";

    @Column(name = "COST_PER_KWH")
    private String costPerKwh = "";

    @Column(name = "COUNTRY")
    private String country = "";

    @Column(name = "DEFROST")
    private String defrost = "";

    @Column(name = "DISTRIBUTOR")
    private String distributor = "";

    @Column(name = "ID", nullable = false)
    private long id;

    @Column(name = "JOB_NUMBER")
    private String jobNumber = "";

    @Column(name = "LABEL_NAME")
    private String labelName = "";

    @Column(name = "MANUFACTURER")
    private String manufacturer = "";

    @Column(name = "MODEL")
    private String model = "";

    @Column(name = "OPERATING_COST")
    private String operatingCost = "";

    @Column(name = "STANDARD")
    private String standard = "";

    @Column(name = "TYPE")
    private String type = "";

    @Column(name = "VALIDITY")
    private String validity = "";

    /**
     * Creates a new instance of EnergyLabelData
     */
    public EnergyLabelData() {
    }

    /**
     * Creates a new instance of EnergyLabelData with the specified values.
     *
     * @param refrigeratorEnergyLabelDataId the refrigeratorEnergyLabelDataId of
     * the EnergyLabelData
     */
    public EnergyLabelData(BigInteger refrigeratorEnergyLabelDataId) {
        this.refrigeratorEnergyLabelDataId = refrigeratorEnergyLabelDataId;
    }

    /**
     * Creates a new instance of EnergyLabelData with the specified values.
     *
     * @param refrigeratorEnergyLabelDataId the refrigeratorEnergyLabelDataId of
     * the EnergyLabelData
     * @param id the id of the EnergyLabelData
     */
    public EnergyLabelData(BigInteger refrigeratorEnergyLabelDataId, long id) {
        this.refrigeratorEnergyLabelDataId = refrigeratorEnergyLabelDataId;
        this.id = id;
    }

    /**
     * Gets the refrigeratorEnergyLabelDataId of this EnergyLabelData.
     *
     * @return the refrigeratorEnergyLabelDataId
     */
    public BigInteger getEnergyLabelDataId() {
        return this.refrigeratorEnergyLabelDataId;
    }

    /**
     * Sets the refrigeratorEnergyLabelDataId of this EnergyLabelData to the
     * specified value.
     *
     * @param refrigeratorEnergyLabelDataId the new
     * refrigeratorEnergyLabelDataId
     */
    public void setEnergyLabelDataId(BigInteger refrigeratorEnergyLabelDataId) {
        this.refrigeratorEnergyLabelDataId = refrigeratorEnergyLabelDataId;
    }

    /**
     * Gets the annualConsumption of this EnergyLabelData.
     *
     * @return the annualConsumption
     */
    public String getAnnualConsumption() {
        return this.annualConsumption;
    }

    /**
     * Sets the annualConsumption of this EnergyLabelData to the specified
     * value.
     *
     * @param annualConsumption the new annualConsumption
     */
    public void setAnnualConsumption(String annualConsumption) {
        this.annualConsumption = annualConsumption;
    }

    /**
     * Gets the brand of this EnergyLabelData.
     *
     * @return the brand
     */
    public String getBrand() {
        return this.brand;
    }

    /**
     * Sets the brand of this EnergyLabelData to the specified value.
     *
     * @param brand the new brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Gets the capacity of this EnergyLabelData.
     *
     * @return the capacity
     */
    public String getCapacity() {
        return this.capacity;
    }

    /**
     * Sets the capacity of this EnergyLabelData to the specified value.
     *
     * @param capacity the new capacity
     */
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    /**
     * Gets the costPerKwh of this EnergyLabelData.
     *
     * @return the costPerKwh
     */
    public String getCostPerKwh() {
        return this.costPerKwh;
    }

    /**
     * Sets the costPerKwh of this EnergyLabelData to the specified value.
     *
     * @param costPerKwh the new costPerKwh
     */
    public void setCostPerKwh(String costPerKwh) {
        this.costPerKwh = costPerKwh;
    }

    /**
     * Gets the country of this EnergyLabelData.
     *
     * @return the country
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * Sets the country of this EnergyLabelData to the specified value.
     *
     * @param country the new country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the defrost of this EnergyLabelData.
     *
     * @return the defrost
     */
    public String getDefrost() {
        return this.defrost;
    }

    /**
     * Sets the defrost of this EnergyLabelData to the specified value.
     *
     * @param defrost the new defrost
     */
    public void setDefrost(String defrost) {
        this.defrost = defrost;
    }

    /**
     * Gets the distributor of this EnergyLabelData.
     *
     * @return the distributor
     */
    public String getDistributor() {
        return this.distributor;
    }

    /**
     * Sets the distributor of this EnergyLabelData to the specified value.
     *
     * @param distributor the new distributor
     */
    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    /**
     * Gets the id of this EnergyLabelData.
     *
     * @return the id
     */
    public long getId() {
        return this.id;
    }

    /**
     * Sets the id of this EnergyLabelData to the specified value.
     *
     * @param id the new id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the jobNumber of this EnergyLabelData.
     *
     * @return the jobNumber
     */
    public String getJobNumber() {
        return this.jobNumber;
    }

    /**
     * Sets the jobNumber of this EnergyLabelData to the specified value.
     *
     * @param jobNumber the new jobNumber
     */
    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    /**
     * Gets the labelName of this EnergyLabelData.
     *
     * @return the labelName
     */
    public String getLabelName() {
        return this.labelName;
    }

    /**
     * Sets the labelName of this EnergyLabelData to the specified value.
     *
     * @param labelName the new labelName
     */
    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    /**
     * Gets the manufacturer of this EnergyLabelData.
     *
     * @return the manufacturer
     */
    public String getManufacturer() {
        return this.manufacturer;
    }

    /**
     * Sets the manufacturer of this EnergyLabelData to the specified value.
     *
     * @param manufacturer the new manufacturer
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Gets the model of this EnergyLabelData.
     *
     * @return the model
     */
    public String getModel() {
        return this.model;
    }

    /**
     * Sets the model of this EnergyLabelData to the specified value.
     *
     * @param model the new model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Gets the operatingCost of this EnergyLabelData.
     *
     * @return the operatingCost
     */
    public String getOperatingCost() {
        return this.operatingCost;
    }

    /**
     * Sets the operatingCost of this EnergyLabelData to the specified value.
     *
     * @param operatingCost the new operatingCost
     */
    public void setOperatingCost(String operatingCost) {
        this.operatingCost = operatingCost;
    }

    /**
     * Gets the standard of this EnergyLabelData.
     *
     * @return the standard
     */
    public String getStandard() {
        return this.standard;
    }

    /**
     * Sets the standard of this EnergyLabelData to the specified value.
     *
     * @param standard the new standard
     */
    public void setStandard(String standard) {
        this.standard = standard;
    }

    /**
     * Gets the type of this EnergyLabelData.
     *
     * @return the type
     */
    public String getType() {
        return this.type;
    }

    /**
     * Sets the type of this EnergyLabelData to the specified value.
     *
     * @param type the new type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the validity of this EnergyLabelData.
     *
     * @return the validity
     */
    public String getValidity() {
        return this.validity;
    }

    /**
     * Sets the validity of this EnergyLabelData to the specified value.
     *
     * @param validity the new validity
     */
    public void setValidity(String validity) {
        this.validity = validity;
    }

    /**
     * Returns a hash code value for the object. This implementation computes a
     * hash code value based on the id fields in this object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.refrigeratorEnergyLabelDataId != null ? this.refrigeratorEnergyLabelDataId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this EnergyLabelData. The
     * result is <code>true</code> if and only if the argument is not null and
     * is a EnergyLabelData object that has the same id field values as this
     * object.
     *
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EnergyLabelData)) {
            return false;
        }
        EnergyLabelData other = (EnergyLabelData) object;
        if (this.refrigeratorEnergyLabelDataId != other.refrigeratorEnergyLabelDataId && (this.refrigeratorEnergyLabelDataId == null || !this.refrigeratorEnergyLabelDataId.equals(other.refrigeratorEnergyLabelDataId))) {
            return false;
        }
        return true;
    }

    /**
     * Returns a string representation of the object. This implementation
     * constructs that representation based on the id fields.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "jm.org.bsj.labelprint.model.EnergyLabelData[refrigeratorEnergyLabelDataId=" + refrigeratorEnergyLabelDataId + "]";
    }

    public static void main(String[] args) {
        HashMap prop = new HashMap();
        EntityManagerFactory emf;
        EntityManager em;

        prop.put("javax.persistence.jdbc.user", "root");
        prop.put("javax.persistence.jdbc.password", "?des12300!");
        prop.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/mysql");
        prop.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");

        emf = Persistence.createEntityManagerFactory("LabelPrintPU", prop);
        em = emf.createEntityManager();
    }

}
