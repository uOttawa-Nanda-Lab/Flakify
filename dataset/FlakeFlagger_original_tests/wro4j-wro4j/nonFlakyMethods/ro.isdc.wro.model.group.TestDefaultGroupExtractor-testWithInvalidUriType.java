@Test public void testWithInvalidUriType(){
  final HttpServletRequest request=mockRequestForUri("all.someInvalidType");
  assertEquals("all",groupExtractor.getGroupName(request));
  assertEquals(null,groupExtractor.getResourceType(request));
}
