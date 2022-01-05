@Test public void should_notify_listener_on_cas_update_failure() throws Exception {
  final AtomicReference<CASResult> atomicCASResult=new AtomicReference(null);
  CASResultListener listener=new CASResultListener(){
    @Override public void onCASSuccess(){
    }
    @Override public void onCASError(    CASResult casResult){
      atomicCASResult.compareAndSet(null,casResult);
    }
  }
;
  Map<String,Object> expectedCurrentValues=ImmutableMap.<String,Object>of("[applied]",false,"name","John");
  CompleteBean entity=builder().randomId().name("John").addFollowers("Paul","Andrew").buid();
  final CompleteBean managed=manager.persist(entity);
  managed.getFollowers().add("Helen");
  manager.update(managed,ifConditions(new CASCondition("name","Helen")).casResultListener(listener));
  final CASResult casResult=atomicCASResult.get();
  assertThat(casResult).isNotNull();
  assertThat(casResult.operation()).isEqualTo(UPDATE);
  assertThat(casResult.currentValues()).isEqualTo(expectedCurrentValues);
}
