package org.example.notifier;

import org.example.dto.RegisterTrainingEventDto;

public interface TrainingStatsServiceNotifier {
  void registerTrainingEvent(RegisterTrainingEventDto registerTrainingEventDto);
}
