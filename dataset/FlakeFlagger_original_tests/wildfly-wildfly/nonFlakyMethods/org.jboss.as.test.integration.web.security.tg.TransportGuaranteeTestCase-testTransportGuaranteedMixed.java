@Test public void testTransportGuaranteedMixed() throws Exception {
  performRequestsAndCheck("/" + TG_MIXED + "/tg_mixed_override/srv");
}
