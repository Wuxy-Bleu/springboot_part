package icu.bleuweb.service.impl;

import icu.bleuweb.service.OssService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String callback(HttpServletRequest httpServletRequest) {

        return "hit";
    }
}
