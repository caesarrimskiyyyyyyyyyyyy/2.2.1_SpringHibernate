package hiber.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @Column(name = "series")
    private long series;

    @Column(name = "model")
    private String model;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;

    public Car() {
    }

    public Car(int series, String model) {
        this.series = series;
        this.model = model;
    }

    public long getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Car{" +
                "series=" + series +
                ", model='" + model + '\'' +
                '}';
    }
}
