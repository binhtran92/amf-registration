<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
int USA_COUNTRY_ID = 19;
%>

<portlet:actionURL name="<%= MVCCommandNames.SUBMIT %>" var="createUserAccountURL" />

<aui:form action="<%= createUserAccountURL %>" method="post" name="fm">
	<liferay-ui:error exception="<%= UserEmailAddressException.MustNotBeDuplicate.class %>" message="the-email-address-you-requested-is-already-taken" />
	<liferay-ui:error exception="<%= UserScreenNameException.MustNotBeDuplicate.class %>" message="the-screen-name-you-requested-is-already-taken" />
	<liferay-ui:error exception="<%= NoSuchRegionException.class %>" message="please-select-a-region" />
	<liferay-ui:error exception="<%= RegistrationValidationException.class %>">

	<%
		RegistrationValidationException rve = (RegistrationValidationException)errorException;
	%>

			<liferay-ui:message key="<%= rve.getMessage() %>" />
	</liferay-ui:error>

	<div class="form-group">
		<h3 class="sheet-subtitle">Basic Info</h3>

		<div class="row">
			<div class="col-md-5">
				<aui:input autocomplete="off" label="Username" name="username">
					<aui:validator name="required" />
					<aui:validator name="maxLength">16</aui:validator>
					<aui:validator name="minLength">4</aui:validator>
					<aui:validator name="alphanum" />
				</aui:input>

				<aui:input autocomplete="off" label="Email Address" name="email_address">
				<aui:validator name="email" />
				<aui:validator name="maxLength">255</aui:validator>
				<aui:validator name="required" />
				</aui:input>

				<aui:input label="First Name" name="first_name">
					<aui:validator name="maxLength">50</aui:validator>
					<aui:validator name="required" />
					<aui:validator name="alphanum" />
				</aui:input>

				<aui:input label="Last Name" name="last_name">
					<aui:validator name="maxLength">50</aui:validator>
					<aui:validator name="required" />
					<aui:validator name="alphanum" />
				</aui:input>
			</div>

			<div class="col-md-5">
				<aui:input label="Password" name="password1" type="password">
					<aui:validator name="required" />

					<aui:validator errorMessage="invalid-password-policy" name="custom">
						function(val, fieldNode, ruleValue) {
							var regex = new RegExp(/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[^\w\s]).{6,}$/);
							return regex.test(val);
						}
					</aui:validator>
				</aui:input>

				<aui:input label="Confirm Password" name="password2" type="password">
					<aui:validator name="required" />
					<aui:validator errorMessage="Password not match" name="equalTo">'#<portlet:namespace />password1'</aui:validator>
				</aui:input>

				<aui:input label="Birthday" model="<%= Contact.class %>" name="birthday" />
				<aui:input name="male" type="checkbox" />
			</div>
		</div>
	</div>

	<div class="form-group">
		<h3 class="sheet-subtitle">Phone</h3>

		<div class="row">
			<div class="col-md-5">
				<aui:input label="Home Phone" name="home_phone">
					<aui:validator name="digits" />
					<aui:validator name="maxLength">10</aui:validator>
					<aui:validator name="minLength">10</aui:validator>
				</aui:input>
			</div>

			<div class="col-md-5">
				<aui:input label="Mobile Phone" name="mobile_phone">
					<aui:validator name="digits" />
					<aui:validator name="maxLength">10</aui:validator>
					<aui:validator name="minLength">10</aui:validator>
				</aui:input>
			</div>
		</div>
	</div>

	<div class="form-group">
		<h3 class="sheet-subtitle">Billing Address (US-Only)</h3>

		<div class="row">
			<div class="col-md-5">
				<aui:input label="Address 1" name="address">
					<aui:validator name="required" />
					<aui:validator name="maxLength">255</aui:validator>
				</aui:input>

				<aui:input label="Address 2" name="address2">
					<aui:validator name="maxLength">255</aui:validator>
				</aui:input>

				<aui:input label="City" name="city">
					<aui:validator name="maxLength">255</aui:validator>
					<aui:validator name="required" />
				</aui:input>

				<aui:select label="State" name="state" required="true"/>

				<aui:input label="Zip Code" name="zip">
					<aui:validator name="required" />
                    <aui:validator name="digits" />
					<aui:validator name="maxLength">5</aui:validator>
                    <aui:validator name="minLength">5</aui:validator>
				</aui:input>
			</div>

			<div class="col-md-5">
			</div>
		</div>
	</div>

	<div class="form-group">
		<h3 class="sheet-subtitle">Misc.</h3>

		<div class="row">
			<div class="col-md-5">
				<aui:select label="Security Question" name="security_question">

					<%
					Set<String> questions = new HashSet<>();

					questions.add("what-is-make-of-your-first-car");
					questions.add("what-is-your-high-school-mascot");
					questions.add("what-is-your-mother-maiden-name");
					questions.add("who-is-your-favorite-actor");

					for (String question : questions) {
					%>

						<aui:option label="<%= question %>" />

					<%
					}
					%>

				</aui:select>

				<aui:input label="Answer" name="security_answer">
					<aui:validator name="required" />
				</aui:input>
			</div>
		</div>
	</div>

	<div class="form-group">
		<div class="row">
			<aui:input
				label="terms-of-use"
				name="accepted_tou"
				type="checkbox"
			>
				<aui:validator name="required" />
			</aui:input>
		</div>
	</div>

	<aui:button-row>
		<aui:button type="submit" value="Create" />
	</aui:button-row>

	<aui:script use="liferay-dynamic-select">
			new Liferay.DynamicSelect([
				{
					selectData: Liferay.Address.getCountries,
					selectDesc: 'nameCurrentValue',
					selectSort: true,
					selectId: 'countryId',
					selectVal: '<%= USA_COUNTRY_ID %>'
				},
				{
					select: '<portlet:namespace />state',
					selectData: Liferay.Address.getRegions,
					selectDesc: 'name',
					selectSort: true,
					selectId: 'regionId',
				}
			]);
		</aui:script>

</aui:form>

<aui:script use="aui-form-validator, aui-overlay-context-panel">

var DEFAULTS_FORM_VALIDATOR = A.config.FormValidator;
var defaultRequired = DEFAULTS_FORM_VALIDATOR.RULES.required;

var required = function (val, node, ruleValue) {
    if (node.get('name') === '<portlet:namespace/>state' && node.get('value') == 0) {
        return false;
    }
    return defaultRequired(val, node, ruleValue);
};

 A.mix(
    DEFAULTS_FORM_VALIDATOR.RULES, {
        required: required
    },
    true);
</aui:script>