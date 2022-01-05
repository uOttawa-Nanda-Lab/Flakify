@Test public void shouldHaveOneAvailableStrategy(){
  assertEquals(1,victim.getAvailableStrategies().size());
  final Set<String> expectedAliases=new HashSet<String>();
  expectedAliases.add(XmlModelFactory.ALIAS);
  assertEquals(expectedAliases,victim.getAvailableAliases());
}
