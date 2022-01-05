@Test public void shouldExtractGroupWhenUrlContainsJsessionID(){
  final HttpServletRequest request=mockRequestForUri("/contextPath/wro/my.css;jsessionid=blahblah");
  assertEquals("my",groupExtractor.getGroupName(request));
  assertEquals(ResourceType.CSS,groupExtractor.getResourceType(request));
}
