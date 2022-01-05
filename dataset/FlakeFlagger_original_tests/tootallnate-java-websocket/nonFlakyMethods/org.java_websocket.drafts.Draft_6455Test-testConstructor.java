@Test public void testConstructor() throws Exception {
  try {
    Draft_6455 draft_6455=new Draft_6455(null,null);
    fail("IllegalArgumentException expected");
  }
 catch (  IllegalArgumentException e) {
  }
  try {
    Draft_6455 draft_6455=new Draft_6455(Collections.<IExtension>emptyList(),null);
    fail("IllegalArgumentException expected");
  }
 catch (  IllegalArgumentException e) {
  }
  try {
    Draft_6455 draft_6455=new Draft_6455(null,Collections.<IProtocol>emptyList());
    fail("IllegalArgumentException expected");
  }
 catch (  IllegalArgumentException e) {
  }
  Draft_6455 draft_6455=new Draft_6455(Collections.<IExtension>emptyList(),Collections.<IProtocol>emptyList());
  assertEquals(1,draft_6455.getKnownExtensions().size());
  assertEquals(0,draft_6455.getKnownProtocols().size());
}
