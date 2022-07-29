/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.elasticjob.lite.internal.storage;

import io.elasticjob.lite.reg.base.CoordinatorRegistryCenter;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.transaction.CuratorTransaction;
import org.apache.curator.framework.api.transaction.CuratorTransactionBridge;
import org.apache.curator.framework.api.transaction.CuratorTransactionFinal;
import org.apache.curator.framework.api.transaction.TransactionCheckBuilder;
import org.apache.curator.framework.api.transaction.TransactionCreateBuilder;
import org.apache.curator.framework.listen.Listenable;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.unitils.util.ReflectionUtils;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public final class JobNodeStorageTest {
    
    @Mock
    private CoordinatorRegistryCenter regCenter;
    
    private JobNodeStorage jobNodeStorage = new JobNodeStorage(regCenter, "test_job");
    
    @Before
    public void initMocks() throws NoSuchFieldException {
        MockitoAnnotations.initMocks(this);
        ReflectionUtils.setFieldValue(jobNodeStorage, "regCenter", regCenter);
    }
    
    @Test(expected=RuntimeException.class) public void assertExecuteInTransactionFailure() throws Exception{CuratorFramework client=mock(CuratorFramework.class);CuratorTransaction curatorTransaction=mock(CuratorTransaction.class);TransactionCheckBuilder transactionCheckBuilder=mock(TransactionCheckBuilder.class);CuratorTransactionBridge curatorTransactionBridge=mock(CuratorTransactionBridge.class);CuratorTransactionFinal curatorTransactionFinal=mock(CuratorTransactionFinal.class);when(regCenter.getRawClient()).thenReturn(client);when(client.inTransaction()).thenReturn(curatorTransaction);when(curatorTransaction.check()).thenReturn(transactionCheckBuilder);when(transactionCheckBuilder.forPath("/")).thenReturn(curatorTransactionBridge);when(curatorTransactionBridge.and()).thenReturn(curatorTransactionFinal);TransactionCreateBuilder transactionCreateBuilder=mock(TransactionCreateBuilder.class);when(curatorTransactionFinal.create()).thenReturn(transactionCreateBuilder);when(transactionCreateBuilder.forPath("/test_transaction")).thenReturn(curatorTransactionBridge);when(curatorTransactionBridge.and()).thenThrow(new RuntimeException());jobNodeStorage.executeInTransaction(new TransactionExecutionCallback(){@Override public void execute(final CuratorTransactionFinal curatorTransactionFinal) throws Exception{curatorTransactionFinal.create().forPath("/test_transaction").and();}});verify(regCenter).getRawClient();verify(client).inTransaction();verify(curatorTransaction).check();verify(transactionCheckBuilder).forPath("/");verify(curatorTransactionBridge,times(2)).and();verify(curatorTransactionFinal).create();verify(transactionCreateBuilder).forPath("/test_transaction");verify(curatorTransactionFinal,times(0)).commit();}
}
