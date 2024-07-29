package org.autosiso;

import java.util.HashMap;
import java.util.Map;

public class UserInfo {

  Map<String,String> userInfo= new HashMap<>();

  public Map<String, String> getUserInfo() {
    // list of users and passwords
    userInfo.put("<email>","<password>");

    return userInfo;
  }
}
