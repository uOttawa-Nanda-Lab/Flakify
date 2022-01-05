@Test public void testSingleLiteral() throws ScanException {
  List tl=new TokenStream("hello").tokenize();
  List<Token> witness=new ArrayList<Token>();
  witness.add(new Token(Token.LITERAL,"hello"));
  assertEquals(witness,tl);
}
