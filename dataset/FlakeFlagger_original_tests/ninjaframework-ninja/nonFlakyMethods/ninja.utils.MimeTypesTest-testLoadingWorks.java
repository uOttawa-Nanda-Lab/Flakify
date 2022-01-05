@Test public void testLoadingWorks(){
  MimeTypes mimeTypes=new MimeTypes(ninjaProperties);
  assertEquals("application/vnd.ms-cab-compressed",mimeTypes.getMimeType("superfilename.cab"));
  assertEquals("application/vndms-pkiseccat",mimeTypes.getMimeType("superfilename.cat"));
  assertEquals("text/x-c",mimeTypes.getMimeType("superfilename.cc"));
  assertEquals("application/clariscad",mimeTypes.getMimeType("superfilename.ccad"));
  assertEquals("application/custom",mimeTypes.getMimeType("superfilename.custom"));
}
