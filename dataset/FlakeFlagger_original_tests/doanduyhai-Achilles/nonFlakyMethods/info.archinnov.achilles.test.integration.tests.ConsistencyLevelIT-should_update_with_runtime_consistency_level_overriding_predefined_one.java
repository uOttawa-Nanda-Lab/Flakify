@Test public void should_update_with_runtime_consistency_level_overriding_predefined_one() throws Exception {
  CompleteBean entity=builder().randomId().name("name zeruioze").buid();
  entity=manager.persist(entity);
  entity.setName("zeruioze");
  logAsserter.prepareLogLevel();
  manager.update(entity,withConsistency(EACH_QUORUM));
  CompleteBean found=manager.find(CompleteBean.class,entity.getId());
  assertThat(found.getName()).isEqualTo("zeruioze");
  logAsserter.assertConsistencyLevels(EACH_QUORUM,ONE);
}
