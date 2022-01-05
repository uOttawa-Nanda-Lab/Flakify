@Test public void should_notify_listener_when_failing_cas_update_with_ttl() throws Exception {
  final AtomicReference<CASResult> atomicCASResult=new AtomicReference(null);
  CASResultListener listener=new CASResultListener(){
    @Override public void onCASSuccess(){
    }
    @Override public void onCASError(    CASResult casResult){
      atomicCASResult.compareAndSet(null,casResult);
    }
  }
;
  final EntityWithEnum entityWithEnum=new EntityWithEnum(10L,"John",EACH_QUORUM);
  final EntityWithEnum managed=manager.persist(entityWithEnum);
  Map<String,Object> expectedCurrentValues=ImmutableMap.<String,Object>of("[applied]",false,"consistency_level",EACH_QUORUM.name(),"name","John");
  managed.setName("Helen");
  manager.update(managed,ifConditions(new CASCondition("name","name"),new CASCondition("consistency_level",EACH_QUORUM)).casResultListener(listener).withTtl(100));
  final CASResult casResult=atomicCASResult.get();
  assertThat(casResult).isNotNull();
  assertThat(casResult.operation()).isEqualTo(UPDATE);
  assertThat(casResult.currentValues()).isEqualTo(expectedCurrentValues);
  assertThat(casResult.toString()).isEqualTo("CAS operation UPDATE cannot be applied. Current values are: {[applied]=false, consistency_level=EACH_QUORUM, name=John}");
}
