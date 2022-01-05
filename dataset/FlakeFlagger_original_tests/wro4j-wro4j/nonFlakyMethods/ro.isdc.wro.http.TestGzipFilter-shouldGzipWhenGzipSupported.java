@Test public void shouldGzipWhenGzipSupported() throws Exception {
  markGzipAsAllowed();
  final String content="sampleContent";
  final InputStream sourceStream=new ByteArrayInputStream(content.getBytes());
  final ByteArrayOutputStream targetStream=new ByteArrayOutputStream();
  when(mockRequest.getInputStream()).thenReturn(new DelegatingServletInputStream(sourceStream));
  when(mockResponse.getOutputStream()).thenReturn(new DelegatingServletOutputStream(targetStream));
  victim.doFilter(mockRequest,mockResponse,mockFilterChain);
  verify(mockFilterChain,Mockito.never()).doFilter(mockRequest,mockResponse);
  verify(mockFilterChain,Mockito.times(1)).doFilter(Mockito.eq(mockRequest),Mockito.any(HttpServletResponse.class));
  final InputStream ungzippedStream=new GZIPInputStream(new ByteArrayInputStream(targetStream.toByteArray()));
  assertEquals(content,IOUtils.toString(ungzippedStream));
}
