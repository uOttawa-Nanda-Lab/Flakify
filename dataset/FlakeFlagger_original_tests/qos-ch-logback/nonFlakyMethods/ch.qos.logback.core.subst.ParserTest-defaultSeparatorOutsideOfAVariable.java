@Test public void defaultSeparatorOutsideOfAVariable() throws ScanException {
  Tokenizer tokenizer=new Tokenizer("{a:-b}");
  Parser parser=new Parser(tokenizer.tokenize());
  Node node=parser.parse();
  dump(node);
  Node witness=new Node(Node.Type.LITERAL,"{");
  Node t=witness.next=new Node(Node.Type.LITERAL,"a");
  t.next=new Node(Node.Type.LITERAL,":-");
  t=t.next;
  t.next=new Node(Node.Type.LITERAL,"b");
  t=t.next;
  t.next=new Node(Node.Type.LITERAL,"}");
  assertEquals(witness,node);
}
