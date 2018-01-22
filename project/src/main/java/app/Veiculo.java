package main.java.app;
 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
 
@Entity
public class Veiculo {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
 
    private String Brand;
    private String Model;
    private String Color;
    private int Year;
    private float Price;
    private String Description;
    private boolean IsNew;
    private String Register;
    private String Update;

 
    public String getBrand() {
        return Brand;
    }
 
    public void setBrand(String Brand) {
        this.Brand = Brand;
    }
 
    public String getModel() {
        return Model;
    }
 
    public void setModel(String Model) {
        this.Model = Model;
    }

    public String getColor() {
        return Color;
    }
 
    public void setColor(String Color) {
        this.Color = Color;
    }

    public int getYear() {
        return Year;
    }
 
    public void setYear(int Year) {
        this.Year = Year;
    }

    public float getPrice() {
        return Price;
    }
 
    public void setPrice(float Price) {
        this.Price = Price;
    }

    public String getDescription() {
        return Description;
    }
 
    public void setDescription(String Description) {
        this.Description = Description;
    }

    public boolean getIsNew() {
        return IsNew;
    }
 
    public void setIsNew(boolean IsNew) {
        this.IsNew = IsNew;
    }

    public String getRegister() {
        return Register;
    }
 
    public void setRegister(String Register) {
        this.Register = Register;
    }

    public String getUpdate() {
        return Update;
    }
 
    public void setUpdate(String Update) {
        this.Update = Update;
    }
}