@Test public void should_persist_with_runtime_consistency_level_overriding_predefined_one() throws Exception {
  CompleteBean entity=builder().randomId().name("name zerferg").buid();
  logAsserter.prepareLogLevel();
  manager.persist(entity,withConsistency(EACH_QUORUM));
  CompleteBean found=manager.find(CompleteBean.class,entity.getId());
  assertThat(found.getName()).isEqualTo("name zerferg");
  logAsserter.assertConsistencyLevels(EACH_QUORUM,ONE);
}
