@Test public void should_exception_when_failing_cas_update() throws Exception {
  final EntityWithEnum entityWithEnum=new EntityWithEnum(10L,"John",EACH_QUORUM);
  final EntityWithEnum managed=manager.persist(entityWithEnum);
  Map<String,Object> expectedCurrentValues=ImmutableMap.<String,Object>of("[applied]",false,"consistency_level",EACH_QUORUM.name(),"name","John");
  AchillesCASException casException=null;
  managed.setName("Helen");
  try {
    manager.update(managed,ifConditions(new CASCondition("name","name"),new CASCondition("consistency_level",EACH_QUORUM)));
  }
 catch (  AchillesCASException ace) {
    casException=ace;
  }
  assertThat(casException).isNotNull();
  assertThat(casException.operation()).isEqualTo(UPDATE);
  assertThat(casException.currentValues()).isEqualTo(expectedCurrentValues);
  assertThat(casException.toString()).isEqualTo("CAS operation UPDATE cannot be applied. Current values are: {[applied]=false, consistency_level=EACH_QUORUM, name=John}");
}
