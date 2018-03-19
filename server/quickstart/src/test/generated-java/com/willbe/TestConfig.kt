package com.willbe

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Profile
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@Profile("test")
@EnableWebMvc
@ComponentScan(basePackages = arrayOf("com.willbe.giftapp"))
//        excludeFilters = arrayOf(ComponentScan.Filter(type = ASSIGNABLE_TYPE, value = *arrayOf(PipeCaller::class)))
//@Configuration

class TestConfig