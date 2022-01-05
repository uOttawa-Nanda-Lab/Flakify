@Test public void testBasic() throws ScanException {
{
    List tl=new TokenStream("%c").tokenize();
    List<Token> witness=new ArrayList<Token>();
    witness.add(Token.PERCENT_TOKEN);
    witness.add(new Token(Token.SIMPLE_KEYWORD,"c"));
    assertEquals(witness,tl);
  }
{
    List tl=new TokenStream("%a%b").tokenize();
    List<Token> witness=new ArrayList<Token>();
    witness.add(Token.PERCENT_TOKEN);
    witness.add(new Token(Token.SIMPLE_KEYWORD,"a"));
    witness.add(Token.PERCENT_TOKEN);
    witness.add(new Token(Token.SIMPLE_KEYWORD,"b"));
    assertEquals(witness,tl);
  }
{
    List tl=new TokenStream("xyz%-34c").tokenize();
    List<Token> witness=new ArrayList<Token>();
    witness.add(new Token(Token.LITERAL,"xyz"));
    witness.add(Token.PERCENT_TOKEN);
    witness.add(new Token(Token.FORMAT_MODIFIER,"-34"));
    witness.add(new Token(Token.SIMPLE_KEYWORD,"c"));
    assertEquals(witness,tl);
  }
}
