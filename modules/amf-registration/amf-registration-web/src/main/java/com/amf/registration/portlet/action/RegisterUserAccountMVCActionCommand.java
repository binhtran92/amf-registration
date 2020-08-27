package com.amf.registration.portlet.action;

import com.amf.registration.constants.AMFRegistrationPortletKeys;
import com.amf.registration.constants.MVCCommandNames;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + AMFRegistrationPortletKeys.AMFREGISTRATION,
                "mvc.command.name=" + MVCCommandNames.SUBMIT
        },
        service = MVCActionCommand.class
)
public class RegisterUserAccountMVCActionCommand extends BaseMVCActionCommand {
    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

    }
}
