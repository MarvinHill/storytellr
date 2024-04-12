package de.storyteller.api.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.storyteller.api.dto.cover.CoverUriDTO;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.SneakyThrows;

@Converter(autoApply = true)
public class CoverUriConverterJson implements AttributeConverter<CoverUriDTO, String> {

  private final static ObjectMapper objectMapper = new ObjectMapper();

  @SneakyThrows
  @Override
  public String convertToDatabaseColumn(CoverUriDTO meta) {
      return objectMapper.writeValueAsString(meta);
  }

  @SneakyThrows
  @Override
  public CoverUriDTO convertToEntityAttribute(String dbData) {
      return objectMapper.readValue(dbData, CoverUriDTO.class);
  }

}