@Test public void should_override_mapping_on_class_by_runtime_value_on_batch_mode_for_normal_type() throws Exception {
  EntityWithConsistencyLevelOnClassAndField entity=new EntityWithConsistencyLevelOnClassAndField();
  long id=RandomUtils.nextLong();
  entity.setId(id);
  entity.setName("name");
  manager.persist(entity);
  Batch batchEm=pmf.createBatch();
  batchEm.startBatch(ONE);
  logAsserter.prepareLogLevel();
  entity=batchEm.find(EntityWithConsistencyLevelOnClassAndField.class,entity.getId());
  logAsserter.assertConsistencyLevels(ONE);
  batchEm.endBatch();
  assertThatBatchContextHasBeenReset(batchEm);
  assertThat(entity.getName()).isEqualTo("name");
  expectedEx.expect(UnavailableException.class);
  expectedEx.expectMessage("Not enough replica available for query at consistency THREE (3 required but only 1 alive)");
  manager.find(EntityWithConsistencyLevelOnClassAndField.class,entity.getId());
}
