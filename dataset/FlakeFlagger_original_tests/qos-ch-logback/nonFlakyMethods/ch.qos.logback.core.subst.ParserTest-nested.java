@Test public void nested() throws ScanException {
  Tokenizer tokenizer=new Tokenizer("a${b${c}}d");
  Parser parser=new Parser(tokenizer.tokenize());
  Node node=parser.parse();
  Node witness=new Node(Node.Type.LITERAL,"a");
  Node bLiteralNode=new Node(Node.Type.LITERAL,"b");
  Node cLiteralNode=new Node(Node.Type.LITERAL,"c");
  Node bVariableNode=new Node(Node.Type.VARIABLE,bLiteralNode);
  Node cVariableNode=new Node(Node.Type.VARIABLE,cLiteralNode);
  bLiteralNode.next=cVariableNode;
  witness.next=bVariableNode;
  witness.next.next=new Node(Node.Type.LITERAL,"d");
  assertEquals(witness,node);
}
