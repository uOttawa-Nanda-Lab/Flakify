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

package info.archinnov.achilles.internal.statement.wrapper;

import static com.datastax.driver.core.ColumnDefinitionBuilder.buildColumnDef;
import static com.datastax.driver.core.ColumnDefinitions.Definition;
import static com.datastax.driver.core.ConsistencyLevel.LOCAL_SERIAL;
import static com.datastax.driver.core.ConsistencyLevel.ONE;
import static info.archinnov.achilles.internal.statement.wrapper.AbstractStatementWrapper.CAS_RESULT_COLUMN;
import static info.archinnov.achilles.listener.CASResultListener.CASResult;
import static info.archinnov.achilles.listener.CASResultListener.CASResult.Operation.INSERT;
import static info.archinnov.achilles.listener.CASResultListener.CASResult.Operation.UPDATE;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.fest.assertions.data.MapEntry;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.datastax.driver.core.ColumnDefinitions;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.DataType;
import com.datastax.driver.core.ExecutionInfo;
import com.datastax.driver.core.QueryTrace;
import com.datastax.driver.core.RegularStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.google.common.base.Optional;
import info.archinnov.achilles.exception.AchillesCASException;
import info.archinnov.achilles.internal.reflection.RowMethodInvoker;
import info.archinnov.achilles.listener.CASResultListener;
import info.archinnov.achilles.test.mapping.entity.CompleteBean;
import info.archinnov.achilles.test.sample.entity.Entity1;

@RunWith(MockitoJUnitRunner.class)
public class RegularStatementWrapperTest {

    private RegularStatementWrapper wrapper;

    @Mock
    private RegularStatement rs;

    @Mock
    private Session session;

    @Mock
    private Row row;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private ResultSet resultSet;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private ColumnDefinitions columnDefinitions;

    @Mock
    private RowMethodInvoker invoker;

    private static final Optional<CASResultListener> NO_LISTENER = Optional.absent();
    private  static final Optional<com.datastax.driver.core.ConsistencyLevel> NO_SERIAL_CONSISTENCY = Optional.absent();


    @Before
    public void setUp() {
        when(rs.getQueryString()).thenReturn("SELECT * FROM table");
    }

    @Test public void should_notify_listener_on_cas_error() throws Exception{wrapper=new RegularStatementWrapper(CompleteBean.class,rs,new Object[]{1},ONE,NO_LISTENER,NO_SERIAL_CONSISTENCY);wrapper.invoker=invoker;when(rs.getQueryString()).thenReturn("INSERT INTO table IF NOT EXISTS");when(session.execute(rs)).thenReturn(resultSet);when(resultSet.one()).thenReturn(row);when(row.getBool(CAS_RESULT_COLUMN)).thenReturn(false);when(row.getColumnDefinitions()).thenReturn(columnDefinitions);when(columnDefinitions.iterator().hasNext()).thenReturn(true,true,false);Definition col1=buildColumnDef("keyspace","table","[applied]",DataType.cboolean());Definition col2=buildColumnDef("keyspace","table","id",DataType.bigint());when(columnDefinitions.iterator().next()).thenReturn(col1,col2);when(invoker.invokeOnRowForType(row,DataType.cboolean().asJavaClass(),"[applied]")).thenReturn(false);when(invoker.invokeOnRowForType(row,DataType.bigint().asJavaClass(),"id")).thenReturn(10L);AchillesCASException caughtEx=null;try {wrapper.execute(session);} catch (AchillesCASException ace){caughtEx=ace;}verify(session).execute(rs);assertThat(caughtEx).isNotNull();assertThat(caughtEx.operation()).isEqualTo(INSERT);assertThat(caughtEx.currentValues()).contains(MapEntry.entry("[applied]",false),MapEntry.entry("id",10L));}
}
