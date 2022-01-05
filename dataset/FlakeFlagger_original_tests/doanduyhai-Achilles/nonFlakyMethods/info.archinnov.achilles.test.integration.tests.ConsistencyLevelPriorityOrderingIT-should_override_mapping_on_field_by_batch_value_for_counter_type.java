@Test public void should_override_mapping_on_field_by_batch_value_for_counter_type() throws Exception {
  EntityWithConsistencyLevelOnClassAndField entity=new EntityWithConsistencyLevelOnClassAndField();
  entity.setId(RandomUtils.nextLong());
  entity.setName("name");
  entity.setCount(CounterBuilder.incr());
  Batch batchEm=pmf.createBatch();
  batchEm.startBatch(THREE);
  entity=batchEm.persist(entity);
  expectedEx.expect(UnavailableException.class);
  expectedEx.expectMessage("Not enough replica available for query at consistency THREE (3 required but only 1 alive)");
  entity.getCount();
}
