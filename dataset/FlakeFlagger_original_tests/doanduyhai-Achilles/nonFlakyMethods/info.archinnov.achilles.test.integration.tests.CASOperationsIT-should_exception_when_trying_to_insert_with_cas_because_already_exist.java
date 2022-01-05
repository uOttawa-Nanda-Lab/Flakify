@Test public void should_exception_when_trying_to_insert_with_cas_because_already_exist() throws Exception {
  final EntityWithEnum entityWithEnum=new EntityWithEnum(10L,"name",EACH_QUORUM);
  Map<String,Object> expectedCurrentValues=ImmutableMap.<String,Object>of("id",10L,"[applied]",false,"consistency_level",EACH_QUORUM.name(),"name","name");
  AchillesCASException casException=null;
  manager.persist(entityWithEnum);
  try {
    manager.persist(entityWithEnum,OptionsBuilder.ifNotExists());
  }
 catch (  AchillesCASException ace) {
    casException=ace;
  }
  assertThat(casException).isNotNull();
  assertThat(casException.operation()).isEqualTo(INSERT);
  assertThat(casException.currentValues()).isEqualTo(expectedCurrentValues);
  assertThat(casException.toString()).isEqualTo("CAS operation INSERT cannot be applied. Current values are: {[applied]=false, consistency_level=EACH_QUORUM, id=10, name=name}");
}
