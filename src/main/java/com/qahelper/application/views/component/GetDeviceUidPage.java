package com.qahelper.application.views.component;

import com.qahelper.application.Constant;
import com.qahelper.application.data.database.DbOperation;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class GetDeviceUidPage extends HorizontalLayout {

    private Label titleLbl;
    private Label resultTextLbl;
    private TextField msisdnTf;
    private Button getUIDBtn;
    private Button incLimitBtn;
    private Label resultLbl;
    private ComboBox platformCB;

    public GetDeviceUidPage(){

        List<String> platformAL = new ArrayList<>();
        platformAL.add("Android");
        platformAL.add("IOS");

        platformCB = new ComboBox<String>();
        platformCB.setItems(platformAL);
        platformCB.setLabel("Select platform :");
        platformCB.setAllowCustomValue(false);

        titleLbl = new Label("Get device UID :");
        resultTextLbl = new Label("Result :");
        msisdnTf = new TextField();
        msisdnTf.setPlaceholder("Eg: 0172430001");
        msisdnTf.setLabel("Insert MSISDN :");
        getUIDBtn = new Button("Get UID");
        incLimitBtn = new Button("Increase reg limit");
        resultLbl = new Label();

        HorizontalLayout mainHL = new HorizontalLayout(platformCB, msisdnTf, getUIDBtn, incLimitBtn);
        mainHL.setVerticalComponentAlignment(Alignment.END, getUIDBtn);
        mainHL.setVerticalComponentAlignment(Alignment.END, incLimitBtn);

        HorizontalLayout resultHL = new HorizontalLayout(resultTextLbl, resultLbl);

        getUIDBtn.addClickListener(e -> {
            String result = platformFunc((String) platformCB.getValue());
            resultLbl.setText(result);
        });

        incLimitBtn.addClickListener(e -> {
            String result = platformFunc((String) platformCB.getValue());

            DbOperation test = new DbOperation();
            String result2 = test.updateRegLimit(result);

            resultLbl.setText(result2);
        });

        VerticalLayout mainVL = new VerticalLayout();
        mainVL.add(titleLbl, mainHL, resultHL);
        add(mainVL);
    }

    private String platformFunc(String platform){
        DbOperation dbOps = new DbOperation();
        String result = "";

        switch (platform) {
            case "Android":
                if(StringUtils.isNotBlank(msisdnTf.getValue())) {
                    result = dbOps.getAndroidUid(Constant.MY_MOBILE_PREFIXES+ msisdnTf.getValue());
                }else {
                    Notification.show("Please enter your msisdn !", 1000, Notification.Position.MIDDLE);
                }

                break;
            case "IOS":
                if(StringUtils.isNotBlank(msisdnTf.getValue())) {
                    String customerId = dbOps.getCustomerId(Constant.MY_MOBILE_PREFIXES+ msisdnTf.getValue());
                    result = dbOps.getIosUid(customerId);
                }else {
                    Notification.show("Please enter your msisdn !", 1000, Notification.Position.MIDDLE);
                }
                break;
            default:
                Notification.show("Please select your platform !", 1000, Notification.Position.MIDDLE);
        }

        return result;
    }
}
