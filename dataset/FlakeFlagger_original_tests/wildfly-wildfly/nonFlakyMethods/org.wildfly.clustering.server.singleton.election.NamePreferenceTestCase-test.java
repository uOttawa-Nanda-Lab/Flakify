@Test public void test(){
  Preference preference=new NamePreference("node1");
  Node node1=mock(Node.class);
  Node node2=mock(Node.class);
  when(node1.getName()).thenReturn("node1");
  when(node2.getName()).thenReturn("node2");
  assertTrue(preference.preferred(node1));
  assertFalse(preference.preferred(node2));
}
