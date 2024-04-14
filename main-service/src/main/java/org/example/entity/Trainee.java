package org.example.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Trainee implements IdEntity<UUID> {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private UUID id;

  private LocalDate birthDate;
  private String address;

  @OneToOne(cascade = CascadeType.ALL)
  private User user;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name="trainer_trainee",
      joinColumns=@JoinColumn(name="trainee_id"),
      inverseJoinColumns=@JoinColumn(name="trainer_id"))
  private List<Trainer> trainers;

  @OneToMany(mappedBy = "trainee", orphanRemoval = true, cascade = CascadeType.ALL)
  private List<Training> trainings;

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