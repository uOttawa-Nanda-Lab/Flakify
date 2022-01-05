@Test public void solrIsDown() throws Exception {
  SolrServer solrServer=mock(SolrServer.class);
  given(solrServer.ping()).willThrow(new IOException("Connection failed"));
  SolrHealthIndicator healthIndicator=new SolrHealthIndicator(solrServer);
  Health health=healthIndicator.health();
  assertEquals(Status.DOWN,health.getStatus());
  assertTrue(((String)health.getDetails().get("error")).contains("Connection failed"));
}
