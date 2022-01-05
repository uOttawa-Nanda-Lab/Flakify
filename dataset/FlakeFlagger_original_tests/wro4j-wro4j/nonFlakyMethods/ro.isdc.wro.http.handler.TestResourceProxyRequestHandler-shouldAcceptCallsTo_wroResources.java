@Test public void shouldAcceptCallsTo_wroResources(){
  when(request.getRequestURI()).thenReturn("/wro/wroResources?id=test.css");
  assertTrue(victim.accept(request));
}
