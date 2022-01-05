@Test public void getStatus(){
  ComponentStatus expected=ComponentStatus.INITIALIZING;
  when(this.manager.getStatus()).thenReturn(expected);
  ComponentStatus result=this.subject.getStatus();
  assertSame(expected,result);
}
