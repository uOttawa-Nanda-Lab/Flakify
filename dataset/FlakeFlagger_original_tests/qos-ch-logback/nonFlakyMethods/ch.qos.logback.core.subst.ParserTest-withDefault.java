@Test public void withDefault() throws ScanException {
  Tokenizer tokenizer=new Tokenizer("${b:-c}");
  Parser parser=new Parser(tokenizer.tokenize());
  Node node=parser.parse();
  Node witness=new Node(Node.Type.VARIABLE,new Node(Node.Type.LITERAL,"b"));
  witness.defaultPart=new Node(Node.Type.LITERAL,"c");
  assertEquals(witness,node);
}
