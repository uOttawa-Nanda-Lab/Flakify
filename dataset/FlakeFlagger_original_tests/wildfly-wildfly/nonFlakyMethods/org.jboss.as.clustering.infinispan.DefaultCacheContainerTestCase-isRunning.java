@Test public void isRunning(){
  when(this.manager.isRunning("other")).thenReturn(true);
  boolean result=this.subject.isRunning("other");
  assertTrue(result);
}
