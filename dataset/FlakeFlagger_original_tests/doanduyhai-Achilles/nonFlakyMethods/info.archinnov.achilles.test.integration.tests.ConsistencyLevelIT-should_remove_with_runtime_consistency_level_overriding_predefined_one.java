@Test public void should_remove_with_runtime_consistency_level_overriding_predefined_one() throws Exception {
  CompleteBean entity=builder().randomId().name("name").buid();
  entity=manager.persist(entity);
  logAsserter.prepareLogLevel();
  manager.remove(entity,withConsistency(EACH_QUORUM));
  assertThat(manager.find(CompleteBean.class,entity.getId())).isNull();
  logAsserter.assertConsistencyLevels(EACH_QUORUM,ONE);
}
