package org.tappoz.controller;

import com.google.common.net.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.tappoz.bean.AvatarPlayer;
import org.tappoz.bean.UserName;
import org.tappoz.dao.DummyUserDao;
import org.tappoz.service.ExceptionHandlingService;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;


@RequestMapping("/dummyUser")
@Controller
public class DummyUserController {

    private final static Logger log = LoggerFactory.getLogger(DummyUserController.class);

    @Autowired
    private ExceptionHandlingService exceptionHandlingService;
    @Autowired
    private DummyUserDao dummyUserDao;

    /**
     *
     * http://localhost:8080/SpringMvcSample/dummyUser/getRandomUser
     *
     * @return
     */
    @RequestMapping (
            value = "/getRandomUser",
            method= RequestMethod.GET)
    public HttpEntity<AvatarPlayer> getRandomUser() {

        try {

            AvatarPlayer avatarPlayer = dummyUserDao.getRandomUser();
            log.debug("About to return the random user: " + avatarPlayer.toString());
            return new ResponseEntity<>(avatarPlayer, HttpStatus.OK);

        } catch (Exception e) {
            return exceptionHandlingService.treatRestCalls(e);
        }
    }

    /**
     * The following cURL execution should return false:
     *  curl -X POST -d '{ "userName" : "testUserName" }' --header "Content-Type:application/json" --header "checkOnlyUserName:true" http://localhost:8080/SpringMvcSample/dummyUser/inspect
     *
     * The following cURL execution should return true:
     *  curl -X POST -d '{ "userName" : "mario" }' --header "Content-Type:application/json" --header "checkOnlyUserName:true" http://localhost:8080/SpringMvcSample/dummyUser/inspect
     *
     * @param checkOnlyUserName
     * @param inputUserNameBean
     * @return
     */
    @RequestMapping(
            value="/inspect",
            method= RequestMethod.POST,
            consumes= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> inspect(
            @RequestHeader(value = "checkOnlyUserName", required = true) Boolean checkOnlyUserName,
            @RequestBody UserName inputUserNameBean) {

        try {

            if (checkOnlyUserName) {
                Boolean booleanResponse = dummyUserDao.inspect(inputUserNameBean);
                log.debug("About to return the following response: " + StringUtils.quote(booleanResponse.toString()));
                return new ResponseEntity<>(booleanResponse.toString(), HttpStatus.OK);
            } else {
                log.info("We currently can check the existence only using the userName field of the input JSON object");
                return new ResponseEntity<>("Functionality not yet implemented", HttpStatus.NOT_ACCEPTABLE);
            }

        } catch (Exception e) {
            return exceptionHandlingService.treatRestCalls(e);
        }
    }
}
