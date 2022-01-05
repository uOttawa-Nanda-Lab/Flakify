@Test public void should_test() throws Exception {
  Pattern pattern=Pattern.compile("writing request [A-Z]+ .+\\[cl=");
  String text="TRACE [21:31:47,052][] com.datastax.driver.core.Connection@:write [localhost/127.0.0.1:9184-4] writing request EXECUTE 0xd138318ced105ea0cd75474c40cbe55c ([cl=ONE, vals=[java.nio.HeapByteBuffer[pos=0 lim=8 cap=8], java.nio.HeapByteBuffer[pos=0 lim=11 cap=12], java.nio.HeapByteBuffer[pos=0 lim=4 cap=4], java.nio.HeapByteBuffer[pos=0 lim=4 cap=4]], skip=false, psize=5000, state=null, serialCl=LOCAL_SERIAL])";
  assertThat(pattern.matcher(text).find()).isTrue();
}
