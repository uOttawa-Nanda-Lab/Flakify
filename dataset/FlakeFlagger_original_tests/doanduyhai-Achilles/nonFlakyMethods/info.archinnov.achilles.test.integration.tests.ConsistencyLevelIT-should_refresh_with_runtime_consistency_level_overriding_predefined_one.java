@Test public void should_refresh_with_runtime_consistency_level_overriding_predefined_one() throws Exception {
  boolean exceptionCaught=false;
  CompleteBean entity=builder().randomId().name("name").buid();
  entity=manager.persist(entity);
  try {
    manager.refresh(entity,EACH_QUORUM);
  }
 catch (  InvalidQueryException e) {
    assertThat(e).hasMessage("EACH_QUORUM ConsistencyLevel is only supported for writes");
    exceptionCaught=true;
  }
  assertThat(exceptionCaught).isTrue();
  logAsserter.prepareLogLevel();
  manager.refresh(entity,ALL);
  logAsserter.assertConsistencyLevels(ALL);
}
