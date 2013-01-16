package com.xy.scpms.view.page;

import java.text.SimpleDateFormat;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class UrgeSettleBean {
    public UrgeSettleBean() {
        super();
    }
    
    public String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(date);
        return dateStr;
    }

    //Holds the contract_no of the last one we rendered
    String _breakGroupLastKey = "start";

    //The fake Map
    Map _breakGroupMap = new BreakGroupFakeMap();

    //Getter for the face map. No setter required

    public Map getBreakGroupMap() {
        
        return _breakGroupMap;
    }

    //Implementation of the fake map as an inner class

    public class BreakGroupFakeMap implements Map {

        @Override
        public Object get(Object key) {
            Boolean retValue = false;

            if (key instanceof String) {
                if (((String)key).equals(_breakGroupLastKey)) {
                    
                    retValue = true;
                } else {
                    retValue = false;
                    
                    _breakGroupLastKey = (String)key;

                }
            }
            
            return retValue;

        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean containsKey(Object key) {
            return false;
        }

        @Override
        public boolean containsValue(Object value) {
            return false;
        }

        @Override
        public Object put(Object key, Object value) {
            return null;
        }

        @Override
        public Object remove(Object key) {
            return null;
        }

        @Override
        public void putAll(Map m) {
        }

        @Override
        public void clear() {
        }

        @Override
        public Set keySet() {
            return Collections.emptySet();
        }

        @Override
        public Collection values() {
            return Collections.emptySet();
        }

        @Override
        public Set entrySet() {
            return Collections.emptySet();
        }

    }


}
