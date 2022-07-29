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

import static info.archinnov.achilles.type.ConsistencyLevel.EACH_QUORUM;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.reflect.internal.WhiteboxImpl;
import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.RegularStatement;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.google.common.base.Optional;
import info.archinnov.achilles.interceptor.Event;
import info.archinnov.achilles.internal.context.AbstractFlushContext.FlushType;
import info.archinnov.achilles.internal.interceptor.EventHolder;
import info.archinnov.achilles.internal.metadata.holder.EntityMeta;
import info.archinnov.achilles.internal.statement.wrapper.AbstractStatementWrapper;
import info.archinnov.achilles.internal.statement.wrapper.BoundStatementWrapper;
import info.archinnov.achilles.internal.statement.wrapper.RegularStatementWrapper;
import info.archinnov.achilles.listener.CASResultListener;
import info.archinnov.achilles.test.mapping.entity.CompleteBean;
import info.archinnov.achilles.type.ConsistencyLevel;

@RunWith(MockitoJUnitRunner.class)
public class BatchingFlushContextTest {

    private BatchingFlushContext context;

    @Mock
    private DaoContext daoContext;

    @Mock
    private BoundStatementWrapper bsWrapper;

    @Mock
    private RegularStatement query;

    @Captor
    ArgumentCaptor<BatchStatement> batchCaptor;

    private static final Optional<CASResultListener> NO_LISTENER = Optional.absent();
    private  static final Optional<com.datastax.driver.core.ConsistencyLevel> NO_SERIAL_CONSISTENCY = Optional.absent();

    @Before
    public void setUp() {
        context = new BatchingFlushContext(daoContext, EACH_QUORUM,NO_SERIAL_CONSISTENCY);
    }

    @Test public void should_end_batch_with_logged_batch() throws Exception{EventHolder eventHolder=mock(EventHolder.class);RegularStatement statement1=QueryBuilder.select().from("table1");RegularStatement statement2=QueryBuilder.select().from("table2");AbstractStatementWrapper wrapper1=new RegularStatementWrapper(CompleteBean.class,statement1,null,com.datastax.driver.core.ConsistencyLevel.ONE,NO_LISTENER,NO_SERIAL_CONSISTENCY);AbstractStatementWrapper wrapper2=new RegularStatementWrapper(CompleteBean.class,statement2,null,com.datastax.driver.core.ConsistencyLevel.ONE,NO_LISTENER,NO_SERIAL_CONSISTENCY);context.eventHolders=Arrays.asList(eventHolder);context.statementWrappers=Arrays.asList(wrapper1,wrapper2);context.counterStatementWrappers=Arrays.asList(wrapper1,wrapper2);context.consistencyLevel=ConsistencyLevel.LOCAL_QUORUM;context.serialConsistencyLevel=Optional.fromNullable(com.datastax.driver.core.ConsistencyLevel.LOCAL_SERIAL);context.endBatch();verify(eventHolder).triggerInterception();verify(daoContext,times(2)).executeBatch(batchCaptor.capture());assertThat(batchCaptor.getAllValues()).hasSize(2);final BatchStatement batchStatement1=batchCaptor.getAllValues().get(0);assertThat(batchStatement1.getConsistencyLevel()).isSameAs(com.datastax.driver.core.ConsistencyLevel.LOCAL_QUORUM);assertThat(batchStatement1.getSerialConsistencyLevel()).isSameAs(com.datastax.driver.core.ConsistencyLevel.LOCAL_SERIAL);final List<Statement> statements1=WhiteboxImpl.getInternalState(batchStatement1,"statements");assertThat(statements1).contains(statement1,statement2);final BatchStatement batchStatement2=batchCaptor.getAllValues().get(1);assertThat(batchStatement2.getConsistencyLevel()).isSameAs(com.datastax.driver.core.ConsistencyLevel.LOCAL_QUORUM);assertThat(batchStatement2.getSerialConsistencyLevel()).isSameAs(com.datastax.driver.core.ConsistencyLevel.LOCAL_SERIAL);final List<Statement> statements2=WhiteboxImpl.getInternalState(batchStatement2,"statements");assertThat(statements2).contains(statement1,statement2);}
}
