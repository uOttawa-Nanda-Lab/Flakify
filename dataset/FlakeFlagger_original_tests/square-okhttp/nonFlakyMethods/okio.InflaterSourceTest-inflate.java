@Test public void inflate() throws Exception {
  OkBuffer deflated=decodeBase64("eJxzz09RyEjNKVAoLdZRKE9VL0pVyMxTKMlIVchIzEspVshPU0jNS8/MS00tK" + "tYDAF6CD5s=");
  OkBuffer inflated=inflate(deflated);
  assertEquals("God help us, we're in the hands of engineers.",readUtf8(inflated));
}
