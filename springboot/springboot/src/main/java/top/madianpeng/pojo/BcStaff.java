package top.madianpeng.pojo;

public class BcStaff extends Pageweb{

	private static final long serialVersionUID = 1L;

	private String id;

    private String name;

    private String telephone;

    private String haspda;

    private String deltag;

    private String station;

    private String standard;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getHaspda() {
        return haspda;
    }

    public void setHaspda(String haspda) {
        this.haspda = haspda == null ? null : haspda.trim();
    }

    public String getDeltag() {
        return deltag;
    }

    public void setDeltag(String deltag) {
        this.deltag = deltag == null ? null : deltag.trim();
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station == null ? null : station.trim();
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard == null ? null : standard.trim();
    }
}