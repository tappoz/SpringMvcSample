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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tappoz.bean.AvatarPlayer;
import org.tappoz.dao.DummyUserDao;
import org.tappoz.service.ExceptionHandlingService;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;


@RequestMapping("/dummyuser")
@Controller
public class DummyUserController {

    private final static Logger log = LoggerFactory.getLogger(DummyUserController.class);

    @Autowired
    private ExceptionHandlingService exceptionHandlingService;
    @Autowired
    private DummyUserDao dummyUserDao;

    /**
     *
     * http://localhost:8080/SpringMvcSample/dummyuser/getRandomUser
     *
     * @return
     */
    @RequestMapping (
            value = "/getRandomUser",
            method= RequestMethod.GET,
            headers = HttpHeaders.ACCEPT + "=" + MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<AvatarPlayer> getRandomUser() {

        try {

            AvatarPlayer avatarPlayer = dummyUserDao.getRandomUser();
            log.debug("About to return the random user: " + avatarPlayer.toString());
            return new ResponseEntity<>(avatarPlayer, HttpStatus.OK);

        } catch (Exception e) {
            return exceptionHandlingService.treatRestCalls(e);
        }
    }
}
