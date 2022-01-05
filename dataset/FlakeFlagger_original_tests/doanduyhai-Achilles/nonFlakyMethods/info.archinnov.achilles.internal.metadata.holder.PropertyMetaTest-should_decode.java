@Test public void should_decode() throws Exception {
  PropertyMeta pm=new PropertyMeta();
  pm.setTranscoder(transcoder);
  assertThat(pm.decode((Object)null)).isNull();
  assertThat(pm.decodeKey((Object)null)).isNull();
  assertThat(pm.decode((List<?>)null)).isNull();
  assertThat(pm.decode((Set<?>)null)).isNull();
  assertThat(pm.decode((Map<?,?>)null)).isNull();
  assertThat(pm.decodeFromComponents((List<?>)null)).isNull();
  Object value="";
  List<Object> list=new ArrayList<Object>();
  Set<Object> set=new HashSet<Object>();
  Map<Object,Object> map=new HashMap<Object,Object>();
  when(transcoder.decode(pm,value)).thenReturn(value);
  when(transcoder.decodeKey(pm,value)).thenReturn(value);
  when(transcoder.decode(pm,list)).thenReturn(list);
  when(transcoder.decode(pm,set)).thenReturn(set);
  when(transcoder.decode(pm,map)).thenReturn(map);
  when(transcoder.decodeFromComponents(pm,list)).thenReturn(list);
  assertThat(pm.decode(value)).isEqualTo(value);
  assertThat(pm.decodeKey(value)).isEqualTo(value);
  assertThat(pm.decode(list)).isEqualTo(list);
  assertThat(pm.decode(set)).isEqualTo(set);
  assertThat(pm.decode(map)).isEqualTo(map);
  assertThat(pm.decodeFromComponents(list)).isEqualTo(list);
}
