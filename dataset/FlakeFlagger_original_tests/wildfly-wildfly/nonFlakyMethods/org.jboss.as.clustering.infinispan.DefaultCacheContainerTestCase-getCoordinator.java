@Test public void getCoordinator(){
  Address expected=mock(Address.class);
  when(this.manager.getCoordinator()).thenReturn(expected);
  Address result=this.subject.getCoordinator();
  assertSame(expected,result);
}
