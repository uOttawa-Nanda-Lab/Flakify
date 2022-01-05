@Test public void testCreateSubcontext() throws Exception {
  assertTrue(namingContext.createSubcontext(new CompositeName("test")) instanceof NamingContext);
  assertTrue(testActionPermission(JndiPermission.ACTION_CREATE_SUBCONTEXT,namingContext,"securitytest") instanceof NamingContext);
}
