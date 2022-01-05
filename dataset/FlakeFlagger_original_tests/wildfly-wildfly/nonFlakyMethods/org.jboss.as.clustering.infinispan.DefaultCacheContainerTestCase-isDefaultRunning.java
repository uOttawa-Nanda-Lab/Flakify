@Test public void isDefaultRunning(){
  when(this.manager.isDefaultRunning()).thenReturn(true);
  boolean result=this.subject.isDefaultRunning();
  assertTrue(result);
}
