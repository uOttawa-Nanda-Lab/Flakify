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

  @Test public void testCreateInstance(){UUID id=UUID.randomUUID();long longProp1=1L;Long longProp2=2L;int intProp1=3;Integer intProp2=4;boolean boolProp1=false;Boolean boolProp2=true;String strProp="a string property";UUID uuidProp=UUID.randomUUID();Date dateProp=new Date();byte[] bytesProp="somebytes".getBytes();String extraProp="extra extra";Colors color=Colors.RED;MySerial serialProp=new MySerial(1,2L);ColumnSliceMockImpl slice=new ColumnSliceMockImpl();slice.add("lp1",LongSerializer.get().toBytes(longProp1));slice.add("lp2",LongSerializer.get().toBytes(longProp2));slice.add("ip1",IntegerSerializer.get().toBytes(intProp1));slice.add("ip2",IntegerSerializer.get().toBytes(intProp2));slice.add("bp1",BooleanSerializer.get().toBytes(boolProp1));slice.add("bp2",BooleanSerializer.get().toBytes(boolProp2));slice.add("sp",StringSerializer.get().toBytes(strProp));slice.add("up",UUIDSerializer.get().toBytes(uuidProp));slice.add("dp",DateSerializer.get().toBytes(dateProp));slice.add("bytes",BytesArraySerializer.get().toBytes(bytesProp));slice.add("color",StringSerializer.get().toBytes(color.getName()));slice.add("serialProp",ObjectSerializer.get().toBytes(serialProp));slice.add("extra",StringSerializer.get().toBytes(extraProp));CFMappingDef<MyTestBean, UUID> cfMapDef=cacheMgr.getCfMapDef(MyTestBean.class,true);MyTestBean obj=new HectorObjectMapper(cacheMgr).createObject(cfMapDef,id,slice);assertEquals(id,obj.getBaseId());assertEquals(longProp1,obj.getLongProp1());assertEquals(longProp2,obj.getLongProp2());assertEquals(intProp1,obj.getIntProp1());assertEquals(intProp2,obj.getIntProp2());assertFalse(obj.isBoolProp1());assertTrue(obj.getBoolProp2());assertEquals(strProp,obj.getStrProp());assertEquals(uuidProp,obj.getUuidProp());assertEquals("somebytes",new String(obj.getBytesProp()));assertEquals(dateProp.getTime(),obj.getDateProp().getTime());assertEquals(serialProp,obj.getSerialProp());assertEquals(extraProp,obj.getAnonymousProp("extra"));}

  

  // --------------------

  @Before
  public void setupTest() {
    cacheMgr.initializeCacheForClass(MyTestBean.class);
    cacheMgr.initializeCacheForClass(MyCustomIdBean.class);
  }
}
