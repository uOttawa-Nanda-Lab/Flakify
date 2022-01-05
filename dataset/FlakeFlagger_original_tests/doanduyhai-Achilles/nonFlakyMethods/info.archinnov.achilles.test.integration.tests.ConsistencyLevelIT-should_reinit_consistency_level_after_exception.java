@Test public void should_reinit_consistency_level_after_exception() throws Exception {
  CompleteBean entity=builder().randomId().name("name qzerferf").buid();
  logAsserter.prepareLogLevel();
  manager.persist(entity,withConsistency(EACH_QUORUM));
  CompleteBean found=manager.find(CompleteBean.class,entity.getId());
  assertThat(found.getName()).isEqualTo("name qzerferf");
  logAsserter.assertConsistencyLevels(EACH_QUORUM,ONE);
}
