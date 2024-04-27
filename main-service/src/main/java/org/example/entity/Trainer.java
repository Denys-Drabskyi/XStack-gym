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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.util.List;
import java.util.UUID;
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
public class Trainer implements IdEntity<UUID> {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private UUID id;

  @ManyToOne
  private TrainingType specialization;

  @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
  private User user;


  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name="trainer_trainee",
      joinColumns=@JoinColumn(name="trainer_id"),
      inverseJoinColumns=@JoinColumn(name="trainee_id"))
  private List<Trainee> trainees;

  public static class TrainerBuilder {
    public TrainerBuilder() {}
  }
}
