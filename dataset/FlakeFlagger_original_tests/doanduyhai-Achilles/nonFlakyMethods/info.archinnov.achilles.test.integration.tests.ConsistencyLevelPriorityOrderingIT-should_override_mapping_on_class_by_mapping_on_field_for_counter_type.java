@Test public void should_override_mapping_on_class_by_mapping_on_field_for_counter_type() throws Exception {
  EntityWithConsistencyLevelOnClassAndField entity=new EntityWithConsistencyLevelOnClassAndField();
  entity.setId(RandomUtils.nextLong());
  entity.setName("name");
  entity.setCount(CounterBuilder.incr());
  entity=manager.persist(entity);
  logAsserter.prepareLogLevel();
  Counter counter=entity.getCount();
  counter.incr(10L);
  assertThat(counter.get()).isEqualTo(11L);
  logAsserter.assertConsistencyLevels(ONE);
}
