@Test public void testForUnescapedCharacterInURLisAccepted() throws Exception {
  String res=getResult(PORT);
  Assert.assertTrue(res,res.startsWith("HTTP/1.1 200"));
  Assert.assertTrue(res,res.contains("ECHO:/한 글"));
}
