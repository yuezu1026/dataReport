package com.vat.service;

import com.vat.bean.ParamConfigVO;

public interface ConfigService {
     void saveComputingConfig(String userId, String computingMethod);
     
     ParamConfigVO findConfig(String userId);
}
