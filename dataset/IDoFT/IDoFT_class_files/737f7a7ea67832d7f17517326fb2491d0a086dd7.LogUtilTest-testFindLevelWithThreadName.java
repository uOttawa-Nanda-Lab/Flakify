/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.dubbo.common.utils;

import org.apache.log4j.Level;
import org.junit.After;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LogUtilTest {
    @After
    public void tearDown() throws Exception {
        DubboAppender.logList.clear();
    }

    @Test
    public void testStartStop() throws Exception {
        LogUtil.start();
        assertThat(DubboAppender.available, is(true));
        LogUtil.stop();
        assertThat(DubboAppender.available, is(false));
    }

    @Test
    public void testCheckNoError() throws Exception {
        Log log = mock(Log.class);
        DubboAppender.logList.add(log);
        when(log.getLogLevel()).thenReturn(Level.ERROR);
        assertThat(LogUtil.checkNoError(), is(false));
        when(log.getLogLevel()).thenReturn(Level.INFO);
        assertThat(LogUtil.checkNoError(), is(true));
    }

    @Test
    public void testFindName() throws Exception {
        Log log = mock(Log.class);
        DubboAppender.logList.add(log);
        when(log.getLogName()).thenReturn("a");
        assertThat(LogUtil.findName("a"), equalTo(1));
    }

    @Test
    public void testFindLevel() throws Exception {
        Log log = mock(Log.class);
        DubboAppender.logList.add(log);
        when(log.getLogLevel()).thenReturn(Level.ERROR);
        assertThat(LogUtil.findLevel(Level.ERROR), equalTo(1));
        assertThat(LogUtil.findLevel(Level.INFO), equalTo(0));
    }

    @Test
    public void testFindLevelWithThreadName() throws Exception {
        Log log = mock(Log.class);
        DubboAppender.logList.add(log);
        when(log.getLogLevel()).thenReturn(Level.ERROR);
        when(log.getLogThread()).thenReturn("thread-1");
        log = mock(Log.class);
        DubboAppender.logList.add(log);
        when(log.getLogLevel()).thenReturn(Level.ERROR);
        when(log.getLogThread()).thenReturn("thread-2");
        assertThat(LogUtil.findLevelWithThreadName(Level.ERROR, "thread-2"), equalTo(1));
    }

    @Test
    public void testFindThread() throws Exception {
        Log log = mock(Log.class);
        DubboAppender.logList.add(log);
        when(log.getLogThread()).thenReturn("thread-1");
        assertThat(LogUtil.findThread("thread-1"), equalTo(1));
    }

    @Test
    public void testFindMessage1() throws Exception {
        Log log = mock(Log.class);
        DubboAppender.logList.add(log);
        when(log.getLogMessage()).thenReturn("message");
        assertThat(LogUtil.findMessage("message"), equalTo(1));
    }

    @Test
    public void testFindMessage2() throws Exception {
        Log log = mock(Log.class);
        DubboAppender.logList.add(log);
        when(log.getLogMessage()).thenReturn("message");
        when(log.getLogLevel()).thenReturn(Level.ERROR);
        log = mock(Log.class);
        DubboAppender.logList.add(log);
        when(log.getLogMessage()).thenReturn("message");
        when(log.getLogLevel()).thenReturn(Level.INFO);
        assertThat(LogUtil.findMessage(Level.ERROR, "message"), equalTo(1));
    }

}
