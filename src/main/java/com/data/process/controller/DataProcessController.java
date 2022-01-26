package com.data.process.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.process.service.LoadDataService;

@RestController
@RequestMapping("/load")
public class DataProcessController {

	@Autowired
	private LoadDataService loadData;

	@GetMapping("/kafka")
	public String sendDataToKafka() {

		try {
			loadData.sendDataToKafka();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "json message sent succuessfully";
	}

}