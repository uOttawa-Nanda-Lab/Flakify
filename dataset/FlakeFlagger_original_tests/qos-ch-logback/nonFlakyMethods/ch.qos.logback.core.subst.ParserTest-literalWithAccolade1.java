@Test public void literalWithAccolade1() throws ScanException {
  Tokenizer tokenizer=new Tokenizer("%x{a}");
  Parser parser=new Parser(tokenizer.tokenize());
  Node node=parser.parse();
  Node witness=new Node(Node.Type.LITERAL,"%x");
  Node t=witness.next=new Node(Node.Type.LITERAL,"{");
  t.next=new Node(Node.Type.LITERAL,"a");
  t=t.next;
  t.next=new Node(Node.Type.LITERAL,"}");
  assertEquals(witness,node);
}
