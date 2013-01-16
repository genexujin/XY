package com.xy.scpms.model.common;

import oracle.jbo.server.ViewDefImpl;
import oracle.jbo.server.ViewObjectImpl;

public class NoAutoQueryView extends ViewObjectImpl {
    
    
    
    public NoAutoQueryView(String string, ViewDefImpl viewDefImpl) {
        super(string, viewDefImpl);
    }

    public NoAutoQueryView() {
        super();
    }
        
    public void clearRowSet(){
        executeEmptyRowSet();
    }
    
}
