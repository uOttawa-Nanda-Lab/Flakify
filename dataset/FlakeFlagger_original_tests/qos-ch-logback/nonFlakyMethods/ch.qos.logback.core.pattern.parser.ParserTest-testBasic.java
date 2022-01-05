@Test public void testBasic() throws Exception {
  Parser p=new Parser("hello");
  Node t=p.parse();
  assertEquals(Node.LITERAL,t.getType());
  assertEquals("hello",t.getValue());
}
