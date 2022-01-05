@Test public void testIsModified(){
  HttpCacheToolkit httpCacheToolkit=new HttpCacheToolkitImpl(ninjaProperties);
  when(context.getHeader(HttpHeaderConstants.IF_NONE_MATCH)).thenReturn("etag_xyz");
  assertFalse(httpCacheToolkit.isModified(Optional.of("etag_xyz"),Optional.of(0L),context));
  assertTrue(httpCacheToolkit.isModified(Optional.of("etag_xyz_modified"),Optional.of(0L),context));
  when(context.getHeader(HttpHeaderConstants.IF_NONE_MATCH)).thenReturn(null);
  when(context.getHeader(HttpHeaderConstants.IF_MODIFIED_SINCE)).thenReturn(null);
  assertTrue(httpCacheToolkit.isModified(Optional.of("etag_xyz_modified"),Optional.of(0L),context));
  when(context.getHeader(HttpHeaderConstants.IF_MODIFIED_SINCE)).thenReturn("Thu, 01 Jan 1970 00:00:00 GMT");
  assertTrue(httpCacheToolkit.isModified(Optional.of("etag_xyz_modified"),Optional.of(1000L),context));
  when(context.getHeader(HttpHeaderConstants.IF_MODIFIED_SINCE)).thenReturn("Thu, 01 Jan 1970 00:00:00 GMT");
  assertFalse(httpCacheToolkit.isModified(Optional.of("etag_xyz_modified"),Optional.of(0L),context));
  when(context.getHeader(HttpHeaderConstants.IF_MODIFIED_SINCE)).thenReturn("Thu, 01 Jan 1971 00:00:00 GMT");
  assertFalse(httpCacheToolkit.isModified(Optional.of("etag_xyz_modified"),Optional.of(0L),context));
  when(context.getHeader(HttpHeaderConstants.IF_MODIFIED_SINCE)).thenReturn("STRANGE_TIMESTAMP");
  assertTrue(httpCacheToolkit.isModified(Optional.of("etag_xyz_modified"),Optional.of(0L),context));
}
