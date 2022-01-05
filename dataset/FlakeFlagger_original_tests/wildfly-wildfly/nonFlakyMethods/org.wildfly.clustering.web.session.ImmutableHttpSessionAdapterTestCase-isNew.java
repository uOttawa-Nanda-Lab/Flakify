@Test public void isNew(){
  SessionMetaData metaData=mock(SessionMetaData.class);
  when(this.session.getMetaData()).thenReturn(metaData);
  when(metaData.isNew()).thenReturn(true,false);
  assertTrue(this.httpSession.isNew());
  assertFalse(this.httpSession.isNew());
}
