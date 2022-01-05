@Test public void getAttribute(){
  SessionAttributes attributes=mock(SessionAttributes.class);
  String name="name";
  Object expected=new Object();
  when(this.session.getAttributes()).thenReturn(attributes);
  when(attributes.getAttribute(name)).thenReturn(expected);
  Object result=this.httpSession.getAttribute(name);
  assertSame(expected,result);
}
