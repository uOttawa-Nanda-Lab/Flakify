@Test public void testCreateValidEmailFromString() throws Exception {
  String user="The user <the.user@me.com>";
  Tuple<String,String> tuple=commonsmailHelper.createValidEmailFromString(user);
  assertEquals("the.user@me.com",tuple.x);
  assertEquals("The user",tuple.y);
  user="email@me.com";
  tuple=commonsmailHelper.createValidEmailFromString(user);
  assertEquals("email@me.com",tuple.x);
  assertEquals(null,tuple.y);
}
