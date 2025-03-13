package com.xiao.vbot.service.glm;

import com.xiao.vbot.sdk.glm.model.chat.Model;
import com.xiao.vbot.service.glm.impl.IGlm4ModelService;
import com.xiao.vbot.service.glm.impl.IGlm4VModelService;
import com.xiao.vbot.service.glm.impl.IGlmCogviewModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ModelServiceFactory {
    private final Map<String, IModelService> modelServiceMap = new HashMap<>();

    @Autowired
    public ModelServiceFactory(IGlm4ModelService IGlm4ModelService,
                               IGlmCogviewModelService IGlmCogviewModelService,
                               IGlm4VModelService IGlm4VModelService) {
        modelServiceMap.put(Model.GLM_4_FLASH.getName(), IGlm4ModelService);
        modelServiceMap.put(Model.COGVIEW_3_FLASH.getName(), IGlmCogviewModelService);
        modelServiceMap.put(Model.GLM_4V_FLASH.getName(), IGlm4VModelService);
    }

    public IModelService getModelService(String modelName) {
        return modelServiceMap.get(modelName);
    }
}