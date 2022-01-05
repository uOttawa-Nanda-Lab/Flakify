@Test public void getId(){
  String expected="session";
  when(this.session.getId()).thenReturn(expected);
  String result=this.httpSession.getId();
  assertSame(expected,result);
}
