@Test public void setVariablesSnapshots_should_set_parent_variables_in_the_snapshot_holder_when_parent_is_multi_instance() throws Exception {
  Map<String,Object> parentVariables=Collections.<String,Object>singletonMap("parentCount",1L);
  ExecutionEntity parentExecution=buildExecutionEntity(parentVariables);
  when(parentExecution.isMultiInstanceRoot()).thenReturn(true);
  Map<String,Object> localVariables=Collections.<String,Object>singletonMap("subCount",1L);
  ExecutionEntity subProcessExecution=buildExecutionEntity(parentExecution,localVariables);
  ExecutionEntity snapshotHolderExecution=mock(ExecutionEntity.class);
  snapshotter.setVariablesSnapshots(subProcessExecution,snapshotHolderExecution);
  verify(snapshotHolderExecution).setVariablesLocal(localVariables);
  verify(snapshotHolderExecution).setVariablesLocal(parentVariables);
}
