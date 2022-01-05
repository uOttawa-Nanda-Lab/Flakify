@Test public void testValidCssUri(){
  final HttpServletRequest request=mockRequestForUri("group1.css");
  assertEquals("group1",groupExtractor.getGroupName(request));
  assertEquals(ResourceType.CSS,groupExtractor.getResourceType(request));
}
