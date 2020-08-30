package com.amf.registration.portlet.action;

import com.amf.registration.constants.AMFRegistrationPortletKeys;
import com.amf.registration.constants.MVCCommandNames;
import com.amf.registration.validator.AMFRegistrationException;
import com.amf.registration.validator.AMFRegistrationValidator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.*;
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
import java.util.List;
import java.util.Optional;

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
        boolean male = ParamUtil.getBoolean(actionRequest, "male", false);
        final String password1 = ParamUtil.getString(actionRequest, "password1");
        final String password2 = ParamUtil.getString(actionRequest, "password2");
        int birthdayMonth = ParamUtil.getInteger(
                actionRequest, "birthdayMonth");
        int birthdayDay = ParamUtil.getInteger(actionRequest, "birthdayDay");
        int birthdayYear = ParamUtil.getInteger(actionRequest, "birthdayYear");

        final String reminderQueryQuestion = ParamUtil.getString(actionRequest, "security_question");
        final String reminderQueryAnswer = ParamUtil.getString(actionRequest, "security_answer");

        ServiceContext serviceContext = ServiceContextFactory.getInstance(
                User.class.getName(), actionRequest);

        AMFRegistrationValidator.validateBasicInfo( firstName,  lastName,  userName,  email,  birthdayDay,
         birthdayMonth,  birthdayYear,  password1,  password2);

        final User user = _userService.addUser(company.getCompanyId(), false, password1, password2,
                false, userName, email, DEFAULT_INT, EMPTY_STRING,
                themeDisplay.getLocale(), firstName, EMPTY_STRING, lastName, DEFAULT_INT, DEFAULT_INT,
                male, birthdayMonth, birthdayDay, birthdayYear, EMPTY_STRING, null,
                null, null, null, true, serviceContext);

        _addReminder(reminderQueryQuestion, reminderQueryAnswer, user);

        _addPhoneNumber(actionRequest, serviceContext, user);

        _addAddress(actionRequest, serviceContext, user);

        _sendRedirect(actionRequest, actionResponse, themeDisplay, user);
    }

    private void _addReminder(String reminderQueryQuestion, String reminderQueryAnswer, User user) throws AMFRegistrationException {
        AMFRegistrationValidator.validateIsNull(reminderQueryAnswer, "Reminder answer");

        user.setReminderQueryQuestion(reminderQueryQuestion);
        user.setReminderQueryAnswer(reminderQueryAnswer);
        _userLocalService.updateUser(user);
    }

    private void _addPhoneNumber(ActionRequest actionRequest, ServiceContext serviceContext, User user) throws PortalException, AMFRegistrationException {
        final String homePhone = ParamUtil.getString(actionRequest, "home_phone");
        final String mobilePhone = ParamUtil.getString(actionRequest, "mobile_phone");

        AMFRegistrationValidator.validatePhoneNumber(homePhone);
        AMFRegistrationValidator.validatePhoneNumber(mobilePhone);

        if (Validator.isNotNull(homePhone)) {
            _phoneLocalService.addPhone(user.getUserId(), Contact.class.getName(), user.getContactId(), homePhone,
                    null, _getPhoneListTypeId("other"), false, serviceContext);
        }
        if (Validator.isNotNull(mobilePhone)) {
            _phoneLocalService.addPhone(user.getUserId(), Contact.class.getName(), user.getContactId(), homePhone,
                    null, _getPhoneListTypeId("mobile-phone"), false, serviceContext);
        }
    }

    private void _addAddress(ActionRequest actionRequest, ServiceContext serviceContext, User user) throws PortalException {
        final String address = ParamUtil.getString(actionRequest, "address");
        final String address2 = ParamUtil.getString(actionRequest, "address2");
        final String city = ParamUtil.getString(actionRequest, "city");
        final int state = ParamUtil.getInteger(actionRequest, "state");
        final String zipcode = ParamUtil.getString(actionRequest, "zip");

        final long addressBusinessType = _getAddressListTypeId("business");

        _addressLocalService.addAddress(user.getUserId(), Contact.class.getName(), user.getContactId(), address, address2,
                EMPTY_STRING, city, zipcode, state, USA_COUNTRY_ID, addressBusinessType, false, false, serviceContext);
    }

    private long _getPhoneListTypeId(String phoneTypeName) {
        List<ListType> listTypes =
                _listTypeLocalService.getListTypes(ListTypeConstants.CONTACT_PHONE);
        final Optional<ListType> result = listTypes.stream().filter(e -> phoneTypeName.equals(e.getName())).findFirst();
        return result.map(ListTypeModel::getListTypeId).orElse(0L);
    }

    private long _getAddressListTypeId(String addressType) {
        List<ListType> listTypes =
                _listTypeLocalService.getListTypes(
                        Contact.class.getName() + ListTypeConstants.ADDRESS);
        final Optional<ListType> result = listTypes.stream().filter(e -> addressType.equals(e.getName())).findFirst();
        return result.map(ListTypeModel::getListTypeId).orElse(0L);
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


    private static final String EMPTY_STRING = "";

    private static final int DEFAULT_INT = 0;

    private static final int USA_COUNTRY_ID = 19;

    @Reference
    private UserService _userService;

    @Reference
    private UserLocalService _userLocalService;

    @Reference
    private Portal _portal;

    @Reference
    private PhoneLocalService _phoneLocalService;

    @Reference
    private ListTypeLocalService _listTypeLocalService;

    @Reference
    private AddressLocalService _addressLocalService;
}
