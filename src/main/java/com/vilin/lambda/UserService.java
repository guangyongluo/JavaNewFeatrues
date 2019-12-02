package com.vilin.lambda;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    public List<User> filterUser(List<User> userList, MyPredicate<User> myPredicate){
        List<User> resultList = new ArrayList<User>();

        for(User user : userList){
            if(myPredicate.test(user)){
                resultList.add(user);
            }
        }
        return resultList;
    }
}
