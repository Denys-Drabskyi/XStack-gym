package org.example.entity;

//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.JoinTable;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Trainee implements IdEntity<UUID> {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "id", columnDefinition = "char(36)")
  @Type(type = "org.hibernate.type.UUIDCharType")
  private UUID id;

  private LocalDate birthDate;
  private String address;

  @OneToOne(cascade = CascadeType.ALL)
  private User user;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name="trainer_trainee",
      joinColumns=@JoinColumn(name="trainee_id"),
      inverseJoinColumns=@JoinColumn(name="trainer_id"))
  private List<Trainer> trainers;

  public static class TraineeBuilder {
    public TraineeBuilder() {}
  }

  @Override
  public String toString() {
    return "Trainee{" +
        "id=" + id +
        ", birthDate=" + birthDate +
        ", address='" + address + '\'' +
        ", user=" + user +
        '}';
  }
}
