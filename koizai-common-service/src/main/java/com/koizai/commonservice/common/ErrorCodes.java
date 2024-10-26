package com.koizai.commonservice.common;

public interface ErrorCodes {
    //COMMON
    String UNKNOWN_EXCEPTION = "1000";
    String MANDATORY_RETIREMENT_GOAL = "1001";
    String MANDATORY_BIRTH_DATE = "1002";
    String MANDATORY_GOAL_PARAMETER = "1003";
    String MANDATORY_INFLATION_RATE = "1004";
    String MANDATORY_INVESTMENT_RATE = "1005";
    String MANDATORY_TYPE = "1006";
    String MANDATORY_VALUE = "1007";
    String MANDATORY_ANNUAL_RATE = "1008";
    String MANDATORY_FREQUENCY = "1009";
    String MANDATORY_START_DATE = "1010";
    String INVALID_PATH = "1011";
    String INVALID_FORMAT = "1012";

    String UNHANDLED_TYPE = "2000";
    String PROCESS_FAILED = "2001";
    String REMOVE_FAILED = "2002";
    String AUTH_2FA_FAILED = "2003";
    String ID_NOT_FOUND = "2004";
    String EMAIL_NOT_FOUND = "2005";
    String DATA_NOT_FOUND = "2006";
    String EMAIL_PASS_EXISTS = "2007";
    String USER_NEED_LOGOUT = "2008";



    String IB_FAIL_RESPONSE = "3000";
    String IB_EMPTY_RESPONSE = "3001";
    String IB_FA_MUST_LOGGED_IN = "3002";
    String IB_UNHANDLED_REASON = "3003";
    String IB_FA_LOGIN_FAILED = "3004";
    String IB_DUPLICATE_ACCOUNT = "3005";
    String IB_UNRECOGNIZED_ACCOUNT = "3006";
    String IB_TOKEN_EXPIRED= "3007";

    String API_FAIL_RESPONSE = "4000";
    String API_EMPTY_RESPONSE = "4001";

}
