@Test public void getMembers(){
  List<Address> expected=Collections.singletonList(mock(Address.class));
  when(this.manager.getMembers()).thenReturn(expected);
  List<Address> result=this.subject.getMembers();
  assertSame(expected,result);
}
