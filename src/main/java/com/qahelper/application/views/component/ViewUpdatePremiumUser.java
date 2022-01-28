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

public class ViewUpdatePremiumUser extends HorizontalLayout {

    private Label titleLbl;
    private Label resultTextLbl;
    private TextField msisdnTf;
    private Button upgradeBtn;
    private Label resultLbl;

    public ViewUpdatePremiumUser() {
        titleLbl = new Label("Upgrade to premium :");
        resultTextLbl = new Label("Result :");
        resultLbl = new Label();
        msisdnTf = new TextField();
        msisdnTf.setPlaceholder("Eg: 0172430001");
        msisdnTf.setLabel("Insert MSISDN :");
        upgradeBtn = new Button("Submit");

        HorizontalLayout mainHL = new HorizontalLayout(msisdnTf, upgradeBtn);
        mainHL.setVerticalComponentAlignment(Alignment.END, upgradeBtn);

        HorizontalLayout resultHL = new HorizontalLayout(resultTextLbl, resultLbl);

        upgradeBtn.addClickListener(e -> {
            DbOperation test = new DbOperation();
            if(StringUtils.isNotBlank(msisdnTf.getValue())) {
                String custID = test.getCustomerId(Constant.MY_MOBILE_PREFIXES+ msisdnTf.getValue());

                //Todo - Continue upgradePremium function in DbOperation.java

                //resultLbl.setText(result);
            }else {
                Notification.show("Please enter your msisdn !", 1000, Notification.Position.MIDDLE);
            }
        });

        VerticalLayout mainVL = new VerticalLayout();
        mainVL.add(titleLbl, mainHL, resultHL);
        add(mainVL);
    }
}
