@Test public void should_insert_and_notify_cas_listener_on_success() throws Exception {
  final AtomicBoolean casSuccess=new AtomicBoolean(false);
  CASResultListener listener=new CASResultListener(){
    @Override public void onCASSuccess(){
      casSuccess.compareAndSet(false,true);
    }
    @Override public void onCASError(    CASResult casResult){
    }
  }
;
  final EntityWithEnum entityWithEnum=new EntityWithEnum(10L,"name",EACH_QUORUM);
  manager.persist(entityWithEnum,OptionsBuilder.ifNotExists().casResultListener(listener));
  final EntityWithEnum found=manager.find(EntityWithEnum.class,10L);
  assertThat(found).isNotNull();
  assertThat(found.getName()).isEqualTo("name");
  assertThat(found.getConsistencyLevel()).isEqualTo(EACH_QUORUM);
  assertThat(casSuccess.get()).isTrue();
}
