@Test public void getId(){
  String id="id";
  when(this.session.getId()).thenReturn(id);
  String result=this.adapter.getId();
  assertSame(id,result);
}
