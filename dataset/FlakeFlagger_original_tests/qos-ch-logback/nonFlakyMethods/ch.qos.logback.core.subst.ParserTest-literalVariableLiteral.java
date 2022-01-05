@Test public void literalVariableLiteral() throws ScanException {
  Tokenizer tokenizer=new Tokenizer("a${b}c");
  Parser parser=new Parser(tokenizer.tokenize());
  Node node=parser.parse();
  Node witness=new Node(Node.Type.LITERAL,"a");
  witness.next=new Node(Node.Type.VARIABLE,new Node(Node.Type.LITERAL,"b"));
  witness.next.next=new Node(Node.Type.LITERAL,"c");
  assertEquals(witness,node);
}
