@Test public void getId(){
  String id="sso";
  when(this.sso.getId()).thenReturn(id);
  String result=this.subject.getId();
  assertSame(id,result);
  verifyZeroInteractions(this.batch);
}
