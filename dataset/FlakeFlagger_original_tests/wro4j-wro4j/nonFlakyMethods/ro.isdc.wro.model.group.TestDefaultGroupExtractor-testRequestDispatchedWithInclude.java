@Test public void testRequestDispatchedWithInclude(){
  final HttpServletRequest request=mockRequestForUri("all.someInvalidType");
  Mockito.when(request.getAttribute(DefaultGroupExtractor.ATTR_INCLUDE_PATH)).thenReturn("dispatched.type");
  assertEquals("dispatched",groupExtractor.getGroupName(request));
  assertEquals(null,groupExtractor.getResourceType(request));
}
