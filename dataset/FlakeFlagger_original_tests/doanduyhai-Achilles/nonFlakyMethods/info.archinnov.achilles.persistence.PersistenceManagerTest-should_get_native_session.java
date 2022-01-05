@Test public void should_get_native_session() throws Exception {
  Session session=mock(Session.class);
  when(daoContext.getSession()).thenReturn(session);
  Session actual=manager.getNativeSession();
  assertThat(actual).isSameAs(session);
}
