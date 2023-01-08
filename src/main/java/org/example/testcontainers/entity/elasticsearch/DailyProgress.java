package org.example.testcontainers.entity.elasticsearch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
@Document(indexName = "daily_progress")
public class DailyProgress {

  @Id
  @Field(type = FieldType.Text)
  private String id;

  @Field(type = FieldType.Long)
  private Long carNumber;

  @Field(type = FieldType.Integer)
  private Integer miles;

  @Field(type = FieldType.Date, pattern = "yyyy-MM-dd")
  private LocalDate date;
}
