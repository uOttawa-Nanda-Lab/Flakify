@Test public void testInitialFactory() throws Exception {
  System.setProperty(Context.INITIAL_CONTEXT_FACTORY,InitialContextFactory.class.getName());
  InitialContext initialContext=new InitialContext();
  Context context=(Context)initialContext.lookup("");
  assertTrue(context instanceof NamingContext);
  if (!NamingManager.hasInitialContextFactoryBuilder()) {
    NamingManager.setInitialContextFactoryBuilder(new InitialContextFactoryBuilder());
  }
  initialContext=new InitialContext();
  context=(Context)initialContext.lookup("");
  assertTrue(context instanceof NamingContext);
}
