@Test public void should_not_override_batch_mode_level_by_runtime_value_for_normal_type() throws Exception {
  EntityWithConsistencyLevelOnClassAndField entity=new EntityWithConsistencyLevelOnClassAndField();
  entity.setId(RandomUtils.nextLong());
  entity.setName("name sdfsdf");
  manager.persist(entity);
  Batch batchEm=pmf.createBatch();
  batchEm.startBatch(EACH_QUORUM);
  expectedEx.expect(AchillesException.class);
  expectedEx.expectMessage("Runtime custom Consistency Level cannot be set for batch mode. Please set the Consistency Levels at batch start with 'startBatch(consistencyLevel)'");
  batchEm.find(EntityWithConsistencyLevelOnClassAndField.class,entity.getId(),ONE);
}
