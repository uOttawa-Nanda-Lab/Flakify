/*
 * Copyright (C) 2012-2014 DuyHai DOAN
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package info.archinnov.achilles.internal.helper;

import static org.fest.assertions.api.Assertions.assertThat;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import com.google.common.collect.Lists;
import info.archinnov.achilles.test.mapping.entity.CompleteBean;

public class LoggerHelperTest {

	@Test public void should_transform_field_list_to_field_name_list() throws Exception{Field field=CompleteBean.class.getDeclaredField("id");List<Field> fields=new ArrayList<Field>();fields.add(field);assertThat(Lists.transform(fields,LoggerHelper.fieldToStringFn)).contains("id");}
}
