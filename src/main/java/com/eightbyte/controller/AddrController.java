package com.eightbyte.controller;

import com.eightbyte.util.AddrUtil;
import com.eightbyte.vo.ResultVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addr")
public class AddrController extends BaseController {


    @Value("${byte.current.place.url}")
    private String currentPlaceUrl;

    /**
     * 获取当前所在地
     *
     * @return
     */
    @GetMapping("/getCurrentPlace")
    public ResultVo getCurrentAddr() {
        return success(AddrUtil.getCurrentPlace(currentPlaceUrl));
    }
}
