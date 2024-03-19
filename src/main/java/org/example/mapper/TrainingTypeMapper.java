package org.example.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.example.entity.TrainingType;
import org.springframework.stereotype.Component;

@Component
public class TrainingTypeMapper {
  public String toName(TrainingType trainingType){
    return trainingType.getName();
  }

  public TrainingType toType(String name){
    TrainingType trainingType = new TrainingType();
    trainingType.setName(name);
    return trainingType;
  }

  public List<String> toNames(List<TrainingType> trainingTypes){
    return trainingTypes.stream().map(this::toName).collect(Collectors.toList());
  }

  public List<TrainingType> toTypes(List<String> names){
    return names.stream().map(this::toType).collect(Collectors.toList());
  }
}
