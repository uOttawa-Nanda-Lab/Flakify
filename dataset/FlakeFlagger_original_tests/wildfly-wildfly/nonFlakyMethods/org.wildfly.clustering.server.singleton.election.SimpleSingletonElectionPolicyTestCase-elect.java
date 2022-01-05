@Test public void elect(){
  Node node1=mock(Node.class);
  Node node2=mock(Node.class);
  Node node3=mock(Node.class);
  List<Node> nodes=Arrays.asList(node1,node2,node3);
  assertSame(node1,new SimpleSingletonElectionPolicy().elect(nodes));
  assertSame(node1,new SimpleSingletonElectionPolicy(0).elect(nodes));
  assertSame(node2,new SimpleSingletonElectionPolicy(1).elect(nodes));
  assertSame(node3,new SimpleSingletonElectionPolicy(2).elect(nodes));
  assertSame(node1,new SimpleSingletonElectionPolicy(3).elect(nodes));
  assertNull(new SimpleSingletonElectionPolicy().elect(Collections.<Node>emptyList()));
}
