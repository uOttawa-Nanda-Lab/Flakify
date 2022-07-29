package me.prettyprint.hom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.persistence.DiscriminatorType;

import me.prettyprint.hom.badbeans.MyBadTestBean;
import me.prettyprint.hom.badbeans.MyMissingIdSetterBean;
import me.prettyprint.hom.beans.MyBlueTestBean;
import me.prettyprint.hom.beans.MyPurpleTestBean;
import me.prettyprint.hom.beans.MyRedTestBean;
import me.prettyprint.hom.beans.MyTestBean;
import me.prettyprint.hom.dupebean.MyDupeCF1;
import me.prettyprint.hom.dupebean.MyDupeCF2;

import org.junit.Ignore;
import org.junit.Test;

import com.mycompany.furniture.Desk;
import com.mycompany.furniture.Furniture;

public class ClassCacheMgrTest {

  // create an anonymous class .. don't know another way
  @SuppressWarnings("serial")
  Map<Long, MyTestBean> tmplMap = new HashMap<Long, MyTestBean>() {
    {
      put(1L, new MyTestBean() {
        {
          setBaseId(UUID.randomUUID());
          setIntProp1(1);
        }
      });

      put(2L, new NewBean() {
        {
          setBaseId(UUID.randomUUID());
          setIntProp1(1);
        }
      });
    }
  };

  @Test public void testGetColFamMapDefByClassAnonymousClassOK(){ClassCacheMgr cacheMgr=new ClassCacheMgr();cacheMgr.initializeCacheForClass(tmplMap.get(1L).getClass());assertTrue(tmplMap.get(1L).getClass().isAnonymousClass());CFMappingDef<?, Long> cfMapDef=cacheMgr.getCfMapDef(tmplMap.get(1L).getClass(),false);assertNotNull(cfMapDef);assertEquals(MyTestBean.class,cfMapDef.getClazz());}
}

// --------------

class NewBean extends MyTestBean {

}
