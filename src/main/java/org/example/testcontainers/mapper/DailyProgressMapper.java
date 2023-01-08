package org.example.testcontainers.mapper;

import org.example.testcontainers.dto.couchbase.DailyProgressDto;
import org.example.testcontainers.entity.elasticsearch.DailyProgress;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DailyProgressMapper {
  DailyProgress toEntity(DailyProgressDto dto);

  DailyProgressDto toDto(DailyProgress entity);
}
