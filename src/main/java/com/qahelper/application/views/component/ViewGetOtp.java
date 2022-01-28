package com.qahelper.application.views.component;

import com.qahelper.application.Constant;
import com.qahelper.application.data.database.DbOperation;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.apache.commons.lang3.StringUtils;

public class ViewGetOtp extends HorizontalLayout {

    private Label titleLbl;
    private Label resultTextLbl;
    private TextField msisdnTf;
    private Button getOTPBtn;
    private Label resultLbl;

    public ViewGetOtp() {
        titleLbl = new Label("Get OTP pin :");
        resultTextLbl = new Label("Result :");
        msisdnTf = new TextField();
        msisdnTf.setPlaceholder("Eg: 0172430001");
        msisdnTf.setLabel("Insert MSISDN :");
        getOTPBtn = new Button("Submit");
        resultLbl = new Label();

        HorizontalLayout mainHL = new HorizontalLayout(msisdnTf, getOTPBtn);
        mainHL.setVerticalComponentAlignment(Alignment.END, getOTPBtn);

        HorizontalLayout resultHL = new HorizontalLayout(resultTextLbl, resultLbl);

        getOTPBtn.addClickListener(e -> {
            DbOperation test = new DbOperation();
            if(StringUtils.isNotBlank(msisdnTf.getValue())) {
                String result = test.getCustomerOtp(Constant.MY_MOBILE_PREFIXES+ msisdnTf.getValue());
                resultLbl.setText(result);
            }else {
                Notification.show("Please enter your msisdn !", 1000, Notification.Position.MIDDLE);
            }
        });

        VerticalLayout mainVL = new VerticalLayout();
        mainVL.add(titleLbl, mainHL, resultHL);
        add(mainVL);
    }
}
