package com.fitness.aiservice.service;

import com.fitness.aiservice.dto.Activity;

public interface IActivityMessageListener {

    void processActivityMessage(Activity activity);
}
