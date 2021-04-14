package com.github.bestheroz.standard.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.bestheroz.standard.common.exception.BusinessException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;

@Slf4j
@UtilityClass
public class MapperUtils {
  private final ObjectMapper OBJECT_MAPPER =
      new ObjectMapper()
          .setDateFormat(new StdDateFormat().withColonInTimeZone(true))
          .registerModule(new JavaTimeModule())
          .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

  public ObjectMapper getObjectMapper() {
    return OBJECT_MAPPER;
  }

  public String toString(final Object source) {
    try {
      return OBJECT_MAPPER.writeValueAsString(source);
    } catch (final JsonProcessingException e) {
      log.warn(ExceptionUtils.getStackTrace(e));
      throw new RuntimeException(e);
    }
  }

  public <T> T toObject(final Object source, final Class<T> targetType) {
    try {
      T t = targetType.getDeclaredConstructor().newInstance();
      BeanUtils.copyProperties(source, t);
      return t;
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      log.warn(ExceptionUtils.getStackTrace(e));
      throw new BusinessException(e);
    }
  }

  public Map<String, Object> toMap(final Object source) {
    final Map<String, Object> map = new HashMap<>();
    final Field[] fields =
        ArrayUtils.addAll(
            source.getClass().getDeclaredFields(),
            source.getClass().getSuperclass().getDeclaredFields());
    for (final Field field : fields) {
      field.setAccessible(true);
      try {
        map.put(field.getName(), field.get(source));
      } catch (final Exception e) {
        log.warn(ExceptionUtils.getStackTrace(e));
      }
    }
    return map;
  }
}
