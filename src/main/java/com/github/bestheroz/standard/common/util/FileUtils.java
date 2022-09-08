package com.github.bestheroz.standard.common.util;

import com.github.bestheroz.standard.common.exception.BusinessException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@UtilityClass
@Slf4j
public class FileUtils {

  public String getEncodedFileName(final HttpServletRequest request, final String fileName) {
    try {
      final String header = request.getHeader("User-Agent");

      final String encodedFilename;
      if (StringUtils.contains(header, "MSIE")) {
        encodedFilename =
            URLEncoder.encode(fileName, StandardCharsets.UTF_8.displayName())
                .replaceAll("\\+", "%20");
      } else if (StringUtils.contains(header, "Trident")) {
        encodedFilename =
            URLEncoder.encode(fileName, StandardCharsets.UTF_8.displayName())
                .replaceAll("\\+", "%20");
      } else if (StringUtils.contains(header, "Chrome")) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fileName.length(); i++) {
          final char c = fileName.charAt(i);
          if (c > '~') {
            sb.append(
                URLEncoder.encode(StringUtils.EMPTY + c, StandardCharsets.UTF_8.displayName()));
          } else {
            sb.append(c);
          }
        }
        encodedFilename = sb.toString();
      } else {
        encodedFilename =
            URLDecoder.decode(
                "\""
                    + new String(
                        fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1)
                    + "\"",
                StandardCharsets.UTF_8.displayName());
      }
      return encodedFilename;
    } catch (final UnsupportedEncodingException e) {
      log.warn(LogUtils.getStackTrace(e));
      throw new BusinessException(e);
    }
  }
}
