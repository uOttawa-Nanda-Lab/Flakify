@Test public void should_activate_query_tracing() throws Exception {
  wrapper=new RegularStatementWrapper(Entity1.class,rs,new Object[]{1},ONE,NO_LISTENER,NO_SERIAL_CONSISTENCY);
  wrapper.activateQueryTracing(rs);
  verify(rs).enableTracing();
}
