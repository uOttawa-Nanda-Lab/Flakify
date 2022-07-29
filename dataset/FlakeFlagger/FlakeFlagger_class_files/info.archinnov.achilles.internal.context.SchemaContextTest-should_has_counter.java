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

package info.archinnov.achilles.internal.context;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.KeyspaceMetadata;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.TableMetadata;
import com.google.common.collect.Sets;

import info.archinnov.achilles.internal.metadata.holder.EntityMeta;
import info.archinnov.achilles.internal.metadata.parsing.context.ParsingResult;
import info.archinnov.achilles.internal.table.TableCreator;
import info.archinnov.achilles.internal.table.TableUpdater;
import info.archinnov.achilles.internal.table.TableValidator;

@RunWith(MockitoJUnitRunner.class)
public class SchemaContextTest {

    private SchemaContext context;

    @Mock
    private Session session;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private Cluster cluster;

    @Mock
    private Map<Class<?>, EntityMeta> entityMetaMap;

    @Mock
    private TableCreator tableCreator;

	@Mock
	private TableUpdater tableUpdater;

    @Mock
    private TableValidator tableValidator;


    private String keyspaceName = "keyspace";

    @Before
    public void setUp() {
        context = new SchemaContext(true, session, keyspaceName, cluster, new ParsingResult(entityMetaMap, true));
        Whitebox.setInternalState(context, TableCreator.class, tableCreator);
		Whitebox.setInternalState(context, TableUpdater.class, tableUpdater);
		Whitebox.setInternalState(context, TableValidator.class, tableValidator);
    }

    @Test public void should_has_counter() throws Exception{assertThat(context.hasSimpleCounter()).isTrue();}
}
