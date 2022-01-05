@Test public void inflateTruncated() throws Exception {
  OkBuffer deflated=decodeBase64("eJxzz09RyEjNKVAoLdZRKE9VL0pVyMxTKMlIVchIzEspVshPU0jNS8/MS00tK" + "tYDAF6CDw==");
  try {
    inflate(deflated);
    fail();
  }
 catch (  EOFException expected) {
  }
}
