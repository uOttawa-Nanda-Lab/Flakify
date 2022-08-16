/**
 * Logback: the reliable, generic, fast and flexible logging framework.
 * Copyright (C) 1999-2013, QOS.ch. All rights reserved.
 *
 * This program and the accompanying materials are dual-licensed under
 * either the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation
 *
 * or (per the licensee's choosing)
 *
 * under the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation.
 */
package ch.qos.logback.core.rolling;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.ContextBase;
import ch.qos.logback.core.encoder.EchoEncoder;
import ch.qos.logback.core.rolling.helper.RollingCalendar;
import ch.qos.logback.core.testUtil.RandomUtil;
import ch.qos.logback.core.util.CoreTestConstants;
import ch.qos.logback.core.util.StatusPrinter;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ch.qos.logback.core.CoreConstants.DAILY_DATE_PATTERN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TimeBasedRollingWithArchiveRemoval_Test extends ScaffoldingForRollingTests {
  String MONTHLY_DATE_PATTERN = "yyyy-MM";
  String MONTHLY_CRONOLOG_DATE_PATTERN = "yyyy/MM";
  final String DAILY_CRONOLOG_DATE_PATTERN = "yyyy/MM/dd";


  RollingFileAppender<Object> rfa = new RollingFileAppender<Object>();
  TimeBasedRollingPolicy<Object> tbrp = new TimeBasedRollingPolicy<Object>();

  // by default tbfnatp is an instance of DefaultTimeBasedFileNamingAndTriggeringPolicy
  TimeBasedFileNamingAndTriggeringPolicy<Object> tbfnatp = new DefaultTimeBasedFileNamingAndTriggeringPolicy<Object>();

  long MILLIS_IN_MINUTE = 60 * 1000;
  long MILLIS_IN_HOUR = 60 * MILLIS_IN_MINUTE;
  long MILLIS_IN_DAY = 24 * MILLIS_IN_HOUR;
  long MILLIS_IN_MONTH = (long) ((365.242199 / 12) * MILLIS_IN_DAY);
  int MONTHS_IN_YEAR = 12;

  int slashCount = 0;

  @Before
  public void setUp() {
    super.setUp();
  }


  private int computeSlashCount(String datePattern) {
    if (datePattern == null)
      return 0;
    else {
      int count = 0;
      for (int i = 0; i < datePattern.length(); i++) {
        char c = datePattern.charAt(i);
        if (c == '/')
          count++;
      }
      return count;
    }
  }


  void generateDailyRollover(long now, int maxHistory, int simulatedNumberOfPeriods, int startInactivity,
                             int numInactivityPeriods) {
    slashCount = computeSlashCount(DAILY_DATE_PATTERN);
    logOverMultiplePeriods(now, randomOutputDir + "clean-%d{" + DAILY_DATE_PATTERN + "}.txt", MILLIS_IN_DAY, maxHistory, simulatedNumberOfPeriods, startInactivity, numInactivityPeriods);
    check(expectedCountWithoutFoldersWithInactivity(maxHistory, simulatedNumberOfPeriods, startInactivity + numInactivityPeriods));
  }

  void logOncePeriod(long currentTime, String fileNamePattern, int maxHistory) {
    buildRollingFileAppender(currentTime, fileNamePattern, maxHistory, DO_CLEAN_HISTORY_ON_START);
    rfa.doAppend("Hello ----------------------------------------------------------" + new Date(currentTime));
    rfa.stop();
  }

  @Test public void cleanHistoryOnStart(){long now=this.currentTime;String fileNamePattern=randomOutputDir + "clean-%d{" + DAILY_DATE_PATTERN + "}.txt";int maxHistory=3;for (int i=0;i <= 5;i++){logOncePeriod(now,fileNamePattern,maxHistory);now=now + MILLIS_IN_DAY;}StatusPrinter.print(context);check(expectedCountWithoutFolders(maxHistory));}


  int expectedCountWithoutFolders(int maxHistory) {
    return maxHistory + 1;
  }


  int expectedCountWithFolders(int maxHistory, boolean withExtraFolder) {
    int numLogFiles = (maxHistory + 1);
    int numLogFilesAndFolders = numLogFiles * 2;
    int result = numLogFilesAndFolders + slashCount;
    if (withExtraFolder) result += 1;
    return result;
  }


  void buildRollingFileAppender(long currentTime, String fileNamePattern, int maxHistory,
                                boolean cleanHistoryOnStart) {
    rfa.setContext(context);
    rfa.setEncoder(encoder);
    tbrp.setContext(context);
    tbrp.setFileNamePattern(fileNamePattern);
    tbrp.setMaxHistory(maxHistory);
    tbrp.setParent(rfa);
    tbrp.setCleanHistoryOnStart(cleanHistoryOnStart);
    tbrp.timeBasedFileNamingAndTriggeringPolicy = tbfnatp;
    tbrp.timeBasedFileNamingAndTriggeringPolicy.setCurrentTime(currentTime);
    tbrp.start();
    rfa.setRollingPolicy(tbrp);
    rfa.start();
  }

  boolean DO_CLEAN_HISTORY_ON_START = true;
  boolean DO_NOT_CLEAN_HISTORY_ON_START = false;


  long logOverMultiplePeriodsContinuously(long simulatedTime, String fileNamePattern, long periodDurationInMillis, int maxHistory,
                                          int simulatedNumberOfPeriods) {
    return logOverMultiplePeriods(simulatedTime, fileNamePattern, periodDurationInMillis, maxHistory,
            simulatedNumberOfPeriods, 0, 0);
  }

  long logOverMultiplePeriods(long simulatedTime, String fileNamePattern, long periodDurationInMillis, int maxHistory,
                              int simulatedNumberOfPeriods, int startInactivity,
                              int numInactivityPeriods) {
    buildRollingFileAppender(simulatedTime, fileNamePattern, maxHistory, DO_NOT_CLEAN_HISTORY_ON_START);
    int ticksPerPeriod = 512;
    int runLength = simulatedNumberOfPeriods * ticksPerPeriod;
    int startInactivityIndex = 1 + startInactivity * ticksPerPeriod;
    int endInactivityIndex = startInactivityIndex + numInactivityPeriods * ticksPerPeriod;
    long tickDuration = periodDurationInMillis / ticksPerPeriod;

    for (int i = 0; i <= runLength; i++) {
      if (i < startInactivityIndex || i > endInactivityIndex) {
        rfa.doAppend("Hello ----------------------------------------------------------" + i);
      }
      tbrp.timeBasedFileNamingAndTriggeringPolicy.setCurrentTime(addTime(tbrp.timeBasedFileNamingAndTriggeringPolicy.getCurrentTime(),
              tickDuration));
      add(tbrp.future);
      waitForJobsToComplete();
    }
    rfa.stop();
    return tbrp.timeBasedFileNamingAndTriggeringPolicy.getCurrentTime();
  }

  boolean extraFolder(int numPeriods, int periodsPerEra, int beginPeriod, int maxHistory) {
    int valueOfLastMonth = ((beginPeriod) + numPeriods) % periodsPerEra;
    return (valueOfLastMonth < maxHistory);
  }

  long addTime(long time, long timeToWait) {
    return time + timeToWait;
  }


  void expectedFileAndDirCount(int expectedFileAndDirCount, int expectedDirCountMin, int expectedDirCountMax) {
    File dir = new File(randomOutputDir);
    List<File> fileList = new ArrayList<File>();
    findFilesInFolderRecursivelyByPatterMatch(dir, fileList, "clean");
    List<File> dirList = new ArrayList<File>();
    findAllFoldersInFolderRecursively(dir, dirList);
    String msg = "expectedDirCountMin=" + expectedDirCountMin + ", expectedDirCountMax=" + expectedDirCountMax + " actual value=" + dirList.size();
    assertTrue(msg, expectedDirCountMin <= dirList.size() && dirList.size() <= expectedDirCountMax);
  }


  void check(int expectedCount) {
    File dir = new File(randomOutputDir);
    List<File> fileList = new ArrayList<File>();
    findAllDirsOrStringContainsFilesRecursively(dir, fileList, "clean");
    assertEquals(expectedCount, fileList.size());
  }

  int expectedCountWithoutFoldersWithInactivity(int maxHistory, int totalPeriods, int endOfInactivity) {
    int availableHistory = (totalPeriods + 1) - endOfInactivity;
    int actualHistory = Math.min(availableHistory, maxHistory + 1);
    return actualHistory;
  }

  void genericFindMatching(final FileMatchFunction matchFunc, File dir, List<File> fileList, final String pattern, boolean includeDirs) {
    if (dir.isDirectory()) {
      File[] matchArray = dir.listFiles(new FileFilter() {
        public boolean accept(File f) {
          return f.isDirectory() || matchFunc.match(f, pattern);
        }
      });
      for (File f : matchArray) {
        if (f.isDirectory()) {
          if (includeDirs) fileList.add(f);
          genericFindMatching(matchFunc, f, fileList, pattern, includeDirs);
        } else
          fileList.add(f);
      }
    }
  }

  private void findAllFoldersInFolderRecursively(File dir, List<File> fileList) {
    FileMatchFunction alwaysFalse = new FileMatchFunction() {
      public boolean match(File f, String pattern) {
        return false;
      }
    };
    genericFindMatching(alwaysFalse, dir, fileList, null, true);
  }

  private void findAllDirsOrStringContainsFilesRecursively(File dir, List<File> fileList, String pattern) {
    FileMatchFunction matchFunction = new FileMatchFunction() {
      public boolean match(File f, String pattern) {
        return f.getName().contains(pattern);
      }
    };
    genericFindMatching(matchFunction, dir, fileList, pattern, true);
  }

  void findFilesInFolderRecursivelyByPatterMatch(File dir, List<File> fileList, String pattern) {
    FileMatchFunction matchByPattern = new FileMatchFunction() {
      public boolean match(File f, String pattern) {
        return f.getName().matches(pattern);
      }
    };
    genericFindMatching(matchByPattern, dir, fileList, pattern, false);
  }

  Set<String> groupByClass(List<File> fileList, String regex) {
    Pattern p = Pattern.compile(regex);
    Set<String> set = new HashSet<String>();
    for (File f : fileList) {
      String n = f.getName();
      Matcher m = p.matcher(n);
      m.matches();
      int begin = m.start(1);
      String reduced = n.substring(0, begin);
      set.add(reduced);
    }
    return set;
  }


  void checkPatternCompliance(int expectedClassCount, String regex) {
    File dir = new File(randomOutputDir);
    List<File> fileList = new ArrayList<File>();
    findFilesInFolderRecursivelyByPatterMatch(dir, fileList, regex);
    Set<String> set = groupByClass(fileList, regex);
    assertEquals(expectedClassCount, set.size());
  }

  void checkDirPatternCompliance(int expectedClassCount) {
    File dir = new File(randomOutputDir);
    List<File> fileList = new ArrayList<File>();
    findAllFoldersInFolderRecursively(dir, fileList);
    for (File f : fileList) {
      assertTrue(f.list().length >= 1);
    }
    assertEquals(expectedClassCount, fileList.size());
  }
}
