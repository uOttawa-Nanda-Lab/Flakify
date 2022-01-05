@Test public void testValidJsUri(){
  final HttpServletRequest request=mockRequestForUri("otherGroup.js");
  assertEquals("otherGroup",groupExtractor.getGroupName(request));
  assertEquals(ResourceType.JS,groupExtractor.getResourceType(request));
}
