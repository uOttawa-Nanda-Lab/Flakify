@Test public void testTransportGuaranteedAnnotation() throws Exception {
  performRequestsAndCheck("/" + TG_ANN + TransportGuaranteeAnnotatedServlet.servletContext);
}
