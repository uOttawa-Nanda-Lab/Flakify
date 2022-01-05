@Test public void getAliases(){
  Set<String> result=this.host.getAliases();
  assertTrue(result.toString(),result.contains(this.hostName));
  assertTrue(result.toString(),result.contains(this.alias));
}
