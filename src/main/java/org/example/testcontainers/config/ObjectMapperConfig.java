package org.example.testcontainers.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Configuration
public class ObjectMapperConfig {

  private static final String GENERAL_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
  private static final String GENERAL_DATE_PATTERN = "yyyy-MM-dd";

  /**
   * Object mapper.
   */
  @Bean("objectMapper")
  @Primary
  public ObjectMapper getObjectMapper() {
    return new ObjectMapper()
            .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
            .configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true)
            .registerModule(customDateSerializersModule(GENERAL_DATE_PATTERN, GENERAL_DATE_TIME_PATTERN))
            .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
            .enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES)
            .disable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .disable(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);
  }

  private static SimpleModule customDateSerializersModule(String datePattern, String timePattern) {
    return new SimpleModule()
            .addSerializer(LocalDate.class,
                    new LocalDateSerializer(DateTimeFormatter.ofPattern(datePattern, Locale.getDefault())))
            .addSerializer(LocalDateTime.class,
                    new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(timePattern, Locale.getDefault())))
            .addSerializer(LocalTime.class, new LocalTimeSerializer())
            .addDeserializer(LocalDate.class,
                    new LocalDateDeserializer(DateTimeFormatter.ofPattern(datePattern, Locale.getDefault())))
            .addDeserializer(LocalDateTime.class,
                    new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(timePattern, Locale.getDefault())))
            .addDeserializer(LocalTime.class, new LocalTimeDeserializer());
  }

  private static class LocalTimeSerializer extends StdSerializer<LocalTime> {
    public LocalTimeSerializer() {
      super(LocalTime.class);
    }

    @Override
    public void serialize(LocalTime localTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
      jsonGenerator.writeString(localTime.toString());
    }
  }

  public static class LocalTimeDeserializer extends StdDeserializer<LocalTime> {
    public LocalTimeDeserializer() {
      super(LocalTime.class);
    }

    @Override
    public LocalTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
      return LocalTime.parse(jsonParser.readValueAs(String.class), DateTimeFormatter.ISO_LOCAL_TIME);
    }
  }
}
