@Test public void testRegisterURLSchemeHandler() throws Exception {
  InitialContext ictx=new InitialContext(null);
  try {
    ictx.lookup("foobar:something");
    Assert.fail("Precondition: the foobar: scheme should not yet be registered");
  }
 catch (  NamingException ne) {
  }
  ObjectFactory tof=new TestObjectFactory();
  InitialContext.addUrlContextFactory("foobar",tof);
  String something=(String)ictx.lookup("foobar:something");
  Assert.assertTrue("The object should now be provided by our TestObjectFactory",something.startsWith("TestObject:"));
  try {
    InitialContext.removeUrlContextFactory("foobar:",new TestObjectFactory());
    Assert.fail("Should throw an IllegalArgumentException since the associated factory object doesn't match the registration");
  }
 catch (  IllegalArgumentException iae) {
  }
  Assert.assertEquals("The foobar: scheme should still be registered",something,ictx.lookup("foobar:something"));
  InitialContext.removeUrlContextFactory("foobar",tof);
  try {
    ictx.lookup("foobar:something");
    Assert.fail("The foobar: scheme should not be registered any more");
  }
 catch (  NamingException ne) {
  }
}
