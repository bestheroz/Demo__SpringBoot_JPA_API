package com.github.bestheroz.standard.common.util;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class NullUtils {

  // int 도 함께 커버됨
  public boolean equals(final Integer n1, final Integer n2) {
    try {
      return n1 != null && n2 != null && n1.intValue() == n2.intValue();
    } catch (final Throwable e) {
      log.warn(LogUtils.getStackTrace(e));
      return false;
    }
  }

  public boolean equals(final Long n1, final Long n2) {
    try {
      return n1 != null && n2 != null && n1.longValue() == n2.longValue();
    } catch (final Throwable e) {
      log.warn(LogUtils.getStackTrace(e));
      return false;
    }
  }

  public boolean exists(final File file) {
    try {
      return file != null && file.exists();
    } catch (final Throwable e) {
      log.warn(LogUtils.getStackTrace(e));
      return false;
    }
  }

  public boolean hasNext(final Iterator<?> iterator) {
    try {
      return iterator != null && iterator.hasNext();
    } catch (final Throwable e) {
      log.warn(LogUtils.getStackTrace(e));
      return false;
    }
  }

  public boolean isEmpty(final List<?> list) {
    try {
      return size(list) == 0;
    } catch (final Throwable e) {
      log.warn(LogUtils.getStackTrace(e));
      return true;
    }
  }

  public boolean isEmpty(final Set<?> list) {
    try {
      return size(list) == 0;
    } catch (final Throwable e) {
      log.warn(LogUtils.getStackTrace(e));
      return true;
    }
  }

  public boolean isNotEmpty(final List<?> list) {
    return !isEmpty(list);
  }

  public boolean isNotEmpty(final Set<?> list) {
    return !isEmpty(list);
  }

  public int size(final List<?> list) {
    try {
      return list == null ? 0 : list.size();
    } catch (final Throwable e) {
      log.warn(LogUtils.getStackTrace(e));
      return 0;
    }
  }

  public int size(final Set<?> list) {
    try {
      return list == null ? 0 : list.size();
    } catch (final Throwable e) {
      log.warn(LogUtils.getStackTrace(e));
      return 0;
    }
  }

  public int size(final Map<String, ?> map) {
    try {
      return map == null ? 0 : map.size();
    } catch (final Throwable e) {
      log.warn(LogUtils.getStackTrace(e));
      return 0;
    }
  }
}
