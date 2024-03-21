package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Training implements IdEntity<UUID> {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne
  private Trainee trainee;
  @ManyToOne
  private Trainer trainer;
  @Column(nullable = false)
  private String name;
  @ManyToOne
  private TrainingType type;
  private Date date;
  private long duration;

  @Override
  public String toString() {
    return "Training{" +
        "id=" + id +
        ", date=" + date +
        '}';
  }

  public static class TrainingBuilder {
    public TrainingBuilder() {}
  }
}
