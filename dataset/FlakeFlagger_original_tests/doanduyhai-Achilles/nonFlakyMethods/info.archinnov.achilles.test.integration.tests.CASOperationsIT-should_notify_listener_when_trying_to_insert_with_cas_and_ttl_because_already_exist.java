@Test public void should_notify_listener_when_trying_to_insert_with_cas_and_ttl_because_already_exist() throws Exception {
  final AtomicReference<CASResult> atomicCASResult=new AtomicReference(null);
  CASResultListener listener=new CASResultListener(){
    @Override public void onCASSuccess(){
    }
    @Override public void onCASError(    CASResult casResult){
      atomicCASResult.compareAndSet(null,casResult);
    }
  }
;
  final EntityWithEnum entityWithEnum=new EntityWithEnum(10L,"name",EACH_QUORUM);
  Map<String,Object> expectedCurrentValues=ImmutableMap.<String,Object>of("id",10L,"[applied]",false,"consistency_level",EACH_QUORUM.name(),"name","name");
  manager.persist(entityWithEnum);
  manager.persist(entityWithEnum,OptionsBuilder.ifNotExists().withTtl(100).casResultListener(listener));
  final CASResult casResult=atomicCASResult.get();
  assertThat(casResult.operation()).isEqualTo(INSERT);
  assertThat(casResult.currentValues()).isEqualTo(expectedCurrentValues);
  assertThat(casResult.toString()).isEqualTo("CAS operation INSERT cannot be applied. Current values are: {[applied]=false, consistency_level=EACH_QUORUM, id=10, name=name}");
}
