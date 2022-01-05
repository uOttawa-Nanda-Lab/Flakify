@Test public void stop(){
  when(this.manager.getDefaultMaxInactiveInterval()).thenReturn(Duration.ZERO);
  this.adapter.stop();
  verify(this.manager).stop();
}
