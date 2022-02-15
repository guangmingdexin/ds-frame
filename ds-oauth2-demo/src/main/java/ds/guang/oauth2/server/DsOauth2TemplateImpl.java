package ds.guang.oauth2.server;

import com.guang.dstoken.ouath2.logic.DsOAuth2Template;
import com.guang.dstoken.ouath2.model.DsClientModel;
import org.springframework.stereotype.Component;

/**
 * @author guangyong.deng
 * @date 2021-12-21 9:22
 */
@Component
public class DsOauth2TemplateImpl extends DsOAuth2Template {



    // 根据 id 获取 Client 信息
    @Override
    public DsClientModel getClientModel(String clientId) {
        // 此为模拟数据，真实环境需要从数据库查询
        if("1001".equals(clientId)) {
            return new DsClientModel()
                    .setClientId("10001")
                    .setClientSecret("aaaa-bbbb-cccc-dddd-eeee")
                    .setAllowUrl("*")
                    .setContractScope("userinfo");
        }
        return null;
    }

    // 根据ClientId 和 LoginId 获取openid
    @Override
    public String getOpenid(String clientId, Object loginId) {
        // 此为模拟数据，真实环境需要从数据库查询
        return "gr_SwoIN0MC1ewxHX_vfCW3BothWDZMMtx__";
    }

    // -------------- 其它需要重写的函数
}
