package model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class PhoneNumber {

    String areaCode;
    String localNumber;
    @ManyToOne PhoneServiceProvider provider;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getLocalNumber() {
        return localNumber;
    }

    public void setLocalNumber(String localNumber) {
        this.localNumber = localNumber;
    }

    public PhoneServiceProvider getProvider() {
        return provider;
    }

    public void setProvider(PhoneServiceProvider provider) {
        this.provider = provider;
    }
}
