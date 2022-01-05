@Test public void withColon() throws ScanException {
  Tokenizer tokenizer=new Tokenizer("a:${b}");
  Parser parser=new Parser(tokenizer.tokenize());
  Node node=parser.parse();
  Node witness=new Node(Node.Type.LITERAL,"a");
  Node t=witness.next=new Node(Node.Type.LITERAL,":");
  t.next=new Node(Node.Type.VARIABLE,new Node(Node.Type.LITERAL,"b"));
  assertEquals(witness,node);
}
