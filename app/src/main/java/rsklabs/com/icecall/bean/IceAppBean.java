package rsklabs.com.icecall.bean;

/**
 * Created by Kumar on 2/8/2016.
 */
public class IceAppBean {

    private String ownerName;
    private String emrgncyCtPrsn;
    private String emrgncyCtNo;
    private String bloodGroup;
    private String relation;
    private int widgetColor;


    public int getWidgetColor() {
        return widgetColor;
    }

    public void setWidgetColor(int widgetColor) {
        this.widgetColor = widgetColor;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getEmrgncyCtNo() {
        return emrgncyCtNo;
    }

    public void setEmrgncyCtNo(String emrgncyCtNo) {
        this.emrgncyCtNo = emrgncyCtNo;
    }

    public String getEmrgncyCtPrsn() {
        return emrgncyCtPrsn;
    }

    public void setEmrgncyCtPrsn(String emrgncyCtPrsn) {
        this.emrgncyCtPrsn = emrgncyCtPrsn;
    }
}
