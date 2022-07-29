/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.common.utils;

import org.apache.dubbo.common.Constants;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class StringUtilsTest {
    @Test public void testReplace() throws Exception{assertThat(StringUtils.replace(null,"*","*"),nullValue());assertThat(StringUtils.replace("","*","*"),equalTo(""));assertThat(StringUtils.replace("any",null,"*"),equalTo("any"));assertThat(StringUtils.replace("any","*",null),equalTo("any"));assertThat(StringUtils.replace("any","","*"),equalTo("any"));assertThat(StringUtils.replace("aba","a",null),equalTo("aba"));assertThat(StringUtils.replace("aba","a",""),equalTo("b"));assertThat(StringUtils.replace("aba","a","z"),equalTo("zbz"));assertThat(StringUtils.replace(null,"*","*",64),nullValue());assertThat(StringUtils.replace("","*","*",64),equalTo(""));assertThat(StringUtils.replace("any",null,"*",64),equalTo("any"));assertThat(StringUtils.replace("any","*",null,64),equalTo("any"));assertThat(StringUtils.replace("any","","*",64),equalTo("any"));assertThat(StringUtils.replace("any","*","*",0),equalTo("any"));assertThat(StringUtils.replace("abaa","a",null,-1),equalTo("abaa"));assertThat(StringUtils.replace("abaa","a","",-1),equalTo("b"));assertThat(StringUtils.replace("abaa","a","z",0),equalTo("abaa"));assertThat(StringUtils.replace("abaa","a","z",1),equalTo("zbaa"));assertThat(StringUtils.replace("abaa","a","z",2),equalTo("zbza"));}
}