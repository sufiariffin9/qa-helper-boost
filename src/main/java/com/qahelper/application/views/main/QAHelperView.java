package com.qahelper.application.views.main;

import com.qahelper.application.views.component.ViewGetCustId;
import com.qahelper.application.views.component.ViewGetDeviceUid;
import com.qahelper.application.views.component.ViewGetOtp;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.qahelper.application.views.MainLayout;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("QA Helper")
@Route(value = "hello", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@AnonymousAllowed
public class QAHelperView extends HorizontalLayout {

    public QAHelperView() {
        addClassName("hello-world-view");

        VerticalLayout mainVL = new VerticalLayout();

        //add GetCustIdPage into main vertical layout
        ViewGetCustId gci = new ViewGetCustId();
        mainVL.add(gci);

        //add GetOtpPage into main vertical layout
        ViewGetOtp gop = new ViewGetOtp();
        mainVL.add(gop);

        //add GetDeviceUidPage into main vertical layout
        ViewGetDeviceUid gdup = new ViewGetDeviceUid();
        mainVL.add(gdup);

        //add to main vertical layout into QAHelperView horizontal layout
        add(mainVL);
    }
}