@Test public void testJavaContext() throws Exception {
  System.setProperty(Context.INITIAL_CONTEXT_FACTORY,InitialContextFactory.class.getName());
  System.setProperty(Context.URL_PKG_PREFIXES,"org.jboss.as.naming.interfaces");
  InitialContext initialContext=new InitialContext();
  Context context=(Context)initialContext.lookup("java:");
  assertTrue(context instanceof NamingContext);
}
