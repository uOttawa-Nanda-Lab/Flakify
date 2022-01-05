@Test public void should_encode() throws Exception {
  PropertyMeta pm=new PropertyMeta();
  pm.setTranscoder(transcoder);
  assertThat(pm.encode((Object)null)).isNull();
  assertThat(pm.encodeKey((Object)null)).isNull();
  assertThat(pm.encode((List<?>)null)).isNull();
  assertThat(pm.encode((Set<?>)null)).isNull();
  assertThat(pm.encode((Map<?,?>)null)).isNull();
  assertThat(pm.encodeToComponents((List<?>)null)).isNull();
  Object value="";
  List<Object> list=new ArrayList<Object>();
  Set<Object> set=new HashSet<Object>();
  Map<Object,Object> map=new HashMap<Object,Object>();
  when(transcoder.encode(pm,value)).thenReturn(value);
  when(transcoder.encodeKey(pm,value)).thenReturn(value);
  when(transcoder.encode(pm,list)).thenReturn(list);
  when(transcoder.encode(pm,set)).thenReturn(set);
  when(transcoder.encode(pm,map)).thenReturn(map);
  when(transcoder.encodeToComponents(pm,list)).thenReturn(list);
  when(transcoder.encodeToComponents(pm,list)).thenReturn(list);
  assertThat(pm.encode(value)).isEqualTo(value);
  assertThat(pm.encodeKey(value)).isEqualTo(value);
  assertThat(pm.encode(list)).isEqualTo(list);
  assertThat(pm.encode(set)).isEqualTo(set);
  assertThat(pm.encode(map)).isEqualTo(map);
  assertThat(pm.encodeToComponents(list)).isEqualTo(list);
  assertThat(pm.encodeToComponents(list)).isEqualTo(list);
}
