@Test public void testKeyword() throws Exception {
{
    Parser p=new Parser("hello%xyz");
    Node t=p.parse();
    Node witness=new Node(Node.LITERAL,"hello");
    witness.next=new SimpleKeywordNode("xyz");
    assertEquals(witness,t);
  }
{
    Parser p=new Parser("hello%xyz{x}");
    Node t=p.parse();
    Node witness=new Node(Node.LITERAL,"hello");
    SimpleKeywordNode n=new SimpleKeywordNode("xyz");
    List<String> optionList=new ArrayList<String>();
    optionList.add("x");
    n.setOptions(optionList);
    witness.next=n;
    assertEquals(witness,t);
  }
}
