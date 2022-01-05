@Test public void setVariablesSnapshots_should_set_all_source_local_variables_in_the_snapshot_holder() throws Exception {
  Map<String,Object> variables=Collections.<String,Object>singletonMap("subCount",1L);
  ExecutionEntity subProcessExecution=buildExecutionEntity(variables);
  ExecutionEntity snapshotHolderExecution=mock(ExecutionEntity.class);
  snapshotter.setVariablesSnapshots(subProcessExecution,snapshotHolderExecution);
  verify(snapshotHolderExecution).setVariablesLocal(variables);
}
