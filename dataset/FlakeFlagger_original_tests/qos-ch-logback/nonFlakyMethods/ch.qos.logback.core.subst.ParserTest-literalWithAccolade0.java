@Test public void literalWithAccolade0() throws ScanException {
  Tokenizer tokenizer=new Tokenizer("{}");
  Parser parser=new Parser(tokenizer.tokenize());
  Node node=parser.parse();
  Node witness=new Node(Node.Type.LITERAL,"{");
  witness.next=new Node(Node.Type.LITERAL,"}");
  assertEquals(witness,node);
}
