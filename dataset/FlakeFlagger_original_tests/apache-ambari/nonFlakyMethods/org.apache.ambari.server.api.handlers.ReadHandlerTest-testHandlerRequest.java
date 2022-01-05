@Test public void testHandlerRequest() throws Exception {
  Request request=createStrictMock(Request.class);
  ResourceInstance resource=createStrictMock(ResourceInstance.class);
  Query query=createMock(Query.class);
  Predicate predicate=createMock(Predicate.class);
  Result result=createStrictMock(Result.class);
  Map<String,TemporalInfo> mapPartialResponseFields=new HashMap<String,TemporalInfo>();
  mapPartialResponseFields.put("foo",null);
  mapPartialResponseFields.put("bar/c",null);
  mapPartialResponseFields.put("bar/d/e",null);
  expect(request.getResource()).andReturn(resource);
  expect(resource.getQuery()).andReturn(query);
  expect(request.getFields()).andReturn(mapPartialResponseFields);
  query.addProperty(null,"foo",null);
  query.addProperty("bar","c",null);
  query.addProperty("bar/d","e",null);
  expect(request.getQueryPredicate()).andReturn(predicate);
  query.setUserPredicate(predicate);
  expect(query.execute()).andReturn(result);
  replay(request,resource,query,predicate,result);
  ReadHandler handler=new ReadHandler();
  assertSame(result,handler.handleRequest(request));
  verify(request,resource,query,predicate,result);
}
