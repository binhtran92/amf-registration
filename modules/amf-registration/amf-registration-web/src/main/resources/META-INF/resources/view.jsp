<%@ include file="/init.jsp" %>

<portlet:actionURL name="<%=MVCCommandNames.SUBMIT %>" var="createUserAccountURL" />
<aui:form action="<%= createUserAccountURL %>" method="post" name="fm" >
    <div class="form-group">
        <h3 class="sheet-subtitle">Basic Info</h3>
        <div class="row">
            <div class="col-md-5">
                <aui:input name="username" label="Username" autocomplete="off"/>
                <aui:input name="email_address" label="Email Address" autocomplete="off"/>
                <aui:input name="first_name" label="First Name"/>
                <aui:input name="last_name" label="Last Name"/>
            </div>
            <div class="col-md-5">
                <aui:input name="password1" label="Password" type="password"/>
                <aui:input name="password2" label="Confirm Password" type="password"/>
                <aui:input name="birthday" label="Birthday" model="<%= Contact.class %>"/>
                <aui:input name="male" type="checkbox" />
            </div>
        </div>
    </div>
    <div class="form-group">
        <h3 class="sheet-subtitle">Phone</h3>
        <div class="row">
            <div class="col-md-5">
                <aui:input name="home_phone" label="Home Phone"/>
            </div>
            <div class="col-md-5">
                <aui:input name="mobile_phone" label="Mobile Phone"/>
            </div>
        </div>
    </div>
    <div class="form-group">
        <h3 class="sheet-subtitle">Billing Address (US-Only)</h3>
        <div class="row">
            <div class="col-md-5">
                <aui:input name="address" label="Address 1"/>
                <aui:input name="address2" label="Address 2"/>
                <aui:input name="city" label="City"/>
                <aui:input name="state" label="State"/>
                <aui:input name="zip" label="Zip Code"/>
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
                	questions.add("what-is-your-mother-maiden-name");
                	questions.add("what-is-make-of-your-first-car");
                	questions.add("what-is-your-high-school-mascot");
                	questions.add("who-is-your-favorite-actor");
                	for (String question : questions) {
                	%>
                        <aui:option label="<%= question %>" />
                	<%
                	}
                	%>
                </aui:select>
                <aui:input name="security_answer" label="Answer"/>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="row">
            <aui:input name="accepted_tou" type="checkbox" label="terms-of-use"/>
        </div>
    </div>
    <aui:button-row>
        <aui:button type="submit" value="Create"/>
    </aui:button-row>
</aui:form>