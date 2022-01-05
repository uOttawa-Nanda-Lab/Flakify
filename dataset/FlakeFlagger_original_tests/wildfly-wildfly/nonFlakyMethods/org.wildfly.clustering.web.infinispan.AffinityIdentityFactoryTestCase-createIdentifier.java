@Test public void createIdentifier(){
  String expected="id";
  Address address=mock(Address.class);
  when(this.manager.getAddress()).thenReturn(address);
  when(this.affinity.getKeyForAddress(address)).thenReturn(new Key<>(expected));
  String result=this.subject.createIdentifier();
  assertSame(expected,result);
}
