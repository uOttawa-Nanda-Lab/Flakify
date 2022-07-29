package org.activiti.engine.impl.bpmn.helper;

import java.util.Collections;
import java.util.Map;

import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**

 */
public class SubProcessVariableSnapshotterTest {

    private SubProcessVariableSnapshotter snapshotter = new SubProcessVariableSnapshotter();

    private ExecutionEntity buildExecutionEntity(Map<String, Object> variables) {
        ExecutionEntity subProcessExecution = mock(ExecutionEntity.class);
        when(subProcessExecution.getVariablesLocal()).thenReturn(variables);
        return subProcessExecution;
    }

    private ExecutionEntity buildExecutionEntity(ExecutionEntity parentExecution, Map<String, Object> localVariables) {
        ExecutionEntity subProcessExecution = buildExecutionEntity(localVariables);
        when(subProcessExecution.getParent()).thenReturn(parentExecution);
        return subProcessExecution;
    }

    @Test public void setVariablesSnapshots_should_not_set_parent_variables_in_the_snapshot_holder_when_parent_is_not_multi_instance() throws Exception{Map<String, Object> parentVariables=Collections.<String,Object>singletonMap("parentCount",1L);ExecutionEntity parentExecution=buildExecutionEntity(parentVariables);when(parentExecution.isMultiInstanceRoot()).thenReturn(false);Map<String, Object> localVariables=Collections.<String,Object>singletonMap("subCount",1L);ExecutionEntity subProcessExecution=buildExecutionEntity(parentExecution,localVariables);ExecutionEntity snapshotHolderExecution=mock(ExecutionEntity.class);snapshotter.setVariablesSnapshots(subProcessExecution,snapshotHolderExecution);verify(snapshotHolderExecution).setVariablesLocal(localVariables);verify(snapshotHolderExecution,never()).setVariablesLocal(parentVariables);}


}