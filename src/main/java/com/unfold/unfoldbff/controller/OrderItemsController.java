package com.unfold.unfoldbff.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.unfold.unfoldbff.utils.Constants.PROD_URL;

@RestController
@CrossOrigin(origins = PROD_URL)
@RequestMapping(value = "/rest/unfold")
public class OrderItemsController {


}

