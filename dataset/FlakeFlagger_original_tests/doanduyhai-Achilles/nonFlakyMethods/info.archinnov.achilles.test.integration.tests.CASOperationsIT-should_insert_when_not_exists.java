@Test public void should_insert_when_not_exists() throws Exception {
  final EntityWithEnum entityWithEnum=new EntityWithEnum(10L,"name",EACH_QUORUM);
  logAsserter.prepareLogLevel();
  manager.persist(entityWithEnum,OptionsBuilder.ifNotExists().casLocalSerial());
  final EntityWithEnum found=manager.find(EntityWithEnum.class,10L);
  assertThat(found).isNotNull();
  assertThat(found.getName()).isEqualTo("name");
  assertThat(found.getConsistencyLevel()).isEqualTo(EACH_QUORUM);
  logAsserter.assertSerialConsistencyLevels(LOCAL_SERIAL,ONE);
}
