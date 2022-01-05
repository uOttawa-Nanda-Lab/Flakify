@Test public void getAddress(){
  Address expected=mock(Address.class);
  when(this.manager.getAddress()).thenReturn(expected);
  Address result=this.subject.getAddress();
  assertSame(expected,result);
}
