@Test public void getAttributeNames(){
  SessionAttributes attributes=mock(SessionAttributes.class);
  Set<String> expected=new TreeSet<>();
  when(this.session.getAttributes()).thenReturn(attributes);
  when(attributes.getAttributeNames()).thenReturn(expected);
  Enumeration<String> result=this.httpSession.getAttributeNames();
  assertEquals(new ArrayList<>(expected),Collections.list(result));
}
