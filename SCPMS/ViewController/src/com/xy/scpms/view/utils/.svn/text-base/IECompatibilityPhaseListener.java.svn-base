package com.xy.scpms.view.utils;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.adf.controller.v2.lifecycle.Lifecycle;

import oracle.adf.controller.v2.lifecycle.PagePhaseEvent;
import oracle.adf.controller.v2.lifecycle.PagePhaseListener;

import org.apache.myfaces.trinidad.context.Agent;
import org.apache.myfaces.trinidad.context.RequestContext;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

public class IECompatibilityPhaseListener implements PagePhaseListener {

    //defining unique session keys to test browser checking and version
    //for user sessions
    private final String BROWSER_CHECK_KEY = "____$browser-compatibility-checked-key$____";

    private final String IS_IE9_CHECK_KEY = "____$IS-IE9-check-key$____";

    public IECompatibilityPhaseListener() {
        super();
    }

    //    public void afterPhase(PhaseEvent phaseEvent) {
    //
    //        //check if browser is IE 9 in RestoreView phase if this
    //        //hasn't been checked before
    //        if (phaseEvent.getPhaseId() == PhaseId.RESTORE_VIEW && !this.isBrowserChecked()) {
    //            //which browser does the user use
    //            RequestContext trinidadContext = RequestContext.getCurrentInstance();
    //            Agent agent = trinidadContext.getAgent();
    //            String browserName = agent.getAgentName();
    //            String browserVersion = agent.getAgentVersion();
    //            //is it IE
    //            if (browserName.toLowerCase().indexOf("ie") > -1) {
    //                if (browserVersion.equalsIgnoreCase("9.0")) {
    //                    this.setIsIE9(true);
    //                } else {
    //                    this.setIsIE9(false);
    //                }
    //            } else {
    //                this.setIsIE9(false);
    //            }
    //            //browser has been checked
    //            this.setBrowserChecked(true);
    //        }
    //    }
    //
    //    public void beforePhase(PhaseEvent phaseEvent) {
    //        //check render response
    //        if (phaseEvent.getPhaseId() == PhaseId.RENDER_RESPONSE && this.isIsIE9()) {
    //            FacesContext fctx = FacesContext.getCurrentInstance();
    //            ExternalContext ectx = fctx.getExternalContext();
    //            HttpServletResponse response = (HttpServletResponse)ectx.getResponse();
    //            response.addHeader("X-UA-Compatible", "IE=8");
    //        }
    //    }

    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }

    //    private void setIsIE9(boolean isIE9) {
    //        setBooleanSessionKeyValue(IS_IE9_CHECK_KEY, isIE9);
    //    }
    //
    //    private boolean isIsIE9() {
    //        return getBooleanSessionKeyValue(IS_IE9_CHECK_KEY);
    //    }
    //
    //    private void setBrowserChecked(boolean browserChecked) {
    //        setBooleanSessionKeyValue(BROWSER_CHECK_KEY, new Boolean(browserChecked));
    //    }
    //
    //    private boolean isBrowserChecked() {
    //        return getBooleanSessionKeyValue(BROWSER_CHECK_KEY);
    //    }

    //    private boolean getBooleanSessionKeyValue(String _key) {
    //        FacesContext fctx = FacesContext.getCurrentInstance();
    //        ExternalContext ectx = fctx.getExternalContext();
    //        //get user session
    //        HttpSession userSession = (HttpSession)ectx.getSession(true);
    //        Object browserCheckObject = userSession.getAttribute(_key);
    //        if (browserCheckObject == null) {
    //            return false;
    //        } else {
    //            return ((Boolean)browserCheckObject).booleanValue();
    //        }
    //    }
    //
    //    private void setBooleanSessionKeyValue(String _key, Object _value) {
    //        FacesContext fctx = FacesContext.getCurrentInstance();
    //        ExternalContext ectx = fctx.getExternalContext();
    //        //get user session
    //        HttpSession userSession = (HttpSession)ectx.getSession(true);
    //        userSession.setAttribute(_key, _value);
    //    }

    @Override
    public void afterPhase(PagePhaseEvent pagePhaseEvent) {

        if (pagePhaseEvent.getPhaseId() == Lifecycle.PREPARE_RENDER_ID) {
//            System.out.println("here");
            String myAppName = "contract";
            FacesContext facesCtx = FacesContext.getCurrentInstance();
            ExternalContext extCtx = facesCtx.getExternalContext();
            HttpSession session = (HttpSession)extCtx.getSession(false);
            if (session != null) {
                int sessTimeout = session.getMaxInactiveInterval();

                if (sessTimeout > 0) {
                    HttpServletRequest req = (HttpServletRequest)extCtx.getRequest();
                    String appURL =
                        "http://" + req.getServerName() + ":" + req.getServerPort() + "/" + myAppName + "/faces/Home";
                    sessTimeout += 2;
                    String js = "var t;\n" + 
                    "function doredirect(){\n" + 
                    "	window.location.href='"+appURL+"';\n" + 
                    "}\n" +                     
                    "t=setTimeout(\"doredirect()\","+sessTimeout * 1000+");"; 
//                    String js = "alert('test');";
                    ExtendedRenderKitService eks =
                        Service.getRenderKitService(facesCtx, ExtendedRenderKitService.class);
                    eks.addScript(facesCtx, js);

                }
            }

        }
    }

    @Override
    public void beforePhase(PagePhaseEvent pagePhaseEvent) {
    }
}


