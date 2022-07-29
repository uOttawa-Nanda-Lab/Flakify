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

import org.apache.dubbo.common.logger.Logger;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LogHelperTest {

    @Test public void testError() throws Exception{Logger logger=Mockito.mock(Logger.class);when(logger.isErrorEnabled()).thenReturn(true);LogHelper.error(logger,"error");verify(logger).error("error");Throwable t=new RuntimeException();LogHelper.error(logger,t);verify(logger).error(t);LogHelper.error(logger,"error",t);verify(logger).error("error",t);}
}
