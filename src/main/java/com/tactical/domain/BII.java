package com.tactical.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * A BII.
 */
@Document(collection = "bii")
public class BII implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("type")
    private String type;

    @Field("bii_id")
    private String biiId;

    @Field("detection_timestamp")
    private Long detectionTimestamp;

    @Field("source_id")
    private String sourceId;

    @Field("detection_system_name")
    private String detectionSystemName;

    @Field("detected_value")
    private String detectedValue;

    @Field("detection_context")
    private String detectionContext;

    @Field("etc")
    private String etc;

    @Field("etcetc")
    private String etcetc;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public BII name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public BII type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBiiId() {
        return biiId;
    }

    public BII biiId(String biiId) {
        this.biiId = biiId;
        return this;
    }

    public void setBiiId(String biiId) {
        this.biiId = biiId;
    }

    public Long getDetectionTimestamp() {
        return detectionTimestamp;
    }

    public BII detectionTimestamp(Long detectionTimestamp) {
        this.detectionTimestamp = detectionTimestamp;
        return this;
    }

    public void setDetectionTimestamp(Long detectionTimestamp) {
        this.detectionTimestamp = detectionTimestamp;
    }

    public String getSourceId() {
        return sourceId;
    }

    public BII sourceId(String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getDetectionSystemName() {
        return detectionSystemName;
    }

    public BII detectionSystemName(String detectionSystemName) {
        this.detectionSystemName = detectionSystemName;
        return this;
    }

    public void setDetectionSystemName(String detectionSystemName) {
        this.detectionSystemName = detectionSystemName;
    }

    public String getDetectedValue() {
        return detectedValue;
    }

    public BII detectedValue(String detectedValue) {
        this.detectedValue = detectedValue;
        return this;
    }

    public void setDetectedValue(String detectedValue) {
        this.detectedValue = detectedValue;
    }

    public String getDetectionContext() {
        return detectionContext;
    }

    public BII detectionContext(String detectionContext) {
        this.detectionContext = detectionContext;
        return this;
    }

    public void setDetectionContext(String detectionContext) {
        this.detectionContext = detectionContext;
    }

    public String getEtc() {
        return etc;
    }

    public BII etc(String etc) {
        this.etc = etc;
        return this;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }

    public String getEtcetc() {
        return etcetc;
    }

    public BII etcetc(String etcetc) {
        this.etcetc = etcetc;
        return this;
    }

    public void setEtcetc(String etcetc) {
        this.etcetc = etcetc;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BII)) {
            return false;
        }
        return id != null && id.equals(((BII) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "BII{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", biiId='" + getBiiId() + "'" +
            ", detectionTimestamp=" + getDetectionTimestamp() +
            ", sourceId='" + getSourceId() + "'" +
            ", detectionSystemName='" + getDetectionSystemName() + "'" +
            ", detectedValue='" + getDetectedValue() + "'" +
            ", detectionContext='" + getDetectionContext() + "'" +
            ", etc='" + getEtc() + "'" +
            ", etcetc='" + getEtcetc() + "'" +
            "}";
    }
}
