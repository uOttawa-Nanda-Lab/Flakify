@Test public void literal() throws ScanException {
  Tokenizer tokenizer=new Tokenizer("abc");
  Parser parser=new Parser(tokenizer.tokenize());
  Node node=parser.parse();
  Node witness=new Node(Node.Type.LITERAL,"abc");
  assertEquals(witness,node);
}
