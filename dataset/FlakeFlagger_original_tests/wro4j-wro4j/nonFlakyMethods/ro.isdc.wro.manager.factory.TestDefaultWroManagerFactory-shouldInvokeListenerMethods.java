@Test public void shouldInvokeListenerMethods(){
  final WroManagerFactory mockManagerFactory=Mockito.mock(WroManagerFactory.class);
  victim=new DefaultWroManagerFactory(new Properties()){
    @Override protected WroManagerFactory newManagerFactory(){
      return mockManagerFactory;
    }
  }
;
  victim.onCachePeriodChanged(0);
  Mockito.verify(mockManagerFactory).onCachePeriodChanged(0);
  victim.onModelPeriodChanged(0);
  Mockito.verify(mockManagerFactory).onModelPeriodChanged(0);
}
