@Test public void testLiteralWithPercent() throws ScanException {
{
    List tl=new TokenStream("hello\\%world").tokenize();
    List<Token> witness=new ArrayList<Token>();
    witness.add(new Token(Token.LITERAL,"hello%world"));
    assertEquals(witness,tl);
  }
{
    List tl=new TokenStream("hello\\%").tokenize();
    List<Token> witness=new ArrayList<Token>();
    witness.add(new Token(Token.LITERAL,"hello%"));
    assertEquals(witness,tl);
  }
{
    List tl=new TokenStream("\\%").tokenize();
    List<Token> witness=new ArrayList<Token>();
    witness.add(new Token(Token.LITERAL,"%"));
    assertEquals(witness,tl);
  }
}
