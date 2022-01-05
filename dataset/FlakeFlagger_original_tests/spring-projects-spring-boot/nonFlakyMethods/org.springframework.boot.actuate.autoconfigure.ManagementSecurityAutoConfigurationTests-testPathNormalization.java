@Test public void testPathNormalization() throws Exception {
  String path="admin/./error";
  assertEquals("admin/error",StringUtils.cleanPath(path));
}
