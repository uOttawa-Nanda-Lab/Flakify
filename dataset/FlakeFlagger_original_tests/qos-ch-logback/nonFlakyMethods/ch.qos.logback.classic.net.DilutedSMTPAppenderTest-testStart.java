@Test public void testStart(){
  try {
    Address[] addressArray=appender.getMessage().getFrom();
    Address address=addressArray[0];
    assertEquals("user@host.dom",address.toString());
    addressArray=null;
    address=null;
    assertEquals("sebastien.nospam@qos.ch%nopex",appender.getToAsListOfString().get(0));
    assertEquals("logging report",appender.getSubject());
    assertTrue(appender.isStarted());
  }
 catch (  MessagingException ex) {
    fail("Unexpected exception.");
  }
}
