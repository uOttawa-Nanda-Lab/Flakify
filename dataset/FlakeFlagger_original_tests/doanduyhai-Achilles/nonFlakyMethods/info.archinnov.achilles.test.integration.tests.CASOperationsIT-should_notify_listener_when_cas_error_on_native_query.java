@Test public void should_notify_listener_when_cas_error_on_native_query() throws Exception {
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
  CompleteBean entity=builder().randomId().name("John").buid();
  manager.persist(entity);
  final NativeQuery nativeQuery=manager.nativeQuery("UPDATE CompleteBean SET name = 'Helen' WHERE id=" + entity.getId() + " IF name='Andrew'",casResultListener(listener));
  nativeQuery.execute();
  final CASResult casResult=atomicCASResult.get();
  assertThat(casResult).isNotNull();
  assertThat(casResult.operation()).isEqualTo(UPDATE);
  assertThat(casResult.currentValues()).isEqualTo(expectedCurrentValues);
}
