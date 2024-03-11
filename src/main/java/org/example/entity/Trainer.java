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
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

  @OneToMany(fetch = FetchType.EAGER)
  private List<TrainingType> specializations;

  @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
  private User user;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name="trainer_trainee",
      joinColumns=@JoinColumn(name="trainer_id"),
      inverseJoinColumns=@JoinColumn(name="trainee_id"))
  private List<Trainee> trainees;

  public static class TrainerBuilder {
    public TrainerBuilder() {}
  }
}
