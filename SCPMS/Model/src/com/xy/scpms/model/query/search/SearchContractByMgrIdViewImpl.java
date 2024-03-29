package com.xy.scpms.model.query.search;


import com.xy.scpms.model.query.search.common.SearchContractByMgrIdView;

import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sun Jun 19 17:09:00 CST 2011
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class SearchContractByMgrIdViewImpl extends SearchContractBaseViewImpl implements SearchContractByMgrIdView {
    /**
     * This is the default constructor (do not remove).
     */
    public SearchContractByMgrIdViewImpl() {
    }

    /**
     * Returns the bind variable value for MgrIdBind.
     * @return bind variable value for MgrIdBind
     */
    public String getMgrIdBind() {
        return (String)getNamedWhereClauseParam("MgrIdBind");
    }

    /**
     * Sets <code>value</code> for bind variable MgrIdBind.
     * @param value value to bind as MgrIdBind
     */
    public void setMgrIdBind(String value) {
        setNamedWhereClauseParam("MgrIdBind", value);
    }
}
