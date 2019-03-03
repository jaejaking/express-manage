package com.eightbyte.controller;

import com.eightbyte.vo.ResultVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController extends BaseController {

    @GetMapping("/info")
    public ResultVo weatherInfo(String cityName) {
        return null;
    }
}
