package me.prettyprint.hom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import me.prettyprint.cassandra.serializers.BooleanSerializer;
import me.prettyprint.cassandra.serializers.BytesArraySerializer;
import me.prettyprint.cassandra.serializers.DateSerializer;
import me.prettyprint.cassandra.serializers.IntegerSerializer;
import me.prettyprint.cassandra.serializers.LongSerializer;
import me.prettyprint.cassandra.serializers.ObjectSerializer;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.cassandra.serializers.UUIDSerializer;
import me.prettyprint.hector.api.beans.HColumn;
import me.prettyprint.hom.CFMappingDef;
import me.prettyprint.hom.ClassCacheMgr;
import me.prettyprint.hom.HectorObjectMapper;
import me.prettyprint.hom.beans.MyCustomIdBean;
import me.prettyprint.hom.beans.MyTestBean;

import org.junit.Before;
import org.junit.Test;

import com.mycompany.MySerial;


public class HectorObjectMapperTest {
  ClassCacheMgr cacheMgr = new ClassCacheMgr();

  @Test public void testCreateColumnSet(){MyTestBean obj=new MyTestBean();obj.setBaseId(UUID.randomUUID());obj.setLongProp1(111L);obj.setLongProp2(222L);obj.setIntProp1(333);obj.setIntProp2(444);obj.setBoolProp1(false);obj.setBoolProp2(true);obj.setStrProp("aStr");obj.setUuidProp(UUID.randomUUID());obj.setDateProp(new Date());obj.setBytesProp("somebytes".getBytes());obj.setColor(Colors.BLUE);obj.setSerialProp(new MySerial(1,2L));obj.addAnonymousProp("foo","bar");obj.addAnonymousProp("rice","beans");Map<String, HColumn<String, byte[]>> colMap=new HectorObjectMapper(cacheMgr).createColumnMap(obj);assertNull("id should not have been added to column collection",colMap.get("id"));assertEquals(obj.getLongProp1(),(long)LongSerializer.get().fromBytes(colMap.get("lp1").getValue()));assertEquals(obj.getLongProp2(),LongSerializer.get().fromBytes(colMap.get("lp2").getValue()));assertEquals(obj.getIntProp1(),(int)IntegerSerializer.get().fromBytes(colMap.get("ip1").getValue()));assertEquals(obj.getIntProp2(),IntegerSerializer.get().fromBytes(colMap.get("ip2").getValue()));assertEquals(obj.isBoolProp1(),BooleanSerializer.get().fromBytes(colMap.get("bp1").getValue()));assertEquals(obj.getBoolProp2(),BooleanSerializer.get().fromBytes(colMap.get("bp2").getValue()));assertEquals(obj.getStrProp(),StringSerializer.get().fromBytes(colMap.get("sp").getValue()));assertEquals(obj.getUuidProp(),UUIDSerializer.get().fromBytes(colMap.get("up").getValue()));assertEquals(obj.getDateProp(),DateSerializer.get().fromBytes(colMap.get("dp").getValue()));assertEquals("somebytes",new String(BytesArraySerializer.get().fromBytes(colMap.get("bytes").getValue())));assertEquals(obj.getSerialProp(),ObjectSerializer.get().fromBytes(colMap.get("serialProp").getValue()));assertEquals(2,obj.getAnonymousProps().size());assertEquals(obj.getAnonymousProp("foo"),StringSerializer.get().fromBytes(colMap.get("foo").getValue()));assertEquals(obj.getAnonymousProp("rice"),StringSerializer.get().fromBytes(colMap.get("rice").getValue()));}

  

  // --------------------

  @Before
  public void setupTest() {
    cacheMgr.initializeCacheForClass(MyTestBean.class);
    cacheMgr.initializeCacheForClass(MyCustomIdBean.class);
  }
}
