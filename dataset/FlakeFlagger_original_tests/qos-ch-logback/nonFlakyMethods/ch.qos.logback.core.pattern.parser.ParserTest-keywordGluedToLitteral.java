@Test public void keywordGluedToLitteral() throws Exception {
  Parser p=new Parser("%x{}a");
  Node t=p.parse();
  SimpleKeywordNode witness=new SimpleKeywordNode("x");
  witness.setOptions(new ArrayList<String>());
  witness.next=new Node(Node.LITERAL,"a");
  assertEquals(witness,t);
}
