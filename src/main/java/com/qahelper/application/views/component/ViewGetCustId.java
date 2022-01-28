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

public class ViewGetCustId extends HorizontalLayout {

    private Label titleLbl;
    private Label resultTextLbl;
    private TextField msisdnTf;
    private Button findCustomerIdBtn;
    private Label resultLbl;

    public ViewGetCustId(){
        titleLbl = new Label("Get customer ID :");
        resultTextLbl = new Label("Result :");
        msisdnTf = new TextField();
        msisdnTf.setPlaceholder("Eg: 0172430001");
        msisdnTf.setLabel("Insert MSISDN :");
        findCustomerIdBtn = new Button("Submit");
        resultLbl = new Label();

        HorizontalLayout mainHL = new HorizontalLayout(msisdnTf, findCustomerIdBtn);
        mainHL.setVerticalComponentAlignment(Alignment.END, findCustomerIdBtn);

        HorizontalLayout resultHL = new HorizontalLayout(resultTextLbl, resultLbl);

        findCustomerIdBtn.addClickListener(e -> {
            DbOperation test = new DbOperation();
            if(StringUtils.isNotBlank(msisdnTf.getValue())) {
                String result = test.getCustomerId(Constant.MY_MOBILE_PREFIXES+ msisdnTf.getValue());
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
