package com.xiao.vbot.service.coze;

import com.alibaba.fastjson.JSONObject;
import com.coze.openapi.api.WorkflowChatAPI;
import com.coze.openapi.client.workflows.run.RunWorkflowReq;
import com.coze.openapi.client.workflows.run.RunWorkflowResp;
import com.coze.openapi.service.service.CozeAPI;
import com.coze.openapi.service.service.workflow.WorkflowService;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: xiaopeng
 * @Description: Coze Api Service
 * @DateTime: 2025/3/20 上午10:23 星期四
 **/
@Service
public class CozeApiServiceImpl {

    @Resource
    private CozeAPI cozeAPI;

    @Resource
    private OkHttpClient client;

    private static final String VOICE_WORKFLOW_ID ="7481172534386524187";

    public String getVoice(String message)
    {
        WorkflowService workflows = cozeAPI.workflows();
        RunWorkflowReq runWorkflowReq = new RunWorkflowReq();
        runWorkflowReq.setWorkflowID(VOICE_WORKFLOW_ID);
        Map<String, Object> map = new HashMap<>();
        map.put("input", message);
        runWorkflowReq.setParameters(map);
        RunWorkflowResp runWorkflowResp = workflows.runs().create(runWorkflowReq);
        JSONObject jsonObject = JSONObject.parseObject(runWorkflowResp.getData());
        JSONObject output = jsonObject.getJSONObject("output");

        return null;
    }


}
