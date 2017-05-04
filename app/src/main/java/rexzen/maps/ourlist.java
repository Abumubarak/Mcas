package rexzen.maps;

/**
 * Created by harishananth on 29/11/16.
 */

public class ourlist {

    private String name;
    private String subone;
    private String subtwo;
    private String subthree;

    public ourlist(String name, String subone,String subtwo,String subthree) {

        this.name = name;
        this.subone=subone;
        this.subtwo=subtwo;
        this.subthree=subthree;

    }

    public String getname() {
        return name;
    }
    public void setname(String name) {
        this.name = name;
    }
    public String getsubone() {
        return subone;
    }
    public void setsubone(String subone) {
        this.subone = subone;
    }
    public String getsubtwo() {
        return subtwo;
    }
    public void setsubtwo(String subtwo) {
        this.subtwo = subtwo;
    }
    public String getsubthree() {
        return subthree;
    }
    public void setsubthree(String subthree) {
        this.subthree = subthree;
    }

    @Override
    public String toString() {
        return subtwo + "\n";
    }


}
