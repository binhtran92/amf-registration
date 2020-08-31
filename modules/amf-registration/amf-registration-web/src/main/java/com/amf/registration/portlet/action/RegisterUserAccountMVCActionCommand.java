package com.amf.registration.portlet.action;

import com.amf.registration.constants.AMFRegistrationPortletKeys;
import com.amf.registration.constants.MVCCommandNames;
import com.amf.registration.model.UserExtraInfo;
import com.amf.registration.service.UserExtraInfoLocalService;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.*;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.*;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

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
        try {
            _createUserAccount(actionRequest, actionResponse);
        } catch (Exception exception) {
            SessionErrors.add(
                    actionRequest, exception.getClass(), exception);
        }
    }

    private void _createUserAccount(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(
                WebKeys.THEME_DISPLAY);

        Company company = themeDisplay.getCompany();

        final String firstName = ParamUtil.getString(actionRequest, "first_name");
        final String lastName = ParamUtil.getString(actionRequest, "last_name");
        final String userName = ParamUtil.getString(actionRequest, "username");
        final String email = ParamUtil.getString(actionRequest, "email_address");
        boolean isMale = ParamUtil.getBoolean(actionRequest, "male", false);
        final String password1 = ParamUtil.getString(actionRequest, "password1");
        final String password2 = ParamUtil.getString(actionRequest, "password2");
        int birthdayMonth = ParamUtil.getInteger(
                actionRequest, "birthdayMonth");
        int birthdayDay = ParamUtil.getInteger(actionRequest, "birthdayDay");
        int birthdayYear = ParamUtil.getInteger(actionRequest, "birthdayYear");
        final String homePhone = ParamUtil.getString(actionRequest, "home_phone");
        final String mobilePhone = ParamUtil.getString(actionRequest, "mobile_phone");

        final String address = ParamUtil.getString(actionRequest, "address");
        final String address2 = ParamUtil.getString(actionRequest, "address2");
        final String city = ParamUtil.getString(actionRequest, "city");
        final int state = ParamUtil.getInteger(actionRequest, "state");
        final String zipcode = ParamUtil.getString(actionRequest, "zip");

        final String reminderQueryQuestion = ParamUtil.getString(actionRequest, "security_question");
        final String reminderQueryAnswer = ParamUtil.getString(actionRequest, "security_answer");

        ServiceContext serviceContext = ServiceContextFactory.getInstance(
                User.class.getName(), actionRequest);

        final User user = _userExtraInfoLocalService.addUserAndExtraInfo(company.getCompanyId(), password1, password2, userName,
                email, firstName, lastName, isMale,
                mobilePhone, homePhone, birthdayMonth, birthdayDay,
                birthdayYear, address, address2, city, state,
                zipcode, reminderQueryQuestion, reminderQueryAnswer,
                serviceContext);

        _sendRedirect(actionRequest, actionResponse, themeDisplay, user);
    }


    private void _sendRedirect(
            ActionRequest actionRequest, ActionResponse actionResponse,
            ThemeDisplay themeDisplay, User user)
            throws Exception {

        String login;

        Company company = themeDisplay.getCompany();

        String authType = company.getAuthType();

        if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
            login = String.valueOf(user.getUserId());
        } else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
            login = user.getScreenName();
        } else {
            login = user.getEmailAddress();
        }

        HttpServletRequest httpServletRequest = _portal.getHttpServletRequest(
                actionRequest);

        PortletURL loginURL = _getLoginURL(
                httpServletRequest, themeDisplay.getPlid());

        loginURL.setParameter("login", login);
        SessionMessages.add(httpServletRequest, "userAdded");
        actionResponse.sendRedirect(loginURL.toString());
    }

    private PortletURL _getLoginURL(
            HttpServletRequest httpServletRequest, long plid)
            throws PortletModeException, WindowStateException {

        PortletURL portletURL = PortletURLFactoryUtil.create(
                httpServletRequest, PortletKeys.LOGIN, plid,
                PortletRequest.RENDER_PHASE);

        portletURL.addProperty("saveLastPath", Boolean.FALSE.toString());
        portletURL.addProperty("mvcRenderCommandName", "/login/login");
        portletURL.setPortletMode(PortletMode.VIEW);
        portletURL.setWindowState(WindowState.MAXIMIZED);

        return portletURL;
    }


    @Reference
    private Portal _portal;

    @Reference
    private UserExtraInfoLocalService _userExtraInfoLocalService;

}
