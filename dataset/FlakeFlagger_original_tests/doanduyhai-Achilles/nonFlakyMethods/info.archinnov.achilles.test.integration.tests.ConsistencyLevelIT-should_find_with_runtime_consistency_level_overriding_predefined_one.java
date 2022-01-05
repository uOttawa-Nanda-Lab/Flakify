@Test public void should_find_with_runtime_consistency_level_overriding_predefined_one() throws Exception {
  boolean exceptionCaught=false;
  CompleteBean entity=builder().randomId().name("name rtprt").buid();
  manager.persist(entity);
  try {
    manager.find(CompleteBean.class,entity.getId(),EACH_QUORUM);
  }
 catch (  InvalidQueryException e) {
    assertThat(e).hasMessage("EACH_QUORUM ConsistencyLevel is only supported for writes");
    exceptionCaught=true;
  }
  assertThat(exceptionCaught).isTrue();
  logAsserter.prepareLogLevel();
  CompleteBean found=manager.find(CompleteBean.class,entity.getId(),ALL);
  assertThat(found.getName()).isEqualTo("name rtprt");
  logAsserter.assertConsistencyLevels(ALL);
}
