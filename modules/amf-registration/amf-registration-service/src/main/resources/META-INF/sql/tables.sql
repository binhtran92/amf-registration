create table AMF_UserExtraInfo (
	uuid_ VARCHAR(75) null,
	extraInfoId LONG not null primary key,
	userId LONG,
	address VARCHAR(255) null,
	address2 VARCHAR(255) null,
	city VARCHAR(255) null,
	state_ INTEGER,
	zip VARCHAR(5) null
);