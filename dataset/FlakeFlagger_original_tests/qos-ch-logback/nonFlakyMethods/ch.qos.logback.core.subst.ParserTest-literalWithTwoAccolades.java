@Test public void literalWithTwoAccolades() throws ScanException {
  Tokenizer tokenizer=new Tokenizer("%x{y} %a{b} c");
  Parser parser=new Parser(tokenizer.tokenize());
  Node node=parser.parse();
  Node witness=new Node(Node.Type.LITERAL,"%x");
  Node t=witness.next=new Node(Node.Type.LITERAL,"{");
  t.next=new Node(Node.Type.LITERAL,"y");
  t=t.next;
  t.next=new Node(Node.Type.LITERAL,"}");
  t=t.next;
  t.next=new Node(Node.Type.LITERAL," %a");
  t=t.next;
  t.next=new Node(Node.Type.LITERAL,"{");
  t=t.next;
  t.next=new Node(Node.Type.LITERAL,"b");
  t=t.next;
  t.next=new Node(Node.Type.LITERAL,"}");
  t=t.next;
  t.next=new Node(Node.Type.LITERAL," c");
  node.dump();
  System.out.println("");
  assertEquals(witness,node);
}
