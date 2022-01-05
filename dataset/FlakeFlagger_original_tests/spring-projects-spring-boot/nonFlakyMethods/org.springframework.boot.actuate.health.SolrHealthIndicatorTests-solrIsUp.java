@Test public void solrIsUp() throws Exception {
  SolrServer solrServer=mock(SolrServer.class);
  SolrPingResponse pingResponse=new SolrPingResponse();
  NamedList<Object> response=new NamedList<Object>();
  response.add("status","OK");
  pingResponse.setResponse(response);
  given(solrServer.ping()).willReturn(pingResponse);
  SolrHealthIndicator healthIndicator=new SolrHealthIndicator(solrServer);
  Health health=healthIndicator.health();
  assertEquals(Status.UP,health.getStatus());
  assertEquals("OK",health.getDetails().get("solrStatus"));
}
