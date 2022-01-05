@Test public void getListeners(){
  Set<Object> expected=Collections.singleton(new Object());
  when(this.manager.getListeners()).thenReturn(expected);
  Set<Object> result=this.subject.getListeners();
  assertSame(expected,result);
}
