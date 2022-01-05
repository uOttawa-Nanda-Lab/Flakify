@Test public void getConnectors(){
  Iterator<org.jboss.modcluster.container.Connector> results=this.engine.getConnectors().iterator();
  assertTrue(results.hasNext());
  org.jboss.modcluster.container.Connector connector=results.next();
  String listenerName="default";
  assertSame(listenerName,connector.toString());
  assertFalse(results.hasNext());
}
